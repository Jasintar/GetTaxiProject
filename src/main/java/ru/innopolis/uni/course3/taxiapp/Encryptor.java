package ru.innopolis.uni.course3.taxiapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public class Encryptor  implements PasswordEncoder{
    private static final Logger LOG = LoggerFactory.getLogger(Encryptor.class);

    public static final String SALT = "spring is coming";

    public static String hashPassword(String password, String salt){
        byte[] hash = null;
        StringBuffer  hexString = new StringBuffer();

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(salt.getBytes("UTF-8"));
            hash = messageDigest.digest(password.getBytes("UTF-8"));
            for (int i = 0; i < hash.length; i++) {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return hexString.toString();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        byte[] hash = null;
        StringBuffer  hexString = new StringBuffer();

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(SALT.getBytes("UTF-8"));
            hash = messageDigest.digest(rawPassword.toString().getBytes("UTF-8"));
            for (int i = 0; i < hash.length; i++) {
                hexString.append(Integer.toHexString(0xFF & hash[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            LOG.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        return hexString.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            LOG.warn("Empty encoded password");
            return false;
        }

        return encodedPassword.equals(encode(rawPassword));
    }
}
