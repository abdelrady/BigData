package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordCount {
    List<Mapper> mappers;
    List<Reducer> reducers;

    public WordCount(int m, int r){
        this.mappers = new ArrayList<>(m);
        this.reducers = new ArrayList<>(r);

        for (int i = 0;i<m;i++){
            this.mappers.add(new Mapper());
        }

        for (int i = 0;i<r;i++){
            this.reducers.add(new Reducer());
        }
    }

    public int getPartition(String key){
        return (int) key.hashCode() % this.reducers.size();
    }


    public void run() throws IOException {

        System.out.println(String.format("Number of Input-Splits: %d", this.mappers.size()));
        System.out.println(String.format("Number of Reducers: %d", this.reducers.size()));

        for (int i=0;i<this.mappers.size();i++){
            System.out.println(String.format("Mapper %d Input",i));
            List<String> mapperInputs = Files.readAllLines(Paths.get(String.format("src/com/company/files/%d.txt", i)));
            for (String line:mapperInputs) {
                System.out.println(line);
            }
        }

        for (int i = 0;i<this.mappers.size();i++){
            Mapper mapper = this.mappers.get(i);

            List<String> mapperInputs = Files.readAllLines(Paths.get(String.format("src/com/company/files/%d.txt", i)));
            for (String line:mapperInputs) {
                mapper.setList(line);
            }

            System.out.println(String.format("Mapper %d Output", i));
            mapper.print();
        }

        for (int i = 0;i<this.mappers.size();i++) {
            Mapper mapper = this.mappers.get(i);

            for (int j = 0; j < this.reducers.size(); j++) {
                int finalJ = j;
                List<Pair<String, Integer>> reducerInput = mapper.getOutput().stream().filter(x->this.getPartition(x.Key) == finalJ)
                        .collect(Collectors.toList());

                System.out.println(String.format("Pairs send from Mapper %s Reducer %s", i, j));
                Collections.sort(reducerInput);
                reducerInput.forEach(p->System.out.println(String.format("< %s , %s >", p.Key, p.Value)));

                Reducer reducer = this.reducers.get(j);
                reducer.groupPairs(reducerInput);
            }
        }

        for (int j = 0; j < this.reducers.size(); j++) {
            Reducer reducer = this.reducers.get(j);
            System.out.println(String.format("Reducer %d input", j));
            reducer.printInput();
        }

        for (int j = 0; j < this.reducers.size(); j++) {
            Reducer reducer = this.reducers.get(j);
            reducer.reduce();
            System.out.println(String.format("Reducer %d output", j));
            reducer.printOutput();
        }
    }
}
