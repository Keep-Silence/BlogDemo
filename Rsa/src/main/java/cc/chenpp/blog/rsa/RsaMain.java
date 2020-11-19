package cc.chenpp.blog.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collections;
import java.util.HashMap;

public class RsaMain {

    public static void main(String[] args) throws Exception {
        String pkcs1PrivateKeyStr = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEpgIBAAKCAQEAr2LVxzkfwf0DsVo17F8mqUpdzW6NyfMdy/l6Ot/rHPeWKng/\n" +
                "M379eKBD3iIxgjAGxp+hL6adppzskQyUIHNH8K/dMit4JbVamziy+A9GJFuB4co5\n" +
                "C/KH/UMhdTLlPKec6Ras3DZWRDNVYZFdN4gtkYPHDpdsWZceEq4kfSXvfcpBitZM\n" +
                "bvj80M05sVxh5i+LeUO6x5RANBXDz+wkRL3hJdR6cM7vK50CAbIyZbXKtOVwyIW7\n" +
                "pqj9g4Z9tWkj83vHvJ6iXyxWsIjksgj56E+bRaNYMPTV6e5fRwQ7sHcY8PKXF6eL\n" +
                "eLV124E6QpkMJPK/FW+HAiKxU+ZJoXQZ3W+a8wIDAQABAoIBAQCcaNoZt3n4+Hlo\n" +
                "mvjn+dtysl2BLYzJg0UZeT1mb/cveCW1blN+y0ulXdforDnWzpncXNPUuRRzWBMp\n" +
                "MDyRy/1mSpBLg7S8fEV3lFMXIhf9qJXmFQBXBU39sPaPnHoPY7Nts8kpAlRgsh78\n" +
                "yjSFp65rzUeiXaxFIZ2yLxdffItec52MWB+uLEScgVuXPzgPlwwxoCEygxY7Tz1c\n" +
                "pqb9e3YndhxspCy6+bCnroTXAiMDr1O/HUxlOyeVOnJLYNfdd4tJiOoEMUD2O6P2\n" +
                "4woBbZqMx5IE1ecxYylrdmpwW0H7jTGabcK2M8L9C1ALoL96AFzYZkl5LmT37Hl0\n" +
                "yfG7exLRAoGBANesaBSS1t7kdHNoxAWtyzA5O1F+OCahDjVyJfP2UW1AjW7+GZ8q\n" +
                "jSQ+/75+hbd0CKA6Df1kyS46PROR//P0x6/xYxlEJEA/rD9ZpeTC/Qe44x1jNmFD\n" +
                "dwIrcKNEL0kY6L4XGbJq6K/z4pLvLoRu1/tCFYzukvlPIbzai/80VzUFAoGBANAu\n" +
                "AEb8zOTdUHpq2lPnPcBHYZqxAC8zm1IUNKyYhj6Rbqiw14Yg507blqd+fXaB6hs+\n" +
                "hrC2McjI1eMJ91oIrNqL4vIFnbUMBTRw8kF6rxNeWPOx9zRKwQHigSEYuhHrMSHY\n" +
                "VrS+MZSbgnVct0zE77Irn49ToCBFzB8X7H/eshGXAoGBALekuksqbYQFi5Lp1l/v\n" +
                "iGBbm0Lc8vsRkIO52s9NIjChEs072J1LIjuG2UVffoSjbOTiCh+Fvjh6pp8PZp5Y\n" +
                "A+Cw7e0JtMRwcz7OGxAbaFE3AUx4ukk/pQpX6uVandjkOOoDyYG+vrt0M61htH5X\n" +
                "09jCgI8sZkGbO+MPRL3d15PxAoGBAIPMC8rlc4aVgsTt7yldZd/ckTORSa+YeYX+\n" +
                "WtbGfX8RLcDIrLozDJ4FMZz63M1xNFhpPnaT8nLOi3MAtUOf6Gc4VsaovY6IfKcV\n" +
                "tpmz5TFa8u/dxMaraQQbdWsRr4N2phVqVIJ7QwJ1baOFvlpw99uR+KOR6vdjmPY6\n" +
                "fDT8NTojAoGBAJKwk+AXaXr2yZJACBV5YZeeI67j1ry8tm5K5Pnp4kYMiN4za+8a\n" +
                "oiRl3KBXqJA5sRCnPGw/RDcsvJTjD/zFYw/9g81xkpINyZ0xy/rl8fpxrBQpqL+T\n" +
                "t0FZ5poFJ0Pb9Wt+XwDrV08OME/qkW39BwNrz1m0axuWNGwdJVnkQN6B\n" +
                "-----END RSA PRIVATE KEY-----\n";
        String pkcs1publicKeyStr = "-----BEGIN RSA PUBLIC KEY-----\n" +
                "MIIBCgKCAQEAr2LVxzkfwf0DsVo17F8mqUpdzW6NyfMdy/l6Ot/rHPeWKng/M379\n" +
                "eKBD3iIxgjAGxp+hL6adppzskQyUIHNH8K/dMit4JbVamziy+A9GJFuB4co5C/KH\n" +
                "/UMhdTLlPKec6Ras3DZWRDNVYZFdN4gtkYPHDpdsWZceEq4kfSXvfcpBitZMbvj8\n" +
                "0M05sVxh5i+LeUO6x5RANBXDz+wkRL3hJdR6cM7vK50CAbIyZbXKtOVwyIW7pqj9\n" +
                "g4Z9tWkj83vHvJ6iXyxWsIjksgj56E+bRaNYMPTV6e5fRwQ7sHcY8PKXF6eLeLV1\n" +
                "24E6QpkMJPK/FW+HAiKxU+ZJoXQZ3W+a8wIDAQAB\n" +
                "-----END RSA PUBLIC KEY-----\n";

        String privateKeyStr = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC88F9jZ5xCxHBv\n" +
                "fa46tCUopWVqC3K8wW/NAzp82fnzNLBSHHuCmb6D7gMRBLAq0eTX1t9gGwy2uBUh\n" +
                "sMVT/C/GkDeYH70QDXB7P+4B1OpIrSEpFcfXpvuXUpEnS3HSFH8gtFxKKCQTEgr3\n" +
                "SBhMmFFR7eIe+DptO0CUcEhMLeuweuZsGyUzNpnFbEWR3arIJG7gDmEbTL5Rny/c\n" +
                "GfFfauaDq6Fjvm61BZhmvS7ZNnCtFZJ79bdHl/Io5tzf10c6rK1IIFIAVS4wHSoU\n" +
                "hJqJM1Aq5o3W6kjTCWAlIEFSJeP6HdAuc0tYZ7WsCPhxH8leRK7JO7Us4DUcI91l\n" +
                "eUHJWFwnAgMBAAECggEAIFxnaiY/7Guq+l2jbuiWCL0zfpNRZqUXGh04clP/bLmU\n" +
                "javVN8xPhldI7SxJZDwpHxLtlY9sgQtMi0W0tqrWIANpkkO1ZT94CZQsluG58xK8\n" +
                "QEY7wWMXapa4x+szJpFeKOLbf9QaQa3wdORDTTM1knvVJArOLxFcAVCuAWZIHSTU\n" +
                "Ul/SHnkkBeUsDUhEPy+vMJ29WX3yzMv60qfhUqSZTRrv0/d9jHd6mS0RXk2l+KhI\n" +
                "ZxiqyEIa18iyoVLW4MalEqawusHLGzBh39x4H+AnQrFd06PTS0phQyB+OYvppzLl\n" +
                "Oggwnf/O2tS4yJz2HsNJfjIc3nCsNpaWy6FGyW0BwQKBgQDmElrVnbsohbslyYhW\n" +
                "ezbblAQhU8dXYWh9gqK5Q2xr/a6HNCXRLA6eAb//zoDNS44NayHVSgZ6OE2xJsPY\n" +
                "YpURgmJpVETjhvGbz1va+D/LYCm8fKCHcm0sRJt6oMilx66dDD9Gt7uRNASeS50C\n" +
                "HV3p8ydpbYcxu8oNGXnbkUpJxwKBgQDSO1MOyOGr4f/aPGjoZNSd5SPQ0l69Jtbx\n" +
                "JLLnUVbx/TywBmxzY9u++CR3a5zjHhAPMcf1N8W3HZsCNxHU+q1nPi/c6q15sAvm\n" +
                "NJgQ3NI9xt53yt4ciAEEM0+Vf7ZASupvV/5c7dbLH0mfm0+VnFKFxBgaYdRs9ppS\n" +
                "4WB51tBaoQKBgBpqvx1KCCRl/pAfLNVpPmpF+5dsDDZ/S8Uli9Oj5z4bGcASf8kd\n" +
                "uuvvnYEcz/NCmqP1yiO4ZTgBYQJXRiOBZkZlZzFa6LILW5CdZZml6YVLnF81HCWR\n" +
                "QPHVStjwvzkxzV3g+x7+l73bcDBQ1E1a296x+bOETQKr4k5Qv4ozi4kfAoGBAKTT\n" +
                "/HfwCfeQdt/tUzc5rvWo7R7RgZP9+rIQWvA/9tI+ATHlp2wFFAozVawHxHbRYDf5\n" +
                "iNP7CVpbrOfcs29vpzr2J+KmYKKy43F3NO6ro27qGAyGEwT9oQZhEHmfKw5Xbl0j\n" +
                "+Xt0wl55RmkMBSbA7HaSW2972mggJLjsmY6NXMhhAoGABFZFs7cHng7r67zp5i56\n" +
                "oZBFG6WL3FOeTZs1NpunryqU/+mXvEWFwlUEQsMOWpr7Qw4orbSuPlEQ+ngPA5wS\n" +
                "mZBAELruAuNwG1WMnwCFE1HJ2mQYq9AcSYEMiQULhXxAHDxbzHZbnLnSj9C6C8nz\n" +
                "jTLzExCmzGkzv4yWNdwtYLE=\n" +
                "-----END PRIVATE KEY-----\n";
        String publicKeyStr = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvPBfY2ecQsRwb32uOrQl\n" +
                "KKVlagtyvMFvzQM6fNn58zSwUhx7gpm+g+4DEQSwKtHk19bfYBsMtrgVIbDFU/wv\n" +
                "xpA3mB+9EA1wez/uAdTqSK0hKRXH16b7l1KRJ0tx0hR/ILRcSigkExIK90gYTJhR\n" +
                "Ue3iHvg6bTtAlHBITC3rsHrmbBslMzaZxWxFkd2qyCRu4A5hG0y+UZ8v3BnxX2rm\n" +
                "g6uhY75utQWYZr0u2TZwrRWSe/W3R5fyKObc39dHOqytSCBSAFUuMB0qFISaiTNQ\n" +
                "KuaN1upI0wlgJSBBUiXj+h3QLnNLWGe1rAj4cR/JXkSuyTu1LOA1HCPdZXlByVhc\n" +
                "JwIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";

        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyStr);
        PrivateKey privateKey = RsaUtils.getPkcs8PrivateKey(privateKeyStr);

