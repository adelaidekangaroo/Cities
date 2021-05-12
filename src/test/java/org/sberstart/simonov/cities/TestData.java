package org.sberstart.simonov.cities;

import org.sberstart.simonov.cities.model.City;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final File SOURCE = new File("src/test/resources/cities.txt");

    public static final City CITY_1 = new City("Адыгейск", "Адыгея", "Южный", 12248, (short) 1973);
    public static final City CITY_2 = new City("Майкоп", "Адыгея", "Южный", 144246, (short) 1857);
    public static final City CITY_3 = new City("Горно-Алтайск", "Алтай", "Сибирский", 56928, (short) 1830);

    public static final List<City> CITIES = Arrays.asList(CITY_1, CITY_2, CITY_3);
}