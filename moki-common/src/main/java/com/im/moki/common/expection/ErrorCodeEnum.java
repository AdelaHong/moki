package com.im.moki.common.expection;

import org.apache.commons.lang3.StringUtils;

public enum  ErrorCodeEnum  implements ErrorCode {

    /** 未指明的异常 */
    UNSPECIFIED("500", "网络异常，请稍后再试"),
    NO_SERVICE("404", "网络异常, 服务器熔断"),
    NO_TOKEN("404", "无TOKEN"),
    PAST_TOKEN("404", "TOKEN已过期"),
    DISABLE_TOKEN("404", "无效TOKEN"),
    ;

    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String msg;

    /**
     * @param code 错误码
     * @param description 描述
     */
    private ErrorCodeEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static ErrorCodeEnum getByCode(String code) {
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    /**
     * 枚举是否包含此code
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code){
        for (ErrorCodeEnum value : ErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
