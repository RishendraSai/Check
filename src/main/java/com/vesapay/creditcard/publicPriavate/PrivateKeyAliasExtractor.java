package com.vesapay.creditcard.publicPriavate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Enumeration;

public class PrivateKeyAliasExtractor {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\pinelabs-ssl-prod.p12";
        String password = "pinelabspayout"; // Change this to your actual password


        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            FileInputStream fis = new FileInputStream(p12FilePath);
            keyStore.load(fis, password.toCharArray());
            fis.close();

            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                if (keyStore.isKeyEntry(alias)) {
                    Key key = keyStore.getKey(alias, password.toCharArray());
                    if (key instanceof PrivateKey) {
                        System.out.println("Alias of the Private Key Entry: " + alias);
                        // You can also access the PrivateKey instance using 'key' variable.
                        break; // Assuming you want to print the first private key entry found.
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
