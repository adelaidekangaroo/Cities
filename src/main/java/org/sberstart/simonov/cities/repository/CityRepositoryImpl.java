package org.sberstart.simonov.cities.repository;

import org.sberstart.simonov.cities.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.sberstart.simonov.cities.repository.DbConfig.getConnection;
import static org.sberstart.simonov.cities.repository.Queries.*;

public class CityRepositoryImpl implements CityRepository {

    @Override
    public void create(City city) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, city.getName());
            statement.setString(2, city.getRegion());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());
            statement.setShort(5, city.getFoundation());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(City city) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, city.getName());
            statement.setString(2, city.getRegion());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());
            statement.setShort(5, city.getFoundation());
            statement.setInt(6, city.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public City getByName(String name) {

        City city = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_NAME)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = parseFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City getById(int id) {

        City city = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    city = parseFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> getSortedByDistrictAndName(boolean district, boolean name) {
        List<City> results = new ArrayList<>();

        String query = null;

        if (district && name) {
            query = GET_SORTED_BY_DISTRICT_AND_NAME;
        } else if (name) {
            query = GET_SORTED_BY_NAME;
        } else {
            throw new IllegalArgumentException();
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(parseFromResultSet(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getCityIndexWithMaxPopulation() {

        int maxIndex = -1;
        int maxPopulation = -1;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_CITY_INDEX_WITH_MAX_POPULATION)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    maxIndex = resultSet.getInt("id");
                    maxPopulation = resultSet.getInt("population");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new AbstractMap.SimpleEntry<>(maxIndex, maxPopulation);
    }

    @Override
    public Map<String, Long> getNumberOfCitiesByRegions() {

        Map<String, Long> result = new HashMap<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NUMBERS_OF_CITIES_BY_REGION)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String region = resultSet.getString("region");
                    long population = resultSet.getInt("count");
                    result.put(region, population);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private City parseFromResultSet(ResultSet resultSet) throws SQLException {
        return new City(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("region"),
                resultSet.getString("district"),
                resultSet.getInt("population"),
                resultSet.getShort("foundation")
        );
    }
}
