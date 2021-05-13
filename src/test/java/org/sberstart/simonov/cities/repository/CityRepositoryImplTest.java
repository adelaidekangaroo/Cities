package org.sberstart.simonov.cities.repository;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sberstart.simonov.cities.TestData;
import org.sberstart.simonov.cities.model.City;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.sberstart.simonov.cities.TestData.*;
import static org.sberstart.simonov.cities.repository.Queries.INIT_DB;
import static org.sberstart.simonov.cities.repository.Queries.POPULATE;

public class CityRepositoryImplTest {

    private static final CityRepository repository = new CityRepositoryImpl();

    @Before
    public void resetDb() {
        DbConfig.executeScript(INIT_DB);
        DbConfig.executeScript(POPULATE);
    }

    @Test
    public void create() {
        City created = TestData.created();
        repository.create(created);
        created.setId(NEW_CITY_ID);
        City received = repository.getById(NEW_CITY_ID);
        Assert.assertEquals(received, created);
    }

    @Test
    public void update() {
        City updated = TestData.updated();
        repository.update(updated);
        City received = repository.getById(updated.getId());
        Assert.assertEquals(received, updated);
    }

    @Test
    public void updateNotExecute() {
        City updated = TestData.updated();
        updated.setId(0);
        Assert.assertFalse(repository.update(updated));
    }

    @Test
    public void delete() {
        repository.delete(CITY_1.getId());
        Assert.assertNull(repository.getById(CITY_1.getId()));
    }

    @Test
    public void getByName() {
        City actual = repository.getByName(CITY_1.getName());
        Assert.assertEquals(CITY_1, actual);
    }

    @Test
    public void notFoundByName() {
        City actual = repository.getByName("unknown");
        Assert.assertNull(actual);
    }

    @Test
    public void getById() {
        City actual = repository.getById(CITY_1.getId());
        Assert.assertEquals(CITY_1, actual);
    }

    @Test
    public void getSortedByName() {
        List<City> actual = repository.getSortedByDistrictAndName(false, true);
        assertThat(actual, contains(CITY_1, CITY_3, CITY_2));
    }

    @Test
    public void getSortedByDistrictAndName() {
        List<City> actual = repository.getSortedByDistrictAndName(true, true);
        assertThat(actual, contains(CITY_3, CITY_1, CITY_2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSortedByOnlyDistrict() {
        repository.getSortedByDistrictAndName(true, false);
    }

    @Test
    public void getCityIndexWithMaxPopulation() {
        AbstractMap.SimpleEntry<Integer, Integer> actual = repository.getCityIndexWithMaxPopulation();
        int index = actual.getKey();
        int population = actual.getValue();
        assertEquals(index, CITY_2.getId());
        assertEquals(population, CITY_2.getPopulation());
    }

    @Test
    public void getNumberOfCitiesByRegions() {
        Map<String, Long> actual = repository.getNumberOfCitiesByRegions();
        Map<String, Long> expected = new HashMap<String, Long>() {{
            put(CITY_1.getRegion(), 2L);
            put(CITY_3.getRegion(), 1L);
        }};
        assertThat(actual.entrySet(), Matchers.equalTo(expected.entrySet()));
    }
}