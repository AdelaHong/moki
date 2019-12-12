package com.im.moki.tomoko.rpc;

import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ChannelRequest;

import java.util.Map;

public interface ChannelProtocol {

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
