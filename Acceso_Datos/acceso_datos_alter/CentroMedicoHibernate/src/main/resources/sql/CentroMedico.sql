DROP DATABASE IF EXISTS CentroMedico1;
CREATE DATABASE CentroMedico1;
Use CentroMedico1;

CREATE TABLE Paciente(
emailID varchar(256) primary key,
DNI varchar(9),
nombre varchar (20),
pass varchar(64),
direccion varchar(100),
telefono varchar(15)
);


CREATE TABLE Cita(
NCita int unsigned auto_increment primary key,
fecha date,
nombreEsp varchar(100),
fk_emailID_Paciente varchar(256)
);

-- Claves foráneas
ALTER TABLE Cita
ADD FOREIGN KEY(fk_emailID_Paciente)
REFERENCES Paciente(emailID);


-- INSERT PACIENTES
INSERT INTO Paciente (emailID, DNI, nombre, pass, direccion, telefono) VALUES
('carlos@gmail.com','71188856R','Carlos', SHA2('carlos',256),'c/Cortada n3','623845672'),
('maria@gmail.com','52900473H','María', SHA2('maria',256),'Av. Libertad 45','612334567'),
('jose@gmail.com','83529104L','José', SHA2('jose',256),'c/Mayor 12','634556789'),
('lucia@gmail.com','74219835M','Lucía', SHA2('lucia',256),'c/Colón 89','654123987'),
('antonio@gmail.com','61983725Z','Antonio', SHA2('antonio',256),'Plaza España 3','622789345'),
('laura@gmail.com','98321745B','Laura', SHA2('laura',256),'c/Sevilla 77','699223344'),
('javier@gmail.com','71593284F','Javier', SHA2('javier',256),'Av. Andalucía 102','633445566'),
('elena@gmail.com','85219473D','Elena', SHA2('elena',256),'c/Granada 15','611778899'),
('miguel@gmail.com','79138462T','Miguel', SHA2('miguel',256),'c/Sol 5','624112233'),
('ana@gmail.com','63429175K','Ana', SHA2('ana',256),'Av. de la Paz 66','688990011');


-- CITAS ADICIONALES PARA TODOS LOS PACIENTES
INSERT INTO Cita (fecha, nombreEsp, fk_emailID_Paciente) VALUES
('2025-10-15', 'Traumatología', 'carlos@gmail.com'),
('2025-10-16', 'Cardiología', 'maria@gmail.com'),
('2025-10-17', 'Medico de familia', 'jose@gmail.com'),
('2025-10-20', 'Neumología', 'lucia@gmail.com'),
('2025-10-22', 'Psiquiatría', 'antonio@gmail.com'),
('2025-10-25', 'Cirugia', 'laura@gmail.com'),
('2025-10-27', 'Dermatología', 'javier@gmail.com'),
('2025-10-29', 'Traumatología', 'elena@gmail.com'),
('2025-11-02', 'Cardiología', 'miguel@gmail.com'),
('2025-11-04', 'Medico de familia', 'ana@gmail.com'),
('2025-11-10', 'Neumología', 'carlos@gmail.com'),
('2025-11-12', 'Psiquiatría', 'maria@gmail.com'),
('2025-11-14', 'Cirugia', 'jose@gmail.com'),
('2025-11-15', 'Dermatología', 'lucia@gmail.com'),
('2025-11-18', 'Traumatología', 'antonio@gmail.com'),
('2025-11-20', 'Cardiología', 'laura@gmail.com'),
('2025-11-22', 'Medico de familia', 'javier@gmail.com'),
('2025-11-25', 'Neumología', 'elena@gmail.com'),
('2025-11-27', 'Psiquiatría', 'miguel@gmail.com'),
('2025-11-30', 'Cirugia', 'ana@gmail.com');





