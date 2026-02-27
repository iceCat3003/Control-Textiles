CREATE DATABASE IF NOT EXISTS Textiles;
USE Textiles;

-- =========================
-- ROLES
-- =========================
CREATE TABLE Roles (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(20) NOT NULL UNIQUE CHECK (LENGTH(nombreRol) > 0)
) ENGINE=InnoDB;

-- =========================
-- USUARIOS
-- =========================
CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre1 VARCHAR(20) NOT NULL CHECK (LENGTH(nombre1) > 0),
    nombre2 VARCHAR(20),
    apellido1 VARCHAR(20) NOT NULL CHECK (LENGTH(apellido1) > 0),
    apellido2 VARCHAR(20),
    telefono VARCHAR(13) NOT NULL UNIQUE CHECK (LENGTH(telefono) = 13),
    usuario VARCHAR(20) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL, -- permitir hash
    estadoUsuario ENUM ('ACTIVO','SUSPENDIDO','BLOQUEADO','BAJA') 
        NOT NULL DEFAULT 'ACTIVO',
    nivelAcceso ENUM ('ALTO','MEDIO','BAJO') NOT NULL,
    idRol INT NOT NULL,
    imagen MEDIUMBLOB,
    salario DECIMAL(12,2) NOT NULL CHECK (salario >= 0),

    CONSTRAINT fk_Usuarios_Roles 
        FOREIGN KEY (idRol) REFERENCES Roles(idRol)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    INDEX idx_usuario_rol (idRol)
) ENGINE=InnoDB;

-- =========================
-- MATERIAS PRIMAS
-- =========================
CREATE TABLE MateriasPrimas (
    idMateria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL CHECK (LENGTH(nombre) > 0),
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    foto MEDIUMBLOB,
    costo DECIMAL(12,2) NOT NULL CHECK (costo >= 0),
    descripcion VARCHAR(255) NOT NULL CHECK (LENGTH(descripcion) > 0)
) ENGINE=InnoDB;

-- =========================
-- PRODUCTOS
-- =========================
CREATE TABLE Productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL CHECK (LENGTH(nombre) > 0),
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    foto MEDIUMBLOB,
    precio DECIMAL(12,2) NOT NULL CHECK (precio >= 0),
    descripcion VARCHAR(255) NOT NULL CHECK (LENGTH(descripcion) > 0)
) ENGINE=InnoDB;

-- =========================
-- VENTAS
-- =========================
CREATE TABLE Ventas (
    idVenta INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(14,2) NOT NULL CHECK (total >= 0),
    estado ENUM ('COMPLETADO','ESPERA','CANCELADO') 
        NOT NULL DEFAULT 'COMPLETADO',
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idUsuario INT NOT NULL,

    CONSTRAINT fk_Ventas_Usuarios 
        FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    INDEX idx_ventas_usuario (idUsuario),
    INDEX idx_ventas_fecha (fecha)
) ENGINE=InnoDB;

-- =========================
-- COMPRAS
-- =========================
CREATE TABLE Compras (
    idCompra INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(14,2) NOT NULL CHECK (total >= 0),
    estado ENUM ('COMPLETADO','ESPERA','CANCELADO') 
        NOT NULL DEFAULT 'COMPLETADO',
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    idUsuario INT NOT NULL,

    CONSTRAINT fk_Compras_Usuarios 
        FOREIGN KEY (idUsuario) REFERENCES Usuarios(idUsuario)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    INDEX idx_compras_usuario (idUsuario),
    INDEX idx_compras_fecha (fecha)
) ENGINE=InnoDB;

-- =========================
-- DETALLE VENTA
-- =========================
CREATE TABLE Venta_Producto (
    idVentaProducto INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precioUnitario DECIMAL(12,2) NOT NULL CHECK (precioUnitario >= 0),

    CONSTRAINT fk_VentaProducto_Ventas 
        FOREIGN KEY (idVenta) REFERENCES Ventas(idVenta)
        ON DELETE CASCADE,

    CONSTRAINT fk_VentaProducto_Productos 
        FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
        ON DELETE RESTRICT,

    INDEX idx_detalle_venta (idVenta),
    INDEX idx_detalle_producto (idProducto)
) ENGINE=InnoDB;

-- =========================
-- DETALLE COMPRA
-- =========================
CREATE TABLE Compra_Materia (
    idCompraMateria INT AUTO_INCREMENT PRIMARY KEY,
    idCompra INT NOT NULL,
    idMateria INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    costoUnitario DECIMAL(12,2) NOT NULL CHECK (costoUnitario >= 0),

    CONSTRAINT fk_CompraMateria_Compras 
        FOREIGN KEY (idCompra) REFERENCES Compras(idCompra)
        ON DELETE CASCADE,

    CONSTRAINT fk_CompraMateria_Materias 
        FOREIGN KEY (idMateria) REFERENCES MateriasPrimas(idMateria)
        ON DELETE RESTRICT,

    INDEX idx_detalle_compra (idCompra),
    INDEX idx_detalle_materia (idMateria)
) ENGINE=InnoDB;
