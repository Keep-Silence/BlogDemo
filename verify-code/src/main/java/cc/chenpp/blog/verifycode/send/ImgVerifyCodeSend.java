package cc.chenpp.blog.verifycode.send;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

@Log4j2
public class ImgVerifyCodeSend implements VerifyCodeSend {

    @Resource
    HttpServletResponse response;

    @Resource
    DefaultKaptcha defaultKaptcha;

    @Override
    public <T extends VerifyCode> void send(T verifyCode) throws Exception {
        BufferedImage bi = defaultKaptcha.createImage(verifyCode.getCode());
        //禁止缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //设置响应格式为png图片
        response.setContentType("image/png");
        // 写入图片验证码
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(bi, "png", out);
            out.flush();
        }
        log.info(verifyCode);
    }

}
