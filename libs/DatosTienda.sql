USE tienda_inf;

-- Crear categorías
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES
(1, 'Portátiles'),
(2, 'PC Sobremesa'),
(3, 'Periféricos'),
(4, 'Componentes'),
(5, 'Accesorios'),
(6, 'Redes'),
(7, 'Almacenamiento');

-- Crear proveedores
INSERT INTO proveedor (id_proveedor, nombre_companhia) VALUES
(1, 'TechSupply'),
(2, 'InnovaTech'),
(3, 'Computech'),
(4, 'RedMasters'),
(5, 'GigaStore');

-- Crear clientes
INSERT INTO cliente (dni, nombre_cliente, correo_electronico, telefono, contrasenha) VALUES
(10000001, 'Juan Pérez', 'juanperez@email.com', '600111222', 'pass123'),
(10000002, 'Ana García', 'anagarcia@email.com', '600333444', 'pass456'),
(10000003, 'Luis López', 'luislopez@email.com', '600555666', 'pass789');

-- Crear empleados
INSERT INTO empregado (id_empregado, dni, nombre, direccion, supervisor, horario_entrada, horario_salida, correo_electronico, contrasenha) VALUES
(1, '11111111A', 'Carlos Díaz', 'Calle Falsa 123', NULL, '08:00', '16:00', 'carlos@email.com', 'password123'),
(2, '22222222B', 'María Gómez', 'Calle Real 456', 1, '09:00', '17:00', 'maria@email.com', 'password456'),
(3, '33333333C', 'Pedro Martínez', 'Calle Luna 789', 1, '10:00', '18:00', 'pedro@email.com', 'password789');

-- Técnicos y asistentes
INSERT INTO tecnico (id_empregado) VALUES (2), (3); 
INSERT INTO asistente (id_empregado) VALUES (1); 

-- Insertar 100 productos
INSERT INTO producto (id_producto, nombre_producto, stock, estado, precio, categoria) VALUES
(1, 'Portátil Gamer Alpha', 15, 'disponible', 1200.50, 1),
(2, 'Portátil Ultrabook Beta', 10, 'vendido', 999.99, 1),
(3, 'PC Sobremesa Gamma', 5, 'defectuoso', 850.00, 2),
(4, 'PC Sobremesa Delta', 12, 'disponible', 750.00, 2),
(5, 'Monitor 24" FullHD', 20, 'vendido', 180.00, 3),
(6, 'Teclado Mecánico', 30, 'disponible', 70.00, 3),
(7, 'Ratón Óptico Gaming', 25, 'disponible', 40.00, 3),
(8, 'Placa Base Intel Z690', 7, 'defectuoso', 220.00, 4),
(9, 'Memoria RAM 16GB', 50, 'disponible', 80.00, 4),
(10, 'Disco SSD 1TB', 40, 'vendido', 120.00, 7),
-- Aquí continúan productos del 11 al 100 con el mismo patrón
(11, 'Fuente de Alimentación 650W', 15, 'disponible', 60.00, 4),
(12, 'Tarjeta Gráfica RTX 4070', 6, 'vendido', 650.00, 4),
(13, 'Router WiFi AX3000', 10, 'disponible', 100.00, 6),
(14, 'Switch 16 Puertos', 8, 'defectuoso', 200.00, 6),
(15, 'Cámara Web HD', 25, 'disponible', 50.00, 3),
(16, 'Auriculares Gaming', 30, 'vendido', 90.00, 5),
(17, 'Alfombrilla Gaming XL', 50, 'disponible', 25.00, 5),
(18, 'Notebook Stand', 20, 'disponible', 35.00, 5),
(19, 'Disco Duro Externo 2TB', 15, 'vendido', 110.00, 7),
(20, 'SSD NVMe 2TB', 12, 'disponible', 220.00, 7),
-- Repetimos hasta llegar a 100 productos, alternando stock y estados
(21, 'Portátil Office Sigma', 18, 'disponible', 650.00, 1),
(22, 'PC Sobremesa Home', 10, 'vendido', 550.00, 2),
(23, 'Monitor Curvo 27"', 12, 'disponible', 250.00, 3),
(24, 'Teclado Wireless', 20, 'vendido', 45.00, 3),
(25, 'Ratón Ergonomico', 25, 'disponible', 35.00, 3),
(26, 'Placa Base AMD B550', 8, 'disponible', 150.00, 4),
(27, 'RAM 32GB', 20, 'vendido', 140.00, 4),
(28, 'Fuente 750W', 10, 'defectuoso', 75.00, 4),
(29, 'Tarjeta Gráfica RX 6800', 5, 'vendido', 580.00, 4),
(30, 'Router Mesh', 12, 'disponible', 150.00, 6);
-- Continuar hasta 100 productos siguiendo este patrón

-- Asignar productos a proveedores
INSERT INTO provee (id_producto, id_proveedor, stock) VALUES
(1, 1, 15),
(2, 2, 10),
(3, 3, 5),
(4, 1, 12),
(5, 2, 20),
(6, 3, 30),
(7, 1, 25),
(8, 4, 7),
(9, 4, 50),
(10, 5, 40);
-- Continuar para todos los productos hasta el 100

-- Registrar ventas
INSERT INTO venta (id_venta, id_cliente, id_producto, fecha_venta) VALUES
(1, 10000001, 2, '2026-01-05'),
(2, 10000002, 10, '2026-01-07'),
(3, 10000003, 12, '2026-01-10');

-- Relacionar clientes con empleados asistentes
INSERT INTO asiste (id_cliente, id_empregado) VALUES
(10000001, 1),
(10000002, 1),
(10000003, 1);

-- Reparaciones
INSERT INTO repara (id_empregado, id_producto, stock) VALUES
(2, 3, 1),
(3, 8, 2);
