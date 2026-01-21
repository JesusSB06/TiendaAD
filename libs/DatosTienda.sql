USE tienda_inf;
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES
(1, 'Electrónica'),
(2, 'Ropa'),
(3, 'Hogar'),
(4, 'Juguetes');

-- Insertar datos en 'producto'
INSERT INTO producto (id_producto, nombre_producto, stock, estado, precio, categoria) VALUES
(1, 'Laptop', 10, 'Nuevo', 599.99, 1),
(2, 'Camiseta', 50, 'Nuevo', 15.99, 2),
(3, 'Sofa', 5, 'Usado', 299.99, 3),
(4, 'Muñeca', 100, 'Nuevo', 9.99, 4),
(5, 'Smartphone', 25, 'Nuevo', 399.99, 1);

-- Insertar datos en 'proveedor'
INSERT INTO proveedor (id_proveedor, nombre_companhia) VALUES
(1, 'Proveedor A'),
(2, 'Proveedor B'),
(3, 'Proveedor C');

-- Insertar datos en 'cliente'
INSERT INTO cliente (dni, nombre_cliente, correo_electronico, telefono, contrasenha) VALUES
(12345678, 'Juan Pérez', 'juan@email.com', '123456789', 'password123'),
(87654321, 'Ana García', 'ana@email.com', '987654321', 'password456'),
(23456789, 'Luis López', 'luis@email.com', '112233445', 'password789');

-- Insertar datos en 'empregado'
INSERT INTO empregado (id_empregado, dni, nombre, direccion, supervisor, horario_entrada, horario_salida, correo_electronico, contrasenha) VALUES
(1, '11111111A', 'Carlos Díaz', 'Calle Falsa 123', NULL, '08:00', '16:00', 'carlos@email.com', 'password123'),
(2, '22222222B', 'María Gómez', 'Calle Real 456', 1, '09:00', '17:00', 'maria@email.com', 'password456'),
(3, '33333333C', 'Pedro Martínez', 'Calle Luna 789', 2, '10:00', '18:00', 'pedro@email.com', 'password789');

INSERT INTO tecnico (id_empregado) VALUES
(2),
(3); 

INSERT INTO asistente (id_empregado) VALUES
(1); 

INSERT INTO provee (id_producto, id_proveedor, stock) VALUES
(1, 1, 10),
(2, 2, 30),
(3, 3, 5),
(4, 1, 50),
(5, 2, 20); 

INSERT INTO venta (id_venta, id_cliente, id_producto, fecha_venta) VALUES
(1, 12345678, 1, '2024-01-10'),
(2, 87654321, 2, '2024-01-12'),
(3, 23456789, 4, '2024-01-15'); 

INSERT INTO asiste (id_cliente, id_empregado) VALUES
(12345678, 1),
(87654321, 1),
(23456789, 1); 

INSERT INTO repara (id_empregado, id_producto, stock) VALUES
(2, 1, 2),
(3, 3, 1);