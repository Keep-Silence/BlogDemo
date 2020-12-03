package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.model.VerifyCode;

public class EmailVerifyCodeSend implements VerifyCodeSend {

    @Override
    public <T extends VerifyCode> void send(T verifyCode) {
        LOG.info(verifyCode);
    }

}
