package com.company;

import java.io.File;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Main {

    public static void main(String[] args) throws IOException {

        Mapper m = new Mapper();

        Files.readAllLines(Paths.get("src/com/company/testDataForW1D1.txt")).forEach(l->m.setList(l));

        m.sort();

        System.out.println("Mapper Output\n");
        m.print();

        Reducer r = new Reducer();

        r.groupPairs(m.getOutput());

        System.out.println("Reducer Input\n");
        r.printInput();

        r.reduce();

        System.out.println("Reducer Output\n");
        r.printOutput();
    }

}
