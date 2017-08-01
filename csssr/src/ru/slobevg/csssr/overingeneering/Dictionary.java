package ru.slobevg.csssr.overingeneering;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Dictionary {

    private final static Comparator<? super String> comparator = (string, anotherString) -> string.length() != anotherString.length()
        ? string.length() < anotherString.length() ? 1 : -1
        : string.compareTo(anotherString);
    private final static BinaryOperator<Dictionary> combiner = (dict, anotherDict) -> {
        dict.groups.putAll(anotherDict.groups);
        return dict;
    };
    private final static BiFunction<Dictionary, Entry<Character, List<String>>, Dictionary> accumulator = (dict, entry) -> {
        dict.groups.merge(entry.getKey(), new Group(entry.getValue()), (group, anotherGroup) -> group.merge(anotherGroup));
        return dict;
    };

    private final Map<Character, Group> groups = new TreeMap<>();

    /**
     * returns new Dictionary object
     */
    public static Dictionary from(String text) {
        return Arrays
            .stream(text.split(" "))
            .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.toList()))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue().size() > 1)
            .peek(e -> Collections.sort(e.getValue(), comparator))
            .reduce(new Dictionary(), accumulator, combiner);
    }

    public void printTo(PrintStream printStream) {
        printStream.println(groups);
    }

}
