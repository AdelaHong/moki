package com.im.moki.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.im.moki.web.token.JwtTokenUtil;
import com.im.moki.web.token.PassToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class TestController {

    @RequestMapping("test")
    @ResponseBody
    public JSONObject tokenTest(){
        JSONObject jsonObject = null;
        try {
            String token = JwtTokenUtil.generateToken("1","1",false);
            String token1 = JwtTokenUtil.generateToken("1","1",true);
            jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("token1",token1);
        } catch (Exception e) {
            jsonObject.put("token","无token，请重新登录");
        }
        return jsonObject;
    }
}
