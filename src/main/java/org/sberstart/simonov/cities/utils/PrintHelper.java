package org.sberstart.simonov.cities.utils;

import org.sberstart.simonov.cities.model.City;

import java.util.List;
import java.util.Map;

public class PrintHelper {

    private PrintHelper() {}

    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    public static void print(Map<String, Long> map) {
        map.forEach((k, v) -> System.out.printf("%s - %d%n", k, v));
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printError(Throwable e) {
        System.err.println(e);
    }

}
