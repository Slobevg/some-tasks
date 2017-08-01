package ru.slobevg.csssr.simple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        final String s = "сапог сарай арбуз болт бокс биржа";
        final Map<Character, List<String>> collect = Arrays
            .stream(s.split(" "))
            .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.toList()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue().size() > 1)
            .peek(e -> Collections.sort(e.getValue(), (string, anotherString) -> {
                if (string.length() != anotherString.length()) {
                    return string.length() < anotherString.length() ? 1 : -1;
                } else {
                    return string.compareTo(anotherString);
                }
            }))
            .collect(TreeMap::new,
                (t, u) -> t.put(u.getKey(), u.getValue()),
                (t, u) -> t.putAll(u));

        System.out.println(collect);
    }
}
