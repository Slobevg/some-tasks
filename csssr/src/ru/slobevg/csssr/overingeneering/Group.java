package ru.slobevg.csssr.overingeneering;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final List<String> words = new ArrayList<>();

    public Group(List<String> words) {
        this.words.addAll(words);
    }

    public List<String> getWords() {
        return words;
    }

    public Group add(String word) {
        words.add(word);
        return this;
    }

    public Group merge(Group group) {
        words.addAll(group.words);
        return this;
    }

    @Override
    public String toString() {
        return words.toString();
    }

}
