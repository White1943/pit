package com.example.platform.uilt;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordMD5Utils {
    public static String toMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            // bytes to hex
            BigInteger number = new BigInteger(1, hashInBytes);
            String hashtext = number.toString(16);

            // Now we need to zero pad it if you actually want the full 32 characters.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to get an instance of MessageDigest", e);
        }
    }
}

//基于时间同步密钥