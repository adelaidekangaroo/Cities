package org.sberstart.simonov.cities.service;

import org.sberstart.simonov.cities.model.City;
import org.sberstart.simonov.cities.utils.ParseUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.sberstart.simonov.cities.utils.PrintHelper.printError;

public class CityReader {

    public List<City> readFromFile(File source) {
        List<City> cities = new ArrayList<>();

        try (Scanner scanner = new Scanner(source)) {
            while (scanner.hasNextLine()) {
                cities.add(ParseUtil.parseToCityObject(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            printError(e);
        }

        return cities;
    }
}
