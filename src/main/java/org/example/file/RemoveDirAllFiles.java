package org.example.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class RemoveDirAllFiles {
    static String path = "E:\\迅雷下载\\动物急症室.Animal.ER.S01-S02";
    static File dir = new File(path);
    static File targetDir = new File(path + "\\target");
    static Set<String> set = new HashSet<>();
    static int num = 1;

    public static void moveFile(File file){
        if(!targetDir.getAbsolutePath().equals(file.getAbsolutePath())){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File f:files){
                    moveFile(f);
                }
            }else {

                String name = file.getName();
                if(set.contains(name)){
                    int index = name.lastIndexOf('.');
                    name  = name.substring(0,index)+"-"+num+name.substring(index);
                    num++;
                }
                set.add(name);
                File newFile= new File(targetDir.getAbsolutePath()+"\\"+name);
               // file.renameTo(newFile);

            }
        }
    }
    public static void main(String[] args) {
        if(!targetDir.exists()){
            targetDir.mkdir();
        }
        moveFile(new File(path));



    }
}
