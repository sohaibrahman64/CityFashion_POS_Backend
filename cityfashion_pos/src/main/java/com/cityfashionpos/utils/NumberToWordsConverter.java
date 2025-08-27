package com.cityfashionpos.utils;

public class NumberToWordsConverter {

    private static final String[] ones = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };
    private static final String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    private static final String[] teens = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
            "Seventeen", "Eighteen", "Nineteen" };

    public static String convertToWords(double num) {
        if (num == 0)
            return "Zero";

        int rupees = (int) num;
        int paise = Math.round((float) ((num - rupees) * 100));

        StringBuilder result = new StringBuilder();
        result.append(convert(rupees)).append(" Rupees");

        if (paise > 0) {
            result.append(" and ").append(convert(paise)).append(" Paisa");
        }
        result.append(" Only");

        return result.toString();
    }

    private static String convertLessThanOneThousand(int n) {
        if (n == 0)
            return "";

        if (n < 10)
            return ones[n];
        if (n < 20)
            return teens[n - 10];
        if (n < 100)
            return tens[n / 10] + (n % 10 != 0 ? " " + ones[n % 10] : "");
        if (n < 1000)
            return ones[n / 100] + " Hundred" + (n % 100 != 0 ? " " + convertLessThanOneThousand(n % 100) : "");
        return "";
    }

    private static String convert(int n) {
        if (n == 0)
            return "Zero";

        int crore = n / 10000000;
        int lakh = (n % 10000000) / 100000;
        int thousand = (n % 100000) / 1000;
        int remainder = n % 1000;

        StringBuilder result = new StringBuilder();

        if (crore > 0) {
            result.append(convertLessThanOneThousand(crore)).append(" Crore ");
        }
        if (lakh > 0) {
            result.append(convertLessThanOneThousand(lakh)).append(" Lakh ");
        }
        if (thousand > 0) {
            result.append(convertLessThanOneThousand(thousand)).append(" Thousand ");
        }
        if (remainder > 0) {
            result.append(convertLessThanOneThousand(remainder));
        }

        return result.toString().trim();
    }
}
