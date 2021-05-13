package org.sberstart.simonov.cities.service;

import org.junit.Test;
import org.sberstart.simonov.cities.TestData;
import org.sberstart.simonov.cities.model.City;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.sberstart.simonov.cities.TestData.*;

public class CityReaderTest {

    private final CityReader reader = new CityReader();

    @Test
    public void readFromFile() {
        List<City> actual = reader.readFromFile(TestData.SOURCE);
        assertThat(actual, contains(CITY_1, CITY_2, CITY_3));
    }
}