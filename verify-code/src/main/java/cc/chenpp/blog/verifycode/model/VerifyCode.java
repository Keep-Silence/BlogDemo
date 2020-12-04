package cc.chenpp.blog.verifycode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCode {

    private VerifyTypeEnum verifyType;
    private String code;

}
