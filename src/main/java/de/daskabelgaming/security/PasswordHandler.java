package de.daskabelgaming.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordHandler {

    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] keyPass) {
        try {
            byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, keyPass);
            return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
        }catch (Exception exception) {exception.printStackTrace();}
        return false;
    }

    public byte[] getEncryptedPassword(String password, byte[] keyPass)
    {
        String algorithm = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 160;
        int iterations  = 20000;
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), keyPass, iterations, derivedKeyLength);
            SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
            return f.generateSecret(spec).getEncoded();
        }catch (Exception exception) {exception.printStackTrace();}
        return null;
    }

    public byte[] generateKeyPass() {
        byte[] keyPass = null;
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyPass = new byte[8];
            random.nextBytes(keyPass);
        }catch (Exception exception) {exception.printStackTrace();}
        return keyPass;
    }
}
