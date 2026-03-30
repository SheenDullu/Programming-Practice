package com.practice.code.array;

import java.util.Arrays;

public class MaximumUnitsOnATruck {

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        Arrays.stream(boxTypes).forEach(arr -> System.out.println(Arrays.toString(arr)));
        int unitCount = 0;
        int maxUnits = 0;
        for(int[] box: boxTypes) {
            int boxCount = Math.min(box[0], truckSize);
            unitCount += (boxCount * box[1]);
            truckSize -= boxCount;
            if (truckSize == 0) {
                break;
            }
        }
        return unitCount;
    }

    public static void main(String[] args) {
        int[][] boxTypes = {{5,10},{2,5},{4,7},{3,9}};
        System.out.println(maximumUnits(boxTypes, 10));
    }
}