        String strJoin = String.join("", Collections.nCopies(50, "测试"));
        System.out.println("PKCS8格式加解密：");
        System.out.printf("待加密字符串：%s\n", strJoin);
        String encryption = RsaUtils.encryption(strJoin, publicKey);
        System.out.printf("加密后Base64：%s\n", encryption);
        System.out.printf("解密后字符串：%s\n", RsaUtils.decryption(encryption, privateKey));

        System.out.println();
        publicKey = RsaUtils.getPublicKey(pkcs1publicKeyStr);
        privateKey = RsaUtils.getPkcs1PrivateKey(pkcs1PrivateKeyStr);

        System.out.println("PKCS1格式加解密：");
        System.out.printf("待加密字符串：%s\n", strJoin);
        encryption = RsaUtils.encryption(strJoin, publicKey);
        System.out.printf("加密后Base64：%s\n", encryption);
        System.out.printf("解密后字符串：%s\n", RsaUtils.decryption(encryption, privateKey));

        System.out.println();
        System.out.println("私钥加签：");
        HashMap<String, String> data = new HashMap<String, String>() {{
            put("name", "Test");
            put("age", "22");
        }};
        System.out.printf("需要加签的数据：%s\n", data);
        String sign = RsaUtils.dataSign(data, privateKey, "SHA1WithRSA");
        System.out.printf("加签结果：%s\n", sign);
        System.out.println("公钥验签：");
        System.out.printf("验签结果：%s\n", RsaUtils.checkSign(data, sign, publicKey, "SHA1WithRSA"));

        System.out.println();
        System.out.println("PKCS1格式，私钥加密、公钥解密：");
        System.out.printf("待加密字符串：%s\n", strJoin);
        encryption = RsaUtils.encryptionByPrivateKey(strJoin, privateKey);
        System.out.printf("加密后Base64：%s\n", encryption);
        System.out.printf("解密后字符串：%s\n", RsaUtils.decryptionByPublicKey(encryption, publicKey));
    }

}