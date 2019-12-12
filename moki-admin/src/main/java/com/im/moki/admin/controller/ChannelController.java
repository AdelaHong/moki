package com.im.moki.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.RT;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ChannelRequest;
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


    /**
     * 列表查询
     * @param params
     * @return
     */
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
    public RT addData(@RequestBody Map<String,Object> params){
        Result result = channelProtocol.addChannelData(params);
        if (result.isSuccess()) {
            return RT.ok("添加频道成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }


    /**
     * 更新频道信息
     * @return
     */
    @RequestMapping("updateData")
    @ResponseBody
    public RT updateData(ChannelRequest request){
        Result result = channelProtocol.updateChannelData(request);
        if (result.isSuccess()) {
            return RT.ok("更新频道信息成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }


    /**
     * 删除频道
     * @return
     */
    @RequestMapping("deleteData")
    @ResponseBody
    public RT deleteData(@RequestBody Map<String,Object> params){
        Result result = channelProtocol.deleteChannelData(params);
        if (result.isSuccess()) {
            return RT.ok("删除频道成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }
}
