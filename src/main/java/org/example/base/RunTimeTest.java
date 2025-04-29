package org.example.base;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunTimeTest {

    @Test
    public void xunleiPlay(){
        String player= "C:\\Program Files (x86)\\Thunder Network\\Xmp\\Program\\Xmp.exe";
        String video="E:\\迅雷下载\\大江大河1\\3.mp4";

        String command = "\"" + player + "\" \"" + video + "\"";

        try {
            // 执行命令
            Runtime.getRuntime().exec(command);
        }catch (Exception e){

        }
    }

    @Test
    public void cmdTest() throws Exception {
        String cmd = "kubectl -n kube-system logs --since=10m calico-node-pfmhw -c 'calico-node'";
        Runtime runtime = Runtime.getRuntime();

        // 执行命令
        Process process = runtime.exec(cmd);

        // 读取命令的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitVal = process.waitFor();
        if (exitVal == 0) {
            System.out.println("Success!");
        } else {
            // 处理错误情况
            System.out.println("Something went wrong!");
        }
       // System.out.println(result);
    }
}
