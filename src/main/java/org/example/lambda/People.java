package org.example.lambda;

import lombok.Data;

@Data
public class People {
    private String name;
    private Integer age;

    public People(){

    }

    public People(Integer age){
        this.age=age;
    }
}
