package com.jony.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * jony.lai
 */


public class PasswordEncoderUtil {

    private static final String SITE_WIDE_SECRET = "my-secret-key";
    public PasswordEncoderUtil() {
    }

    private static final PasswordEncoder encoder = new StandardPasswordEncoder(
            SITE_WIDE_SECRET);

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }

    public static void main(String[] args) {
        System.out.println(PasswordEncoderUtil.encrypt("123456"));
        System.out.println(PasswordEncoderUtil.encrypt("tel618601"));

        //但是把每次结果拿出来进行match，你会发现可以得到true。
    }
}