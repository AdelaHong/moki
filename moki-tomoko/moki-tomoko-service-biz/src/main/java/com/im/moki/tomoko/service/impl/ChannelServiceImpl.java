package com.im.moki.tomoko.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.PageInfoHelper;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.entity.ChannelEntity;
import com.im.moki.tomoko.mapper.ChannelMapper;
import com.im.moki.tomoko.service.intf.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
