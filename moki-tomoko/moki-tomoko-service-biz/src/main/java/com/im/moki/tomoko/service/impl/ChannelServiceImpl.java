package com.im.moki.tomoko.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.im.moki.common.util.BeanUtility;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.PageInfoHelper;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.constants.TomokoCode;
import com.im.moki.tomoko.entity.ChannelEntity;
import com.im.moki.tomoko.mapper.ChannelMapper;
import com.im.moki.tomoko.request.ChannelRequest;
import com.im.moki.tomoko.response.TomokoResult;
import com.im.moki.tomoko.service.intf.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @Description: ( Service Impl)
 * @Author huangsihong
 * @Date 2019-12-9
 */
@Service
@Slf4j
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, ChannelEntity> implements ChannelService {

    /**
     * 频道列表查询
     * @param params
     * @return
     */
    @Override
    public PageInfo selectChannelData(Map<String, Object> params) {
        Page<ChannelEntity> page = PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        List<ChannelEntity> list = baseMapper.selectChannelData(params);
        return PageInfoHelper.pageToPageInfo(page, list);
    }


    /**
     * 增加频道
     * @param params
     * @return
     */
    @Override
    public Result addChannelData(Map<String, Object> params) {
        try {
            String channelName = (String)params.get("channelName");
            String logo = (String)params.get("logo");
            String playUrl = (String)params.get("playUrl");

            if (StringUtils.isEmpty(channelName)) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请输入频道名称！");
            }
            if (StringUtils.isEmpty(logo)) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请上传频道图标！");
            }
            if (StringUtils.isEmpty(playUrl)) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请输入播放地址！");
            }

            ChannelEntity entity = baseMapper.selectByChannelName(channelName);
            if (null != entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"已存在该频道，请核实！");
            }
            entity = new ChannelEntity();
            entity.setChannelName(channelName);
            entity.setLogo(logo);
            entity.setPlayUrl(playUrl);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());

            if (this.insert(entity)){
                return TomokoResult.createSuccRes("success");
            }
        } catch (Exception e) {
            log.error("增加频道异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }


    /**
     * 更新频道信息
     * @param request
     * @return
     */
    @Override
    public Result updateChannelData(ChannelRequest request) {
        try {
            if (null == request.getId()) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请选择需要修改的频道！");
            }
            ChannelEntity entity = baseMapper.selectById(request.getId());
            if (null == entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"不存在该频道信息，请核实！");
            }

            request.setUpdateTime(new Date());
            entity = BeanUtility.copy(request, ChannelEntity.class);
            if (this.updateById(entity)){
                return TomokoResult.createSuccRes("success");
            }

        } catch (Exception e) {
            log.error("更新频道信息异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }


    /**
     * 删除频道
     * @param params
     * @return
     */
    @Override
    public Result deleteChannelData(Map<String, Object> params) {
        try {
            Long id = (Long)params.get("id");
            if (null == id) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"请选择需要修改的频道！");
            }
            ChannelEntity entity = baseMapper.selectById(id);
            if (null == entity) {
                return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL.getCode(),"不存在该频道信息，请核实！");
            }

            entity.setDeleteFlag(1);
            entity.setUpdateTime(new Date());
            if (this.updateById(entity)){
                return TomokoResult.createSuccRes("success");
            }

        } catch (Exception e) {
            log.error("删除频道异常",e);
        }

        return TomokoResult.createFailedRes(TomokoCode.DEFAULT_FAIL);
    }
}
