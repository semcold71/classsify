package ru.samcold.classify.utils;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Decor {

    public String decoration(int value) {

        String result = "";
        final String s4 = "\u2074";
        final String s5 = "\u2075";
        final String s6 = "\u2076";
        final String s7 = "\u2077";
        double x;

        switch (String.valueOf(value).length()) {
            case 5:
                x = value / 10000.0;
                result = String.format("%,.2f", x) + " \u00D7 10" + s4;
                break;
            case 6:
                x = value / 100000.0;
                result = String.format("%,.2f", x) + " \u00D7 10" + s5;
                break;
            case 7:
                x = value / 1000000.0;
                result = String.format("%,.2f", x) + " \u00D7 10" + s6;
                break;
            case 8:
                x = value / 10000000.0;
                result = String.format("%,.2f", x) + " \u00D7 10" + s7;
                break;
        }

        return result;
    }
}
