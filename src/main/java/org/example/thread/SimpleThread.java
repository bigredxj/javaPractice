package org.example.thread;

public class SimpleThread extends Thread{
    public void run() {
        System.out.println("run=" + this.isAlive());
    }
}
