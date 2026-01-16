CREATE DATABASE tienda_inf;
USE tienda_inf;
CREATE TABLE categoria(id_categoria INT, nombre_categoria VARCHAR(25), primary key(id_categoria));
CREATE TABLE producto(id_producto INT, nombre_producto VARCHAR(25), stock INT, estado VARCHAR(25), precio DOUBLE, categoria INT, primary key(id_producto),FOREIGN KEY (categoria) REFERENCES categoria(id_categoria));
CREATE TABLE proveedor (id_proveedor INT, nombre_companhia VARCHAR(25), primary key(id_proveedor));
CREATE TABLE cliente (dni VARCHAR(9), nombre_cliente VARCHAR(55),  correo_electronico VARCHAR(35), telefono VARCHAR(15), contrasenha VARCHAR(255), Primary key(dni));
CREATE  TABLE empregado (id_empregado INT, dni VARCHAR(9), nombre VARCHAR(55), direccion VARCHAR(35), supervisor INT, horario_entrada VARCHAR(25), horario_salida VARCHAR(25), 
	correo_electronico VARCHAR(35), contrasenha VARCHAR(255), primary key(id_empregado), FOREIGN KEY (supervisor) REFERENCES empregado(id_empregado));
CREATE TABLE tecnico (id_empregado INT, primary key(id_empregado), FOREIGN KEY (id_empregado) REFERENCES empregado(id_empregado));
CREATE TABLE venta (id_venta INT, id_cliente INT, id_producto INT, stock INT, fecha_venta DATE, primary key (id_venta),FOREIGN KEY (id_cliente) REFERENCES cliente(dni), FOREIGN KEY (id_producto) REFERENCES producto(id_producto));
CREATE TABLE asistente (id_empregado INT, primary key(id_empregado), FOREIGN KEY (id_empregado) REFERENCES empregado(id_empregado));
CREATE TABLE provee (id_producto INT, id_proveedor INT, stock INT, primary key( id_producto, id_proveedor), FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor));
CREATE TABLE asiste ( id_cliente INT, id_empregado INT, primary key(id_cliente,id_empregado),FOREIGN KEY (id_cliente) REFERENCES cliente(dni),
    FOREIGN KEY (id_empregado) REFERENCES asistente(id_empregado)); 
CREATE TABLE repara (id_empregado INT, id_producto INT, stock INT, primary key(id_empregado, id_producto), FOREIGN KEY (id_empregado) REFERENCES tecnico(id_empregado),
    FOREIGN KEY (id_producto) REFERENCES producto(id_producto));
 