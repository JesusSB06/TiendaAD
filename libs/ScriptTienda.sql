CREATE DATABASE tienda_inf;
USE tienda_inf;


CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT,
    nombre_categoria VARCHAR(25) NOT NULL,
    PRIMARY KEY (id_categoria)
);


CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT,
    nombre_producto VARCHAR(80) NOT NULL,
    stock INT NOT NULL,
    estado VARCHAR(25),
    precio DOUBLE NOT NULL,
    id_categoria INT,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria)
        REFERENCES categoria(id_categoria)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);


CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT,
    nombre_companhia VARCHAR(25) NOT NULL,
    PRIMARY KEY (id_proveedor)
);


CREATE TABLE cliente (
    dni VARCHAR(9),
    nombre_cliente VARCHAR(55) NOT NULL,
    correo_electronico VARCHAR(35),
    telefono VARCHAR(15),
    saldo double,
    contrasenha VARCHAR(255) NOT NULL,
    PRIMARY KEY (dni)
);


CREATE TABLE empleado (
    id_empleado INT AUTO_INCREMENT,
    dni VARCHAR(9) NOT NULL,
    nombre VARCHAR(55) NOT NULL,
    direccion VARCHAR(35),
    supervisor INT,
    horario_entrada VARCHAR(25),
    horario_salida VARCHAR(25),
    correo_electronico VARCHAR(35),
    contrasenha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_empleado),
    FOREIGN KEY (supervisor)
        REFERENCES empleado(id_empleado)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);


CREATE TABLE tecnico (
    id_empleado INT,
    PRIMARY KEY (id_empleado),
    FOREIGN KEY (id_empleado)
        REFERENCES empleado(id_empleado)
        ON DELETE CASCADE
);


CREATE TABLE asistente (
    id_empleado INT,
    PRIMARY KEY (id_empleado),
    FOREIGN KEY (id_empleado)
        REFERENCES empleado(id_empleado)
        ON DELETE CASCADE
);


CREATE TABLE venta (
    id_venta INT AUTO_INCREMENT,
    dni_cliente VARCHAR(9),
    id_producto INT,
    fecha_venta DATE NOT NULL,
    PRIMARY KEY (id_venta),
    FOREIGN KEY (dni_cliente)
        REFERENCES cliente(dni)
        ON DELETE CASCADE,
    FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
        ON DELETE CASCADE
);

CREATE TABLE provee (
    id_producto INT,
    id_proveedor INT,
    stock INT NOT NULL,
    PRIMARY KEY (id_producto, id_proveedor),
    FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
        ON DELETE CASCADE,
    FOREIGN KEY (id_proveedor)
        REFERENCES proveedor(id_proveedor)
        ON DELETE CASCADE
);


CREATE TABLE asiste (
    dni_cliente VARCHAR(9),
    id_empleado INT,
    PRIMARY KEY (dni_cliente, id_empleado),
    FOREIGN KEY (dni_cliente)
        REFERENCES cliente(dni)
        ON DELETE CASCADE,
    FOREIGN KEY (id_empleado)
        REFERENCES asistente(id_empleado)
        ON DELETE CASCADE
);

CREATE TABLE repara (
    id_empleado INT,
    id_producto INT,
    stock INT NOT NULL,
    PRIMARY KEY (id_empleado, id_producto),
    FOREIGN KEY (id_empleado)
        REFERENCES tecnico(id_empleado)
        ON DELETE CASCADE,
    FOREIGN KEY (id_producto)
        REFERENCES producto(id_producto)
        ON DELETE CASCADE
);
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO categoria (nombre_categoria) VALUES
('Portátiles'),
('PC Sobremesa'),
('Periféricos'),
('Componentes'),
('Accesorios'),
('Redes'),
('Almacenamiento');

INSERT INTO producto (nombre_producto, stock, estado, precio, id_categoria) VALUES
('Portátil Lenovo IdeaPad',15,'nuevo',599.99,1),
('Portátil HP Pavilion',10,'disponible',749.99,1),
('Portátil ASUS VivoBook',8,'usado',529.50,1),
('Portátil Dell Inspiron',0,'vendido',699.00,1),
('Portátil Acer Aspire',4,'defectuoso',450.00,1),

('PC Gaming Ryzen 5',6,'nuevo',899.99,2),
('PC Oficina Intel i3',12,'disponible',499.99,2),
('PC Gaming i7',0,'vendido',1299.99,2),
('PC Mini ITX',5,'usado',650.00,2),
('PC Sobremesa Básico',3,'defectuoso',300.00,2),

