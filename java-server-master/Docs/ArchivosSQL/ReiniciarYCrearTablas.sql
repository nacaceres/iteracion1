----------------------------------------------------------------------------
----------------------DROPEAR TODO------------------------------------------
----------------------------------------------------------------------------
drop table "ALOJAMIENTOS" cascade constraints PURGE;
drop table "CLIENTES" cascade constraints PURGE;
drop table "CONTRATOS" cascade constraints PURGE;
drop table "HABITACIONES_HOSTAL" cascade constraints PURGE;
drop table "HABITACIONES_HOTEL" cascade constraints PURGE;
drop table "HABITACIONES_VIV_UNI" cascade constraints PURGE;
drop table "LES_GUSTA" cascade constraints PURGE;
drop table "OPERADORES" cascade constraints PURGE;
drop table "RELACIONES" cascade constraints PURGE;
drop table "RESERVAS" cascade constraints PURGE;
drop table "SE_HAN_QUEDADO" cascade constraints PURGE;
drop table "SEGUROS" cascade constraints PURGE;
drop table "SERVICIOS" cascade constraints PURGE;
drop table "SERVICIOS_ADICIONALES" cascade constraints PURGE;
drop table "VIVIENDAS" cascade constraints PURGE;
drop table "APARTAMENTOS" cascade constraints PURGE;
drop table "SERVICIOS_OFRECIDOS" cascade constraints PURGE;



--------------------------------CREACION DE TABLAS PRINCIPALES CON ID PROPIA -----------------------------------

CREATE TABLE  RELACIONES
(
ID NUMBER(5,0) NOT NULL UNIQUE,
TIPO   VARCHAR2(40) NOT NULL,
CARNET   VARCHAR2(30),
CONSTRAINT CHECK_CARNET_TIPO_DD
CHECK((CARNET IS  NULL AND TIPO IN('HOTEL','HOSTAL','VECINO','FENICIA','ADMININISTRADOR VIVIENDA UNIVERSITARIA','REGISTRADO'))
OR (CARNET IS NOT NULL AND TIPO IN('ESTUDIANTE' ,'PROFESOR', 'EMPLEADO','PROFESOR INVITADO','EGRESADO','PADRE ESTUDIANTE'))),
CONSTRAINT CHECK_TIPO_RELA
CHECK (TIPO IN ('ESTUDIANTE','HOSTAL' ,'PROFESOR', 'HOTEL', 'EMPLEADO','VECINO','FENICIA','PROFESOR INVITADO','REGISTRADO','EGRESADO','PADRE ESTUDIANTE','ADMININISTRADOR VIVIENDA UNIVERSITARIA' ))
);

CREATE TABLE  CLIENTES
(
ID NUMBER(15,0) NOT NULL UNIQUE,
TIPO_ID   VARCHAR2(3) NOT NULL,
NOMBRE VARCHAR(20) NOT NULL,
CONTACTO VARCHAR2(50)NOT NULL,
ID_RELACION NUMBER(5,0) NOT NULL UNIQUE,
CONSTRAINT CHECK_TIPO_ID_CLI
CHECK (TIPO_ID IN ('CC', 'CE', 'NIT'))
);

CREATE TABLE  OPERADORES
(
ID NUMBER(15,0) NOT NULL UNIQUE,
TIPO_ID   VARCHAR2(3) NOT NULL,
NOMBRE VARCHAR(20) NOT NULL,
CONTACTO VARCHAR2(50)NOT NULL,
ID_RELACION NUMBER(5,0) NOT NULL UNIQUE,
 CONSTRAINT CHECK_TIPO_ID_OPER
CHECK (TIPO_ID IN ('CC', 'CE', 'NIT'))
);

CREATE TABLE  SERVICIOS
(
ID NUMBER(4,0) NOT NULL UNIQUE,
NOMBRE   VARCHAR2(20) NOT NULL,
DESCRIPCION   VARCHAR2(60) NOT NULL,
COSTO_ADICIONAL   NUMBER(7,0),
 CONSTRAINT CHECK_COSTO_ADICIONAL_SERV
CHECK (COSTO_ADICIONAL>=0)
);

