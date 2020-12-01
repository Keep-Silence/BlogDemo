package cc.chenpp.blog.verifycode.config;

import cc.chenpp.blog.verifycode.exception.CodeVerifyFailException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
public class VerifyCodeInterceptor implements HandlerInterceptor {

    public final static String IMG_CODE_NAME = "imgCode";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String imgCode = (String) session.getAttribute(IMG_CODE_NAME);
        String reqImgCode = request.getParameter(IMG_CODE_NAME);
        if (imgCode == null || reqImgCode == null) {
            throw new CodeVerifyFailException();
        } else {
            if (!imgCode.equals(reqImgCode)) {
                throw new CodeVerifyFailException();
            }
            session.removeAttribute(IMG_CODE_NAME);
        }
        return true;
    }


}
