package org.example.file;

import java.io.File;

public class ReNameTVFile {
    static int num =1;
    public static boolean isTv(String name){
        String[] formats = {".mp4",".mkv"};
        for(String format:formats){
            if(name.endsWith(format)){
                return true;
            }
        }
        return false;
    }

    public static String getNewName(String name){
        System.out.println(name);
        String[] str = name.split("\\.");
        int len = str.length;
        String format=str[len-1];
       // String a = "S01E";
        String a = "";
        if(a!=""){
            name = name.split(a,2)[1];
        }
        String num = name.substring(0,2);
        String newName = num+"集."+format;
       // num++;
        System.out.println(newName);
        return newName;
    }
    public static void main(String[] args) {
        String path = "E:\\迅雷下载\\黑土无言";
        File dir = new File(path);
        File[] files = dir.listFiles();

        for(File file:files){
            String name = file.getName();
            if(isTv(name)){
                String newName = getNewName(name);
                File newFile = new File(path+"\\"+newName);
                file.renameTo(newFile);
            }

        }
    }
}
