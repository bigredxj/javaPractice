package org.example.memory;

public class MaxMemoryTest {
    public static void main(String[] args) {
        System.out.println("调整堆内存大小"); // 中文说明：调整堆内存大小
        // 获取并输出当前JVM堆内存的相关信息
        long maxMemory = Runtime.getRuntime().maxMemory(); // 最大内存
        System.out.println("最大内存: " + maxMemory / (1024 * 1024) + " MB"); // 输出最大内存
        long totalMemory = Runtime.getRuntime().totalMemory(); // 总内存
        System.out.println("总内存: " + totalMemory / (1024 * 1024) + " MB"); // 输出总内存
    }
}
