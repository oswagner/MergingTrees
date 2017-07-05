package com.pucrs.finalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Wagner Santos.
 */

public class Main {
    public static void main(String[] args) {
        //TODO: Receive filename by args
        Path filePath = Paths.get("res/test_case/input/F_10");
        try (FileReader fileReader = new FileReader(filePath.toFile())) {
            BufferedReader buffer = new BufferedReader(fileReader);
            MergingTrees mt = new MergingTrees(buffer);
            mt.mergeTree();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
