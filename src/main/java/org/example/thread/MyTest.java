package org.example.thread;

import org.junit.jupiter.api.Test;

public class MyTest {

    @Test
    public void currentThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println(name);
            }
        });
        thread.setName("my-test");
        thread.start();
    }

    @Test
    public void tes2(){
        Runnable r = ()->System.out.println("hello");
        Thread thread = new Thread(r);
        thread.start();
    }

    @Test
    public void isAlive(){
        SimpleThread mythread = new SimpleThread();
        System.out.println("begin ==" + mythread.isAlive());
        mythread.start();
        try {
            Thread.sleep(1000);
            mythread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("end ==" + mythread.isAlive());
    }
}
