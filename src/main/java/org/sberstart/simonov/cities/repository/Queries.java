package org.sberstart.simonov.cities.repository;

public class Queries {

    public static final String INSERT = "INSERT INTO city(name, region, district, population, foundation) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE = "UPDATE city SET name = ?, region = ?, district = ?, population = ?, foundation = ? WHERE id = ?";

    public static final String GET_BY_NAME = "SELECT id, name, region, district, population, foundation FROM city WHERE name = ?";

    public static final String GET_BY_ID = "SELECT id, name, region, district, population, foundation FROM city WHERE id = ?";

    public static final String GET_SORTED_BY_NAME = "SELECT * FROM city ORDER BY name";

    public static final String GET_SORTED_BY_DISTRICT_AND_NAME = "SELECT * FROM city ORDER BY district, name";

    public static final String DELETE = "DELETE FROM city WHERE id = ?";

    public static final String GET_NUMBERS_OF_CITIES_BY_REGION = "SELECT region, COUNT(name) as count FROM city GROUP BY region";

    public static final String GET_CITY_INDEX_WITH_MAX_POPULATION = "SELECT id, population FROM city WHERE population = (SELECT MAX(population) FROM city)";

    public static final String INIT_DB = "DROP TABLE IF EXISTS city;" +
            "CREATE TABLE city ( " +
            "id bigint auto_increment, " +
            "name VARCHAR(255) NOT NULL, " +
            "region VARCHAR(255) NOT NULL," +
            "district VARCHAR(255) NOT NULL, " +
            "population INT NOT NULL, " +
            "foundation SMALLINT NOT NULL," +
            "CONSTRAINT unique_city_name UNIQUE (name));";

    public static final String POPULATE = "TRUNCATE TABLE city;" +
            "INSERT INTO city(name, region, district, population, foundation)" +
            "VALUES ('Адыгейск', 'Адыгея', 'Южный', 12248, 1973), " +
            "('Майкоп', 'Адыгея', 'Южный', 144246, 1857), " +
            "('Горно-Алтайск', 'Алтай', 'Сибирский', 56928, 1830);";

    private Queries() {
    }
}