CREATE  TABLE ALOJAMIENTOS(
ID NUMBER(5,0) NOT NULL UNIQUE,
UBICACION VARCHAR2(60) NOT NULL,
COSTO_BASICO NUMBER(10,2) NOT NULL,
CAPACIDAD NUMBER(2,0) NOT NULL,
VIGENTE  VARCHAR2(1) DEFAULT 'T' NOT NULL ,
FECHA_RETIRO DATE  DEFAULT NULL,
TIPO VARCHAR2(40) NOT NULL,
ID_OPERADOR NUMBER(15,0) NOT NULL,
CONSTRAINT CHECK_TIPO_VALUES_ALOJ
CHECK (TIPO IN ('HAB UNIVERSITARIA','APARTAMENTO','HAB HOSTAL','HAB HOTEL','VIVIENDA'))
);
------------------------

CREATE  TABLE RESERVAS(
ID NUMBER(5,0) NOT NULL UNIQUE,--NUM_DIAS NUMBER(3,0) NOT NULL,-->
FECHA_INICIO DATE  NOT NULL,
FECHA_FIN DATE NOT NULL,
NUM_PERSONAS NUMBER(2,0) NOT NULL ,
CANCELADA VARCHAR2(1) DEFAULT'F',
FECHA_CANCELACION DATE DEFAULT NULL,
COSTO_DEFINITIVO NUMBER(10,2) NOT NULL,
TIEMPO_OPORTUNO DATE NOT NULL,
TERMINADA VARCHAR2(1) DEFAULT'F' NOT NULL,
ID_ALOJAMIENTO NUMBER NOT NULL,
ID_CLIENTE NUMBER NOT NULL,
COLECTIVA VARCHAR2(1) DEFAULT'F',
ID_COLECTIVA NUMBER(8,0) DEFAULT NULL,

CONSTRAINT CHECK_NUM_PERSONAS_VALUE
CHECK (NUM_PERSONAS >0),

 CONSTRAINT CHECK_COSTO_DEFINITIVO_VALUE
CHECK (COSTO_DEFINITIVO>0),
CONSTRAINT  CHECK_CANCELACION
CHECK ((CANCELADA='T') OR (CANCELADA='F' AND FECHA_CANCELACION IS   NULL)),
CONSTRAINT CHECK_COLECTIVA_VALUE CHECK ((COLECTIVA='T' AND ID_COLECTIVA IS NOT  NULL) OR (COLECTIVA='F' AND ID_COLECTIVA IS   NULL)),
CONSTRAINT CHECK_FECHAS CHECK ((FECHA_CANCELACION <=  FECHA_INICIO)AND( FECHA_INICIO<= FECHA_FIN))
);

CREATE  TABLE CONTRATOS(
ID NUMBER(6,0) NOT NULL UNIQUE,
NUM_DIAS NUMBER(3,0) NOT NULL,
PAGO_ANTICIPADO  VARCHAR2(1) NOT NULL,
INCLUYE_SERVICIOS  VARCHAR2(1) NOT NULL,
INCLUYE_ADMON  VARCHAR2(1) NOT NULL,
FECHA DATE  NOT NULL,
COSTO_DEFINITIVO NUMBER(10,2) NOT NULL,
ID_CLIENTE NUMBER NOT NULL,
ID_ALOJAMIENTO NUMBER(5,0) NOT NULL,
CONSTRAINT CHECK_NUM_DIAS_VALUE_CT
CHECK (NUM_DIAS>0),
CONSTRAINT CHECK_COS_DEF_VALUE_CT
CHECK (COSTO_DEFINITIVO>0)
);
-------------------CREACION DE TABLAS PARA LAS SUBCLASES DE ALOJAMIENTO

