package com.company;

import java.io.File;
import java.nio.file.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class Main {

    public static void main(String[] args) throws IOException {

        WordCount wc = new WordCount(3, 4);

        wc.run();
    }

}
