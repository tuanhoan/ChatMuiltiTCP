/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghost.chat;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author tuanHoan
 */
public class AES {
   public SecretKey getAESKeyFromPort(String port){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] key = port.getBytes("UTF-8");
            key = md5.digest(key);
            return new SecretKeySpec(key, "AES");
        } catch (Exception e) {
        }
        return null;
    }
    
    public String enAES(String msg, String port){
        try {
            SecretKey secretKey = getAESKeyFromPort(port);
//            System.out.println(secretKey);
//            System.out.println(port);
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            return Base64.getEncoder().encodeToString(aesCipher.doFinal(msg.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return null;
    }
    
    public String deAES(String cipher, String port){
        try {
            SecretKey secretKey = getAESKeyFromPort(port);
//            System.out.println(secretKey);
//            System.out.println(port);
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(aesCipher.doFinal(Base64.getDecoder().decode(cipher)),"UTF-8");
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return null;
    }
}
