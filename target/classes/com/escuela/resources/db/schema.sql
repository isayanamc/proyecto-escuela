# Tabla Estudiantes
CREATE TABLE estudiantes_isayana (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

# Tabla Profesores
CREATE TABLE profesores_isayana (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

# Tabla Cursos
CREATE TABLE cursos_isayana (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

# Tabla Grupos 
CREATE TABLE grupos_isayana (
    id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

# Tabla Relacion Grupo Curso 
CREATE TABLE grupocursos_isayana (
    id INT NOT NULL AUTO_INCREMENT,
    grupo_id INT NOT NULL,
    curso_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (grupo_id) REFERENCES grupos_isayana(id) ON DELETE CASCADE,
    FOREIGN KEY (curso_id) REFERENCES cursos_isayana(id) ON DELETE CASCADE
);

DELIMITER //

# Procedimiento almacenado para insertar estudiantes 
CREATE PROCEDURE InsertarEstudiante(
    IN p_nombre VARCHAR(100),
    IN p_identificacion VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_fecha_nacimiento DATE
)
BEGIN
    INSERT INTO estudiantes_isayana (nombre, identificacion, email, fecha_nacimiento, estado)
    VALUES (p_nombre, p_identificacion, p_email, p_fecha_nacimiento, 1);
END //


# Procedimiento para listar estudiantes
CREATE PROCEDURE ListarEstudiantes()
BEGIN
    SELECT * FROM estudiantes_isayana;
END //


# Procedimiento para modificar un estudiante
CREATE PROCEDURE ModificarEstudiante(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_identificacion VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_fecha_nacimiento DATE
)
BEGIN
    UPDATE estudiantes_isayana
    SET nombre = p_nombre,
        identificacion = p_identificacion,
        email = p_email,
        fecha_nacimiento = p_fecha_nacimiento
    WHERE id = p_id;
END //

# Procedimiento para eliminar un estudiante (marcándolo como inactivo)
CREATE PROCEDURE EliminarEstudiante(IN p_id INT)
BEGIN
    UPDATE estudiantes_isayana SET estado = 0 WHERE id = p_id;
END //

# Procedimiento para listar estudiantes activos
CREATE PROCEDURE ListarEstudiantes()
BEGIN
    SELECT * FROM estudiantes_isayana WHERE estado = 1;
END //

# Profesores
# Procedimiento almacenado para insertar profesores
CREATE PROCEDURE InsertarProfesor(
    IN p_nombre VARCHAR(100),
    IN p_identificacion VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_departamento VARCHAR(100)
)
BEGIN
    INSERT INTO profesores_isayana (nombre, identificacion, email, departamento, estado)
    VALUES (p_nombre, p_identificacion, p_email, p_departamento, 1);
END //

# Procedimiento para listar profesores
CREATE PROCEDURE ListarProfesores()
BEGIN
    SELECT * FROM profesores_isayana;
END //


# Procedimiento para modificar un profesor
CREATE PROCEDURE ModificarProfesor(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_identificacion VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_departamento VARCHAR(100)
)
BEGIN
    UPDATE profesores_isayana
    SET nombre = p_nombre,
        identificacion = p_identificacion,
        email = p_email,
        departamento = p_departamento
    WHERE id = p_id;
END //

# Procedimiento para eliminar un profesor (marcándolo como inactivo)
CREATE PROCEDURE EliminarProfesor(IN p_id INT)
BEGIN
    UPDATE profesores_isayana SET estado = 0 WHERE id = p_id;
END //

# Procedimiento para listar profesores activos
CREATE PROCEDURE ListarProfesores()
BEGIN
    SELECT * FROM profesores_isayana WHERE estado = 1;
END //

# Procedimiento para insertar un grupo
CREATE PROCEDURE InsertarGrupo(
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    INSERT INTO grupos_isayana (nombre, descripcion, estado)
    VALUES (p_nombre, p_descripcion, 1);
END //

# Procedimiento para modificar un grupo
CREATE PROCEDURE ModificarGrupo(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    UPDATE grupos_isayana
    SET nombre = p_nombre,
        descripcion = p_descripcion
    WHERE id = p_id;
END //

# Procedimiento para eliminar un grupo (marcándolo como inactivo)
CREATE PROCEDURE EliminarGrupo(IN p_id INT)
BEGIN
    UPDATE grupos_isayana SET estado = 0 WHERE id = p_id;
END //

# Procedimiento para listar grupos  
CREATE PROCEDURE ListarGrupos()
BEGIN
    SELECT * FROM grupos_isayana WHERE estado = 1;
END //

# Procedimiento para insertar un curso
CREATE PROCEDURE InsertarCurso(
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    INSERT INTO cursos_isayana (nombre, descripcion, estado)
    VALUES (p_nombre, p_descripcion, 1);
END //

# Procedimiento para modificar un curso
CREATE PROCEDURE ModificarCurso(
    IN p_id INT,
    IN p_nombre VARCHAR(100),
    IN p_descripcion TEXT
)
BEGIN
    UPDATE cursos_isayana
    SET nombre = p_nombre,
        descripcion = p_descripcion
    WHERE id = p_id;
END //

# Procedimiento para eliminar un curso (marcándolo como inactivo)
CREATE PROCEDURE EliminarCurso(IN p_id INT)
BEGIN
    UPDATE cursos_isayana SET estado = 0 WHERE id = p_id;
END //

# Procedimiento para listar cursos 
CREATE PROCEDURE ListarCursos()
BEGIN
    SELECT * FROM cursos_isayana WHERE estado = 1;
END //


DELIMITER ;
