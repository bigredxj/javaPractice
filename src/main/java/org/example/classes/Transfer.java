package org.example.classes;

import java.util.ArrayList;
import java.util.List;

public class Transfer <T>{

    public  List<T> arrsyToList(T[] a){
        List<T> list =new ArrayList<>();
        for(int i=0;i<a.length;i++){
            list.add(a[i]);
        }
        return  list;
    }

    public void showa(String a){
        System.out.println(a);
    }

    public void show(T a){
        System.out.println(a);
    }
}
