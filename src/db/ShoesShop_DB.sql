
-- ==========================================
-- BASE DE DATOS SHOES SHOP
-- ==========================================

-- TABLA MARCAS
CREATE TABLE marcas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- TABLA SEXOS
CREATE TABLE sexos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    genero VARCHAR(100) NOT NULL
);

-- TABLA COLORES
CREATE TABLE color(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- TABLA TIPOS
CREATE TABLE tipos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- TABLA MATERIALES
CREATE TABLE material(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL
);

-- ==========================================
-- TABLA ZAPATILLAS
-- ==========================================

CREATE TABLE zapatillas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio INT NOT NULL,
    imagen VARCHAR(255),
    marca_id INT,
    sexo_id INT,

    CONSTRAINT fk_zapatillas_marcas
        FOREIGN KEY (marca_id)
        REFERENCES marcas(id),

    CONSTRAINT fk_zapatillas_sexos
        FOREIGN KEY (sexo_id)
        REFERENCES sexos(id)
);

-- ==========================================
-- TABLA COLORES_ZAPATILLAS
-- ==========================================

CREATE TABLE colores_zapatillas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    zapatilla_id INT,
    color_id INT,

    CONSTRAINT fk_colores_zapatillas
        FOREIGN KEY (zapatilla_id)
        REFERENCES zapatillas(id),

    CONSTRAINT fk_colores_color
        FOREIGN KEY (color_id)
        REFERENCES color(id)
);

-- ==========================================
-- TABLA TIPOS_ZAPATILLAS
-- ==========================================

CREATE TABLE tipos_zapatillas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    zapatilla_id INT,
    tipo_id INT,

    CONSTRAINT fk_tipos_zapatillas
        FOREIGN KEY (zapatilla_id)
        REFERENCES zapatillas(id),

    CONSTRAINT fk_tipos_tipo
        FOREIGN KEY (tipo_id)
        REFERENCES tipos(id)
);

-- ==========================================
-- TABLA MATERIALES_ZAPATILLAS
-- ==========================================

CREATE TABLE materiales_zapatillas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    zapatilla_id INT,
    material_id INT,

    CONSTRAINT fk_materiales_zapatillas
        FOREIGN KEY (zapatilla_id)
        REFERENCES zapatillas(id),

    CONSTRAINT fk_materiales_material
        FOREIGN KEY (material_id)
        REFERENCES material(id)
);

-- ==========================================
-- INSERTAR DATOS INICIALES
-- ==========================================

INSERT INTO marcas(nombre) VALUES
('Nike'),
('Adidas'),
('Puma');

INSERT INTO sexos(genero) VALUES
('Hombre'),
('Mujer');

INSERT INTO color(nombre) VALUES
('Negro'),
('Blanco'),
('Rojo');

INSERT INTO tipos(nombre) VALUES
('Running'),
('Basketball'),
('Casual');

INSERT INTO material(nombre) VALUES
('Cuero'),
('Malla'),
('Sintetico');

-- ZAPATILLAS CON IMAGEN
INSERT INTO zapatillas(nombre, precio, imagen, marca_id, sexo_id)
VALUES
('Nike Air Max', 89990, '/img/nike-air-max.jpg', 1, 1),
('Adidas Superstar', 79990, '/img/adidas-superstar.jpg', 2, 2);

-- ==========================================
-- RELACIONES DE ZAPATILLAS
-- ==========================================

INSERT INTO colores_zapatillas(zapatilla_id, color_id)
VALUES
(1,1),
(1,2),
(2,3);

INSERT INTO tipos_zapatillas(zapatilla_id, tipo_id)
VALUES
(1,1),
(2,3);

INSERT INTO materiales_zapatillas(zapatilla_id, material_id)
VALUES
(1,2),
(2,1);

-- ==========================================
-- TABLAS DE BOLETAS
-- ==========================================

CREATE TABLE metodoEnvio(
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL,
    costo INT NOT NULL,
    tiempoEntrega INT NOT NULL
);

CREATE TABLE metodoPago(
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100) NOT NULL
);

CREATE TABLE boleta(
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    total INT NOT NULL,
    metodoEnvio_id INT NOT NULL,
    metodoPago_id INT NOT NULL,

    CONSTRAINT fk_boleta_metodoEnvio
        FOREIGN KEY (metodoEnvio_id)
        REFERENCES metodoEnvio(id),

    CONSTRAINT fk_boleta_metodoPago
        FOREIGN KEY (metodoPago_id)
        REFERENCES metodoPago(id)
);

CREATE TABLE boletas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    boleta_id INT NOT NULL,

    CONSTRAINT fk_boletas_boleta
        FOREIGN KEY (boleta_id)
        REFERENCES boleta(id)
);

-- ==========================================
-- DATOS INICIALES DE BOLETAS
-- ==========================================

INSERT INTO metodoEnvio (id, tipo, costo, tiempoEntrega)
VALUES
(1, 'Retiro en tienda', 0, 0),
(2, 'Despacho a domicilio', 3990, 3);

INSERT INTO metodoPago (id, tipo)
VALUES
(1, 'Debito'),
(2, 'Credito');

INSERT INTO boleta (id, fecha, cantidad, total, metodoEnvio_id, metodoPago_id)
VALUES
(1, '2026-06-28', 1, 89990, 1, 1),
(2, '2026-06-28', 1, 79990, 2, 2);

INSERT INTO boletas (id, boleta_id)
VALUES
(1, 1),
(2, 2);
