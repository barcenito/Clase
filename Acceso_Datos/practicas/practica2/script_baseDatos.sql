drop database if exists CentroMedico;
create database CentroMedico;
Use CentroMedico;

CREATE TABLE Paciente(citacitaid
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(9),
    passwd VARCHAR(50),
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
('12345678A', 'pass123', 'Juan Perez', 'Calle Falsa 123', '600123456'),
('23456789B', 'pass234', 'Maria Gomez', 'Av. Siempre Viva 742', '600234567'),
('34567890C', 'pass345', 'Carlos Ruiz', 'Plaza Mayor 1', '600345678'),
('45678901D', 'pass456', 'Ana Torres', 'Calle Luna 45', '600456789'),
('56789012E', 'pass567', 'Luis Fernandez', 'Camino Real 99', '600567890'),
('67890123F', 'pass678', 'Elena Martinez', 'Paseo del Prado 12', '600678901'),
('78901234G', 'pass789', 'Sergio Lopez', 'Gran Via 56', '600789012'),
('89012345H', 'pass890', 'Isabel Sanchez', 'Calle Sol 87', '600890123'),
('90123456I', 'pass901', 'Pablo Diaz', 'Avenida Central 33', '600901234'),
('01234567J', 'pass012', 'Laura Morales', 'Calle Nieve 7', '600012345'),
('11223344K', 'pass111', 'Diego Herrera', 'Camino Verde 10', '600112233'),
('22334455L', 'pass222', 'Marta Jimenez', 'Calle Mar 23', '600223344'),
('33445566M', 'pass333', 'Andres Molina', 'Plaza Libertad 4', '600334455'),
('44556677N', 'pass444', 'Patricia Ruiz', 'Av. Las Americas 88', '600445566'),
('55667788O', 'pass555', 'Javier Castillo', 'Calle Fuego 16', '600556677'),
('66778899P', 'pass666', 'Sofia Vargas', 'Calle Agua 29', '600667788'),
('77889900Q', 'pass777', 'Ricardo Morales', 'Calle Cielo 50', '600778899'),
('88990011R', 'pass888', 'Monica Navarro', 'Avenida Paz 80', '600889900'),
('99001122S', 'pass999', 'Fernando Castro', 'Plaza Sol 11', '600990011'),
('00112233T', 'pass000', 'Gabriela Ortega', 'Calle Luz 3', '600001122');
