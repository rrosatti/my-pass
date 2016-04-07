package com.example.rodri.mypass.util;


import java.util.Random;

/**
 *
 *  These are very very simple encrypt/decrypt methods which I implemented myself.
 *
 * Created by rodri on 4/2/2016.
 */
public class EncryptDecrypt {

    private static String _key = "123";

    public String getKey() {
        return _key;
    }


    /**
     *
     * 1 - backwards
     * 2 - put the three first characters to the final of String
     * 3 - move the second-to-last letter to the first position
     * 4 - add a random number(0 to 9) in the first and last position;
     *
     * @param password
     * @return
     */
    public String encrypt(String password) {

        int n = password.length();
        String[] pass = password.replaceFirst("^", "").split("");
        String[] encPass = new String[n];




        // STEP 1
        int count = 0;
        for (int i = pass.length - 1; i > 0; i--) {
            encPass[count] = pass[i];
            System.out.println(pass[i]);
            count++;
        }
        // END OF STEP 1

        //print(encPass);

        // STEP 2

        /**
         * aux - get the three first characters
         *
         */
        String[] firstThreeCharacters = new String[3];
        String[] lastCharacters = new String[n-3];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i < 3) {
                firstThreeCharacters[i] = encPass[i];
            }
            else {
                lastCharacters[j] = encPass[i];
                j++;
            }
        }
        j = 0;
        for (int i = 0; i < n; i++) {
            if (i >= n - 3) {
                encPass[i] = firstThreeCharacters[j];
                j++;
            }
            else
                encPass[i] = lastCharacters[i];
        }
        // END OF STEP 2

        //print(encPass);

        // STEP 3
        String secondToLastCharacter = encPass[n - 2];
        for (int i = encPass.length - 2; i > 0; i--) {
            String aux = encPass[i - 1];
            encPass[i] = aux;
        }
        encPass[0] = secondToLastCharacter;
        // END OF THE STEP 3

        //print(encPass);

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

        //print(encPass);

        // convert from string array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < randEncPass.length; i++) {
            strBuilder.append(randEncPass[i]);
        }
        String result = strBuilder.toString();

        return result;

    }

    /**
     *
     * 1 - remove first and last characters
     * 2 - move the first character to the second-to-last position
     * 3 - move the last three characters to the beginning
     * 4 - backwards
     *
     * @param encryptedPassword
     * @return
     */
    public String decrypt(String encryptedPassword) {

        int n = encryptedPassword.length();
        String[] tempArray = encryptedPassword.split("");
        String[] decPass = new String[n - 2];



        // STEP 1
        int count = 0;
        for (int i = 2; i < n; i++) {
            System.out.println(tempArray[i]);
            decPass[count++] = tempArray[i];
        }
        // END OF STEP 1

        print(decPass);

        // STEP 2
        String firstCharacter = decPass[0];
        for (int i = 0; i < decPass.length - 2; i++) {
            decPass[i] = decPass[i + 1];
        }
        decPass[decPass.length - 2] = firstCharacter;
        // END OF STEP 2

        print(decPass);

        // STEP 3
        String[] lastThreeCharacters = new String[3];
        String[] firstCharacters = new String[decPass.length - 3];
        int j = 0;
        for (int i = 0; i < decPass.length ; i++) {
            if (i >= decPass.length - 3) {
                lastThreeCharacters[j] = decPass[i];
                j++;
            } else {
                firstCharacters[i] = decPass[i];
            }
        }
        j = 0;
        for (int i = 0; i < decPass.length; i++) {
            if (i < 3)
                decPass[i] = lastThreeCharacters[i];
            else {
                decPass[i] = firstCharacters[j];
                j++;
            }
        }
        // END OF STEP 3

        print(decPass);

        // STEP 4
        String[] forward = new String[n - 2];
        count = 0;
        for (int i = decPass.length - 1; i >= 0; i--) {
            forward[count++] = decPass[i];
        }
        // END OF STEP 4



        // convert from string array to string
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < forward.length; i++) {
            strBuilder.append(forward[i]);
        }
        String result = strBuilder.toString();

        return result;

    }

    public void print(String[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.print(v[i]);
        }
        System.out.println();
    }

}
