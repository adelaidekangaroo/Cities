package org.sberstart.simonov.cities.service;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.sberstart.simonov.cities.model.City;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.sberstart.simonov.cities.TestData.*;

public class CityServiceTest {

    private final CityService service = new CityService();

    @Test
    public void sortByName() {
        List<City> actual = service.sortByName(CITIES);
        assertThat(actual, contains(CITY_1, CITY_3, CITY_2));
    }

    @Test
    public void sortByDistrictAndName() {
        List<City> actual = service.sortByDistrictAndName(CITIES);
        assertThat(actual, contains(CITY_3, CITY_1, CITY_2));
    }

    @Test
    public void getCityIndexWithMaxPopulation() {
        AbstractMap.SimpleEntry<Integer, Integer> actual = service.getCityIndexWithMaxPopulation(CITIES);
        int index = actual.getKey();
        int population = actual.getValue();
        assertEquals(CITIES.toArray()[index], CITY_2);
        assertEquals(population, CITY_2.getPopulation());
    }

    @Test
    public void getNumberOfCitiesByRegions() {
        Map<String, Long> actual = service.getNumberOfCitiesByRegions(CITIES);
        Map<String, Long> expected = new HashMap<String, Long>() {{
            put(CITY_1.getRegion(), 2L);
            put(CITY_3.getRegion(), 1L);
        }};
        assertThat(actual.entrySet(), Matchers.equalTo(expected.entrySet()));
    }
}