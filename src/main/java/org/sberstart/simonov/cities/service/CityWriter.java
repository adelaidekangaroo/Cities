package org.sberstart.simonov.cities.service;

import org.sberstart.simonov.cities.model.City;
import org.sberstart.simonov.cities.utils.ParseUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityWriter {

    public List<City> writeFromFile(File source) {
        List<City> cities = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(source);
            while (scanner.hasNextLine()) {
                cities.add(ParseUtil.parseToCityObject(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } finally {
            scanner.close();
        }
        return cities;
    }
}
