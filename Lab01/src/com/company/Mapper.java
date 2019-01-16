package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    List<Pair<String, Integer>> words = new ArrayList<>();

    // Scale out instead of scale up to solve problems with huge amount of data

    public void setList(String content){

        for (String word : content.split("[\\s-]"))
        {
            if(word.isEmpty() || word.contains("_") || (word.contains(".") && !word.endsWith(".")) || word.matches(".*\\d+.*"))
                continue;

            String outputWord = word
                    .replace("\"", "")
                    .replace(".", "")
                    .replace(",", "")
                    .toLowerCase();
            //if(!words.stream().map(w->w.Key).collect(Collectors.toList()).contains(outputWord))
            words.add(new Pair<>(outputWord, 1));
        }
    }

    public void sort(){
        Collections.sort(words);
    }

    public void print()
    {
        for (Pair<String, Integer> p:words) {
            System.out.println(String.format("< %s , %d >", p.Key, p.Value));
        }
    }

    List<Pair<String, Integer>> getOutput(){
        return words;
    }
}
