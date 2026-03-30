package com.practice.code.general;

import java.util.PriorityQueue;

public class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>(k);

        // Add initial scores to the heap
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Add the new score to the heap
        minHeap.offer(val);

        // If the heap size exceeds k, remove the smallest element
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // The root of the heap is the kth largest element
        return minHeap.peek();
    }
}
