package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Mapper {
    Map<String, Integer> words = new HashMap<>();

    // Scale out instead of scale up to solve problems with huge amount of data

    public void setList(String content){

        for (String word : content.split("[\\s-]"))
        {
            if(word.isEmpty() || word.contains("_") || (word.contains(".") && !word.endsWith(".")) || word.matches(".*\\d+.*"))
                continue;

            String outputWord = word
                    .replace("\"", "")
                    .replace("'", "")
                    .replace(".", "")
                    .replace(",", "")
                    .toLowerCase();
            if(words.keySet().contains(outputWord)){
                this.words.put(outputWord, this.words.get(outputWord) + 1);
            }else{
                this.words.put(outputWord, 1);
            }
        }
    }

    public void sort(){
        //Collections.sort(words);
    }

    public void print()
    {
        for (Map.Entry<String, Integer> p:words.entrySet()) {
            System.out.println(String.format("< %s , %d >", p.getKey(), p.getValue()));
        }
    }

    List<Pair<String, Integer>> getOutput(){
        return words.entrySet().stream()
                .map(p->new Pair<String, Integer>(p.getKey(), p.getValue()))
                .collect(Collectors.toList());
    }
}
