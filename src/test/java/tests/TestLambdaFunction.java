package tests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambdaFunction {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello","world","java");
        List<String> uList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(uList);
    }
}
