package ru.samcold.classify.utils;

import org.springframework.stereotype.Component;
import java.util.AbstractMap;
import java.util.Map;

@Component
public class A2Matrix {
    private final Map<String, Integer> matrix = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("A1", 8000),
            new AbstractMap.SimpleEntry<>("A2", 16000),
            new AbstractMap.SimpleEntry<>("A3", 32000),
            new AbstractMap.SimpleEntry<>("A4", 63000),
            new AbstractMap.SimpleEntry<>("A5", 125000),
            new AbstractMap.SimpleEntry<>("A6", 250000),
            new AbstractMap.SimpleEntry<>("A7", 500000),
            new AbstractMap.SimpleEntry<>("A8", 1000000)
    );

    public int getN(String aIso) {
        return matrix.get(aIso);
    }
}
