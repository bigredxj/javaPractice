package org.example.stream;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class People {
    private String name;
    private Integer age;
    private String sex;

    public People(){ }
    public People(String name,Integer age,String sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public static List<People> listDemoPeople(){
        List<People> list = new ArrayList<>();
        list.add(new People("Jack",20,"boy"));
        list.add(new People("Daniel",10,"boy"));
        list.add(new People("Lucy",10,"girl"));
        list.add(new People("Tom",40,"boy"));
        list.add(new People("Nancy",30,"girl"));
        return list;
    }

    @Override
    public boolean equals(Object p){
        People o = (People) p;
        if(this.age.equals(o.getAge())){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public int hashCode(){
        return this.getAge();
    }
}
