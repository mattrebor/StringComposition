package com.rt;

import java.util.*;


public class StringCompositionNonStream implements StringComposition {

    public static List<List<String>> findComposites(List<String> wordList) {

        List<List<String>> resultList = new ArrayList<>();

        if (wordList != null) {
            Set<String> wordSet = new HashSet<>(wordList);

            for (String word : wordSet) {
                List<List<String>> results = findCompositesInWordNonStream(word, wordSet, Collections.emptyList());
                if (!results.isEmpty()) {
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
