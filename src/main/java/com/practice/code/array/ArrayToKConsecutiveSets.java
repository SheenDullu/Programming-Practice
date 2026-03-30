package com.practice.code.array;

import java.util.Map;
import java.util.TreeMap;

public class ArrayToKConsecutiveSets {
    public static boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else map.put(nums[i], 1);
        }
        while (!map.isEmpty()) {
            Map.Entry<Integer, Integer> m = map.firstEntry();
            int key = m.getKey();
            int value = m.getValue();
            while (value > 0) {
                for (int i = 1; i < k; i++) {
                    if (map.containsKey(key + i) && map.get(key + i) > 0) {
                        map.put(key + i, map.get(key + i) - 1);
                    } else return false;
                }
                value--;
            }
            if (value == 0) map.remove(key);
        }
        return map.size() == 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,2,9,7,2};
        System.out.println(isPossibleDivide(nums, 2));
    }
}
