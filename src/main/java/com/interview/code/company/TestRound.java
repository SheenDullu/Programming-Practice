package com.interview.code.company;

// JPMC Round 1
// nums    remove duplicates
// [1,1,4,2,7,5,1,2] => [1,4,2,7,5]

import java.util.*;
import java.util.stream.Collectors;

public class TestRound {
    public static List<Integer> removeDuplicates(List<Integer> nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num: nums) {
            if (!seen.contains(num)) {
                seen.add(num);
            }
        }
        return seen.stream().collect(Collectors.toList());
    }

    public static Map<Integer, Integer> countElements(List<Integer> nums) {
        Map<Integer, Integer> count = new HashMap<>();

//       [1,1,4,2,7,5,1,2]
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> removeDuplicates = removeDuplicates(List.of(1,4,2,7,5,1,2));
        System.out.println(removeDuplicates);
        System.out.println(countElements(List.of(1,4,2,7,5,1,2)));

    }
}


