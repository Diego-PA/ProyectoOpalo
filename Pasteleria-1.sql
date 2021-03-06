
CREATE TABLE Cancelacion (id_cancelacion SERIAL PRIMARY KEY ,descripcion VARCHAR(75) NOT NULL);
CREATE TABLE Compras (id_compra SERIAL NOT NULL, fechaCompra DATE NOT NULL, CONSTRAINT PK_Compras PRIMARY KEY (id_compra));
CREATE TABLE Cliente (id_cliente SERIAL PRIMARY KEY, nombre VARCHAR(50) NOT NULL, aPaterno VARCHAR(50) NOT NULL, aMaterno VARCHAR(50) NOT NULL, correo VARCHAR(100) NOT NULL, telefono VARCHAR(10) NOT NULL, direccion VARCHAR(100)	NOT NULL);
CREATE TABLE Producto (id_producto SERIAL NOT NULL, nombre VARCHAR(100) NOT NULL, descripcion VARCHAR (100) NOT NULL, existenciaMinima INT NOT NULL, existenciaMaxima INT NOT NULL, existenciaActual INT NOT NULL, CONSTRAINT PK_Producto PRIMARY KEY (id_producto));
CREATE TABLE Factura (id_factura SERIAL PRIMARY KEY, fecha DATE NOT NULL);
CREATE TABLE Insumo (id_Insumo SERIAL PRIMARY KEY, unidadMedida VARCHAR(25) NOT NULL, nombre VARCHAR(50) NOT NULL, existenciaMinima FLOAT NOT NULL, existenciaMaxima FLOAT NOT NULL, existenciaActual FLOAT NOT NULL);
CREATE TABLE Compra_Insumo (id_compra INT REFERENCES Compras(id_compra), id_insumo INT REFERENCES Insumo(id_Insumo), cantidad FLOAT NOT NULL, CHECK (cantidad > 0), costoTotal FLOAT NOT NULL, CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_compra, id_insumo));
CREATE TABLE Receta (id_insumo INT REFERENCES Insumo(id_Insumo), id_producto INT REFERENCES Producto(id_producto), cantidad FLOAT NOT NULL, CHECK (cantidad > 0), CONSTRAINT PK_Receta PRIMARY KEY (id_insumo, id_producto));
CREATE TABLE Precio (id_producto INT REFERENCES Producto(id_producto), fecha DATE NOT NULL, precio FLOAT NOT NULL, CHECK (precio > 0), CONSTRAINT PK_Precio PRIMARY KEY (fecha, id_producto));
CREATE TABLE Venta (id_venta SERIAL PRIMARY KEY, tipoPago VARCHAR(30) NOT NULL, id_cancelacion INT REFERENCES Cancelacion(id_cancelacion), fecha DATE NOT NULL, estado VARCHAR(75) NOT NULL);
CREATE TABLE Venta_Producto (id_venta INT REFERENCES Venta(id_venta), id_producto INT REFERENCES Producto(id_producto), cantidad INT NOT NULL, CHECK (cantidad > 0), CONSTRAINT PK_Ven_Prod PRIMARY KEY (id_venta, id_producto));
CREATE TABLE Venta_Cliente (id_venta INT REFERENCES Venta(id_venta), id_cliente INT REFERENCES Cliente(id_cliente), id_factura INT REFERENCES Factura(id_factura), CONSTRAINT PK_Venta_Producto PRIMARY KEY (id_venta, id_cliente));


/*
DROP TABLE Venta_Cliente;
DROP TABLE Venta_Producto;
DROP TABLE Venta;
DROP TABLE Precio;
DROP TABLE Receta;
DROP TABLE Compra_Insumo;
DROP TABLE Insumo;
DROP TABLE Factura;
DROP TABLE Producto;
DROP TABLE Cliente;
DROP TABLE Compras;
DROP TABLE Cancelacion;
*/

CREATE TABLE Cancelacion (id_cancelacion 	SERIAL 		PRIMARY KEY 
						,descripcion 		VARCHAR(75) NOT NULL
						);


