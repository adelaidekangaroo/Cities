package org.sberstart.simonov.cities;

import org.sberstart.simonov.cities.model.City;
import org.sberstart.simonov.cities.service.CityReader;
import org.sberstart.simonov.cities.service.CityService;
import org.sberstart.simonov.cities.utils.PrintHelper;

import java.io.File;
import java.util.AbstractMap;
import java.util.List;
import java.util.Scanner;

import static org.sberstart.simonov.cities.utils.PrintHelper.print;
import static org.sberstart.simonov.cities.utils.PrintHelper.printError;

public class Main {

    private static final File FILE = new File("src/main/resources/Cities.txt");

    public static void main(String[] args) {
        CityService service = new CityService();
        CityReader reader = new CityReader();

        List<City> cities = reader.readFromFile(FILE);

        print("Список городов: ");
        print(cities);

        print("Введите код нужной функции:\n" +
                "1 - Отсортировать города по названию\n" +
                "2 - Отсортировать города по округу и названию\n" +
                "3 - Найти индекс города с наибольшим населением\n" +
                "4 - Количество городов по регионам\n" +
                "0 - Выйти");

        int code = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                code = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printError(e);
                code = -1;
            }
            switch (code) {
                case 0:
                    System.exit(0);
                case 1:
                    print(service.sortByName(cities));
                    break;
                case 2:
                    print(service.sortByDistrictAndName(cities));
                    break;
                case 3: {
                    AbstractMap.SimpleEntry<Integer, Integer> maxPopulatedCity = service.getCityIndexWithMaxPopulation(cities);
                    int index = maxPopulatedCity.getKey();
                    int value = maxPopulatedCity.getValue();
                    print(String.format("[%d] = %d", index, value));
                    break;
                }
                case 4: {
                    PrintHelper.print(service.getNumberOfCitiesByRegions(cities));
                    break;
                }
                default:
                    print("Неизвестный код");
            }
        } while (code != 0);
    }
}