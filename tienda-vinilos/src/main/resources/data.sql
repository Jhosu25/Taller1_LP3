-- =====================================================================
--  Datos de ejemplo (opcional).
--  Para cargarlos automaticamente: en application.properties pon
--  spring.sql.init.mode=always (recomendado solo la primera vez).
-- =====================================================================

-- Generos
INSERT INTO generos (nombre, descripcion) VALUES ('Rock', 'Rock clasico y moderno');
INSERT INTO generos (nombre, descripcion) VALUES ('Jazz', 'Jazz y blues');
INSERT INTO generos (nombre, descripcion) VALUES ('Pop', 'Musica popular');

-- Artistas
INSERT INTO artistas (nombre, pais) VALUES ('Pink Floyd', 'Reino Unido');
INSERT INTO artistas (nombre, pais) VALUES ('Miles Davis', 'Estados Unidos');
INSERT INTO artistas (nombre, pais) VALUES ('Soda Stereo', 'Argentina');

-- Discos (relacion muchos a uno con artista mediante artista_id)
INSERT INTO discos (titulo, anio, precio, stock, artista_id) VALUES ('The Dark Side of the Moon', 1973, 35.50, 10, 1);
INSERT INTO discos (titulo, anio, precio, stock, artista_id) VALUES ('Kind of Blue', 1959, 29.90, 5, 2);
INSERT INTO discos (titulo, anio, precio, stock, artista_id) VALUES ('Cancion Animal', 1990, 24.00, 8, 3);

-- Relacion MUCHOS A MUCHOS disco-genero (tabla intermedia)
INSERT INTO disco_genero (disco_id, genero_id) VALUES (1, 1);
INSERT INTO disco_genero (disco_id, genero_id) VALUES (2, 2);
INSERT INTO disco_genero (disco_id, genero_id) VALUES (3, 1);
INSERT INTO disco_genero (disco_id, genero_id) VALUES (3, 3);

-- Direcciones (relacion uno a uno con cliente)
INSERT INTO direcciones (calle, ciudad) VALUES ('Av. Amazonas 123', 'Quito');
INSERT INTO direcciones (calle, ciudad) VALUES ('Calle 9 de Octubre 45', 'Guayaquil');

-- Clientes (cada uno con su direccion_id)
INSERT INTO clientes (nombre, email, telefono, direccion_id) VALUES ('Juan Perez', 'juan@correo.com', '0991234567', 1);
INSERT INTO clientes (nombre, email, telefono, direccion_id) VALUES ('Maria Lopez', 'maria@correo.com', '0987654321', 2);

-- Ventas (relacion muchos a uno con cliente y disco)
INSERT INTO ventas (fecha, cantidad, cliente_id, disco_id) VALUES ('2026-06-18', 1, 1, 1);
INSERT INTO ventas (fecha, cantidad, cliente_id, disco_id) VALUES ('2026-06-19', 2, 2, 3);
