package com.vesapay.creditcard.publicPriavate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class Createp12WithoutPrivateKey {

    public static void main(String[] args) {
        String cerFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\cert.cer";
        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\cert.p12";
        String p12Password = "pinelabspayouts";
        String alias = "pinelabspayouts";
        //pinelabspayouts// Provide the alias used in the keystore

        try {
            // Load the certificate file using CertificateFactory
            FileInputStream fis = new FileInputStream(cerFilePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate cert = certificateFactory.generateCertificate(fis);

            // Create a new KeyStore for the PKCS#12 format
            KeyStore p12KeyStore = KeyStore.getInstance("PKCS12");
            p12KeyStore.load(null, p12Password.toCharArray());

            // Add the certificate to the PKCS#12 KeyStore
            p12KeyStore.setCertificateEntry(alias, cert);

            // Save the PKCS#12 KeyStore to a file
            try (FileOutputStream fos = new FileOutputStream(p12FilePath)) {
                p12KeyStore.store(fos, p12Password.toCharArray());
            }

            System.out.println("Conversion successful. PKCS#12 file created at: " + p12FilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




