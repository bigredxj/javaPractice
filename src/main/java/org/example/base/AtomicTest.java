package org.example.base;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    @Test
    public void IntegerTest(){
         AtomicInteger ai = new AtomicInteger(1);
         System.out.println(ai.getAndIncrement());
         System.out.println(ai.get());
    }
}