('Ratón Logitech G203',25,'nuevo',29.99,3),
('Teclado Mecánico Redragon',18,'disponible',59.99,3),
('Auriculares HyperX',0,'vendido',89.99,3),
('Webcam Logitech C920',7,'usado',69.99,3),
('Alfombrilla Gaming XL',2,'defectuoso',15.99,3),

('Placa Base ASUS B550',10,'nuevo',139.99,4),
('Procesador Ryzen 7',0,'vendido',329.99,4),
('Memoria RAM 16GB DDR4',20,'disponible',79.99,4),
('Tarjeta Gráfica RTX 3060',4,'usado',399.99,4),
('Fuente 650W Bronze',1,'defectuoso',59.99,4),

('Mochila Portátil',14,'nuevo',39.99,5),
('Soporte Portátil Aluminio',9,'disponible',29.99,5),
('Base Refrigeradora',0,'vendido',24.99,5),
('Cable HDMI 2.1',30,'usado',12.99,5),
('Hub USB-C',5,'defectuoso',19.99,5),

('Router TP-Link AX3000',8,'nuevo',129.99,6),
('Switch 8 Puertos',11,'disponible',39.99,6),
('Repetidor WiFi',0,'vendido',49.99,6),
('Tarjeta Red PCIe',6,'usado',24.99,6),
('Cable Ethernet Cat6',2,'defectuoso',9.99,6),

('SSD NVMe 1TB',16,'nuevo',99.99,7),
('HDD 2TB Seagate',10,'disponible',59.99,7),
('SSD SATA 500GB',0,'vendido',49.99,7),
('Pendrive 128GB',22,'usado',19.99,7),
('Caja Externa HDD',1,'defectuoso',14.99,7),

('Portátil MSI Gaming',5,'nuevo',1199.99,1),
('PC Workstation Xeon',2,'disponible',1899.99,2),
('Monitor 27\" 144Hz',0,'vendido',279.99,3),
('Impresora Láser',7,'usado',199.99,3),
('Escáner Documentos',1,'defectuoso',149.99,3),

('Refrigeración Líquida 240mm',9,'nuevo',109.99,4),
('Caja ATX Cristal',12,'disponible',89.99,4),
('Pasta Térmica',0,'vendido',8.99,4),
('Ventilador RGB',15,'usado',14.99,4),
('Adaptador SATA-USB',2,'defectuoso',11.99,4),

('Ratón Inalámbrico',20,'nuevo',24.99,5),
('Funda Tablet',18,'disponible',17.99,5),
('Cargador Universal',0,'vendido',29.99,5),
('Soporte Monitor',6,'usado',34.99,5),
('Lector Tarjetas USB',1,'defectuoso',9.99,5),

('Firewall Hardware',4,'nuevo',299.99,6),
('Antena WiFi USB',13,'disponible',19.99,6),
('Router 4G',0,'vendido',159.99,6),
('Switch PoE',3,'usado',89.99,6),
('Crimpadora RJ45',1,'defectuoso',14.99,6),

('NAS 2 Bahías',5,'nuevo',249.99,7),
('Disco SSD 2TB',7,'disponible',179.99,7),
('Tarjeta SD 256GB',0,'vendido',39.99,7),
('Disco Externo 1TB',10,'usado',54.99,7),
('Adaptador M.2',2,'defectuoso',12.99,7);

INSERT INTO proveedor (nombre_companhia) VALUES
('TechSupply'),
('InnovaTech'),
('Computech'),
('RedMasters'),
('GigaStore'),
('MegaHardware'),
('ElectroPlus'),
('NetWorld'),
('ByteStore'),
('HardwarePro'),
('DigitalCore'),
('PCMarket'),
('SmartDevices'),
('FutureTech'),
('CompuGlobal'),
('ITSolutions'),
('CyberZone'),
('TechPlanet'),
('NextGenIT'),
('UltraHardware'),
('CoreSystems'),
('ElectronicaMax'),
('RedDigital'),
('CloudStore'),
('InfinityTech');


