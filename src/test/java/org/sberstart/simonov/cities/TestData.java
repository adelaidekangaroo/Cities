package org.sberstart.simonov.cities;

import org.sberstart.simonov.cities.model.City;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final File SOURCE = new File("src/test/resources/cities.txt");

    public static final City CITY_1 = new City(1, "Адыгейск", "Адыгея", "Южный", 12248, (short) 1973);
    public static final City CITY_2 = new City(2, "Майкоп", "Адыгея", "Южный", 144246, (short) 1857);
    public static final City CITY_3 = new City(3, "Горно-Алтайск", "Алтай", "Сибирский", 56928, (short) 1830);

    public static final int NEW_CITY_ID = CITY_3.getId() + 1;

    public static final List<City> CITIES = Arrays.asList(CITY_1, CITY_2, CITY_3);

    public static City created() {
        return new City("Новый город", "Новый регион", "Северный", 15000, (short) 1900);
    }

    public static City updated() {
        return new City(CITY_1.getId(), CITY_1.getName(), CITY_1.getRegion(), CITY_1.getDistrict(), 1000000, CITY_1.getFoundation());
    }
}