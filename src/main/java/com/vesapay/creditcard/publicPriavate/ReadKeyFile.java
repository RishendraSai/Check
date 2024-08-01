package com.vesapay.creditcard.publicPriavate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadKeyFile {
    public static void main(String[] args) {
        String filePath = "D:\\JAVA\\Credit_Cards_Limits_Service\\src\\main\\resources\\UATPRIVATEKEY.key";  // Replace with the actual file path

        try {
            String fileContent = readKeyFile(filePath);
            System.out.println("File Content:\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readKeyFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] fileBytes = Files.readAllBytes(path);
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
}
