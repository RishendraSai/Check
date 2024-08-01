package com.vesapay.creditcard.publicPriavate;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.util.Base64;

public class KeyPairValidation {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Replace the following strings with your actual public and private keys in Base64 encoded format
//        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxAdhiHr/DRHmz/IlQtm7WLjn0UCjsZ2bSOnfZC8yLJNnuPAKqRGQCpeV2n1nBVDGrkxTUFTwpbc6Bxm1+1gIwA0XkAC2AVGJ7QYqTDLrQgzkfP7h3Zu4s3u2qd7a6YfniryjGgKajzquBVy0im7Mr2ZPT+HNUvo8v6xKyXpYd9Xpt+ksp1iIvkxBvC3uH5mbzH5s21ZlvQRMWJBfKkIjPCQWNblGCl0XiMhRPmdquabmJGITcA18DhaRtwv3rf5BnR92seHOkxF0p++HgqYeuJmM3O+vFDT7ssgmrr7tp5zbJzpngPoEQIPe3n4i/XgnxiM4TX2dortUKnSEdLMwqwIDAQAB";
//        String privateKeyBase64 = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCG1GSu2eeHBx5Yleb/+PU1GE6xLeoZFgITN4iZxFxU4K//s+enxeuwFJVPCSEZuAcEPuIw/VDDNQGtCjYtnbJfXzsDArNdIHy4E44XzcoeXoqrMUln3tzzi9nilXufh+AggeO3fcQp0gon9/SX6hJRs6toM2aHU6mNWwVlHs6EKYgLj1OtbjBxHULcvVfp1bQmCS1QjLmMjKUDeJjQhMQ6a7pBrgwBdNmGFn5KnrJBq/gKpFnPQUxl/Wpov/oQfTxdlNdRU9T9Y86KaMRaGJkXHhhGG7MI5Nym4rc9AUrzKD2oKFcEjtcKIIkR0yJdiphQxv9KVxJLEajVm6Io5QVnAgMBAAECggEAbr2cRJDtPVZ3ckMNDEkq/YUNfTE9CYWuBqKiK8nAHGa/p8HYD1DcmC8Pw264JSuld7M+jQZCg1LSIZI1lZnkm7jX+00IjQDV70sAdkSsMe1s79b3d7zwtYi0WQp2Ez3CtHB63IcLy12cwd9ColO64Cg2LMuq9pOcu2F7OObosFtC0cJ/nw+BQ2YMALFh2/73nbXWp/WOuHrTq9jNChpcAOMKV9eAy/dtD8KB+AXG2xWecdVM2tG9vc/AIQ6yTGY9twiPGn8EdPtp7zyHQ4xnXH57ZfLWieopQUDfLukTupzG+HAoXZPhYIu2YFDtdmmzAjElzFSAV9wuCQV5QV7iAQKBgQDqWL7v6CWOZUOseHH80EUFj+gobdn/Ry3oCc016qqqIl4kjZYqcxNa/gWNNFtLsW+NXKCgeYUVdADQv4Jyb2lVK7bo32MS0pZTRqUPPLIZk5lTgeJ04tjTrwzbiheXiS2S+YU9Y2oYj7wE90ZADycJlD9KgOKzv51muipGlgJGJwKBgQCTSaogdTe9EKDOFGhpZiZrEZDgN/XxsiiO8c99IvVfJy5sUESVWa89GCcTsqNcXNni7iToQ/RyEpYEGBPB05oME03k17m1ktfV4VzpS8zoNxK971u/ymNgSfzuNbe2oWRy/pQHlj3qzQt4zbettY7A2FKhafbjAQlHnkoGcugOwQKBgBVCEaf/nhCJvRbTIjv1xJ0ZjczFEkZm9ZIb+YKPlAW08mdMdLfLEpFpg6Vof3OhV+Yx5dxIybrFeCF3jeTFmH+CZKhBQ2J8ueg5+1Ujg2BwlDMQEDPcNCEXuwnbQAKeDgVMHzsPXpt1xm+I4tWCyl7DFO60PFxj4GpmLtmSWAh3AoGAEatGo3osY5v5Dn/YPryoYzPKQ3uzH3mV8AAGOibVdMLIiOngMGJ/6T5agbxuaIgjA1w6RTrRulWMdZVF2Xy/AC/YqllOx/+DsP7GHJ6hSEJDF1H9mbZ30XdOxmzfSyN1X2425+oYJ3DNrYJQRKCBLZVF5GNH4Neg+1x72OYz/IECgYBKW5cQwaTQ31MXVyI9uwwRMmaJp2xUdMk8+rubcA9gT/y69l7c55xcrZkpSlEIqpRr9Q6evfXdO8yKOoQ4RPtf04WjdTb9DYoaMkzI5zP1oC1vvv8qyOVkQbCpyahikneQIYpU1Ri5TSWriDXtjbPqcwVh1GQXohrPlgQPvwO1hQ==";

