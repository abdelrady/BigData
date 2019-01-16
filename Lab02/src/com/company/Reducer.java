package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Reducer {
    List<Pair<String, Tuple<Integer, Integer>>> pairs = new ArrayList<>();
    List<Pair<String, List<Tuple<Integer, Integer>>>> groupedPairs = new ArrayList<>();
    List<Pair<String, Double>> pairsResult = new ArrayList<>();

    void setInput(List<Pair<String, Tuple<Integer, Integer>>> pairs){
        this.pairs = pairs;
    }

    void groupPairs(){
        for (Pair<String, Tuple<Integer, Integer>> pair: pairs) {
            int pairIndex = this.groupedPairs.stream().map(x->x.Key).collect(Collectors.toList()).indexOf(pair.Key);
            if(pairIndex >= 0){
                this.groupedPairs.get(pairIndex).Value.add(pair.Value);
            }
            else{
                List<Tuple<Integer, Integer>> groupValue = new ArrayList<>();
                groupValue.add(pair.Value);
                this.groupedPairs.add(new Pair<>(pair.Key, groupValue));
            }
        }
    }

    void reduce(){
        for (Pair<String, List<Tuple<Integer, Integer>>> pair:this.groupedPairs) {
            Tuple<Integer, Integer> sumValues = pair.Value.stream()
                    .reduce((a, b)->new Tuple<>(a.x+b.x, a.y + b.y)).get();
            this.pairsResult.add(new Pair<>(pair.Key, ((double)sumValues.x / sumValues.y)));
        }
    }

    void printOutput(){
        Collections.sort(this.pairsResult);
        for (Pair<String, Double> pair:this.pairsResult) {
            System.out.println(String.format("< %s , %s >", pair.Key, pair.Value));
        }
    }

}
