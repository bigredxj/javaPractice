package org.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufCountTest {
    public static void main(String[] args){
        ByteBuf buffer  = ByteBufAllocator.DEFAULT.buffer();
        System.out.println("after create:"+buffer.refCnt());
        buffer.retain();
        System.out.println("after retain:"+buffer.refCnt());
        buffer.release();
        System.out.println("after release:"+buffer.refCnt());
        buffer.release();
        System.out.println("after release:"+buffer.refCnt());
        //错误:refCnt: 0,不能再retain
        buffer.retain();
        System.out.println("after retain:"+buffer.refCnt());

    }
}