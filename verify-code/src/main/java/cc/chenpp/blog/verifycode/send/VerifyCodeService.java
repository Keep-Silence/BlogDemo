package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.exception.CodeVerifyFailException;
import cc.chenpp.blog.verifycode.model.VerifyCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings("unchecked")
public class VerifyCodeService<T extends VerifyCode> {

    private final static String VERIFY_CODE_NAME = "verifyCode";
    private final static String SESSION_VERIFY_CODE = "sessionVerifyCode";
    @Resource
    protected HttpSession session;
    @Resource
    protected Map<String, VerifyCodeSend> verifyCodeSendMap;
    @Resource
    protected HttpServletRequest request;

    public void checkCode() throws CodeVerifyFailException {
        T verifyCode = this.getVerifyCode();
        String reqImgCode = this.getReqVerifyCode();
        if (verifyCode == null || reqImgCode == null || verifyCode.getCode() == null) {
            throw new CodeVerifyFailException("验证码为空");
        } else {
            if (!verifyCode.getCode().equals(reqImgCode)) {
                throw new CodeVerifyFailException("验证码错误");
            }
            this.removeVerifyCode();
        }
    }

    public void send(T verifyCode) throws Exception {
        this.saveVerifyCode(verifyCode);
        String beanName = String.format("%sVerifyCodeSend", verifyCode.getVerifyType().name().toLowerCase());
        VerifyCodeSend verifyCodeSend = verifyCodeSendMap.get(beanName);
        verifyCodeSend.send(verifyCode);
    }

    protected T getVerifyCode() {
        return (T) session.getAttribute(SESSION_VERIFY_CODE);
    }

    protected void saveVerifyCode(T verifyCode) {
        session.setAttribute(SESSION_VERIFY_CODE, verifyCode);
    }

    protected void removeVerifyCode() {
        session.removeAttribute(SESSION_VERIFY_CODE);
    }

    protected String getReqVerifyCode() {
        return request.getParameter(VERIFY_CODE_NAME);
    }
}
