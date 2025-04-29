package org.example.stream;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void filter(){
        List<People> list = People.listDemoPeople();
        List<People> girls = list.stream()
                .sorted(Comparator.comparing(People::getAge).reversed())
                .limit(3)
                .toList();
        System.out.println(girls);
    }

    @Test
    public void flatmap(){
        List<People> list = People.listDemoPeople();
        List<String> strs = list.stream()
                .map(p->p.getName().split(""))
                .flatMap(s-> Arrays.stream(s))
                .distinct()
                .toList();
        System.out.println(strs);
    }

    @Test
    public void allMatch(){
        List<People> list = People.listDemoPeople();
        boolean b = list.stream().allMatch(p-> p.getAge() < 100);
        System.out.println(b);
    }

    @Test
    public void reduce(){
        List<People> list = People.listDemoPeople();
        Optional<People> p = list.stream()
                        .reduce((a,b)-> {
                          if(a.getAge() - b.getAge() > 0){
                              return a;
                          }else {
                              return b;
                          }
                        });
        System.out.println(p.get());
    }

    @Test
    public void groupBy(){
        List<People> list = People.listDemoPeople();
        Map<Integer, List<People>> map = list.stream()
                        .collect(Collectors.groupingBy(People::getAge));

        System.out.println(map);
    }

    @Test
    public void toMap(){
        List<People> list = People.listDemoPeople();
        Map<Integer, People> map = list.stream()
                .collect(Collectors.toMap(People::getAge,p->p,(oldValue, newValue) -> newValue));
        System.out.println(map);
    }

    @Test
    public void partitioningBy(){
        List<People> list = People.listDemoPeople();
        Map<Boolean, People> map = list.stream()
                .collect(Collectors.partitioningBy(p->p.getSex().equals("boy"),
                        Collectors.collectingAndThen(
                                Collectors.maxBy((a,b)-> a.getAge()-b.getAge()),
                                 p-> p.get()))
                );
        System.out.println(map);
    }
}
