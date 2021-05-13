package org.sberstart.simonov.cities.repository;

import org.sberstart.simonov.cities.model.City;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public interface CityRepository {
    void create(City city);

    boolean update(City city);

    boolean delete(int id);

    City getByName(String name);

    City getById(int id);

    List<City> getSortedByDistrictAndName(boolean district, boolean name);

    AbstractMap.SimpleEntry<Integer, Integer> getCityIndexWithMaxPopulation();

    Map<String, Long> getNumberOfCitiesByRegions();
}
