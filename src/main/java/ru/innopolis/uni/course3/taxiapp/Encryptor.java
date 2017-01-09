package ru.innopolis.uni.course3.taxiapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 28.12.2016.
 *
 * @authot Julia Savicheva
 */
public class Encryptor {
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

}
