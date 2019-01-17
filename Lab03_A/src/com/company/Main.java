package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        InMapperWordCount wc = new InMapperWordCount(3, 4);

        wc.run();
    }

}
