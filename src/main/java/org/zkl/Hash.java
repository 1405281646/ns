package org.zkl;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.*;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Hash {

    public static void main(String[] args) throws Exception {


        KeyPair pair = generateKeyPair();
        System.out.println(pair.getPublic());
        System.out.println(pair.getPrivate());


        String message = "the answer to life the universe and everything";
        System.out.println("--------------------");

        System.out.println(message);
        System.out.println("--------------------");

        String hash=getHash(message);
        System.out.println(hash);
//        String cipherText = encrypt(message, pair.getPublic());
//        System.out.println(cipherText);
//        String decipheredMessage = decrypt(cipherText, pair.getPrivate());
//        System.out.println(decipheredMessage);
        System.out.println("--------------------");
        String sign = sign(hash, pair.getPrivate());
        System.out.println(sign);
        System.out.println("--------------------");

        boolean isCorrect1 = verify(hash, sign, pair.getPublic());

        System.out.println(isCorrect1);
        System.out.println("--------------------");

//        String signature = sign("foobar", pair.getPrivate());
//        boolean isCorrect = verify("foobar", signature, pair.getPublic());
//        System.out.println("Signature correct: " + isCorrect);
    }
    public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    public static String sign(String plainText, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 传入String，计算出它的hash值，并以字符串形式输出
     * @param str 传入的Srting类型字符串
     * @return 返回str的hash值
     */
    public static String getHash(String str){
        try{
            // 这里使用了MD5获得hash值
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return new BigInteger(1, messageDigest.digest(str.getBytes("UTF-8"))).toString(2);
        }catch(Exception e){
            e.printStackTrace();
            return str;
        }
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();

        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), UTF_8);
    }



}
