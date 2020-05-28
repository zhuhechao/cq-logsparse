package io.dfjinxin.modules.sys.service.impl;

import com.google.common.collect.Lists;
import io.dfjinxin.modules.analyse.entity.WpBaseIndexValEntity;
import io.jsonwebtoken.lang.Assert;
import lombok.Data;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

/**
 * @Desc:
 * @Author: z.h.c
 * @Date: 2019/11/27 17:37
 * @Version: 1.0
 */
public class TestLambda {

    private Map<String, Integer> getMap() {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        return map;
    }

    @Test
    public void test() {
        Map<String, Integer> map = this.getMap();

//        map.put("A", 1);
//        map.put("B", 2);
//        map.put("C", 3);

//        System.out.println(map.get("D"));
//        System.out.println("****************");
//        System.out.println(map.getOrDefault("D", 0));
//        System.out.println("****************");
//        System.out.println("map = " + map);

//        1. Map entries
        Consumer<Map.Entry<String, Integer>> action = System.out::println;

        map.entrySet().forEach(action);

////2. Map keys
//        Consumer<String> actionOnKeys = System.out::println;
//
//        map.keySet().forEach(actionOnKeys);
//
////3. Map values
//        Consumer<Integer> actionOnValues = System.out::println;
//
//        map.values().forEach(actionOnValues);
    }

    @Test
    public void test2() {
        String str1 = "Strings";
        String str2 = "Strings";
        String str3 = "Strings123";
        String str4 = "2019-11-27";
        String str5 = "2019-11-28";

        Integer[] intArrs = {1, 3, 5, 79, 10, 7};
//        Arrays.asList(intArrs).forEach(val -> System.out.println(val));
        Arrays.asList(intArrs).forEach(System.out::println);
        Collections.sort(Arrays.asList(intArrs));
        Stream<Integer> stream = Arrays.stream(intArrs);
        Stream<Integer> intArrs1 = Stream.of(intArrs);

        // Lambda表达式的书写形式
        Runnable run = () -> System.out.println("Hello World");// 1
        ActionListener listener = event -> System.out.println("button clicked");// 2
        Runnable multiLine = () -> {// 3 代码块
            System.out.print("Hello");
            System.out.println(" Hoolee");
        };
        BinaryOperator<Long> add = (Long x, Long y) -> x + y;// 4
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;// 5 类型推断

    }

    @Test
    public void test3() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.replaceAll((k, v) -> v.toUpperCase());