        String publicKeyBase64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3tZkeEQTZn6uSiVNKeEP" +
                "zEP/2+A0ibcRp3gMrVO07Qyh8ueaqo7ZZYIUj55A7xpkM/G0QwW2pxPsJEiq4N7w" +
                "bXDWI5Q//UZ7XEn6Uvrs1E0qkbwnZiQss8jWmGhx8/u3YMh3Py8vBzbYnCnyrOmw" +
                "Aq8OAeokdKgVKcPGRws4HR4l6UBmGTuDXGnZrtcTZ/EZpPDd25FNmQFD6npF5Ndh" +
                "Kwk4jAEOhGASRyTLQbWBHwBnaXKp3DZqvIzq2i6uKvpZJKPDG0aybYxDX03o/fwG" +
                "c+4rGYWihNvsfeXYc+1O2GCukff5Z4kvHoCTKgjh9x7qaAkGTxp1heQIo8IajtKQ" +
                "1QIDAQAB";
        String privateKeyBase64 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDe1mR4RBNmfq5K" +
                "JU0p4Q/MQ//b4DSJtxGneAytU7TtDKHy55qqjtllghSPnkDvGmQz8bRDBbanE+wk" +
                "SKrg3vBtcNYjlD/9RntcSfpS+uzUTSqRvCdmJCyzyNaYaHHz+7dgyHc/Ly8HNtic" +
                "KfKs6bACrw4B6iR0qBUpw8ZHCzgdHiXpQGYZO4Ncadmu1xNn8Rmk8N3bkU2ZAUPq" +
                "ekXk12ErCTiMAQ6EYBJHJMtBtYEfAGdpcqncNmq8jOraLq4q+lkko8MbRrJtjENf" +
                "Tej9/AZz7isZhaKE2+x95dhz7U7YYK6R9/lniS8egJMqCOH3HupoCQZPGnWF5Aij" +
                "whqO0pDVAgMBAAECggEADmyN9b7zeNDQZVEezNVREFNd6bkxH7GBxpwLbgZ2o8fC" +
                "jZZyS4H/6eDg84ykIxYCYrnNhbiGPgvPqENP5pPVbtSq0zsJiwlOeyuKtPnGgB7y" +
                "l3ZdIDnfgSscLqLHihfnfma265g92g5opiaM9qZaU92S8YrIi1GzGZBDlzlhsAr7" +
                "4OYM84+5pR+otm1fVum/pnPlo7o1k4pvUAgdYA/wkAHxPcF+Su/ke6IInLSplkZ6" +
                "RrZNjgbyhdMxB8ai+7WYTM78gOcZMBcaS1dhmplzKjABUeeYfm8CuKpvDgQJHLeI" +
                "uzJc3oN6h6y5Q297ppe4rbYoBD5o/Nvwvvuo3T3LbQKBgQD4YOEVVduqi8NyK/Q5" +
                "oOnWmqwXAPP7UbLPOwifzQRYBlF0nRPXBaJ2ne1ahzkUGv/CuBVd4w3KJFrpP1v3" +
                "+Pr3cWrmO5rjD41tn1LnPWQriRX0NlB62LoJ0Ccn+zPyVZuuq1HrE7e/cJz5ZcBU" +
                "xsJ1P0gZA/PaK56rdXj/W6raowKBgQDlrOC5tmklJ4FaRdpj7b1oC+QPoQtMU/Gz" +
                "8ZwrNSJJAJDB9cdyAWxsv5dghzNUAFWuj58X3YqQHccFH8UjutaGuFfLE/1x+2E4" +
                "YbH27ySqzu2fyNC2t/J4JsqTyc670nLf/mTO9BQQrx18UznWw7BBI9LiMzgVuEUT" +
                "1FpXFk3WJwKBgD5QP+gUZEoDSoOIV3tlzYTTxTz4Nj3lH1EyZFWDIAXdf+4bwnB9" +
                "r2bG9Fdz+XP72hLGEEY/wqPmPxCpnq81vfD6eXFNnuw8aKUKBciR9GGfwOxF/Cb1" +
                "3Ka9nDVBpqSzqZ1GygSKK91RqXBAgOJEA2FbAQGVpkYkD2jAzhxyqQi1AoGARDRw" +
                "j/Xxh40RxsMTtbU2GB3f3hxPBJ2xG2TWoaYnxlIaqt7YGaz+i9ThmVLNFVb8Gtif" +
                "wDCUI3QUVBs5KgEkD+HSgIYxL5YdKucoOFKQKwFEY7z47in2IWQzqGu3ruMyJf6f" +
                "5S1qgAHg2bjFZle6kb2XtIDNWPXd3aOkKFSBTOUCgYEAmePCn5fvNTn0K42T++7V" +
                "h75KEK+Gay3BGMLX84HsEuZsUU/1VIdjgxuCPRMm7HEQoFyEtAFm84TlCaDB3YTS" +
                "QCG3KcRaSRJehp7N9//OZhhZsU9sta+bpZVG7gmHRIs2aMsoo178X9vVMFimgNUf" +
                "fRdzmmHlcWLDx2QKCIakgzg=";
        PublicKey publicKey = getPublicKey(publicKeyBase64);
        PrivateKey privateKey = getPrivateKey(privateKeyBase64);

        if (isValidKeyPair(publicKey, privateKey)) {
            System.out.println("Valid Key Pair!");
        } else {
            System.out.println("Invalid Key Pair!");
        }
    }

    public static PublicKey getPublicKey(String publicKeyBase64) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    public static PrivateKey getPrivateKey(String privateKeyBase64) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    public static boolean isValidKeyPair(PublicKey publicKey, PrivateKey privateKey) throws Exception {
        String testMessage = "This is a test message to validate the key pair.";
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = encryptCipher.doFinal(testMessage.getBytes());

        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = decryptCipher.doFinal(encryptedData);

        return testMessage.equals(new String(decryptedData));
    }
}
