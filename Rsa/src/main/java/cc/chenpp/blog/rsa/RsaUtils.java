package cc.chenpp.blog.rsa;

import com.alibaba.fastjson.JSON;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

/**
 * Rsa 加解密工具类
 */
public class RsaUtils {

    private static final String KEY_ALGORITHM = "RSA";
    private static final String SIGN_ALGORITHM = "SHA1WithRSA ";

    static {
        // 处理 NoSuchProviderException: no such provider: BC 异常
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 加载 PKCS8 私钥
     *
     * @param privateKeyStr
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPkcs8PrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        PEMParser pemParser = new PEMParser(new StringReader(privateKeyStr));
        PrivateKeyInfo privateKeyInfo = (PrivateKeyInfo) pemParser.readObject();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded());
        return KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(keySpec);
    }


    /**
     * 加载 PKCS1 私钥
     *
     * @param privateKeyStr
     * @return
     * @throws IOException
     */
    public static PrivateKey getPkcs1PrivateKey(String privateKeyStr) throws IOException {
        PEMParser pemParser = new PEMParser(new StringReader(privateKeyStr));
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        KeyPair kp = converter.getKeyPair((PEMKeyPair) pemParser.readObject());
        return kp.getPrivate();
    }

    /**
     * 加载 RSA 公钥
     *
     * @param publicKeyStr
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PublicKey getPublicKey(String publicKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PEMParser pubParser = new PEMParser(new StringReader(publicKeyStr));
        SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pubParser.readObject();
        RSAKeyParameters rsa = (RSAKeyParameters) PublicKeyFactory.createKey(subjectPublicKeyInfo);
        RSAPublicKeySpec rsaSpec = new RSAPublicKeySpec(rsa.getModulus(), rsa.getExponent());
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM, new BouncyCastleProvider());
        return kf.generatePublic(rsaSpec);
    }

    /**
     * 使用公钥对字符串进行加密并返回Base64编码字符串
     *
     * @param data
     * @param publicKey
     * @return 加密失败返回null
     */
    public static String encryption(String data, PublicKey publicKey) {
        return encryptionByKey(data, publicKey);
    }

    /**
     * 使用私钥对字符串进行加密并返回Base64编码字符串
     *
     * @param data
     * @param privateKey
     * @return 加密失败返回null
     */
    public static String encryptionByPrivateKey(String data, PrivateKey privateKey) {
        return encryptionByKey(data, privateKey);
    }

    /**
     * 使用秘钥对字符串进行加密并返回Base64编码字符串
     *
     * @param data
     * @param key
     * @return
     */
    private static String encryptionByKey(String data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            int maxEncryptBlock = ((RSAKey) key).getModulus().bitLength() / 8 - 11;
            byte[] bytes = splitBytesHandle(cipher, dataBytes, maxEncryptBlock);
            byte[] encode = Base64.getEncoder().encode(bytes);
            return new String(encode, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对Base64字符串进行解码并使用私钥解密
     *
     * @param data
     * @param privateKey
     * @return 解密失败返回null
     */
    public static String decryption(String data, PrivateKey privateKey) {
        return decryptionByKey(data, privateKey);
    }

    /**
     * 对Base64字符串进行解码并使用公钥解密
     *
     * @param data
     * @param publicKey
     * @return 解密失败返回null
     */
    public static String decryptionByPublicKey(String data, PublicKey publicKey) {
        return decryptionByKey(data, publicKey);
    }

    /**
     * 对Base64字符串进行解码并使用秘钥解密
     *
     * @param data
     * @param key
     * @return 解密失败返回null
     */
    private static String decryptionByKey(String data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] dataBytes = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
            int maxDecryptBlock = ((RSAKey) key).getModulus().bitLength() / 8;
            byte[] bytes = splitBytesHandle(cipher, dataBytes, maxDecryptBlock);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 分段加解密
     *
     * @param cipher
     * @param dataBytes
     * @param maxDecryptBlock
     * @return
     * @throws IOException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    private static byte[] splitBytesHandle(Cipher cipher, byte[] dataBytes, int maxDecryptBlock) throws IOException, BadPaddingException, IllegalBlockSizeException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int length = dataBytes.length;
            for (int i = 0; i < length; i += maxDecryptBlock) {
                int inputLen = i + maxDecryptBlock < length ? maxDecryptBlock : length - i;
                byte[] doFinalBytes = cipher.doFinal(dataBytes, i, inputLen);
                out.write(doFinalBytes, 0, doFinalBytes.length);
            }
            return out.toByteArray();
        }
    }

    /**
     * 对数据进行签名操作
     *
     * @param data
     * @param privateKey
     * @param signAlgorithm
     * @return
     */
    public static String dataSign(Object data, PrivateKey privateKey, String signAlgorithm) {
        try {
            String signStr = JSON.toJSONString(data);
            Signature instance = Signature.getInstance(signAlgorithm);
            instance.initSign(privateKey);
            instance.update(signStr.getBytes(StandardCharsets.UTF_8));
            byte[] sign = instance.sign();
            byte[] encode = Base64.getEncoder().encode(sign);
            return new String(encode, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对数据进行签名操作
     * 使用此重载貌似会导致 NoSuchAlgorithmException 异常
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String dataSign(Object data, PrivateKey privateKey) {
        return dataSign(data, privateKey, SIGN_ALGORITHM);
    }

    /**
     * 对数据及Sign进行验签操作
     *
     * @param data
     * @param publicKey
     * @param signAlgorithm
     * @return
     */
    public static boolean checkSign(Object data, String sign, PublicKey publicKey, String signAlgorithm) {
        try {
            String signStr = JSON.toJSONString(data);
            Signature instance = Signature.getInstance(signAlgorithm);
            instance.initVerify(publicKey);
            instance.update(signStr.getBytes(StandardCharsets.UTF_8));
            byte[] decode = Base64.getDecoder().decode(sign);
            return instance.verify(decode);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对数据及Sign进行验签操作
     * 使用此重载貌似会导致 NoSuchAlgorithmException 异常
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static boolean checkSign(Object data, String sign, PublicKey publicKey) {
        return checkSign(data, sign, publicKey, SIGN_ALGORITHM);
    }

}
