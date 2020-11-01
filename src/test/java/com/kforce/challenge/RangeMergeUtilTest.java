package com.kforce.challenge;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class RangeMergeUtilTest {

    private final ZipCodeRange zipCodeRange = new ZipCodeRange();

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