INSERT INTO cliente (dni, nombre_cliente, correo_electronico, saldo, telefono, contrasenha) VALUES
('10000001A','Juan Pérez','juanperez@email.com',450.78,'600111222','pass123'),
('10000002B','Ana García','anagarcia@email.com',1000.00,'600333444','pass456'),
('10000003C','Luis López','luislopez@email.com',1345.67,'600555666','pass789'),
('10000004D','Marta Ruiz','marta@email.com',230.50,'600777888','pass111'),
('10000005E','Pablo Torres','pablo@email.com',890.20,'600999000','pass222'),
('10000006F','Laura Sánchez','laura@email.com',150.00,'611111111','pass333'),
('10000007G','David Romero','david@email.com',760.00,'622222222','pass444'),
('10000008H','Carmen Gil','carmen@email.com',320.40,'633333333','pass555'),
('10000009I','Jorge Martín','jorge@email.com',980.00,'644444444','pass666'),
('10000010J','Sara Molina','sara@email.com',1200.00,'655555555','pass777'),
('10000011K','Iván Cruz','ivan@email.com',500.00,'666666666','pass888'),
('10000012L','Lucía Navarro','lucia@email.com',845.30,'677777777','pass999'),
('10000013M','Raúl Ortega','raul@email.com',90.00,'688888888','pass000'),
('10000014N','Elena Ramos','elena@email.com',1340.90,'699999999','passabc'),
('10000015O','Sergio Vega','sergio@email.com',430.75,'611000000','passdef');


INSERT INTO empleado (dni, nombre, direccion, supervisor, horario_entrada, horario_salida, correo_electronico, contrasenha) VALUES
('11111111A','Carlos Díaz','Calle Falsa 123',NULL,'08:00','16:00','carlos@email.com','password123'),
('22222222B','María Gómez','Calle Real 456',1,'09:00','17:00','maria@email.com','password456'),
('33333333C','Pedro Martínez','Calle Luna 789',1,'10:00','18:00','pedro@email.com','password789'),
('44444444D','Laura Peña','Av. Norte 12',2,'08:00','16:00','laura@empresa.com','pass1'),
('55555555E','Javier Soto','Calle Sur 45',2,'09:00','17:00','javier@empresa.com','pass2'),
('66666666F','Mónica León','Av. Centro 8',3,'10:00','18:00','monica@empresa.com','pass3'),
('77777777G','Alberto Cano','Calle Este 22',3,'08:00','16:00','alberto@empresa.com','pass4'),
('88888888H','Patricia Rey','Calle Oeste 77',1,'09:00','17:00','patricia@empresa.com','pass5'),
('99999999I','Rubén Vidal','Plaza Mayor 3',1,'10:00','18:00','ruben@empresa.com','pass6'),
('12121212J','Nuria Campos','Av. Sol 9',2,'08:00','16:00','nuria@empresa.com','pass7'),
('13131313K','Diego Flores','Calle Río 14',2,'09:00','17:00','diego@empresa.com','pass8'),
('14141414L','Sandra Mora','Calle Mar 21',3,'10:00','18:00','sandra@empresa.com','pass9'),
('15151515M','Hugo Serra','Calle Monte 30',1,'08:00','16:00','hugo@empresa.com','pass10'),
('16161616N','Eva Roldán','Av. Prado 6',1,'09:00','17:00','eva@empresa.com','pass11'),
('17171717O','Óscar Núñez','Calle Valle 18',2,'10:00','18:00','oscar@empresa.com','pass12');


INSERT INTO tecnico (id_empleado) VALUES
(2),(3),(4),(5),(6),(7),(8),(9),(10),(11);

INSERT INTO asistente (id_empleado) VALUES
(1),(12),(13),(14),(15);


INSERT INTO venta (dni_cliente, id_producto, fecha_venta) VALUES
('10000001A',2,'2026-01-05'),
('10000002B',10,'2026-01-07'),
('10000003C',12,'2026-01-10'),
('10000004D',1,'2026-01-12'),
('10000005E',5,'2026-01-14'),
('10000006F',6,'2026-01-15'),
('10000007G',7,'2026-01-16'),
('10000008H',8,'2026-01-17'),
('10000009I',9,'2026-01-18'),
('10000010J',11,'2026-01-19'),
('10000011K',13,'2026-01-20'),
('10000012L',14,'2026-01-21'),
('10000013M',15,'2026-01-22'),
('10000014N',16,'2026-01-23'),
('10000015O',17,'2026-01-24');


INSERT INTO asiste (dni_cliente, id_empleado) VALUES
('10000001A',1),
('10000002B',1),
('10000003C',1),
('10000004D',12),
('10000005E',12),
('10000006F',13),
('10000007G',13),
('10000008H',14),
('10000009I',14),
('10000010J',15);


INSERT INTO repara (id_empleado, id_producto, stock) VALUES
(2,3,1),
(3,8,2),
(4,14,1),
(5,15,3),
(6,17,2),
(7,18,1),
(8,19,4),
(9,20,2),
(10,11,1),
(11,9,3);
SET FOREIGN_KEY_CHECKS = 1;