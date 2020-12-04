package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SmsVerifyCodeSend implements VerifyCodeSend {

    @Override
    public <T extends VerifyCode> void send(T verifyCode) {
        log.info(verifyCode);
    }

}
