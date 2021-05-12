package org.sberstart.simonov.cities;

import org.sberstart.simonov.cities.model.City;
import org.sberstart.simonov.cities.service.CityService;
import org.sberstart.simonov.cities.service.CityWriter;

import java.io.File;
import java.util.*;

public class Main {

    private static final File FILE = new File("src/main/resources/Cities.txt");

    public static void main(String[] args) {
        CityService service = new CityService();
        CityWriter writer = new CityWriter();

        Scanner scanner = new Scanner(System.in);

        List<City> cities = writer.writeFromFile(FILE);
        System.out.println("Список городов: ");
        printList(cities);

        System.out.println("Введите код нужной функции:\n" +
                "1 - Отсортировать города по названию\n" +
                "2 - Отсортировать города по округу и названию\n" +
                "3 - Найти индекс города с наибольшим населением\n" +
                "4 - Количество городов по регионам\n" +
                "0 - Выйти");

        int code = -1;
        while (code != 0) {
            try {
                code = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println(e);
                code = -1;
            }
            switch (code) {
                case -1:
                    System.out.println("Неверный формат кода");
                    break;
                case 0:
                    System.exit(0);
                case 1:
                    printList(service.sortByName(cities));
                    break;
                case 2:
                    printList(service.sortByDistrictAndName(cities));
                    break;
                case 3: {
                    AbstractMap.SimpleEntry<Integer, Integer> maxPopulatedCity = service.getCityIndexWithMaxPopulation(cities);
                    int index = maxPopulatedCity.getKey();
                    int value = maxPopulatedCity.getValue();
                    System.out.printf("[%d] - %d%n", index, value);
                    break;
                }
                case 4: {
                    printMap(service.getNumberOfCitiesByRegions(cities));
                    break;
                }
                default:
                    System.out.println("Неизвестный код");

            }
        }
    }

    private static void printList(List<City> cities) {
        cities.forEach(System.out::println);
    }

    private static void printMap(Map<String, Long> map) {
        map.forEach((k, v) -> System.out.printf("%s - %d%n", k, v));
    }
}