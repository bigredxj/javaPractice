package org.example.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MyTest {

    public List<Integer> compute(List<Integer> list, Add add){
        return list.stream().map(a->add.add(a)).collect(Collectors.toList());
    }

    public List<Integer> compute(List<Integer> list, Sub sub){
        return list.stream().map(a->sub.sub(a)).toList();
    }

    @Test
    public void test(){
       List<Integer> list = new ArrayList<>();
       list.add(10);
       list.add(20);

       list = compute(list,(Sub)(a)->a-1);
       System.out.println(list);
    }

    @Test
    public void peopleTest(){
        Supplier<People> s = People::new;
        People p = s.get();

        Function<Integer,Integer> f = x -> x + 1;
        Function<Integer,Integer> g = x -> x * 2;
        Function<Integer,Integer> and = f.andThen(g);
        int result = and.apply(1);
        System.out.println(result);

        Function<Integer,Integer> com = f.compose(g);
        result = com.apply(1);
        System.out.println(result);
    }

    @Test
    public void ageArrayToPeople() {
        int[] arr = {10, 3, 5};
        List<People> list = new ArrayList<>();
        Function<Integer,People> f = People::new;

        for(int i=0;i<arr.length;i++){
            list.add(f.apply(arr[i]));
        }

        System.out.println(list);

        System.out.println("after sort:");
        list.sort(Comparator.comparing(People::getAge));
        System.out.println(list);
        list.sort(Comparator.comparing(People::getAge).reversed());
        System.out.println(list);
    }


    public List<Integer> numberFiler(List<Integer> list,NumberFilter filter){
        List<Integer> resultList = new ArrayList<>();
        for(Integer i:list){
            if(filter.filter(i)){
                resultList.add(i);
            }
        }
        return  resultList;
    }

    @Test
    public void myNumberFilterTest(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        NumberFilter a = (Integer i) -> i%2==0;
        System.out.println(numberFiler(list,a));

        NumberFilter b= (Integer i) -> {
            if(i%2==0){
                return false;
            }else {
                return true;
            }
        };
        System.out.println(numberFiler(list,b));
    }

    public void compute(Add add){
        System.out.println(add.add(10));
        System.out.println("add");
    }

    @Test
    public void testComputer(){
        compute((i)->i);
    }
}
