package com.im.moki.tomoko.protocol;

import com.im.moki.tomoko.rpc.TestProtocol;
import org.springframework.stereotype.Service;

@Service("testProtocol")
public class TestProtocolImpl implements TestProtocol {

    @Override
    public String aa() {
        return "123";
    }
}
