package org.example.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    @Test
    public void test(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(1000L); //模拟耗时任务
                return "test";
            } catch (Exception e){
                return "failed test";
            }
        });
        //情况1：
       // System.out.println(future.join());
       // future.complete("manual test");
        //情况2：
        future.complete("manual test");
        System.out.println(future.join());
    }
}