        map.forEach((k, v) -> System.out.println("k=" + k + ",v=" + v));
    }

    @Test
    public void stream() {
        String[] strArr = {"a", "b", "c"};
        List<Integer> integerList = Lists.newArrayList();
        integerList.add(1);
        integerList.add(12);
        integerList.add(13);
        integerList.add(14);
        Collections.sort(integerList);
        Stream<Integer> stream3 = integerList.stream();
        stream3.forEach(System.out::println);
        //方式1
        Stream<String> stream = Arrays.stream(strArr);
        stream.forEach(System.out::println);

        System.out.println("****************");

        //方式2
        Stream<String> stream1 = Stream.of(strArr);
        stream1.forEach(System.out::println);

        System.out.println("****************");
        //方式3
        Stream<String> stream2 = Arrays.asList(strArr).stream();
        stream2.forEach(System.out::println);
        System.out.println("****************");

        //方式4
        List<String> list = Arrays.asList(strArr);
        list.stream().forEach(s -> System.out.println(s));
        System.out.println("-------------end----------");



        /*String[] array = {"I", "love", "you", "too", "too"};
//        String[] array = {"a", "b", "c"};
//        Stream<String> stream = Stream.of("I", "love", "you", "too", "too");
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);

        streamSupplier.get().sorted((o1, o2) -> o1.compareTo(o2)).forEach(System.out::println);
        System.out.println("*************");
        streamSupplier.get().sorted((o1, o2) -> o2.compareTo(o1)).forEach(System.out::println);

        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(System.out::println);*/

        // fileter 方法
//        stream.filter(str -> str.length() == 3).forEach(str -> System.out.println(str));
        // distiinct 方法
//        stream.distinct().forEach(System.out::println);

//        IntStream.iterate(0, i -> i + 1).limit(10).forEach(System.out::println);

//        IntStream.iterate(0, i -> ( i + 1 ) % 2)
////                .distinct()
//                .limit(10)
//                .forEach(System.out::println);

        // Of course, we create this list using streams:
//        List<Integer> list =
//                IntStream.range(6, 10)
//                        .boxed()
//                        .collect(toCollection(ArrayList::new));
//        list.forEach(x -> System.out.println(x));

        String[] array = {"a", "b", "c", "d", "e"};
        Supplier<Stream<String>> streamSupplier = () -> Stream.of(array);
        Supplier<Stream<String>> streamSupplie2r = (Supplier<Stream<String>>) Stream.of(Arrays.asList(array));

        //get new stream
        streamSupplier.get().forEach(x -> System.out.println(x));

        //get another new stream

        streamSupplier.get().forEach(x -> System.out.println(x));

        long count = streamSupplier.get().filter(x -> "b".equals(x)).count();
        System.out.println(count);

    }

    @Test
    public void collect() {
        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too", "too2");
        List<String> list = stream.collect(Collectors.toList()); // (1)
        list.forEach(x -> System.out.println(x));
        System.out.println("*****1");

        Set<String> set = stream.collect(Collectors.toSet()); // (2)
        set.forEach(x -> System.out.println(x));
        System.out.println("*****2");

        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length)); // (3)
        System.out.println("*****");
        map.forEach((x, v) -> System.out.println(x + "," + v));
    }

    @Test
    public void testGroup() {
        WpBaseIndexValEntity o1 = new WpBaseIndexValEntity();
        WpBaseIndexValEntity o2 = new WpBaseIndexValEntity();
        WpBaseIndexValEntity o3 = new WpBaseIndexValEntity();
        o1.setFrequence("日");
        o2.setFrequence("月");
        o3.setFrequence("周");
        List<WpBaseIndexValEntity> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        list.add(o3);

        Map<String, List<WpBaseIndexValEntity>> collect = list.stream().collect(Collectors.groupingBy(WpBaseIndexValEntity::getFrequence));
        collect.forEach((k, v) -> System.out.println(k + "," + v));
    }

    @Test
    public void test5() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new Runable");
            }
        }).start();
        System.out.println("*****************");

        new Thread(() -> System.out.println("lamdba thread")).start();

        // 3.Lambda使用
        Thread t3 = new Thread(() -> System.out.println("t3 running..."));
        t3.start();
        // 更简写法1
        new Thread(() -> System.out.println("t4 running...")).start();
        // 更简写法2
        TestInterface8.static_method(() -> System.out.println("t5 running..."));
        System.out.println("test scope-1 end!");

        Supplier<String> Strsupplier = () -> new String();
        String string;
        string = "abcc";
        System.out.println(string);
    }


    @Test
    public void test6() {
        /*List<Person> list = Lists.newArrayList();
        list.add(new Person("fhv",22));
        list.add(new Person("chao",23));
        list.add(new Person("tom",21));
        list.add(new Person("luck",22));
        list.add(new Person("james",24));
        List<Person> search = new ArrayList<>();
        Stream<Person> stream = list.stream();

//        list.forEach(person -> System.out.println(person.getName()+","+person.getAge()));
        list.forEach(System.out::println);
        Map<String, Integer> map = stream.collect(Collectors.toMap(Person::getName, Person::getAge));
        map.forEach((k,v)-> System.out.println(k+":"+v));*/


        List<String> list = Arrays.asList("R","C#", "python", "C++","php","java");
        Stream<String> stream = list.stream();
        List<String> list1 = stream.filter(str -> !"java".equals(str)).sorted().collect(Collectors.toList());
//        List<String>    collect = stream.sorted().collect(Collectors.toList());
//        List<String> listResult = stream.sorted().collect(Collectors.toList());
        list1.forEach(System.out::println);
        System.out.println("------------");
//        listResult.forEach(System.out::println);

    }

    @Data
    class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

}

