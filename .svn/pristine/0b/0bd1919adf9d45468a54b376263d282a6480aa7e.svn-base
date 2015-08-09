/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controls;

import java.util.Random;

/**
 *
 * @author cengizhan
 */
public class StringDuzenle {

    public static String getRandomImageName() {
        Random rnd = new Random();

        int i = (rnd.nextInt(1000000000) * 100 + rnd.nextInt(10000));
        return String.valueOf(i);
    }

    public static String getEditString(String text) {

        text = text.replaceAll(" ", "");
        text = text.replaceAll("/", "");
        text = text.replaceAll("-", "");

        text = text.toLowerCase();

        return text;
    }

    public static String editPhoneNumber(String text) {

        text = text.replace("(", "");
        text = text.replace(")", "");
        text = text.replace("-", "");
        text = text.replace(" ", "");
        text = text.replace("_", "");
        
        return text;
    }

}
