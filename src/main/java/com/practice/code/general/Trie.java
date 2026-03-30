package com.practice.code.general;

public class Trie {
    private final TrieNode root;

    // Initializes the trie object
    public Trie() {
        root = new TrieNode();
    }

    // Inserts the string word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Calculate index for the character
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true; // Mark the end of the word
    }

    // Returns true if the string word is in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false; // Word not found
            }
            node = node.children[index];
        }
        return node.isEndOfWord; // Check if it's a complete word
    }

    // Returns true if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return false; // Prefix not found
            }
            node = node.children[index];
        }
        return true; // Prefix exists
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: false
        System.out.println(trie.startsWith("app")); // Output: true
        trie.insert("app");
        System.out.println(trie.search("app"));     // Output: true
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Array for 'a' to 'z'
    boolean isEndOfWord = false; // Flag to indicate the end of a word
}

