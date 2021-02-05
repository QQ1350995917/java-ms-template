package pwd.initializr.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * pwd.initializr.common.utils@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2021-02-04 16:44
 *
 * @author DingPengwei[dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class CryptographerRsa {

    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static BASE64Encoder base64Encoder = new BASE64Encoder();
    private static BASE64Decoder base64Decoder = new BASE64Decoder();

    //map对象中存放公私钥
    private static Map<String, Key> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static String encryptByRsa(String clearText, String publicKey) throws Exception {
        //base64解码的公钥
        byte[] decoded = base64Decoder.decodeBuffer(publicKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM)
            .generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        String cipherText = Base64
            .encodeBase64String(cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8)));
        return cipherText;
    }

    public static String decryptByRsa(String cipherText, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] bytes = base64Decoder.decodeBuffer(cipherText);
        //base64编码的私钥
        byte[] decodeBase64 = Base64.decodeBase64(privateKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM)
            .generatePrivate(new PKCS8EncodedKeySpec(decodeBase64));
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        String clearText = new String(cipher.doFinal(bytes));
        return clearText;
    }

    public static void main(String[] args) {
        try {
            Map<String, Key> keyMap = initKey();
            String publicKey = base64Encoder.encodeBuffer(keyMap.get(PUBLIC_KEY).getEncoded());
            System.out.println(publicKey);
            String privateKey = base64Encoder.encodeBuffer(keyMap.get(PRIVATE_KEY).getEncoded());
            System.out.println(privateKey);

            String clearText = "www.dingpengwei@foxmail.com";
            String cipherText = encryptByRsa(clearText, publicKey);
            String result = decryptByRsa(cipherText, privateKey);

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
