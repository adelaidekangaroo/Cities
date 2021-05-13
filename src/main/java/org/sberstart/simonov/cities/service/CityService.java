package org.sberstart.simonov.cities.service;

import org.sberstart.simonov.cities.model.City;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class CityService {

    public List<City> sortByName(List<City> cities) {
        return cities.stream()
                .sorted(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public List<City> sortByDistrictAndName(List<City> cities) {
        return cities.stream()
                .sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .collect(Collectors.toList());
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getCityIndexWithMaxPopulation(List<City> cities) {
        City[] arrayCities = cities.toArray(new City[0]);
        int maxIndex = -1;
        int maxPopulation = -1;
        for (int i = 0; i < arrayCities.length; i++) {
            int population = arrayCities[i].getPopulation();
            if (maxPopulation < population) {
                maxIndex = i;
                maxPopulation = population;
            }
        }
        return new AbstractMap.SimpleEntry<>(maxIndex, maxPopulation);
    }

    public Map<String, Long> getNumberOfCitiesByRegions(List<City> cities) {
        return cities.stream()
                .collect(groupingBy(City::getRegion, counting()));
    }
}