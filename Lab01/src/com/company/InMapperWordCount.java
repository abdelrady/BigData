package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMapperWordCount {
    int m, r;
    List<Mapper> mappers;
    List<Reducer> reducers;

    public InMapperWordCount(int m, int r){
        this.r = r;
        this.m = m;
        this.mappers = new ArrayList<>(m);
        this.reducers = new ArrayList<>(r);
    }

    public int getPartition(String key){
        return (int) key.hashCode() % r;
    }


    public void run() throws IOException {

        List<String> mappersInputs = new ArrayList<String>(){
            {
                add("\"cat bat\" mat-pat mum.edu sat.\n" +
                        "fat 'rat eat cat' mum_cs mat");
                add("bat-hat mat pat \"oat\n" +
                        "hat rat mum_cs eat oat-pat");
                add("zat lat-cat pat jat.\n" +
                        "hat rat. kat sat wat");
            }
        };

        for (int i = 0;i<m;i++){
            Mapper mapper = this.mappers.get(i);
            for (String line:mappersInputs.get(i).split("\n")) {
                mapper.setList(line);
            }

            mapper.sort();

            System.out.println(String.format("Mapper %d Output", i));
            mapper.print();
        }

        for (int i = 0;i<m;i++) {
            Mapper mapper = this.mappers.get(i);

            for (int j = 0; j < r; j++) {
                int finalJ = j;
                List<Pair<String, Integer>> reducerInput = mapper.getOutput().stream().filter(x->this.getPartition(x.Key) == finalJ)
                        .collect(Collectors.toList());

                System.out.println(String.format("Pairs send from Mapper %s Reducer %s", i, j));
                reducerInput.forEach(p->System.out.println(String.format("< %s , %s >", p.Key, p.Value)));
            }
        }
        /*Reducer r = new Reducer();

        r.groupPairs(m.getOutput());

        System.out.println("Reducer Input\n");
        r.printInput();

        r.reduce();

        System.out.println("Reducer Output\n");
        r.printOutput();*/
    }

}
