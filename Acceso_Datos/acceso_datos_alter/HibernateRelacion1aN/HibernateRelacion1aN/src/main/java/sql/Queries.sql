DROP DATABASE hibernate_relacion_1an;
CREATE DATABASE hibernate_relacion_1an;
USE hibernate_relacion_1an;

CREATE TABLE IF NOT EXISTS armas (
  id_arma integer unsigned NOT NULL AUTO_INCREMENT,
  nombre varchar(500) NOT NULL,
  descripcion varchar(500) DEFAULT NULL,
  dano integer unsigned DEFAULT '5',
  PRIMARY KEY (id_arma)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;


INSERT INTO armas (id_arma, nombre, descripcion, dano) VALUES
(1, 'capa', 'capa roja de superman', 10),
(2, 'martillo', 'Martillo para derribar obtaculos', 20),
(3, 'boomerang', 'arma de ataque', 35);



CREATE TABLE IF NOT EXISTS personaje (
  id_personaje integer unsigned NOT NULL AUTO_INCREMENT,
  nombre varchar(500) NOT NULL,
  nivel integer unsigned DEFAULT '10',
  energia integer unsigned DEFAULT '100',
  puntos integer unsigned DEFAULT '0',
  id_arma integer NOT NULL,
  PRIMARY KEY (id_personaje)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;



INSERT INTO personaje (id_personaje, nombre, nivel, energia, puntos, id_arma) VALUES
(1, 'superman', 333, 34, 4, 1),
(2, 'batman', 34, 44, 4, 3),
(3, 'Hulk', 12, 12, 12, 1),
(4, 'Capitán América', 450, 120, 150, 1),
(5, 'Iron Man', 300, 100, 50, 2),
(6, 'Thor', 850, 160, 225, 2);