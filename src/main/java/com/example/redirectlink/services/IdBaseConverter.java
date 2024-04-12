package com.example.redirectlink.services;

public class IdBaseConverter {
    public static final String baseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String toBase62(Long decimalNumber) {
        return fromDecimalToOtherBase(62, decimalNumber);
    }

    public static Long fromBase62ToLong(String base62Number) {
        long result = 0;
        for (int i = 0; i < base62Number.length(); i++) {
            char c = base62Number.charAt(i);
            int digit = baseDigits.indexOf(c);
            result = result * 62L + digit;
        }
        return result;
    }


    private static String fromDecimalToOtherBase(int base, Long decimalNumber) {
        String result = decimalNumber == 0 ? "0" : "";
        while (decimalNumber != 0) {
            int mod = (int) (decimalNumber % base);
            result = baseDigits.substring(mod, mod + 1) + result;
            decimalNumber = decimalNumber / base;
        }
        return result;
    }
}
