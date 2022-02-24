package com.company;

public class Exercise_1 {
    public void Solve() {
        System.out.println("Hello World");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);

        n = n * 3;
        n = n + 0b10101;
        n = n + 0xFF;
        n = n * 6;

        do {
            n = sum(n);
        } while (n > 9);

        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    private int sum (int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + (n % 10);
            n /= 10;
        }
        return sum;
    }
}
