package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int i=10;
        long a = ~(-1<<i);
        long b = (1<<i) -1;
        long c = -1<<i;
        System.out.println(1<<2);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

    }
}