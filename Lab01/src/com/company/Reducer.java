package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Reducer {
    List<Pair<String, List<Integer>>> GroupByPair = new ArrayList<>();
    List<Pair<String, Integer>> GroupByPairSum = new ArrayList<>();

    void groupPairs(List<Pair<String, Integer>> pairs){
        for (Pair<String, Integer> pair: pairs) {
            int pairIndex = this.GroupByPair.stream().map(x->x.Key).collect(Collectors.toList()).indexOf(pair.Key);
            if(pairIndex >= 0){
                this.GroupByPair.get(pairIndex).Value.add(pair.Value);
            }
            else{
                List<Integer> groupValue = new ArrayList<>();
                groupValue.add(pair.Value);
                this.GroupByPair.add(new Pair<>(pair.Key, groupValue));
            }
        }
    }

    void printInput(){
        for (Pair<String, List<Integer>> pair:this.GroupByPair) {
            System.out.println(String.format("< %s , %s >", pair.Key, pair.Value));
        }
    }

    void reduce(){
        for (Pair<String, List<Integer>> pair:this.GroupByPair) {
            this.GroupByPairSum.add(new Pair<String, Integer>(pair.Key, pair.Value.stream().reduce(0, (a, b)->a+b)));
        }
    }

    void printOutput(){
        for (Pair<String, Integer> pair:this.GroupByPairSum) {
            System.out.println(String.format("< %s , %s >", pair.Key, pair.Value));
        }
    }

}
