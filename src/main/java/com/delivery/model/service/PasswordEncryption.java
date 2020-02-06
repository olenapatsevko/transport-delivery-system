package com.delivery.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public final class PasswordEncryption {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(PasswordEncryption.class);
    private static final byte[] salt = ResourceBundle.getBundle("service").getString("salt").getBytes();


    public static String encrypt(String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("Exception while trying to get new instance of SecretKeyFactory", e);
        }
        byte[] hash = new byte[0];
        try {
            hash = Objects.requireNonNull(factory).generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            LOGGER.warn("Exception while trying to hash password", e);
        }
        return Arrays.toString(hash);

    }
}
