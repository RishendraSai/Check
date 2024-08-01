package com.vesapay.creditcard.publicPriavate;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import org.bouncycastle.openssl.PEMWriter;

public class PrivateKeyToPEM {

    public static void main(String[] args) {
        // Replace privateKeyString with your actual private key string
        String privateKeyString = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDidhjXW5s5B+ca" +
                "mLT2Gm31RM4rhI21nAG7EnMtyLolCbKYTSC0x7+aV/7dRRyTURtfBAdZ1sEltwUo" +
                "UZjkCCwFmbPq/R1nGlgEqLwlGQsaiApEDoLfZHWQURydXNd0yQ0RUsm4t9NvRDyK" +
                "pAdq5gE/8v6txIlynr6AC/h8fLFqdVYksjNbj0S0Z+2W2VrU8YUmpDlW6srfC1tF" +
                "7zIMU421bEG2f5IidZCNGcHe70mAD1tD0mnQ6YSDChhq4PuyvEVxMf7oCJmIFrlS" +
                "HddGx4Xe2MVa7uAumm50odUiVdKFGNVFdI9vVzD66o5xE+pAlhn5WC/gGdxCDmlj" +
                "wRQgI5ljAgMBAAECggEAFUIQwuaOg6QCRbTgB9ojSErVL7CZvNqQLwdkWH/nOWLX" +
                "qQRUdaPqkIqCGjNKo2mRsDGonv5R00CC8231dDJwL3ZmNUDdabhHWuicAL+BE1SB" +
                "4jGah23eu1Vh4kO81fGpkF69IOHgrykwpbMQHzDIHUCqN5WjcWcFau+0u7rYzK1Z" +
                "z2f0GrUfD5o1qXeb3eEkTlcOcLNA8rEDjv60jqg7imHAuMuBa0cZEG8LpU995yI2" +
                "CV0Ohboz8hVkHridhJI1ZQdlghn450+Yuh5pfz467jgSKfMbcTYsaZmX6ydULS4U" +
                "XqRgkzYuUVTbfxdwGzT5Ndq3qL5stvGDUp1qj8HqAQKBgQD8dEo6fY55eWhHO5G3" +
                "NNQJJulivJEO6hgi77qpwQ3KGLGO+g5H5L2eWjE+v7fBOkJf05IvnlUzQLAelv43" +
                "94Kp+MizE3ipeBUrmE4J2NlKQ2SqXRfcA8em/YjNM+7B5XArR1HWRKFw4NJe4poK" +
                "om6kHi4qMjh+yhZbLQPhGBblgwKBgQDlpFktsAMM8rTcd1tkJWZManNmF8iFcl8n" +
                "BoxFKHOc4xVzYQ1T8iD2NpDKLmZWZRarugaj8aI6xso85v83spS9dTdBcrYYn3cQ" +
                "0SaJ8sTc4OebEu0YoJpI+QL/FA01uIhO6iFsnfHxDvoGHNXJiU2PXyl1uPIdqU/Y" +
                "xy/VmqwWoQKBgQDVeZiTITzrJ2iwY4TygO+T1Z2g6odXL0i9si4MAb2hWYQ7vON6" +
                "k8hsMSRXRVMjSBp8YbHKJn8xWdAT7eaGePu6sy8zjpGH7I0qUh4bYY1MqPRkw1e7" +
                "qk6XvOiDbRjGSTeywRpIr7w8cUAzEDbmKziX+xVahSEUxdpluwj+vJoP9QKBgB41" +
                "QVnmNnCEmNALj2UcGlnhAu8iH8mWIvgyP091o8e2g4La+HLbfgSFZwSBmAqzX4kV" +
                "fnwgdBPBRKdAR8Be6HwHqE4etd+FUpdOvAAXhFf96dmXYsMDlWgWAZPmYlp3vM6M" +
                "s/riyAnKy470ShcpzZ8XiI+o1YibVhqRETTLFOohAoGABoU0CwwlI3JsQY4bKciy" +
                "C7/F+BR9YWBS0gdfTRgnsQPqhG1ExzNAARRrTJiDu2YbeyjMR+kw+KIdAns8Oq8d" +
                "3pw2lbUHqWCcawxj7SBw6xARnAjWtEN6/ti4KfBXxJOBZwqIPkPUApPkwK6qWwNZ" +
                "TCO8Cp+r5yh7bs3sZNV+qpI=";

        // Replace outputPath with the desired path for the PEM file
        String outputPath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\privateKeyProd.pem";

        try {
            PrivateKey privateKey = getPrivateKey(privateKeyString);
            writePrivateKeyToPEM(privateKey, outputPath);
            System.out.println("Private key successfully converted to PEM file.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PrivateKey getPrivateKey(String privateKeyString) throws Exception {
        // Remove the PEM header and footer, and convert the Base64-encoded key to bytes
        String privateKeyPEM = privateKeyString.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", "");

        // Decode the Base64-encoded key
        byte[] decoded = java.util.Base64.getDecoder().decode(privateKeyPEM);

        // Generate PrivateKey object from the decoded bytes
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoded));
    }

    private static void writePrivateKeyToPEM(PrivateKey privateKey, String outputPath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(outputPath);
             PEMWriter pemWriter = new PEMWriter(fileWriter)) {
            pemWriter.writeObject(privateKey);
        }
    }
}