CREATE TABLE VIVIENDAS
(
ID NUMBER(5,0) NOT NULL UNIQUE,
NUM_HABITACIONES NUMBER(2,0),
CEDIDO  VARCHAR2(1) NOT NULL,
COMPARTIDO  VARCHAR2(1)  NOT NULL,
CONSTRAINT CHECK_NUM_HABIT_VAL_VIV
CHECK (NUM_HABITACIONES >0),
CONSTRAINT CHECK_COMPART_VS_CED
CHECK((NUM_HABITACIONES IS NULL AND CEDIDO='F') OR ((NUM_HABITACIONES IS NOT NULL AND CEDIDO='T')) )
);

CREATE TABLE HABITACIONES_HOTEL
(
ID NUMBER(5,0) NOT NULL UNIQUE,
TIPO_HABITACION VARCHAR(20),
CONSTRAINT CHECK_TIPO_HABIT_VALUE_H_H
CHECK(TIPO_HABITACION  IN('SUITE','SEMISUITE','ESTANDAR') )
);

CREATE TABLE HABITACIONES_HOSTAL
(
ID NUMBER(5,0) NOT NULL UNIQUE,
HORARIO_APERTURA DATE ,
HORARIO_CIERRE DATE, 
COMPARTIDA VARCHAR2(1)
);

CREATE TABLE HABITACIONES_VIV_UNI
(
ID NUMBER(5,0) NOT NULL UNIQUE,
DURACION_DE_HAB NUMBER(3,0) NOT NULL ,
CONSTRAINT CHECK_DUR_DE_HAB_VAL_V_U
CHECK(DURACION_DE_HAB  IN(1,180,360) )
);

CREATE TABLE APARTAMENTOS
(
ID NUMBER(5,0) NOT NULL UNIQUE,
AMOBLADO  VARCHAR2(1) NOT NULL 
);
----------------------------------
CREATE TABLE SEGUROS
(
ID NUMBER(5,0) NOT NULL UNIQUE,
VALOR NUMBER(10,2)  NOT NULL ,
ID_CONTRATO NUMBER(5,0)  UNIQUE NOT NULL 
);
------------------------TABLAS QUE HACEN REFERENCIA A RELACIONES MUCHOS AMUCHOS O TIENE------------------------
CREATE TABLE SE_HAN_QUEDADO
(
ID_ALOJAMIENTO NUMBER(5,0)   NOT NULL ,
ID_CLIENTE NUMBER(15,0)   NOT NULL ,
CONSTRAINT SE_HAN_QUEDADO_PK PRIMARY KEY(ID_ALOJAMIENTO,ID_CLIENTE)
);

CREATE TABLE LES_GUSTA
(
ID_SERVICIO NUMBER(4,0)   NOT NULL ,
ID_CLIENTE NUMBER(15,0)   NOT NULL ,
CONSTRAINT LES_GUSTA_PK PRIMARY KEY(ID_SERVICIO,ID_CLIENTE)
);

------CREACION DE FOREIGN KEYS
ALTER TABLE LES_GUSTA
ADD CONSTRAINT FK_F_SERVICO
FOREIGN KEY(ID_SERVICIO) REFERENCES SERVICIOS(ID);

ALTER TABLE LES_GUSTA
ADD CONSTRAINT FK_F_CLIENTES
FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES(ID);


----------------

CREATE TABLE SERVICIOS_OFRECIDOS
(
ID_ALOJAMIENTO NUMBER(5,0)   NOT NULL ,
ID_SERVICIO NUMBER(4,0)   NOT NULL ,
CONSTRAINT SERVICIOS_OFRECIDOS_PK PRIMARY KEY(ID_ALOJAMIENTO,ID_SERVICIO)
);
--------------

ALTER TABLE SERVICIOS_OFRECIDOS
ADD CONSTRAINT FK_F_ALOJAMIENTO
FOREIGN KEY(ID_ALOJAMIENTO) REFERENCES ALOJAMIENTOS(ID);



ALTER TABLE SERVICIOS_OFRECIDOS
ADD CONSTRAINT FK_F_SERVICIO
FOREIGN KEY(ID_SERVICIO) REFERENCES SERVICIOS(ID);


