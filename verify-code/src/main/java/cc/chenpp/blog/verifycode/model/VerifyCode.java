package cc.chenpp.blog.verifycode.model;

import cc.chenpp.blog.verifycode.enums.VerifyTypeEnum;

public class VerifyCode {

    private VerifyTypeEnum verifyType;
    private String code;

    public VerifyCode() {
    }

    public VerifyCode(VerifyTypeEnum verifyType, String code) {
        this.verifyType = verifyType;
        this.code = code;
    }

    public VerifyTypeEnum getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(VerifyTypeEnum verifyType) {
        this.verifyType = verifyType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "VerifyCode{" +
                "verifyType=" + verifyType +
                ", code='" + code + '\'' +
                '}';
    }
}
