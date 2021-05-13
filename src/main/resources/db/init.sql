DROP TABLE IF EXISTS city;

CREATE TABLE city
(
    id         bigint auto_increment,
    name       VARCHAR(255) NOT NULL,
    region     VARCHAR(255) NOT NULL,
    district   VARCHAR(255) NOT NULL,
    population INT          NOT NULL,
    foundation SMALLINT     NOT NULL,
    CONSTRAINT unique_city_name UNIQUE (name)
);