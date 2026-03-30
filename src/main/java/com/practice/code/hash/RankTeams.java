package com.practice.code.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeams {
    public String rankTeams(String[] votes) {
        if(votes.length == 1) {
            return votes[0];
        }
        // voteList => team, no of votes in each position
        Map<Character, int[]> voteCount = new HashMap<>();
        int totalTeams = votes[0].length();

        // Initializing votes
        for(char team: votes[0].toCharArray()) {
            voteCount.put(team, new int[totalTeams]);
        }

        for(String vote: votes) {
            for (int i = 0; i < vote.length(); i++){
                char team = vote.charAt(i);
                voteCount.get(team)[i] += 1;
            }
        }

        List<Character> result = new ArrayList<>(voteCount.keySet());
        result.sort((a,b) -> {
            for(int i = 0; i < totalTeams; i++) {
                if(voteCount.get(a)[i] != voteCount.get(b)[i]) {
                    return voteCount.get(b)[i] - voteCount.get(a)[i];
                }
            }
            return a - b;
        });
        StringBuilder sb = new StringBuilder();
        for(char team: result) {
            sb.append(team);
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        RankTeams ranker = new RankTeams();
        String[] votes = {"ABC", "ACB", "ABC", "ACB", "ACB"};
        System.out.println(ranker.rankTeams(votes)); // Expected Output: "ACB"
    }
}
