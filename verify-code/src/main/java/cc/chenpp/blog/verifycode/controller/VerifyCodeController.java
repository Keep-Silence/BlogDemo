package cc.chenpp.blog.verifycode.controller;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import cc.chenpp.blog.verifycode.model.VerifyTypeEnum;
import cc.chenpp.blog.verifycode.send.VerifyCodeService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Log4j2
@RestController
@Component
public class VerifyCodeController {

    @Resource
    VerifyCodeService<VerifyCode> verifyCodeService;

    @Resource
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/getVerifyCode")
    public void getCode(@RequestParam(defaultValue = "IMG") VerifyTypeEnum verifyType) throws Exception {
        String codeText = defaultKaptcha.createText();
        VerifyCode verifyCode = new VerifyCode(verifyType, codeText);
        verifyCodeService.send(verifyCode);
    }

    @PostMapping("/testVerifyCode")
    @ResponseBody
    public String login() {
        return "验证成功";
    }

}
