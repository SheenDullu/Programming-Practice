package com.practice.code.general;

import java.util.PriorityQueue;

public class SeatManager {
    PriorityQueue<Integer> seats;
    int marker;
    public SeatManager(int n) {
        seats = new PriorityQueue<>();
        marker = 1;
    }

    public int reserve() {
        if(!seats.isEmpty()) {
            return seats.poll();
        }

        int reservingSeat = marker;
        marker++;
        return reservingSeat;

    }

    public void unreserve(int seatNumber) {
        seats.offer(seatNumber);
    }

    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5);
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        seatManager.unreserve(2);
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        seatManager.unreserve(5);

    }
}
