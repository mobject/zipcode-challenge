package com.kforce.challenge;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

/**
 * @author minhtran
 */
class ZipCodeRangeTest {
    private final ZipCodeRange zipCodeRange = new ZipCodeRange();

    @Test
    void addRange_sucessful() {
        int[] input = new int[]{12345, 12346};
        zipCodeRange.addRange(input);
        SortedSet<int[]> ranges = zipCodeRange.getSortedRanges();
        Assertions.assertArrayEquals(ranges.first(), input);
    }

    @Test
    void addRange_successful_sortInput() {
        int[] input = new int[]{12346, 12345};
        zipCodeRange.addRange(input);
        SortedSet<int[]> ranges = zipCodeRange.getSortedRanges();
        MatcherAssert.assertThat("Input range array should be sorted automatically", ranges, Matchers.containsInRelativeOrder(new int[]{12345, 12346}));
    }

    @Test
    void addRange_successful_sortRanges() {
        int[] input1 = new int[]{12345, 12346};
        int[] input2 = new int[]{12320, 12325};
        zipCodeRange.addRange(input1);
        zipCodeRange.addRange(input2);
        SortedSet<int[]> actualRanges = zipCodeRange.getSortedRanges();
        List<int[]> expectedRanges = new ArrayList<>();
        expectedRanges.add(input2);
        expectedRanges.add(input1);
        MatcherAssert.assertThat(actualRanges.size(), Matchers.equalTo(expectedRanges.size()));
        MatcherAssert.assertThat("The ranges should be ordered automatically", actualRanges, Matchers.containsInRelativeOrder(expectedRanges.toArray()));
    }

    @Test
    void addRange_error_invalidInput1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> zipCodeRange.addRange(null));
    }

    @Test
    void addRange_error_invalidInput2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> zipCodeRange.addRange(new int[]{12345}));
    }

    @Test
    void addRange_error_invalidInput3() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> zipCodeRange.addRange(new int[]{}));
    }
}