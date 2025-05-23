package org.example.limit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class TokenBucketLimiter {
    public long lastTime = System.currentTimeMillis();
    //桶的容量
    public int capacity = 2;
    //令牌生成速度个/秒
    public int rate = 2;
    //当前令牌的数量
    public int tokens;


    public synchronized boolean tryAcquire(long taskId, int applyCount) {
        long now = System.currentTimeMillis();
        //时间间隔，单位为毫秒
        long gap = now - lastTime;
        //当前令牌数
        tokens = Math.min(capacity, (int) (tokens + gap * rate / 1000));
        log.info("tokens {} capacity {} gap {} ", tokens, capacity, gap);

        if (tokens < applyCount) {
            //若拿不到令牌，则拒绝
            log.info("被限流了.." + taskId + ", applyCount: " + applyCount);
            return false;
        } else {
            //还有令牌，领取令牌
            tokens -= applyCount;
            lastTime = now;

            log.info("剩余令牌.." + tokens);
            return true;
        }
    }


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
                        boolean b = tryAcquire(taskId, 1);
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

