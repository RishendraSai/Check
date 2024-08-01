package com.vesapay.creditcard.publicPriavate;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

public class EctractPrivateKey{

    public static void main(String[] args) {
        String cerFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\cert.cer";
        String keystorePassword = "pinelabspayouts";

        try {
            // Load the certificate file
            FileInputStream fis = new FileInputStream(cerFilePath);

            // Create a KeyStore and load the certificate into it
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(null, keystorePassword.toCharArray());
            Certificate cert = keystore.getCertificate("alias"); // You need to provide the alias

            // Extract the public key
            Key publicKey = cert.getPublicKey();
            System.out.println("Public Key: " + publicKey);

            // Extract the private key (assuming it's stored as a KeyEntry)
            Key privateKey = (Key) keystore.getKey("alias", keystorePassword.toCharArray());
            System.out.println("Private Key: " + privateKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


