package com.pucrs.finalProject;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Wagner Santos.
 */

public class MergingTrees {


    private BufferedReader buffer;

    public MergingTrees(BufferedReader buffer) {
        this.buffer = buffer;
    }

    public void mergeTree() throws IOException {
        // build left tree only with middle nodes
        String leftTreeline = buffer.readLine();
        int leftTreeSize = getLeftTreeSize(leftTreeline);
        int[] leftTreeMiddleNodes = getLeftTreeMiddleNodes(leftTreeSize);


        // build right tree only with middle nodes
        String rightTreeLine = buffer.readLine();
        int rightTreeSize = getRightTreeSize(rightTreeLine);
        int[] rightTreeMiddleNodes = getRightTreeMiddleNodes(rightTreeSize);


        // Analyze left tree
        LeftTreeAnalysis leftTreeAnalysis = new LeftTreeAnalysis(leftTreeSize, leftTreeMiddleNodes).analyze();
        int leftSDC = leftTreeAnalysis.getSdc();
        int lengthLeftTree = leftTreeAnalysis.getLengthTree();


        // Analyze right tree
        RightTreeAnalysis rightTreeAnalysis = new RightTreeAnalysis(rightTreeSize, rightTreeMiddleNodes).analize();
        int lengthRightTree = rightTreeAnalysis.getLengthTree();
        int rightSDC = rightTreeAnalysis.getSdc();


        // Calculate overlapping vertex according suggested solution
        int minimumVertex = CalculateMinimumVertex(leftTreeSize, rightTreeSize, lengthLeftTree, lengthRightTree, leftSDC, rightSDC);

        System.out.println("Minimum Vertex = " + minimumVertex);
    }


    private int CalculateMinimumVertex(int leftTreeSize, int rightTreeSize, int lengthLeftTree, int lengthRightTree, int leftSDC, int rightSDC) {
        int overlappingVertex = Math.max(Math.min(leftSDC, lengthRightTree), Math.min(rightSDC, lengthLeftTree));
        return leftTreeSize + rightTreeSize - overlappingVertex - 1;
    }


    private int[] getRightTreeMiddleNodes(int rightTreeSize) throws IOException {
        int[] middleNodes = new int[rightTreeSize + 1];
        for (int i = 0; i < rightTreeSize; i++) {
            String line = buffer.readLine();
            int node = Integer.parseInt(line.split(" ")[0]);
            int middleNode = Integer.parseInt(line.split(" ")[1]);
            middleNodes[node] = middleNode;
        }
        return middleNodes;
    }

    private int[] getLeftTreeMiddleNodes(int leftTreeSize) throws IOException {
        int[] middleNodes = new int[leftTreeSize + 1];
        for (int i = 0; i < leftTreeSize; i++) {
            String line = buffer.readLine();
            int node = Integer.parseInt(line.split(" ")[0]);
            int middleNode = Integer.parseInt(line.split(" ")[2]);
            middleNodes[node] = middleNode;
        }
        return middleNodes;
    }

    private int getRightTreeSize(String rightTreeSize) {
        return Integer.parseInt(rightTreeSize.toString());
    }

    private int getLeftTreeSize(String leftTreeSize) {
        return Integer.parseInt(leftTreeSize.toString());
    }

    private class LeftTreeAnalysis {

        private int treeSize;
        private int[] treeMiddleNodes;
        private int lengthTree;
        private int sdc;

        public LeftTreeAnalysis(int leftTreeSize, int[] leftTreeMiddleNodes) {
            treeSize = leftTreeSize;
            treeMiddleNodes = leftTreeMiddleNodes;
        }

        public int getLengthTree() {
            return lengthTree;
        }

        public int getSdc() {
            return sdc;
        }

        public LeftTreeAnalysis analyze() {
            int[] visitedNodes = new int[treeSize + 1];
            lengthTree = 0;
            sdc = 0;
            for (int i = 1; i <= treeSize; i++) {
                if (visitedNodes[i] == 0) {
                    int currentLength = 0;
                    int currentNode = i;
                    visitedNodes[currentNode] = 1;
                    while (treeMiddleNodes[currentNode] != 0) {

                        currentNode = treeMiddleNodes[currentNode];
                        currentLength++;
                        visitedNodes[currentNode] = 1;
                    }
                    lengthTree = Math.max(lengthTree, currentLength);
                    if (i == 1) {
                        sdc = currentLength;
                    }
                }
            }
            return this;
        }
    }

    private class RightTreeAnalysis {
        private int treeSize;
        private int[] treeMiddleNodes;
        private int lengthTree;
        private int sdc;

        public RightTreeAnalysis(int rightTreeSize, int[] rightTreeMiddleNodes) {
            treeSize = rightTreeSize;
            treeMiddleNodes = rightTreeMiddleNodes;
        }

        public int getLengthTree() {
            return lengthTree;
        }

        public int getSdc() {
            return sdc;
        }

        public RightTreeAnalysis analize() {
            int[] visitedNodes = new int[treeSize + 1];
            lengthTree = 0;
            sdc = 0;
            for (int i = 1; i <= treeSize; i++) {
                if (visitedNodes[i] == 0) {
                    int currentLength = 0;
                    int currentNode = i;
                    visitedNodes[currentNode] = 1;
                    while (treeMiddleNodes[currentNode] != 0) {
                        currentNode = treeMiddleNodes[currentNode];
                        currentLength++;
                        visitedNodes[currentNode] = 1;
                    }
                    lengthTree = Math.max(lengthTree, currentLength);
                    if (i == 1) {
                        sdc = currentLength;
                    }
                }
            }
            return this;
        }
    }
}