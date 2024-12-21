package org.example.thread;

import java.util.concurrent.Semaphore;

public class PrintAbcSemaphore {
    Semaphore s1 = new Semaphore(1); // 初始许可数为0
    Semaphore s2 = new Semaphore(0); // 初始许可数为0
    Semaphore s3 = new Semaphore(0); // 初始许可数为1

    public void printA()  {
        for (int i = 0; i < 10; i++) {
            try {
                s1.acquire(); // 获取许可，打印A
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("A,");
            s2.release(); // 释放许可给B
        }
    }

    public void printB() {
        for (int i = 0; i < 10; i++) {
            try {
                s2.acquire(); // 获取许可，打印B
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("B,");
            s3.release(); // 释放许可给C
        }
    }

    public void printC()  {
        for (int i = 0; i < 10; i++) {
            try {
                s3.acquire(); // 获取许可，打印C
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("C");
            s1.release(); // 释放许可给A
        }
    }

    public static void main(String[] args) {
        PrintAbcSemaphore printer = new PrintAbcSemaphore();
        new Thread(printer::printA).start(); // 启动线程A打印A
        new Thread(printer::printB).start(); // 启动线程B打印B
        new Thread(printer::printC).start(); // 启动线程C打印C
    }
}
