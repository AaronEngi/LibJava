package com.github.aaronengi.jdkextend;


import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class StringUtil {
    public static String join(CharSequence delimiter, CharSequence... elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrays.stream overhead.
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }

    public static String join(CharSequence delimiter, List<? extends CharSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        // Number of elements not likely worth Arrays.stream overhead.
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs: elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }

    public static String joinListInPair(List<Object> data) {
        StringBuilder sDays = new StringBuilder();
        for (int i = 0; i < data.size(); i += 2) {
            sDays.append("(");
            sDays.append(data.get(i));
            sDays.append(",");
            if (i + 1 < data.size()) {
                sDays.append(data.get(i + 1));
            }
            sDays.append("),");
        }
        return sDays.substring(0, sDays.length() - 1);
    }

    public static String joinTuple(List<List<Object>> data) {
        StringBuilder sDays = new StringBuilder();
        for (List<Object> pair : data) {
            sDays.append("(");
            for (Object item : pair) {
                sDays.append(item);
                sDays.append(",");
            }
            sDays.deleteCharAt(sDays.length() - 1);
            sDays.append("),");
        }
        return sDays.substring(0, sDays.length() - 1);
    }
}

