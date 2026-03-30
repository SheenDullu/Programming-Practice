package com.practice.code.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        if(strs.length == 1) {
            return List.of(List.of(strs[0]));
        }

        Map<String, List<String>> anagrams = new HashMap<>();
        for (String s: strs) {
            // create count array
            int[] count = new int[26];
            for(char c: s.toCharArray()) {
                count[c-'a'] += 1;
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            anagrams.computeIfAbsent(key, k->new ArrayList<>()).add(s);
        }

        return new ArrayList<>(anagrams.values());
    }

    public static void main(String[] args) {
        GroupAnagrams grouper = new GroupAnagrams();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> groupedAnagrams = grouper.groupAnagrams(strs);

        System.out.println(groupedAnagrams);
    }
}
