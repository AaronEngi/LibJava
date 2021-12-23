package com.github.aaronengi.string;

import java.text.NumberFormat;
import java.text.ParsePosition;

import javax.annotation.Nullable;

public class Parser {
    public static double parsePercent(String p) {
        if (p == null) {
            return 0;
        }
        ParsePosition position;
        if (p.startsWith("+")) {
            position = new ParsePosition(1);
        } else {
            position = new ParsePosition(0);
        }
        Number number = NumberFormat.getPercentInstance().parse(p, position);
        return number.doubleValue();
    }

    public static double parseDouble(@Nullable String text) {
        if (text == null) {
            return 0;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int parseInt(@Nullable String text) {
        if (text == null) {
            return 0;
        }
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
