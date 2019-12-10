package com.im.moki.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.im.moki.tomoko.rpc.TestProtocol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Reference
    private TestProtocol testProtocol;


    @RequestMapping("test")
    @ResponseBody
    public String test(){
        String a = testProtocol.aa();
        return "1";
    }

}
