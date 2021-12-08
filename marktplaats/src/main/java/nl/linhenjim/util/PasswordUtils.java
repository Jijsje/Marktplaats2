package nl.linhenjim.util;

import java.security.MessageDigest;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PasswordUtils {
    public static String digestPassword(String plainTextPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes(UTF_8));
            return new String(Base64.getEncoder().encode(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("Exception encoding password.", e);
        }
    }
}

