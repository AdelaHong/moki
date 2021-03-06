package com.im.moki.tomoko.protocol;

import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ChannelRequest;
import com.im.moki.tomoko.rpc.ChannelProtocol;
import com.im.moki.tomoko.service.intf.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("channelProtocol")
public class ChannelProtocolImpl implements ChannelProtocol {

    @Autowired
    private ChannelService channelService;

    /**
     * 频道列表查询
     * @param params
     * @return
     */
    @Override
    public PageInfo selectChannelData(Map<String, Object> params) {
        return channelService.selectChannelData(params);
    }


    /**
     * 增加频道
     * @param params
     * @return
     */
    @Override
    public Result addChannelData(Map<String, Object> params) {
        return channelService.addChannelData(params);
    }


    /**
     * 更新频道信息
     * @param request
     * @return
     */
    @Override
    public Result updateChannelData(ChannelRequest request) {
        return channelService.updateChannelData(request);
    }


    /**
     * 删除频道
     * @param params
     * @return
     */
    @Override
    public Result deleteChannelData(Map<String, Object> params) {
        return channelService.deleteChannelData(params);
    }
}
