CREATE DATABASE IF NOT EXISTS Textiles;
USE Textiles;

-- =========================
-- ROLES
-- =========================
CREATE TABLE Roles (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombreRol VARCHAR(20) NOT NULL UNIQUE,
    
    CONSTRAINT nombreRol_no_vacio CHECK (LENGTH(nombreRol) > 0),
    CONSTRAINT nombreRol_valido CHECK (nombreRol REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$')
) ENGINE=InnoDB;

-- =========================
-- USUARIOS
-- =========================
CREATE TABLE Usuarios (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre1 VARCHAR(20) NOT NULL,
    nombre2 VARCHAR(20),
    apellido1 VARCHAR(20) NOT NULL,
    apellido2 VARCHAR(20),
    telefono VARCHAR(10) NOT NULL UNIQUE,
    usuario VARCHAR(20) NOT NULL UNIQUE,
    contrasenia VARCHAR(255) NOT NULL,
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

    -- Validaciones
    CONSTRAINT telefono_no_vacio CHECK (LENGTH(telefono) = 10),
    CONSTRAINT telefono_solo_num CHECK (telefono REGEXP '^[0-9]+$'),
    CONSTRAINT nombre1_no_vacio CHECK (LENGTH(nombre1) > 0),
    CONSTRAINT apellido1_no_vacio CHECK (LENGTH(apellido1) > 0),
    CONSTRAINT nombre1_valido CHECK (nombre1 REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$'),
    CONSTRAINT nombre2_valido CHECK (nombre2 IS NULL OR nombre2 REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$'),
    CONSTRAINT apellido1_valido CHECK (apellido1 REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$'),
    CONSTRAINT apellido2_valido CHECK (apellido2 IS NULL OR apellido2 REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$'),
    CONSTRAINT usuario_valido CHECK (usuario REGEXP '^[A-Za-z0-9_]+$'),
    
    INDEX idx_usuario_rol (idRol)
) ENGINE=InnoDB;

-- =========================
-- MATERIAS PRIMAS
-- =========================
CREATE TABLE MateriasPrimas (
    idMateria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    foto MEDIUMBLOB,
    costo DECIMAL(12,2) NOT NULL CHECK (costo >= 0),
    descripcion VARCHAR(255) NOT NULL,
    
    CONSTRAINT nombre_prima_no_vacio CHECK (LENGTH(nombre) > 0),
    CONSTRAINT descripcion_prima_no_vacia CHECK (LENGTH(descripcion) > 0),
    CONSTRAINT nombre_prima_valido CHECK (nombre REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$')
) ENGINE=InnoDB;

-- =========================
-- PRODUCTOS
-- =========================
CREATE TABLE Productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    foto MEDIUMBLOB,
    precio DECIMAL(12,2) NOT NULL CHECK (precio >= 0),
    descripcion VARCHAR(255) NOT NULL,
    
    CONSTRAINT nombre_prod_no_vacio CHECK (LENGTH(nombre) > 0),
    CONSTRAINT descripcion_prod_no_vacia CHECK (LENGTH(descripcion) > 0),
    CONSTRAINT nombre_prod_valido CHECK (nombre REGEXP '^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+([[:space:]]|[''-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$')
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
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_VentaProducto_Productos 
        FOREIGN KEY (idProducto) REFERENCES Productos(idProducto)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

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
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_CompraMateria_Materias 
        FOREIGN KEY (idMateria) REFERENCES MateriasPrimas(idMateria)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    INDEX idx_detalle_compra (idCompra),
    INDEX idx_detalle_materia (idMateria)
) ENGINE=InnoDB;
