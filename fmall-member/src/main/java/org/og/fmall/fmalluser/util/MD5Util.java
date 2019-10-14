package org.og.fmall.fmalluser.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author:ougen
 * @date:2019/9/3013:49
 */
public class MD5Util {

    public static final String STATIC_SALT = "SFSDFSAGagdaga54dsf5as4g5adg";

    public static String md5(String pasalt){
        return DigestUtils.md5Hex(pasalt);
    }

    public static String passToDB(String pass,String salt){
        String str = "" + STATIC_SALT.charAt(0)+salt.charAt(3)+pass+STATIC_SALT.charAt(1)+salt;
        return md5(str);
    }
}
