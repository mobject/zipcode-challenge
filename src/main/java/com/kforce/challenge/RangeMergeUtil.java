package com.kforce.challenge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.function.BiConsumer;

/**
 * this is Util class to merge ranges
 *
 * @author minhtran
 */
public class RangeMergeUtil {

    private RangeMergeUtil(){}

    /**
     *
     * @param zipCodeRange is the object which store ranges of zipcode
     * @return collection of merged ranges
     */
    public static Collection<int[]> getMergedRanges(ZipCodeRange zipCodeRange) {
        BiConsumer<ArrayList<int[]>, int[]> arrayListBiConsumer = (mergedList, range) -> {
            if (mergedList.isEmpty())
                mergedList.add(range);
            else {
                int[] lastArray = mergedList.get(mergedList.size() - 1);
                int toA = lastArray[1];
                int fromB = range[0];
                int toB = range[1];
                if (toA < fromB - 1) {
                    mergedList.add(range);
                } else if (toA >= fromB - 1) {
                    lastArray[1] = toB;
                }
            }
        };
        BiConsumer<ArrayList<int[]>, ArrayList<int[]>> biConsumer = (list1, list2) -> {};

        SortedSet<int[]> ranges = zipCodeRange.getSortedRanges();
        return ranges.stream().collect(ArrayList::new, arrayListBiConsumer, biConsumer);
    }
}
