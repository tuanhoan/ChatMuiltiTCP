/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghost.chat;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * @author tuanHoan
 */
public class Ultils {
    public static String SoLanXuatHien(String chuoi){ 
        System.out.println(chuoi);
        chuoi = removeAccent(chuoi);
        String [] arr=chuoi.split("");
        Map<String,Integer> map=new HashMap<>();
        for(String x: arr){
            if(!" ".equals(x)){
                if(map.containsKey(x)){
                    map.put(x,map.get(x)+1);
                }else{
                    map.put(x,1);
                }
            } 
        }
        String kq = "(";
        for(Map.Entry<String,Integer> entry: map.entrySet()){
            kq+=entry.getKey()+": "+entry.getValue() + ",";
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        kq+=")";
        return kq;
    }
    public static String removeAccent(String s) { 
        String temp = Normalizer.normalize(s.toLowerCase(), Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); 
        temp = pattern.matcher(temp).replaceAll(""); 
        return temp.replaceAll("đ", "d"); 
    }
    public static String getMd5(String input)
    {
        try { 
            MessageDigest md = MessageDigest.getInstance("MD5");
   
            byte[] messageDigest = md.digest(input.getBytes());
   
            BigInteger no = new BigInteger(1, messageDigest);
   
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } 
  
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
