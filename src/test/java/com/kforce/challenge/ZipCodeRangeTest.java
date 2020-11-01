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

    @Test
    void getMergedRanges1() {
        zipCodeRange.addRange(new int[]{94133, 94133});
        zipCodeRange.addRange(new int[]{94200, 94299});
        zipCodeRange.addRange(new int[]{94600, 94699});
        Collection<int[]> mergedRanges = RangeMergeUtil.getMergedRanges(zipCodeRange);
        List<int[]> expectedRanges = new ArrayList<>();
        expectedRanges.add(new int[]{94133, 94133});
        expectedRanges.add(new int[]{94200, 94299});
        expectedRanges.add(new int[]{94600, 94699});
        MatcherAssert.assertThat(mergedRanges.size(), Matchers.equalTo(expectedRanges.size()));
        MatcherAssert.assertThat(mergedRanges, Matchers.containsInRelativeOrder(expectedRanges.toArray()));
    }

    @org.junit.jupiter.api.Test
    void getMergedRanges2() {
        zipCodeRange.addRange(new int[]{94133, 94133});
        zipCodeRange.addRange(new int[]{94200, 94299});
        zipCodeRange.addRange(new int[]{94226, 94399});
        Collection<int[]> mergedRanges = RangeMergeUtil.getMergedRanges(zipCodeRange);
        List<int[]> expectedRanges = new ArrayList<>();
        expectedRanges.add(new int[]{94133, 94133});
        expectedRanges.add(new int[]{94200, 94399});
        MatcherAssert.assertThat(mergedRanges.size(), Matchers.equalTo(expectedRanges.size()));
        MatcherAssert.assertThat(mergedRanges, Matchers.containsInRelativeOrder(expectedRanges.toArray()));
    }

    @org.junit.jupiter.api.Test
    void getMergedRanges3() {
        zipCodeRange.addRange(new int[]{94130, 94133});
        zipCodeRange.addRange(new int[]{94134, 94150});
        zipCodeRange.addRange(new int[]{94151, 94400});
        zipCodeRange.addRange(new int[]{94402, 94460});
        Collection<int[]> mergedRanges = RangeMergeUtil.getMergedRanges(zipCodeRange);
        List<int[]> expectedRanges = new ArrayList<>();
        expectedRanges.add(new int[]{94130, 94400});
        expectedRanges.add(new int[]{94402, 94460});
        MatcherAssert.assertThat(mergedRanges.size(), Matchers.equalTo(expectedRanges.size()));
        MatcherAssert.assertThat(mergedRanges, Matchers.containsInRelativeOrder(expectedRanges.toArray()));
    }
}