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

    private static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static BASE64Encoder base64Encoder = new BASE64Encoder();
    private static BASE64Decoder base64Decoder = new BASE64Decoder();
    private static KeyPairGenerator keyPairGenerator;
    private static Cipher cipher;

    static {
        try {
            //获得对象 KeyPairGenerator 参数 RSA 1024个字节
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(1024);

            cipher = Cipher.getInstance(KEY_ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Key> initKey() {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Key> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    public static String encryptByRsa(String clearText, String publicKey) throws Exception {
        byte[] decoded = base64Decoder.decodeBuffer(publicKey);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM)
            .generatePublic(new X509EncodedKeySpec(decoded));
        cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
        return Base64.encodeBase64String(cipher.doFinal(clearText.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decryptByRsa(String cipherText, String privateKey) throws Exception {
        byte[] bytes = base64Decoder.decodeBuffer(cipherText);
        byte[] decodeBase64 = Base64.decodeBase64(privateKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM)
            .generatePrivate(new PKCS8EncodedKeySpec(decodeBase64));
        cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
        return new String(cipher.doFinal(bytes));
    }

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            Map<String, Key> keyMap = initKey();
            String publicKey = base64Encoder.encodeBuffer(keyMap.get(PUBLIC_KEY).getEncoded());
            String privateKey = base64Encoder.encodeBuffer(keyMap.get(PRIVATE_KEY).getEncoded());

            for (int i = 0; i < 100000; i++) {
                String clearText = "www.dingpengwei@foxmail.com";
                String cipherText = encryptByRsa(clearText, publicKey);
                String result = decryptByRsa(cipherText, privateKey);
            }

            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
