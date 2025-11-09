DROP DATABASE IF EXISTS tutorial;
CREATE DATABASE  tutorial;
USE tutorial;
CREATE TABLE  usuario (
  id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre varchar(50) DEFAULT NULL,
  apellidos varchar(100) DEFAULT NULL,
  fechaNacimiento   date DEFAULT NULL

);
CREATE TABLE  profesor (
  id integer NOT NULL PRIMARY KEY,
  nombre varchar(50) DEFAULT NULL,
  ape1 varchar(100) DEFAULT NULL,
  ape2 varchar(100) DEFAULT NULL
  
 
);