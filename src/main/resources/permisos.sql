
INSERT INTO usuario(id, codigo, nombre, apellido, activo, email, password) VALUES (0, 'root', 'Super', 'Usuario', True, 'root@gmail.com', MD5('sql'));

INSERT INTO permiso(id, codigo, descripcion) VALUES (0, 'root','Permiso para ser Super Usuario');
INSERT INTO permiso(id, codigo, descripcion) VALUES(2, 'Ciudad_sel','Permiso para ver ciudades');
INSERT INTO permiso(id, codigo, descripcion) VALUES(3, 'Pais_sel','Permiso para ver paises');
INSERT INTO rol(id, codigo, descripcion) VALUES(0, 'root','Rol SuperUsuario');
INSERT INTO rolpermiso(id, permiso_id, rol_id) VALUES(0, 0, 0);
INSERT INTO rolusuario(id, rol_id, usuario_id) VALUES(0, 0, 0);
INSERT INTO permisousuario(id, usuario_id, permiso_id) VALUES(0, 0, 0);


INSERT INTO config(id, defaultdatasource) VALUES(nextval('config_id_seq'), 'java:jboss/datasources/sgfDS');