CREATE TABLE Compras (id_compra 	SERIAL 		NOT NULL
					 ,fechaCompra 	DATE 		NOT NULL
					 ,CONSTRAINT 	PK_Compras 	PRIMARY KEY (id_compra)
					 );


CREATE TABLE Cliente (id_cliente 	SERIAL 			PRIMARY KEY
					,nombre 		VARCHAR(50) 	NOT NULL
					,aPaterno 		VARCHAR(50) 	NOT NULL
					,aMaterno 		VARCHAR(50) 	NOT NULL
					,correo 		VARCHAR(100) 	NOT NULL
					,telefono 		VARCHAR(10) 	NOT NULL
					,direccion 		VARCHAR(100)	NOT NULL
					);


CREATE TABLE Producto (id_producto 			SERIAL 			NOT NULL
						,nombre 			VARCHAR(100) 	NOT NULL
						,descripcion 		VARCHAR (100) 	NOT NULL
						,existenciaMinima 	INT 			NOT NULL
						,existenciaMaxima 	INT 			NOT NULL
						,existenciaActual 	INT 			NOT NULL
						,CONSTRAINT 		PK_Producto 	PRIMARY KEY (id_producto)
						);


CREATE TABLE Factura (id_factura 	SERIAL 	PRIMARY KEY
					 ,fecha			DATE 	NOT NULL
					 );


CREATE TABLE Insumo (id_Insumo 			SERIAL 		PRIMARY KEY
					,unidadMedida 		VARCHAR(25) NOT NULL
					,nombre 			VARCHAR(50) NOT NULL
					,existenciaMinima 	FLOAT 		NOT NULL
					,existenciaMaxima 	FLOAT 		NOT NULL
					,existenciaActual 	FLOAT 		NOT NULL
					);


CREATE TABLE Compra_Insumo (id_compra 	INT 	REFERENCES Compras(id_compra)
							,id_insumo 	INT 	REFERENCES Insumo(id_Insumo)
							,cantidad 	FLOAT 	NOT NULL, CHECK (cantidad > 0)
							,costoTotal FLOAT 	NOT NULL
							,CONSTRAINT PK_Compra_Insumo PRIMARY KEY (id_compra, id_insumo)
							);


CREATE TABLE Receta (id_insumo 		INT 		REFERENCES Insumo(id_Insumo)
					,id_producto 	INT 		REFERENCES Producto(id_producto)
					,cantidad 		FLOAT 		NOT NULL, CHECK (cantidad > 0),
					CONSTRAINT 		PK_Receta 	PRIMARY KEY (id_insumo, id_producto)
					);


CREATE TABLE Precio (id_producto 	INT 		REFERENCES Producto(id_producto)
					,fecha 			DATE 		NOT NULL
					,precio 		FLOAT 		NOT NULL, CHECK (precio > 0)
					,CONSTRAINT 	PK_Precio 	PRIMARY KEY (fecha, id_producto)
					);


CREATE TABLE Venta (id_venta 		SERIAL 		PRIMARY KEY
					,tipoPago 		VARCHAR(30) NOT NULL
					,id_cancelacion INT 		REFERENCES Cancelacion(id_cancelacion)
					,fecha 			DATE 		NOT NULL
					,estado 		VARCHAR(75) NOT NULL
					);


CREATE TABLE Venta_Producto (id_venta 		INT 		REFERENCES Venta(id_venta)
							,id_producto 	INT 		REFERENCES Producto(id_producto)
							,cantidad 		INT 		NOT NULL, CHECK (cantidad > 0)
							,CONSTRAINT 	PK_Ven_Prod PRIMARY KEY (id_venta, id_producto)
							);


CREATE TABLE Venta_Cliente (id_venta 	INT 				REFERENCES Venta(id_venta)
							,id_cliente INT 				REFERENCES Cliente(id_cliente)
							,id_factura INT 				REFERENCES Factura(id_factura)
							,CONSTRAINT PK_Venta_Producto 	PRIMARY KEY (id_venta, id_cliente)
							);
