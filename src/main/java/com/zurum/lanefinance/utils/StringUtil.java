package com.zurum.lanefinance.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class StringUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final DecimalFormat formatter = new DecimalFormat("#,###");
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance();

    public static boolean isBlank(String string) {
        return string == null || string.trim().isEmpty();
    }

    public static boolean isAnyBlank(String... strings) {
        return Arrays.stream(strings)
                .map(StringUtil::isBlank)
                .reduce(false, (total, current) -> total || current);
    }

    public static String stripComma(final String string) {
        return string.replace(",", "");

    }

    public static boolean isNumeric(String string){
        if (isBlank(string))
            return false;
        return string.matches("\\d+");
    }

    public static String mapToJsonString(HashMap<String, String> map) {
        if (map == null)
            return "{}";
        ObjectNode jsonString = mapper.createObjectNode();
        for (String key : map.keySet()) {
            jsonString.put(key, map.get(key));
        }
        return jsonString.toString();
    }

    public static String stripPunct(String s) {
        if (isBlank(s))
            return null;
        return s.replaceAll("\\W", "");
    }

    public static boolean hasOnlyLetters(String str) {
        if (isBlank(str))
            return false;
        return !str.matches(".*\\d.*");
    }

    public static String cleanSpaceAndSymbols(String s) {
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("-", "").replaceAll(",", "").replaceAll("\\+", "");
        return s;
    }

    public static String stripAccents(String str) {
        if (isBlank(str))
            return null;
        return org.apache.commons.lang3.StringUtils.stripAccents(str);
    }

    public static String prettyNumberFormat(double number) {
        return formatter.format(number);
    }


    public static Optional<String> stripNonDigit(String str) {
        if (isBlank(str))
            return Optional.empty();

        String res = str.replaceAll("[^\\d.]", "");
        if (isBlank(res))
            return Optional.empty();

        return Optional.of(res);
    }


    private static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append(i > start ? " " : "").append(words[i]);
        return sb.toString();
    }

    public static String trim(final String s) {
        if (s == null) {
            return null;
        }
        return s.trim();
    }

    public static String getNairaInKobo(double amount) {
        numberFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(amount * 100);
    }

}
