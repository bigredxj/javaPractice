package org.example.thread;

import java.util.concurrent.locks.LockSupport;

public class Park {
    public static void main(String[] args){

        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread current = Thread.currentThread();
                System.out.println("{},开始执行! "+current.getName());
                for(;;){
                    System.out.println("准备park住当前线程：{}.... "+current.getName());
                    LockSupport.park();   // 或者是LockSupport.park(this);
                   Thread.interrupted();
                    System.out.println("当前线程{}已经被唤醒.... "+current.getName());
                }
            }
        },"t0");
        t0.start();
        try {
            Thread.sleep(2000);
            System.out.println("准备唤醒{}线程! "+t0.getName());
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
