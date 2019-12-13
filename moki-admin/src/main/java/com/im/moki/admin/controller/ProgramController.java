package com.im.moki.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.im.moki.common.util.PageInfo;
import com.im.moki.common.util.RT;
import com.im.moki.common.util.Result;
import com.im.moki.tomoko.request.ProgramRequest;
import com.im.moki.tomoko.rpc.ProgramProtocol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("program")
public class ProgramController {

    @Reference
    private ProgramProtocol programProtocol;


    /**
     * 列表查询
     * @param params
     * @return
     */
    @RequestMapping("queryData")
    @ResponseBody
    public Object selectChannelData(@RequestBody Map<String,Object> params){
        PageInfo page = programProtocol.selectProgramList(params);
        return page;
    }


    /**
     * 添加节目单
     * @return
     */
    @RequestMapping("addData")
    @ResponseBody
    public RT addData(ProgramRequest request){
        Result result = programProtocol.addProgramData(request);
        if (result.isSuccess()) {
            return RT.ok("添加节目单成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }


    /**
     * 更新节目单信息
     * @return
     */
    @RequestMapping("updateData")
    @ResponseBody
    public RT updateData(ProgramRequest request){
        Result result = programProtocol.updateProgramData(request);
        if (result.isSuccess()) {
            return RT.ok("更新节目单信息成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }


    /**
     * 删除节目单
     * @return
     */
    @RequestMapping("deleteData")
    @ResponseBody
    public RT deleteData(@RequestBody Map<String,Object> params){
        Result result = programProtocol.deleteProgramData(params);
        if (result.isSuccess()) {
            return RT.ok("删除节目单成功！");
        }
        return RT.error(result.getErrorCode(),result.getErrorMsg());
    }
}
