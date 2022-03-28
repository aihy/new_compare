package com.hhwyz.newcompare.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5Util {
    public static byte[] md5(byte[] content) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(content);
        return messageDigest.digest();
    }

    public static String md5ToBase64(byte[] content) throws NoSuchAlgorithmException {
        return Base64.getEncoder().encodeToString(md5(content));
    }
}