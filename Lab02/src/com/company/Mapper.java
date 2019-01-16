package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Mapper {
    Map<String, Tuple<Integer, Integer>> words= new HashMap<>();

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
            String firstChar = new String(new char[]{outputWord.charAt(0)});
            if(this.words.containsKey(firstChar))
            {
                Tuple<Integer, Integer> existingPair = this.words.get(firstChar);
                existingPair.x = existingPair.x + outputWord.length();
                existingPair.y += 1;
                this.words.put(firstChar, existingPair);
            }
            else this.words.put(firstChar, new Tuple<>(outputWord.length(), 1));
        }
    }

    public void sort(){
        //Collections.sort(words);
    }

    public void print()
    {
        for (String key : words.keySet()) {
            System.out.println(String.format("< %s , [%d , %d ] >", key, words.get(key).x, words.get(key).y));
        }
    }

    List<Pair<String, Tuple<Integer, Integer>>> getOutput(){

        return words.entrySet().stream().map(x->new Pair<>(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }
}
