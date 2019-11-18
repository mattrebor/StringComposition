package com.rt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCompositionStream implements StringComposition {

    public static List<List<String>> findComposites(List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList != null ? wordList : Collections.emptyList());

        return wordSet
                .stream()
                .flatMap(word -> StringCompositionStream.findCompositesInWord(word, wordSet, Collections.<String>emptyList()))
                .collect(Collectors.toList());
    }

    private static Stream<List<String>> findCompositesInWord(String word, Set<String> wordSet, List<String> context) {

        if (word.isEmpty() && context.size() > 1) {
            return Stream.of(context);
        } else {
            return IntStream.rangeClosed(1, word.length())
                    .mapToObj(i -> new Split(word, i))
                    .filter(s -> wordSet.contains(s.prefix))
                    .flatMap(s -> findCompositesInWord(s.suffix, wordSet, concat(context, s.prefix)));
        }
    }


    static class Split {
        final String prefix;
        final String suffix;

        Split(String word, int index) {
            prefix = word.substring(0, index);
            suffix = word.substring(index);
        }
    }

    private static List<String> concat(List<String> context, String element) {
        List<String> newList = new ArrayList<>();

        if (context.isEmpty()) {
            newList.add(element);
        } else {
            newList.addAll(context);
            newList.add(element);
        }

        return newList;
    }
}
