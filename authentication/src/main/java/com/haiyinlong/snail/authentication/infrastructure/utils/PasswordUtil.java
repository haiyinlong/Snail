package com.haiyinlong.snail.authentication.infrastructure.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * PasswordUtil
 *
 * @author HaiYinLong
 * @version 2025/09/16 15:02
 **/
public class PasswordUtil {

    private static final String ALGORITHM = "SHA-256";

    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@$%^&*_.";

    /**
     * 生成随机salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return bytesToHex(salt);
    }

    /**
     * 使用salt加密密码
     */
    public static String encryptPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用", e);
        }
    }

    /**
     * 验证密码
     */
    public static boolean verifyPassword(String password, String salt, String hashedPassword) {
        String encryptedPassword = encryptPassword(password, salt);
        return encryptedPassword.equals(hashedPassword);
    }

    /**
     * 字节数组转十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * 生成随机密码如：@!78778sdf.
     *
     * @return 明文密码
     */
    public static String generatePassword() {
        // 确保密码包含各类字符至少一个
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        // 添加至少一个大写字母
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        // 添加至少一个小写字母
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        // 添加至少一个数字
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        // 添加至少一个特殊字符
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        // 组合所有字符
        String allChars = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARS;

        // 生成剩余的密码字符（总长度为10位）
        int remainingLength = 10 - 4;
        for (int i = 0; i < remainingLength; i++) {
            password.append(allChars.charAt(random.nextInt(allChars.length())));
        }
        return shuffleString(password.toString(), random);
    }

    /**
     * 打乱字符串顺序
     */
    private static String shuffleString(String string, SecureRandom random) {
        char[] chars = string.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
