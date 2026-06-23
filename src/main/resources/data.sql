-- Datos de ejemplo para probar la aplicacion

INSERT INTO generos (nombre, descripcion) VALUES ('Rock', 'Rock clasico y moderno');
INSERT INTO generos (nombre, descripcion) VALUES ('Jazz', 'Jazz y blues');
INSERT INTO generos (nombre, descripcion) VALUES ('Pop', 'Musica popular');

INSERT INTO artistas (nombre, pais) VALUES ('Pink Floyd', 'Reino Unido');
INSERT INTO artistas (nombre, pais) VALUES ('Miles Davis', 'Estados Unidos');
INSERT INTO artistas (nombre, pais) VALUES ('Soda Stereo', 'Argentina');

INSERT INTO discos (titulo, anio, precio, stock, artista_id, genero_id) VALUES ('The Dark Side of the Moon', 1973, 35.50, 10, 1, 1);
INSERT INTO discos (titulo, anio, precio, stock, artista_id, genero_id) VALUES ('Kind of Blue', 1959, 29.90, 5, 2, 2);
INSERT INTO discos (titulo, anio, precio, stock, artista_id, genero_id) VALUES ('Cancion Animal', 1990, 24.00, 8, 3, 1);

INSERT INTO clientes (nombre, email, telefono) VALUES ('Juan Perez', 'juan@correo.com', '0991234567');
INSERT INTO clientes (nombre, email, telefono) VALUES ('Maria Lopez', 'maria@correo.com', '0987654321');

INSERT INTO ventas (fecha, cantidad, cliente_id, disco_id) VALUES ('2026-06-18', 1, 1, 1);
INSERT INTO ventas (fecha, cantidad, cliente_id, disco_id) VALUES ('2026-06-19', 2, 2, 3);
