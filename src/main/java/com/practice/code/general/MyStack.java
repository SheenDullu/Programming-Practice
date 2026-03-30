package com.practice.code.general;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    // Initialize the stack
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // Push element x to the top of the stack
    public void push(int x) {
        queue2.offer(x); // Push to the secondary queue
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll()); // Move all elements from queue1 to queue2
        }
        // Swap queue1 and queue2
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // Removes the element on the top of the stack and returns it
    public int pop() {
        return queue1.poll(); // The front of queue1 is the top of the stack
    }

    // Returns the element on the top of the stack
    public int top() {
        return queue1.peek(); // The front of queue1 is the top of the stack
    }

    // Returns true if the stack is empty, false otherwise
    public boolean empty() {
        return queue1.isEmpty();
    }

    // Main method for testing
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());    // Output: 2
        System.out.println(stack.pop());    // Output: 2
        System.out.println(stack.empty()); // Output: false
    }
}
