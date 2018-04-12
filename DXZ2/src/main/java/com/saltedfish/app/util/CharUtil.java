package com.saltedfish.app.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharUtil {


    public static boolean isEnglishAnNum(String word) {
        boolean isWord = word.matches("^[0-9a-zA-Z]+$");
        return isWord;
    }
    public static boolean isChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
