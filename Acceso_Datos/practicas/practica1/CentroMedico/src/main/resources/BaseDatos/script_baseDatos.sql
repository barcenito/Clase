drop database if exists CentroMedico;
create database CentroMedico;
Use CentroMedico;

CREATE TABLE Paciente(
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9),
    passwd VARCHAR(256),
    nombre VARCHAR(50),
    direccion VARCHAR(50),
    telefono INT(12)
);
CREATE TABLE especialidad(
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombreEspecialidad VARCHAR(50)
);
CREATE TABLE Cita(
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    fecha timestamp,
	idPaciente INT UNSIGNED,
    idEspecialidad INT UNSIGNED,
    foreign key (idPaciente) references Paciente(id),
    foreign key (idEspecialidad) references Especialidad(id)
);

INSERT INTO Paciente (dni, passwd, nombre, direccion, telefono) VALUES
('12345678A', SHA2('pass123', 256), 'Juan Perez', 'Calle Falsa 123', '600123456'),
('23456789B', SHA2('pass234', 256), 'Maria Gomez', 'Av. Siempre Viva 742', '600234567'),
('34567890C', SHA2('pass345', 256), 'Carlos Ruiz', 'Plaza Mayor 1', '600345678'),
('45678901D', SHA2('pass456', 256), 'Ana Torres', 'Calle Luna 45', '600456789'),
('56789012E', SHA2('pass567', 256), 'Luis Fernandez', 'Camino Real 99', '600567890'),
('67890123F', SHA2('pass678', 256), 'Elena Martinez', 'Paseo del Prado 12', '600678901'),
('78901234G', SHA2('pass789', 256), 'Sergio Lopez', 'Gran Via 56', '600789012'),
('89012345H', SHA2('pass890', 256), 'Isabel Sanchez', 'Calle Sol 87', '600890123'),
('90123456I', SHA2('pass901', 256), 'Pablo Diaz', 'Avenida Central 33', '600901234'),
('01234567J', SHA2('pass012', 256), 'Laura Morales', 'Calle Nieve 7', '600012345'),
('11223344K', SHA2('pass111', 256), 'Diego Herrera', 'Camino Verde 10', '600112233'),
('22334455L', SHA2('pass222', 256), 'Marta Jimenez', 'Calle Mar 23', '600223344'),
('33445566M', SHA2('pass333', 256), 'Andres Molina', 'Plaza Libertad 4', '600334455'),
('44556677N', SHA2('pass444', 256), 'Patricia Ruiz', 'Av. Las Americas 88', '600445566'),
('55667788O', SHA2('pass555', 256), 'Javier Castillo', 'Calle Fuego 16', '600556677'),
('66778899P', SHA2('pass666', 256), 'Sofia Vargas', 'Calle Agua 29', '600667788'),
('77889900Q', SHA2('pass777', 256), 'Ricardo Morales', 'Calle Cielo 50', '600778899'),
('88990011R', SHA2('pass888', 256), 'Monica Navarro', 'Avenida Paz 80', '600889900'),
('99001122S', SHA2('pass999', 256), 'Fernando Castro', 'Plaza Sol 11', '600990011'),
('00112233T', SHA2('pass000', 256), 'Gabriela Ortega', 'Calle Luz 3', '600001122');
