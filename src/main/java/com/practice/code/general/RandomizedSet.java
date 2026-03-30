package com.practice.code.general;

import java.util.*;

public class RandomizedSet {
    private final Map<Integer, Integer> valueToIndex; // Maps value to its index in the list
    private final List<Integer> values; // Stores all values
    private final Random random;

    // Initializes the RandomizedSet object
    public RandomizedSet() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
        random = new Random();
    }

    // Inserts an item into the set if not present
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false; // Already exists
        }
        valueToIndex.put(val, values.size());
        values.add(val);
        return true;
    }

    // Removes an item from the set if present
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false; // Does not exist
        }
        // Swap the element with the last one for O(1) removal
        int index = valueToIndex.get(val);
        int lastValue = values.get(values.size() - 1);
        values.set(index, lastValue);
        valueToIndex.put(lastValue, index);

        // Remove the last element
        values.remove(values.size() - 1);
        valueToIndex.remove(val);
        return true;
    }

    // Returns a random element from the set
    public int getRandom() {
        int randomIndex = random.nextInt(values.size());
        return values.get(randomIndex);
    }

    // Main method for testing
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1)); // Output: true
        System.out.println(randomizedSet.insert(2)); // Output: true
        System.out.println(randomizedSet.insert(2)); // Output: false
        System.out.println(randomizedSet.getRandom()); // Output: 1 or 2
        System.out.println(randomizedSet.remove(1)); // Output: true
        System.out.println(randomizedSet.getRandom()); // Output: 2
    }
}
