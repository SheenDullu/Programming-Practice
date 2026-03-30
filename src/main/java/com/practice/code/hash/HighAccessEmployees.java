package com.practice.code.hash;

import java.util.*;

public class HighAccessEmployees {

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        HashMap<String, List<Integer>> accessMap = new HashMap<>();
        // initialize map
        for(List<String> accessTime: access_times){
            String employee = accessTime.get(0);
            String time = accessTime.get(1);
            int timeInMinutes = (Integer.parseInt(time.substring(0,2)) * 60) + Integer.parseInt(time.substring(2));
            accessMap.computeIfAbsent(employee, k -> new ArrayList<>()).add(timeInMinutes);
        }
        List<String> highAccessEmployees = new ArrayList<>();

        for(Map.Entry<String, List<Integer>> entry: accessMap.entrySet()){
            List<Integer> times = entry.getValue();
            Collections.sort(times);
            for(int i = 0; i < times.size() - 2; i++) {
                if(times.get(i + 2) - times.get(i) < 60 ) {
                    highAccessEmployees.add(entry.getKey());
                    break;
                }
            }
        }
        return highAccessEmployees;
    }

    public static void main(String[] args) {
        HighAccessEmployees solution = new HighAccessEmployees();

        List<List<String>> access_times = List.of(
                List.of("Alice", "0800"), List.of("Alice", "0820"), List.of("Alice", "0830"),
        List.of("Bob", "2350"), List.of("Bob", "0005"), List.of("Bob", "0030"),
                List.of("Charlie", "0915"), List.of("Charlie", "0930"), List.of("Charlie", "0945"),
                List.of("Alice", "0845")
        );

        List<String> result = solution.findHighAccessEmployees(access_times);
        System.out.println(result); // Output could be ["Alice", "Charlie"]
    }
}
