package com.interview.code.company;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestRoundJP {

//  basket<String> .
//  unsorted lists

    public List<Integer> countFruits(List<Integer> nums1, List<Integer> nums2) {

//        Set<Integer> seen = nums1.stream().collect(Collectors.toSet());
//        return nums2.stream().filter(seen::contains).collect(Collectors.toList());
        Set<Integer> seen = nums1.stream().collect(Collectors.toSet());
        Set<Integer> finalSeen = seen;
        return nums2.stream().filter(num -> finalSeen.contains(num)).collect(Collectors.toList());
    }

    // compare the data from a db against the file system in GB
    //
}
