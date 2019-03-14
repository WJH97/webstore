package com.utils;

public class CheckParaUtil {
    public static boolean checkString(String s) {
        return s != null && !s.isEmpty() && !"null".equals(s);
    }

}