package com.rt;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringComposition {

    public static List<List<String>> findComposites(Optional<List<String>> wordList) {
        //return findCompositesNonStream(wordList);
        return findCompositesStream(wordList);

    }

    public static List<List<String>> findCompositesStream(Optional<List<String>> wordList) {

            Set<String> wordSet = new HashSet<>(wordList.orElse(Collections.<String> emptyList()));

            return wordSet
                    .stream()
                    .flatMap(word -> findCompositesInWordStream(word, wordSet, Collections.<String>emptyList()))
                    .collect(Collectors.toList());
    }

    private static Stream<List<String>> findCompositesInWordStream(String word, Set<String> wordSet, List<String> context) {

        if (word.isEmpty() && context.size() > 1) {
            return Stream.of(context);
        }
        else {
            return IntStream.rangeClosed(1, word.length())
                    .mapToObj(i -> new Split(word, i))
                    .filter(s -> wordSet.contains(s.prefix))
                    .flatMap(s -> findCompositesInWordStream(s.suffix, wordSet, concat(context, s.prefix)));
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

    public static List<List<String>> findCompositesNonStream(Optional<List<String>> wordList) {

        List<List<String>> resultList = new ArrayList<>();

        if (wordList != null) {
            Set<String> wordSet = new HashSet<>(wordList.orElse(Collections.<String> emptyList()));

            for (String word : wordSet) {
                List<List<String>> results = findCompositesInWordNonStream(word, wordSet, Collections.<String>emptyList());
                if (results != null && !results.isEmpty()) {
                    resultList.addAll(results);
                }
            }
        }

        return resultList;
    }

    private static List<List<String>> findCompositesInWordNonStream(String word, Set<String> wordSet, List<String> context) {

        List<List<String>> results = new ArrayList<>();

        if (!word.isEmpty()) {
            for (int i = 1; i < word.length() + 1; i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(i);

                if (wordSet.contains(prefix)) {
                    List<List<String>> innerResults = findCompositesInWordNonStream(suffix, wordSet, concat(context, prefix));
                    results.addAll(innerResults);
                }
            }
        }
        else if (context.size() > 1) {
            results.add(context);
        }

        return results;
    }

    private static List<String> concat(List<String> context, String element) {
        List<String> newList = new ArrayList<>();

        if (context.isEmpty()) {
            newList.add(element);
        }
        else {
            newList.addAll(context);
            newList.add(element);
        }

        return newList;
    }
}
