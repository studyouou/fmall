package org.og.fmall.commonapi.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.og.fmall.commonapi.constants.OrderConstants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author:ougen
 * @date:2019/10/1019:19
 */
public class AESUtil {
    public static String encode(String key,String souce) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec spec = new SecretKeySpec(DigestUtils.md5Hex(key).getBytes("UTF-8"),"AES");
        cipher.init(Cipher.ENCRYPT_MODE,spec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(souce.getBytes()));
    }

    public static String decode(String key,String toDecode) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec spec = new SecretKeySpec(DigestUtils.md5Hex(key).getBytes("UTF-8"),"AES");
        cipher.init(Cipher.DECRYPT_MODE,spec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(toDecode.getBytes())));
    }
}
