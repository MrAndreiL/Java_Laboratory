package com.company;
/*
@author Lungu Andrei-Sebastian 2E3
 */

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // 1. Read input and usage check.
        if (args.length < 3) {
            System.err.println("Usage: <n = posititve_integer> <p = positive integer> <c1...cm = ci in {a-z,A-Z}>");
            System.exit(1);
        }

        // 1.1 Read set of integers.
        int n = Integer.parseInt(args[0]);
        if (n < 0) {
            System.err.println("Usage: <n = positive_integer>");
            System.exit(1);
        }
        long begin = 0;
        if (n >= 30_000) {
            begin = System.nanoTime();
        }

        int p = Integer.parseInt(args[1]);
        if (p < 0) {
            System.err.println("Usage: <p = positive_integer>");
            System.exit(1);
        }

        // 1.2 Read and append alphabet character.
        int alphaLength = args.length - 2;
        StringBuilder alphabet = new StringBuilder();
        for (int i = 0; i < alphaLength; i++) {
            char ch = args[i + 2].charAt(0);
            if (!('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z')) {
                System.out.println("Usage: <c1...cm with ci in {a-z,A-Z}>");
                System.exit(1);
            }
            alphabet.append(ch);
        }

        // 2. Create an array of dynamic-sized words.
        StringBuilder[] words = new StringBuilder[n];
        for (int w = 0; w < n; w++) {
            words[w] = generateWord(alphabet, p);
        }

        // 2.1 Display the generated array.
        for (int word = 0; word < n; word++) {
            System.out.print(words[word] + " ");
        }
        System.out.println();


        // 3. Determine and display neighbors.
        boolean[][] relation = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                relation[i][j] = false;
            }
        }

        // 3.1 Find out which words are neighbors.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (areNeighbors(words[i], words[j])) {
                    relation[i][j] = true;
                    relation[j][i] = true;
                }
            }
        }

        // 3.2 Build a data structure which contains the neighbors of each word.
        StringBuilder[][] neighbors = new StringBuilder[n][n];
        for (int i = 0; i < n; i++) {
            int k = 0;
            for (int j = 0; j < n; j++) {
                if (relation[i][j])
                    neighbors[i][k++] = words[j];
            }
        }

        // 3.3 Print the resulting structure.
        if (n < 30_000) {
            for (int i = 0; i < n; i++) {
                int length = getNumberOfNeighbors(i, relation);
                for (int j = 0; j < length; j++) {
                    System.out.print(neighbors[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            long end = System.nanoTime();
            System.out.println("Elapsed time: " + (end - begin));
        }
    }

    // generateWord builds and returns a randomly generated word.
    private static StringBuilder generateWord(StringBuilder alphabet, int length) {
        Random rand = new Random();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int r = rand.nextInt(alphabet.length()); // [0..dim)
            word.append(alphabet.charAt(r));
        }
        return word;
    }

    // areNeighbors iterates over each word and returns true if at least one letter is
    // common between the two.
    private static boolean areNeighbors(StringBuilder word1, StringBuilder word2) {
        for (int i = 0; i < word1.length(); i++) {
            char letter = word1.charAt(i);
            for (int j = 0; j < word2.length(); j++) {
                if (letter == word2.charAt(j))
                    return true;
            }
        }
        return false;
    }

    // getNumberOfNeighbors returns the number of neighbors for each word given as index.
    private static int getNumberOfNeighbors(int wordIndex, boolean[][] relations) {
        int nrNeigh = 0;
        for (int i = 0; i < relations[wordIndex].length; i++) {
                if (relations[wordIndex][i])
                    nrNeigh++;
        }
        return nrNeigh;
    }
}
