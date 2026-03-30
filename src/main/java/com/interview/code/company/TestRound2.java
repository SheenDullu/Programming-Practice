package com.interview.code.company;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Rate Limiter
 * - Threshold per user/ipaddress para: userId,
 * - Allowed or not
 **/

@RunWith(Enclosed.class)
public class TestRound2 {

    private int thresholdPerMin;
    Map<Integer, Deque<LocalDateTime>> mapUserActivity;

    public TestRound2(int thresholdPerMin) {
        this.thresholdPerMin = thresholdPerMin;
        mapUserActivity = new HashMap<>();
    }

    // user - last 60 secs: 2
    public boolean rateLimiter(int userId) {
        LocalDateTime curr = LocalDateTime.now();

        // Get or create the user's activity deque
        mapUserActivity.putIfAbsent(userId, new LinkedList<>());
        Deque<LocalDateTime> queue = mapUserActivity.get(userId);

        // Remove timestamps older than 60 seconds
        while (!queue.isEmpty() && queue.peekFirst().isBefore(curr.minusSeconds(60))) {
            queue.pollFirst();
        }

        // Check if user is within the threshold
        if (queue.size() < thresholdPerMin) {
            queue.addLast(curr);
            return true;
        }
        return false; // User exceeded the rate limit
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a TestRound instance with a threshold of 3 requests per minute
        TestRound2 testRound = new TestRound2(3);

        int userId = 1;

        // Simulate user activity
        System.out.println("First request: " + testRound.rateLimiter(userId)); // Should be true
        Thread.sleep(1000); // Wait 1 second
        System.out.println("Second request: " + testRound.rateLimiter(userId)); // Should be true
        Thread.sleep(1000); // Wait 1 second
        System.out.println("Third request: " + testRound.rateLimiter(userId)); // Should be true
        System.out.println("Fourth request: " + testRound.rateLimiter(userId)); // Should be false (exceeds limit)

        // Wait 60 seconds for the oldest request to expire
        Thread.sleep(60000);
        System.out.println("Fifth request after 60 seconds: " + testRound.rateLimiter(userId)); // Should be true
    }
    public static class TestRoundTest {

        @Test
        public void testRateLimiter() throws InterruptedException {
            TestRound2 testRound = new TestRound2(3); // Threshold of 3 requests per minute
            int userId = 1;

            assertTrue(testRound.rateLimiter(userId)); // First request
            assertTrue(testRound.rateLimiter(userId)); // Second request
            assertTrue(testRound.rateLimiter(userId)); // Third request
            assertFalse(testRound.rateLimiter(userId)); // Fourth request exceeds limit

            Thread.sleep(60000); // Wait 60 seconds
            assertTrue(testRound.rateLimiter(userId)); // Should be allowed after 60 seconds
        }
    }
}
