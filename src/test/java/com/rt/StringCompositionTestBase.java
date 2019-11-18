package com.rt;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public abstract class  StringCompositionTestBase {

    Function<List<String>, List<List<String>>> sc;


    @Test
    public void testFindComposites() {
        List<String> wordList = Arrays.asList(
                "rockstar",
                "rock",
                "star");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(1));

        assertThat(results, containsInAnyOrder(
                Arrays.asList("rock", "star")
        ));
    }

    @Test
    public void testFindCompositesNoMatch() {
        List<String> wordList = Arrays.asList(
                "rockstar",
                "rock",
                "sta");

        List<List<String>> results = sc.apply(wordList);

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesSingleLetter() {
        List<String> wordList = Arrays.asList(
                "rockstar",
                "rock",
                "sta",
                "r");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(1));
        assertThat(results, containsInAnyOrder(
                Arrays.asList("rock", "sta", "r")
        ));
    }

    @Test
    public void testFindCompositesThreeWay() {
        List<String> wordList = Arrays.asList(
                "superhighway",
                "super",
                "high",
                "way");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(1));

        assertThat(results, containsInAnyOrder(
                Arrays.asList("super", "high", "way")
        ));
    }

    @Test
    public void testFindCompositesMultiple() {
        List<String> wordList = Arrays.asList(
                "superhighway",
                "super",
                "rockstar",
                "rock",
                "star",
                "high",
                "way");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(2));

        assertThat(results, containsInAnyOrder(
                Arrays.asList("super", "high", "way"),
                Arrays.asList("rock", "star")
        ));
    }


    @Test
    public void testFindCompositesMatchAndNoMatch() {
        List<String> wordList = Arrays.asList(
                "superhighway",
                "super",
                "rockstar",
                "rock",
                "star",
                "high",
                "way",
                "tar");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(2));

        assertThat(results, containsInAnyOrder(
                Arrays.asList("super", "high", "way"),
                Arrays.asList("rock", "star")
        ));
    }

    @Test
    public void testFindCompositesEmbedded() {
        List<String> wordList = Arrays.asList(
                "superhighway",
                "super",
                "high",
                "highway",
                "way");

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, hasSize(3));

        assertThat(results, containsInAnyOrder(
                Arrays.asList("super", "high", "way"),
                Arrays.asList("super", "highway"),
                Arrays.asList("high", "way")
        ));
    }

    @Test
    public void testFindCompositesEmptyList() {
        List<String> wordList = Collections.emptyList();

        assert sc != null;
        List<List<String>> results = sc.apply(wordList);

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesNullList() {
        assert sc != null;
        List<List<String>> results = sc.apply(null);

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesSingleInput() {
        assert sc != null;
        List<String> wordList = Collections.singletonList("single");

        List<List<String>> results = sc.apply(wordList);

        assertThat(results, empty());
    }


    @Test
    public void testFindCompositesDuplicateInput() {
        assert sc != null;
        List<String> wordList = Arrays.asList("duplicate", "duplicate", "duplicate");

        List<List<String>> results = sc.apply(wordList);

        assertThat(results, empty());
    }


    @Test
    public void testFindCompositeEmptyStrings() {
        assert sc != null;
        List<String> wordList = Arrays.asList("", "", "");

        List<List<String>> results = sc.apply(wordList);

        assertThat(results, empty());
    }
}