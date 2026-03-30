package com.interview.code.company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(Enclosed.class)
public class Solution {


//    In a store, a colleague can work for multiple departments. Here are shifts of a colleague in various departments:
//
//    In Bakery department: From 8 to 10
//    In Checkout department: From 10 to 12
//    In Diary department: From 14 to 19
//    Output: 8-12 14-19
//    List<> 8, 10 10, 12

//
//    Given the above split of shifts, provide an API/method to return the different shifts of a colleague for the
//    day after merging contiguous shifts. This will be exposed to the colleague in different UI and help them plan their day accordingly.
//
//   Her/His shift timings in this case are 8 to 12 and 14 to 19.

    public static List<ShiftTime> mergeShifts(List<ShiftTime> employeeShifts) {
        List<ShiftTime> mergedShifts = new ArrayList<>();
        if (employeeShifts.isEmpty()) return mergedShifts;
        List<ShiftTime> sortedShifts = new ArrayList<>(employeeShifts);
        Collections.sort(sortedShifts);

//        // Sort and merge using streams
//        List<ShiftTime> sortedShifts = employeeShifts.stream()
//                .sorted(Comparator.comparingInt(shift -> shift.start))
//                .toList();
        
        for(ShiftTime shift: sortedShifts) {
            if ( !mergedShifts.isEmpty() && mergedShifts.get(mergedShifts.size()-1).end >= shift.start) {
                mergedShifts.get(mergedShifts.size()-1).setEnd(shift.end);
            } else {
                mergedShifts.add(new ShiftTime(shift.start, shift.end));
            }
        }
        return mergedShifts;
    }

    // Main method to run and test the code
    public static void main(String[] args) {
        List<ShiftTime> employeeShifts = List.of(new ShiftTime(8, 14), new ShiftTime(12, 19), new ShiftTime(18, 23) );
        List<ShiftTime>  resultShifts = mergeShifts(employeeShifts);
        for (ShiftTime shift: resultShifts){
            System.out.println(shift.start + " " + shift.end);
        }
    }

    public static class SolutionTest {

        @Test
        public void testWhenMergeShifts(){
            ShiftTime shift1 = new ShiftTime(8, 10);
            ShiftTime shift2 = new ShiftTime(12, 14);
            ShiftTime shift3 = new ShiftTime(14, 19);
            List<Solution.ShiftTime> expected = List.of(shift1, shift2, shift3);

            List<Solution.ShiftTime>  actual = mergeShifts(List.of(shift1, shift2, shift3));

            Assert.assertEquals(actual.size(), 2);
            Assert.assertEquals(actual.get(0).start, shift1.start);
            Assert.assertEquals(actual.get(0).end , shift1.end);

            Assert.assertEquals(actual.get(1).start,shift2.start);
            Assert.assertEquals(actual.get(1).end, shift3.end);
        }

        @Test
        public void testWhenNoMergeShifts(){
            ShiftTime shift1 = new ShiftTime(8, 10);
            ShiftTime shift2 = new ShiftTime(14, 19);
            List<Solution.ShiftTime> expected = List.of(shift1, shift2, shift2);

            List<Solution.ShiftTime>  actual = mergeShifts(List.of(shift1, shift2, shift2));
            Assert.assertEquals(actual.size(), 2);
            Assert.assertEquals(actual.get(0).start, shift1.start);
            Assert.assertEquals(actual.get(0).end , shift1.end);

            Assert.assertEquals(actual.get(1).start,shift2.start);
            Assert.assertEquals(actual.get(1).end, shift2.end);
        }
    }

    static class ShiftTime implements Comparable<ShiftTime> {
        private int start;
        private int end;

        public ShiftTime() {
        }

        ShiftTime(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
        @Override
        public int compareTo(ShiftTime otherShift) {
            return Integer.compare(this.start, otherShift.start);
        }

    }
}


