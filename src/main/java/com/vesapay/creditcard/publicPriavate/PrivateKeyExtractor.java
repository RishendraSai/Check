package com.vesapay.creditcard.publicPriavate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;

public class PrivateKeyExtractor {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\pinelabs-ssl-prod.p12";
        String password = "pinelabspayout"; // Change this to your actual password

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            FileInputStream fis = new FileInputStream(p12FilePath);
            keyStore.load(fis, password.toCharArray());
            fis.close();

            String alias = "pinelabspayout"; // Provide the alias of the private key entry in the PKCS#12 file
            Key key = keyStore.getKey(alias, password.toCharArray());

            if (key instanceof PrivateKey) {
                PrivateKey privateKey = (PrivateKey) key;
                System.out.println("Private Key: " + privateKey);
            } else {
                System.out.println("No private key found for the specified alias.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