CREATE TABLE SERVICIOS_ADICIONALES
(
ID_RESERVA NUMBER(5,0)   NOT NULL ,
ID_SERVICIO NUMBER(4,0)   NOT NULL ,
CONSTRAINT SERVICIOS_ADICIONALES_PK PRIMARY KEY(ID_RESERVA,ID_SERVICIO)
);

---------CREACION DE FOREIGN KEYS
ALTER TABLE SERVICIOS_ADICIONALES
ADD CONSTRAINT FK_F_RESERVA
FOREIGN KEY(ID_RESERVA) REFERENCES RESERVAS(ID);



ALTER TABLE SERVICIOS_ADICIONALES
ADD CONSTRAINT FK_F_SERVICIO_1
FOREIGN KEY(ID_SERVICIO) REFERENCES SERVICIOS(ID);






-------------------------------CREACION DE FOREIGN KEYS PARA TABLAS PRINCIPALES--------------------------

ALTER TABLE CLIENTES
ADD CONSTRAINT FK_F_RELACION
FOREIGN KEY(ID_RELACION) REFERENCES RELACIONES(ID);


ALTER TABLE OPERADORES
ADD CONSTRAINT FK_F_RELACION_1
FOREIGN KEY(ID_RELACION) REFERENCES RELACIONES(ID);


ALTER TABLE RESERVAS
ADD CONSTRAINT FK_F_ALOJAMIENTO_1
FOREIGN KEY(ID_ALOJAMIENTO) REFERENCES ALOJAMIENTOS(ID);


ALTER TABLE RESERVAS
ADD CONSTRAINT FK_F_CLIENTE
FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES(ID);


ALTER TABLE CONTRATOS
ADD CONSTRAINT FK_F_ALOJAMIENTO_C
FOREIGN KEY(ID_ALOJAMIENTO) REFERENCES ALOJAMIENTOS(ID);


ALTER TABLE CONTRATOS
ADD CONSTRAINT FK_F_CLIENTE_C
FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES(ID);




ALTER TABLE ALOJAMIENTOS 
ADD CONSTRAINT FK_F_OPERADOR_A
FOREIGN KEY(ID_OPERADOR) REFERENCES OPERADORES(ID);

ALTER TABLE VIVIENDAS
ADD CONSTRAINT FK_F_ALOJAMIENTOS_V
FOREIGN KEY(ID) REFERENCES ALOJAMIENTOS (ID);

ALTER TABLE HABITACIONES_HOTEL
ADD CONSTRAINT FK_F_ALOJAM_HEL
FOREIGN KEY(ID) REFERENCES ALOJAMIENTOS (ID);


ALTER TABLE HABITACIONES_HOSTAL
ADD CONSTRAINT FK_F_ALOJAMIENTOS_H_HS
FOREIGN KEY(ID) REFERENCES ALOJAMIENTOS (ID);

ALTER TABLE HABITACIONES_VIV_UNI
ADD CONSTRAINT FK_F_ALOJAMIENTOS_H_U
FOREIGN KEY(ID) REFERENCES ALOJAMIENTOS (ID);


ALTER TABLE APARTAMENTOS
ADD CONSTRAINT FK_F_ALOJAMIENTOS_A
FOREIGN KEY(ID) REFERENCES ALOJAMIENTOS (ID);

ALTER TABLE SEGUROS
ADD CONSTRAINT FK_F_CONTRATOS_S
FOREIGN KEY(ID_CONTRATO) REFERENCES CONTRATOS (ID);


ALTER TABLE SE_HAN_QUEDADO
ADD CONSTRAINT FK_F_CLIENTES_S
FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTES (ID);


ALTER TABLE SE_HAN_QUEDADO
ADD CONSTRAINT FK_F_CLIENTES_S_H
FOREIGN KEY(ID_ALOJAMIENTO) REFERENCES ALOJAMIENTOS (ID);