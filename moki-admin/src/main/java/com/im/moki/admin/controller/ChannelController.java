package com.im.moki.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.rpc.ChannelProtocol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("channel")
public class ChannelController {

    @Reference
    private ChannelProtocol channelProtocol;


    @RequestMapping("queryData")
    @ResponseBody
    public Object selectChannelData(@RequestBody Map<String,Object> params){
        PageInfo page = channelProtocol.selectChannelData(params);
        return page;
    }


    /**
     * 添加频道
     * @return
     */
    @RequestMapping("addData")
    @ResponseBody
    public Result addData(@RequestBody Map<String,Object> params){
        Result result = channelProtocol.addChannelData(params);
        return result;
    }

}
