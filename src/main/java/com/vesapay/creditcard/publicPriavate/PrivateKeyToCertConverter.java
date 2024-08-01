package com.vesapay.creditcard.publicPriavate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;

public class PrivateKeyToCertConverter {

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
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);

                // Write the private key and certificate to PEM files
                writePrivateKeyToPem(privateKey, "privatekey.pem");
                writeCertificateToPem(cert, "certificate.pem");
            } else {
                System.out.println("No private key found for the specified alias.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writePrivateKeyToPem(PrivateKey privateKey, String filename) throws Exception {
        try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            pemWriter.writeObject(new PemObject("PRIVATE KEY", privateKey.getEncoded()));
        }
    }

    public static void writeCertificateToPem(X509Certificate certificate, String filename) throws Exception {
        try (PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            pemWriter.writeObject(new PemObject("CERTIFICATE", certificate.getEncoded()));
        }
    }
}
