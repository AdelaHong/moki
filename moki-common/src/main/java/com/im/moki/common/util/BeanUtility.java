package com.im.moki.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class BeanUtility extends org.springframework.beans.BeanUtils {
    /**
     * 对象拷贝 beanCopy:().
     * @param fromBean
     * @param type
     */
    public static <T> T beanCopy(Object fromBean, Class<T> type) {
        T toBean = null;
        try {
            toBean = type.newInstance();
            if (fromBean == null || toBean == null) {
                return null;
            }
            BeanUtils.copyProperties(fromBean, toBean);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage());
        }
        return toBean;
    }

    /**
     * 对象拷贝 beanCopy:().
     *
     * @param fromBean
     * @param type
     */
    public static <T> T beanCopyIgnoreNull(Object fromBean, Class<T> type) {
        T toBean = null;
        try {
            // JavaBean
            toBean = type.newInstance();
            if (fromBean == null || toBean == null) {
                return null;
            }
            BeanUtils.copyProperties(fromBean, toBean, getNullPropertyNames(fromBean));
        } catch (IllegalAccessException | InstantiationException e) {
            log.error(e.getMessage());
        }
        return toBean;
    }

    public static <T> List<T> convertOtherBeanList(List<T> list, Class<T> tartgetClass) {
        if ((list == null || list.isEmpty())) {
            return null;
        }
        List<T> resultList = new ArrayList<>();
        for (Object object : list) {
            resultList.add(BeanUtility.beanCopy(object, tartgetClass));
        }
        return resultList;
    }

    /**
     * 实例化对象
     *
     * @param clazz 类
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiate(clazz);
    }

    /**
     * 实例化对象
     *
     * @param clazzStr 类名
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Bean的属性
     *
     * @param bean         bean
     * @param propertyName 属性名
     * @return 属性值
     */
    public static Object getProperty(Object bean, String propertyName) {
        PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
        if (pd == null) {
            throw new RuntimeException("Could not read property '" + propertyName + "' from bean PropertyDescriptor is null");
        }
        Method readMethod = pd.getReadMethod();
        if (readMethod == null) {
            throw new RuntimeException("Could not read property '" + propertyName + "' from bean readMethod is null");
        }
        if (!readMethod.isAccessible()) {
            readMethod.setAccessible(true);
        }
        try {
            return readMethod.invoke(bean);
        } catch (Throwable ex) {
            throw new RuntimeException("Could not read property '" + propertyName + "' from bean", ex);
        }
    }

    /**
     * 设置Bean属性
     *
     * @param bean         bean
     * @param propertyName 属性名
     * @param value        属性值
     */
    public static void setProperty(Object bean, String propertyName, Object value) {
        PropertyDescriptor pd = getPropertyDescriptor(bean.getClass(), propertyName);
        if (pd == null) {
            throw new RuntimeException("Could not set property '" + propertyName + "' to bean PropertyDescriptor is null");
        }
        Method writeMethod = pd.getWriteMethod();
        if (writeMethod == null) {
            throw new RuntimeException("Could not set property '" + propertyName + "' to bean writeMethod is null");
        }
        if (!writeMethod.isAccessible()) {
            writeMethod.setAccessible(true);
        }
        try {
            writeMethod.invoke(bean, value);
        } catch (Throwable ex) {
            throw new RuntimeException("Could not set property '" + propertyName + "' to bean", ex);
        }
    }


    /**
     * copy 对象属性到另一个对象，默认不使用Convert
     *
     * @param src
     * @param clazz 类名
     * @return T
     */
    public static <T> T copy(Object src, Class<T> clazz) {
        BeanCopier copier = BeanCopier.create(src.getClass(), clazz, false);

        T to = newInstance(clazz);
        copier.copy(src, to, null);
        return to;
    }

    /**
     * 拷贝对象
     *
     * @param src  源对象
     * @param dist 需要赋值的对象
     */
    public static void copy(Object src, Object dist) {
        BeanCopier copier = BeanCopier
                .create(src.getClass(), dist.getClass(), false);

        copier.copy(src, dist, null);
    }

    /**
     * 将对象装成map形式
     *
     * @param src
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map toMap(Object src) {
        return BeanMap.create(src);
    }

    /**
     * 将map 转为 bean
     */
    public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
        T bean = newInstance(valueType);
        PropertyDescriptor[] beanPds = getPropertyDescriptors(valueType);
        for (PropertyDescriptor propDescriptor : beanPds) {
            String propName = propDescriptor.getName();
            // 过滤class属性
            if (propName.equals("class")) {
                continue;
            }
            if (beanMap.containsKey(propName)) {
                Method writeMethod = propDescriptor.getWriteMethod();
                if (null == writeMethod) {
                    continue;
                }
                Object value = beanMap.get(propName);
                if (!writeMethod.isAccessible()) {
                    writeMethod.setAccessible(true);
                }
                try {
                    writeMethod.invoke(bean, value);
                } catch (Throwable e) {
                    throw new RuntimeException("Could not set property '" + propName + "' to bean", e);
                }
            }
        }
        return bean;
    }

    /**
     * @param source 要拷贝的对象
     * @return
     * @Description <p>获取到对象中属性为null的属性名  </P>
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
