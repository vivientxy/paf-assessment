-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files

DROP DATABASE IF EXISTS brewery;
CREATE DATABASE brewery;
USE brewery;

SOURCE beers.sql;
SOURCE breweries.sql;
SOURCE categories.sql;
SOURCE geocodes.sql;
SOURCE styles.sql;