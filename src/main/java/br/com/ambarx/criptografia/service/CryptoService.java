package br.com.ambarx.criptografia.service;

import org.jasypt.util.text.StrongTextEncryptor;

public class CryptoService {
//  public static void main(String[] args) {
//    var encryptor = new StrongTextEncryptor();
//    encryptor.setPassword("Thondev");
//
//    var encryptedText = encryptor.encrypt("ThonWelling The Best Dev I've ever saw!!");
//
//    var decryptedText = encryptor.decrypt(encryptedText);
//
//    System.out.println("O Texto Encryptado é: " + encryptedText);
//    System.out.println("O Texto Decryptado é: " + decryptedText);
//  }

  private static final StrongTextEncryptor encryptor;
  static {
    encryptor = new StrongTextEncryptor();
    encryptor.setPassword(System.getenv("APP_KEY"));
  }

  public static String encrypt(String rawText){
    return encryptor.encrypt(rawText);
  }

  public static String decrypt(String encryptedText){
    return encryptor.decrypt(encryptedText);
  }
}
