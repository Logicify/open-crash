package org.opencrash.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Fong on 14.05.14.
 */
public class Security {

    public String getHashPassword(String passwordToHash)
    {
        String hash = null;
        //hash = getSecurePassword(passwordToHash);
        hash=getSecurePassword(passwordToHash);
        return  hash;
    }
    public String getHashKey(String name,String version,String username){
        String hash = null;
        hash = hashKeyWithSalt(name,version,username);
        return  hash;
    }

    private static String getSecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String hashString(String string)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(string.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    private String hashWithSalt(String string){
        String salt = "Qfnjkpat18*k78an,as";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((string+salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        return encryptedPassword;
    }
    private String hashKeyWithSalt(String name,String version,String username){
        String salt = "Qfnjkpat18*k78an,as";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((name+version+username+salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        return encryptedPassword;
    }

    public String getHashName(String name, String date) {
        String salt= "l0dadN57ansadaaluanaO";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((name + salt + date).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        return encryptedPassword;
    }

    public String getUserToken(String email, int id) {
        String salt= "l0dadN57ansadaaluanaO";
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update((email + salt + id).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String token = (new BigInteger(messageDigest.digest())).toString(16);
        return token;
    }
}
