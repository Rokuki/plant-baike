package com.treecute.plant.util;

import java.util.Random;

/**
 * Created by mkind on 2017/11/27 0027.
 */

public class RandomString {

    public static String getRandomString(int length){
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
