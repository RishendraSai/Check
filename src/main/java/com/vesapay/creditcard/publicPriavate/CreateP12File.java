package com.vesapay.creditcard.publicPriavate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class CreateP12File {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        String certFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\85c1b20eeb5c1e1a.crt"; // Replace with the actual path of the certificate file
        String privateKeyFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\privateKeyProd.pem"; // Replace with the actual path of the private key file
        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\julyprod.p12"; // Replace with the desired .p12 file path
        String p12Password = "pinelabspayout"; // Replace with the desired password for the .p12 file

        try {
            // Load certificate
            Certificate cert = loadCertificate(certFilePath);

            // Load private key
            PrivateKey privateKey = loadPrivateKey(privateKeyFilePath);

            // Create a new keystore and add the certificate and private key entry
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            keyStore.load(null, null);
            keyStore.setKeyEntry("alias", privateKey, p12Password.toCharArray(), new Certificate[]{cert});

            // Save the keystore to a .p12 file
            FileOutputStream fos = new FileOutputStream(p12FilePath);
            keyStore.store(fos, p12Password.toCharArray());
            fos.close();

            System.out.println("P12 file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Certificate loadCertificate(String certFilePath) throws Exception {
        FileInputStream fis = new FileInputStream(certFilePath);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        Certificate cert = certFactory.generateCertificate(fis);
        fis.close();
        return cert;
    }

    public static PrivateKey loadPrivateKey(String privateKeyFilePath) throws Exception {
        FileReader fileReader = new FileReader(privateKeyFilePath);
        PemReader pemReader = new PemReader(fileReader);
        PEMParser pemParser = new PEMParser(pemReader);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        Object object = pemParser.readObject();
        KeyPair keyPair = converter.getKeyPair((PEMKeyPair) object);
        return keyPair.getPrivate();
    }
}
