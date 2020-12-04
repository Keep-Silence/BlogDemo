package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.model.VerifyCode;

public interface VerifyCodeSend {

    <T extends VerifyCode> void send(T verifyCode) throws Exception;

}
