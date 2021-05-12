package org.sberstart.simonov.cities.utils;

import org.sberstart.simonov.cities.model.City;

public class ParseUtil {

    private ParseUtil() {
    }

    public static City parseToCityObject(String s) {
        String[] args = s.split(";");

        String name = args[1].trim();
        String region = args[2].trim();
        String district = args[3].trim();
        int population = Integer.parseInt(args[4].trim());
        short foundation = Short.parseShort(args[5].trim());

        return new City(name, region, district, population, foundation);
    }
}
