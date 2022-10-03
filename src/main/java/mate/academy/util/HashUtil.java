package mate.academy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String CRYPTO_ALGORITHM = "SHA-512";

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashBuilder = new StringBuilder();
        try {
            MessageDigest instance = MessageDigest.getInstance(CRYPTO_ALGORITHM);
            instance.update(salt);
            byte[] digest = instance.digest(password.getBytes());
            for (byte b : digest) {
                hashBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashBuilder.toString();
    }
}