package org.example.thread;

public class PrintAbcWaitNotify {
    Object lock = new Object();
    // 1:A,2:B,3:C
    int state = 1;

    public static void main(String[] args){
        PrintAbcWaitNotify demo = new PrintAbcWaitNotify();
        Thread a = new Thread(()->demo.printA());
        Thread b = new Thread(demo::printB);

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.printC();
            }
        });
        a.start();
        b.start();
        c.start();
    }

    public void printA(){
        synchronized (lock){
            for(int i=0;i<10;i++){
                while(state!=1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("A,");
                state=2;
                lock.notifyAll();
            }
        }
    }

    public void printB(){
        synchronized (lock){
            for(int i=0;i<10;i++){
                while(state!=2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print("B,");
                state=3;
                lock.notifyAll();
            }
        }
    }
    public void printC(){
        synchronized (lock){
            for(int i=0;i<10;i++){
                while(state!=3){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("C");
                state=1;
                lock.notifyAll();
            }

        }
    }
}
