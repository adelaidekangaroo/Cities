package org.sberstart.simonov.cities.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.sberstart.simonov.cities.TestData;
import org.sberstart.simonov.cities.model.City;

import java.util.List;

import static org.sberstart.simonov.cities.TestData.*;

public class CityReaderTest {

    private final CityReader reader = new CityReader();

    @Test
    public void readFromFile() {
        List<City> actual = reader.readFromFile(TestData.SOURCE);
        MatcherAssert.assertThat(actual, Matchers.contains(CITY_1, CITY_2, CITY_3));
    }
}