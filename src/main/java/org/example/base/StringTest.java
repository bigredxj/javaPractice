package org.example.base;

public class StringTest {

    public static void main(String[] args){

        String name= "aaa.bbb-1.mp4";
        int num = name.lastIndexOf('.');
        name  = name.substring(0,num)+"-1"+name.substring(num);
        System.out.println(name);

    }


}
