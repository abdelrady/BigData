package com.company;

import java.io.File;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Main {

    public static void main(String[] args) throws IOException {

        WordLengthAverage wc = new WordLengthAverage(4, 3);

        wc.run();
    }

}
