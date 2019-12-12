package com.im.moki.tomoko.service.intf;

import com.baomidou.mybatisplus.service.IService;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.entity.ChannelEntity;
import com.im.moki.tomoko.request.ChannelRequest;

import java.util.Map;

/**
 * @version V1.0
 * @Description: ( Service)
 * @Author huangsihong
 * @Date 2019-12-9
 */
public interface ChannelService extends IService<ChannelEntity> {

    /**
     * 频道列表查询
     * @param params
     * @return
     */
    PageInfo selectChannelData(Map<String, Object> params);


    /**
     * 增加频道
     * @param params
     * @return
     */
    Result addChannelData(Map<String, Object> params);


    /**
     * 更新频道信息
     * @param request
     * @return
     */
    Result updateChannelData(ChannelRequest request);


    /**
     * 删除频道
     * @param params
     * @return
     */
    Result deleteChannelData(Map<String, Object> params);
}
