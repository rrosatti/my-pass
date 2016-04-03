package com.example.rodri.mypass.util;


import java.util.Random;

/**
 * Created by rodri on 4/2/2016.
 */
public class EncryptDecrypt {

    private static String _key = "123";

    public String getKey() {
        return _key;
    }

    public String encrypt(String password) {

        int n = password.length();
        String[] pass = password.split("");
        String[] encPass = new String[n];


        // STEP 1
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            encPass[count] = pass[i];
            count++;
        }
        // END OF STEP 1



        // STEP 2

        /**
         * aux - get the three first characters
         *
         */
        String[] firstThreeCharacters = new String[3];
        for (int i = 0; i < encPass.length - 3; i++) {
            if (i < 3) {
                firstThreeCharacters[i] = encPass[i];
            }
            encPass[i] = encPass[i + 3];
        }
        count = 0;
        for (int i = n - 3; i < encPass.length ; i++) {
            encPass[i] = firstThreeCharacters[count++];
        }
        // END OF STEP 2



        // STEP 3
        String secondToLastCharacter = encPass[n - 2];
        for (int i = encPass.length - 2; i > 0; i--) {
            String aux = encPass[i - 1];
            encPass[i] = aux;
        }
        encPass[0] = secondToLastCharacter;
        // END OF THE STEP 3



        // STEP 4
        // random.nextInt(max - min + 1) + min
        Random random = new Random();
        String[] randEncPass = new String[n + 2];
        randEncPass[0] = Integer.toString(random.nextInt(10));
        randEncPass[n + 1] = Integer.toString(random.nextInt(10));
        count = 0;
        for (int i = 1; i < randEncPass.length - 1; i++) {
            randEncPass[i] = encPass[count++];
        }
        // END OF STEP 4



        // convert from string array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < randEncPass.length; i++) {
            strBuilder.append(randEncPass[i]);
        }
        String result = strBuilder.toString();

        return result;

    }

    public String decrypt(String encryptedPassword) {
        return "";
    }

}
