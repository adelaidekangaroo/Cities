package org.sberstart.simonov.cities.model;

import java.util.Objects;

public class City {

    private final String name;
    private final String region;
    private final String district;
    private final int population;
    private final short foundation;

    public City(String name, String region, String district, int population, short foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public short getFoundation() {
        return foundation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population &&
                foundation == city.foundation &&
                Objects.equals(name, city.name) &&
                Objects.equals(region, city.region) &&
                Objects.equals(district, city.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, district, population, foundation);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }
}
