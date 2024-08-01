package com.vesapay.creditcard.publicPriavate;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class ConvertP12ToJKS {

    public static void main(String[] args) {
        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\julyprod.p12"; // Replace with the actual path to your .p12 file
        String p12Password = "pinelabspayout"; // Replace with the password for the .p12 file
        String jksFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\julyprod.jks"; // Replace with the desired path for the .jks file
        String jksPassword = "pinelabspayout"; // Replace with the desired password for the .jks file
        String customAlias = "pinelabspayout"; // Replace with your desired custom alias name

        try {
            // Load the .p12 file
            KeyStore p12Keystore = KeyStore.getInstance("PKCS12");
            FileInputStream p12FileInputStream = new FileInputStream(p12FilePath);
            p12Keystore.load(p12FileInputStream, p12Password.toCharArray());
            p12FileInputStream.close();

            // Create a new JKS keystore
            KeyStore jksKeystore = KeyStore.getInstance("JKS");
            jksKeystore.load(null, jksPassword.toCharArray());

            // Get the aliases from the .p12 keystore and store them in the JKS keystore
            Enumeration<String> aliases = p12Keystore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                if (p12Keystore.isKeyEntry(alias)) {
                    KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12Keystore.getEntry(alias, new KeyStore.PasswordProtection(p12Password.toCharArray()));
                    Certificate[] chain = keyEntry.getCertificateChain();
                    jksKeystore.setKeyEntry(customAlias, keyEntry.getPrivateKey(), jksPassword.toCharArray(), chain);
                } else if (p12Keystore.isCertificateEntry(alias)) {
                    Certificate cert = p12Keystore.getCertificate(alias);
                    jksKeystore.setCertificateEntry(customAlias, cert);
                }
            }

            // Save the JKS keystore to the .jks file
            FileOutputStream jksFileOutputStream = new FileOutputStream(jksFilePath);
            jksKeystore.store(jksFileOutputStream, jksPassword.toCharArray());
            jksFileOutputStream.close();

            System.out.println("Successfully converted .p12 to .jks format with custom alias.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.security.KeyStore;
//import java.security.cert.Certificate;
//import java.util.Enumeration;
//
//public class ConvertP12ToJKS {
//
//    public static void main(String[] args) {
//        String p12FilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\pinelabs-ssl.p12"; // Replace with the actual path to your .p12 file
//        String p12Password = "pinelabspayout"; // Replace with the password for the .p12 file
//        String jksFilePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\pinelabs-ssl.jks"; // Replace with the desired path for the .jks file
//        String jksPassword = "pinelabspayout"; // Replace with the desired password for the .jks file
//
//        try {
//            // Load the .p12 file
//            KeyStore p12Keystore = KeyStore.getInstance("PKCS12");
//            FileInputStream p12FileInputStream = new FileInputStream(p12FilePath);
//            p12Keystore.load(p12FileInputStream, p12Password.toCharArray());
//            p12FileInputStream.close();
//
//            // Create a new JKS keystore
//            KeyStore jksKeystore = KeyStore.getInstance("JKS");
//            jksKeystore.load(null, jksPassword.toCharArray());
//
//            // Get the aliases from the .p12 keystore and store them in the JKS keystore
//            Enumeration<String> aliases = p12Keystore.aliases();
//            while (aliases.hasMoreElements()) {
//                String alias = aliases.nextElement();
//                if (p12Keystore.isKeyEntry(alias)) {
//                    KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) p12Keystore.getEntry(alias, new KeyStore.PasswordProtection(p12Password.toCharArray()));
//                    Certificate[] chain = keyEntry.getCertificateChain();
//                    jksKeystore.setKeyEntry(alias, keyEntry.getPrivateKey(), jksPassword.toCharArray(), chain);
//                } else if (p12Keystore.isCertificateEntry(alias)) {
//                    Certificate cert = p12Keystore.getCertificate(alias);
//                    jksKeystore.setCertificateEntry(alias, cert);
//                }
//            }
//
//            // Save the JKS keystore to the .jks file
//            FileOutputStream jksFileOutputStream = new FileOutputStream(jksFilePath);
//            jksKeystore.store(jksFileOutputStream, jksPassword.toCharArray());
//            jksFileOutputStream.close();
//
//            System.out.println("Successfully converted .p12 to .jks format.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
