package com.practice.code.string;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA
 * without changing the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a
 * predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 * **/

public class WordChain {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Map<String, Integer> dp = new HashMap<>();
        int maxChainLength = 1;
        for(String word: words) {
            int currLength = 1;
            for(int i=0; i < word.length(); i++) {
                String predecessor = word.substring(0,i) + word.substring(i+1);
                if(dp.containsKey(predecessor)) {
                    currLength = Math.max(currLength, dp.get(predecessor) + 1);
                }
            }
            dp.put(word, currLength);
            maxChainLength = Math.max(maxChainLength, currLength);
        }
        return maxChainLength;
    }

    public static void main(String[] args) {
        WordChain wc = new WordChain();

        String[] words1 = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(wc.longestStrChain(words1)); // Output: 4 ("a" -> "ba" -> "bda" -> "bdca")

        String[] words2 = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println(wc.longestStrChain(words2)); // Output: 5 ("xb" -> "xbc" -> "cxbc" -> "pcxbc" -> "pcxbcf")

        String[] words3 = {"abcd", "dbqca"};
        System.out.println(wc.longestStrChain(words3)); // Output: 1
    }
}
