package com.rt;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> wordList = Arrays.asList(
                "superhighway",
                "super",
                "high",
                "highway",
                "way",
                "rockstar",
                "rock",
                "star",
                "tar",
                "ks",
                "roc",
                "foo",
                "bar",
                "rockstars",
                "s"
        );


        List<List<String>> composites = StringComposition.findComposites(wordList);

        List<String> compositesString = Objects.requireNonNull(composites).stream()
                .map(Main::convertListToString)
                .collect(Collectors.toList());

        compositesString
                .forEach(System.out::println);
    }

    private static String convertListToString(List<String> list) {

        return "[" +
                String.join(", ", list) +
                "]";
    }
}
