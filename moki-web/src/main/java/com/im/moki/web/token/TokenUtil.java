package com.im.moki.web.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {

    public static String getToken(String id,String password) {
        String token= JWT.create().withAudience(id)
                .sign(Algorithm.HMAC256(password));
        return token;
    }
}
