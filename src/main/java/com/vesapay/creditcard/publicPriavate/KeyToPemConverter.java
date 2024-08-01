//package com.vesapay.creditcard.publicPriavate;
//
//import org.bouncycastle.util.io.pem.PemObject;
//import org.bouncycastle.util.io.pem.PemWriter;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.PrivateKey;
//import java.security.Security;
//
//public class KeyToPemConverter {
//    public static void main(String[] args) {
//        String keyFilePath = "path/to/your/key.key"; // Replace with the actual path to your .key file
//        String pemFilePath = "path/to/your/output.pem"; // Replace with the desired output path for .pem file
//
//        try {
//            String pemContent = convertKeyToPem(keyFilePath);
//            writePemToFile(pemContent, pemFilePath);
//            System.out.println("Conversion successful!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String convertKeyToPem(String keyFilePath) throws IOException {
//        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
//
//        try (FileReader fileReader = new FileReader(keyFilePath)) {
//            PrivateKey privateKey = org.bouncycastle.openssl.PEMParser.readObject(fileReader);
//
//            PemObject pemObject = new PemObject("PRIVATE KEY", privateKey.getEncoded());
//            StringWriter stringWriter = new StringWriter();
//            try (PemWriter pemWriter = new PemWriter(stringWriter)) {
//                pemWriter.writeObject(pemObject);
//                pemWriter.flush();
//            }
//
//            return stringWriter.toString();
//        }
//    }
//
//    private static void writePemToFile(String pemContent, String pemFilePath) throws IOException {
//        Path outputPath = Paths.get(pemFilePath);
//        try (FileWriter fileWriter = new FileWriter(outputPath.toFile())) {
//            fileWriter.write(pemContent);
//        }
//    }
//}
//
//
//
//
