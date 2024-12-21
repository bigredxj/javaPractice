package org.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintAbcReentrantLock {
    Lock lock = new ReentrantLock();
    Condition cA= lock.newCondition();
    Condition cB= lock.newCondition();
    Condition cC= lock.newCondition();
    int state = 1;

    public static void main(String[] args){
        PrintAbcReentrantLock demo = new PrintAbcReentrantLock();
        Thread a = new Thread(demo::printA);
        Thread b = new Thread(demo::printB);
        Thread c = new Thread(demo::printC);
        b.start();
        a.start();

        c.start();

    }

    public void printA(){
        lock.lock();
        try{
            for(int i=0;i<10;i++){
                while(state!=1){
                    cA.await();
                }
                System.out.print("A,");
                state= 2;
                cB.signal();
            }
        }catch (Exception e){

        }finally {
            System.out.println("unlock-A");
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            for(int i=0;i<10;i++){
                while(state!=2){
                    cB.await();
                }
                System.out.print("B,");
                state= 3;
                cC.signal();
            }
        }catch (Exception e){

        }finally {
            System.out.println("unlock-B");
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try{
            for(int i=0;i<10;i++){
                while(state!=3){
                    cC.await();
                }
                System.out.println("C");
                state= 1;
                cA.signal();
            }
        }catch (Exception e){

        }finally {
            System.out.println("unlock-C");
            lock.unlock();
        }
    }
}
