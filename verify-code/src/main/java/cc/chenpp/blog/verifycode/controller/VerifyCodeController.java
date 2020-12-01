package cc.chenpp.blog.verifycode.controller;

import cc.chenpp.blog.verifycode.enums.VerifyTypeEnum;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Log4j2
@RestController
public class VerifyCodeController {

    @Resource
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/getVerifyCode")
    public void getCode(@RequestParam(defaultValue = "IMG") VerifyTypeEnum verifyType, HttpServletResponse response, HttpSession session) throws IOException {
        String codeText = defaultKaptcha.createText();
        if (VerifyTypeEnum.SMS.equals(verifyType)) {
            log.info("session: {} -> sms code: {}", session.getId(), codeText);
        } else {
            BufferedImage bi = defaultKaptcha.createImage(codeText);
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
            log.info("session: {} -> image code: {}", session.getId(), codeText);
        }
        session.setAttribute("imgCode", codeText);
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String userName, @RequestParam String passWord) {
        return "登录成功";
    }

}
