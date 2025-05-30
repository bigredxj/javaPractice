package org.example.limit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LeakBucketLimiter {
    //计算的起始时间
    private static long lastOutTime = System.currentTimeMillis();
    //流出速率每秒2次
    private static long rate = 2;

    //剩余的水量
    private static long water = 0;

    public static synchronized boolean tryAcquire(long taskId, int turn) {
        long nowTime = System.currentTimeMillis();
        //过去的时间
        long pastTime = nowTime - lastOutTime;

        //漏出水量，按照恒定的速度不断流出
        //漏出的水 = 过去的时间 *预设速率
        long outWater = pastTime * rate / 1000;
        //剩余的水量 = 上次遗留的水量 - 漏出去的水
        water = water - outWater;

        log.info("water {} pastTime {} outWater {} ",
                water, pastTime, outWater);
        //纠正剩余的水量
        if (water < 0) {
            water = 0;
        }
        //若剩余的水量小于等于rate，则放行
        if (water < rate) {
            //更新起始时间，为了下次使用
            lastOutTime = nowTime;
            //增加遗留的水量
            water++;
            return true;
        } else {
            //剩余的水量太大，被限流
            return false;
        }
    }

    //线程池，用于多线程模拟测试

    //测试用例
    @Test
    public void testLimit() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //被限制的次数
        AtomicInteger limited = new AtomicInteger(0);
        //线程数
        final int threads = 2;
        //每条线程的执行轮数
        final int turns = 20;
        //同步器
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            pool.submit(() ->
            {
                try {
                    for (int j = 0; j < turns; j++) {
                        long taskId = Thread.currentThread().getId();
                        boolean b = tryAcquire(taskId, j);
                        if (!b) {
                            //被限制的次数累积
                            limited.getAndIncrement();
                        }
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //等待所有线程结束
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        log.info("限制的次数为：" + limited.get() +
                ",通过的次数为：" + (threads * turns - limited.get()));
        log.info("限制的比例为：" +
                (float) limited.get() / (float) (threads * turns));
        log.info("运行的时长为：" + time);
    }
}
