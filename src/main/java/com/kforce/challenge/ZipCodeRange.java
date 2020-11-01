package com.kforce.challenge;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author minhtran
 * This contains collection of zipcode ranges.
 * Item in the collection will be sorted ascending order of lower
 * E.g: [12345, 12367],[12346, 12389],[12347, 12388]
 */
public class ZipCodeRange {

    private static final int RANGE_ARRAY_MAX_LENGTH = 2;

    /**
     * this SortedSet is used to store all ranges for merging
     */
    private SortedSet<int[]> sortedRanges = new TreeSet<>((range1, range2) -> {
        int low = range1[0] - range2[0];
        if (low == 0) {
            return range1[1] - range2[1];
        }
        return low;
    });

    /**
     * This method is to add range to sortedRanges
     *
     * @param range is array of a range with 2 elements e.g: [12345, 67891]
     * @throws IllegalArgumentException when the range is invalid
     */
    public void addRange(int[] range) {
        if (range != null && range.length == RANGE_ARRAY_MAX_LENGTH) {
            Arrays.sort(range);
            sortedRanges.add(range);
        } else {
            throw new IllegalArgumentException("Range values are invalid");
        }
    }

    /**
     * Get stored ranges
     *
     * @return stored ranges in sorted order
     */
    public SortedSet<int[]> getSortedRanges() {
        return sortedRanges;
    }

    /**
     * @param sortedRanges to store
     */
    public void setSortedRanges(SortedSet<int[]> sortedRanges) {
        this.sortedRanges = sortedRanges;
    }
}
