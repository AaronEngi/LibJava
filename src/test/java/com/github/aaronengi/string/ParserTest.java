package com.github.aaronengi.string;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parsePercent() {
        String t = "13.71%";
        System.out.println("Parser.parsePercent(t) " + t + " = " + Parser.parsePercent(t));

        t = "+1.04%";
        System.out.println("Parser.parsePercent(t) " + t + " = " + Parser.parsePercent(t));

        t = "-1.04%";
        System.out.println("Parser.parsePercent(t) " + t + " = " + Parser.parsePercent(t));
    }
}
