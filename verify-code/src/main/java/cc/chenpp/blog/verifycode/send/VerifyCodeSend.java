package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public interface VerifyCodeSend {

    Log LOG = LogFactory.getLog(VerifyCodeSend.class);

    <T extends VerifyCode> void send(T verifyCode);

}
