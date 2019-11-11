package com.rt;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class StringCompositionTest {

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testFindComposites() {
        List<String> wordList = Arrays.asList(
                "rockstar",
                "rock",
                "star");

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesSingleLetter() {
        List<String> wordList = Arrays.asList(
                "rockstar",
                "rock",
                "sta",
                "r");

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

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

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesNullList() {
        List<List<String>> results = StringComposition.findComposites(Optional.empty());

        assertThat(results, empty());
    }

    @Test
    public void testFindCompositesSingleInput() {
        List<String> wordList = Collections.singletonList("single");

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

        assertThat(results, empty());
    }


    @Test
    public void testFindCompositesDuplicateInput() {
        List<String> wordList = Arrays.asList("duplicate", "duplicate", "duplicate");

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

        assertThat(results, empty());
    }


    @Test
    public void testFindCompositeEmptyStrings() {
        List<String> wordList = Arrays.asList("", "", "");

        List<List<String>> results = StringComposition.findComposites(Optional.of(wordList));

        assertThat(results, empty());
    }
}