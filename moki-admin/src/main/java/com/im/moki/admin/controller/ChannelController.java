package com.im.moki.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
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

        return null;
    }
}
