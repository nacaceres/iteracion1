-----------------------------------------------------------------------------------------------------------------
-------------------------------------------------INSERTIONS--------------------------------------------------------
-------------------------------------------------------------------------------------------------------------

-----------
DELETE FROM RELACIONES WHERE ID IS NOT NULL;
DELETE FROM CLIENTES WHERE ID IS NOT NULL;
DELETE FROM OPERADORES WHERE ID IS NOT NULL;
DELETE FROM SERVICIOS WHERE ID IS NOT NULL;

DELETE FROM ALOJAMIENTOS WHERE ID IS NOT NULL;
DELETE FROM RESERVAS WHERE ID IS NOT NULL;
DELETE FROM CONTRATOS  WHERE ID IS NOT NULL;

DELETE FROM HABITACIONES_HOTEL  WHERE ID IS NOT NULL;
DELETE FROM HABITACIONES_HOSTAL  WHERE ID IS NOT NULL;
DELETE FROM APARTAMENTOS  WHERE ID IS NOT NULL;
DELETE FROM HABITACIONES_VIV_UNI  WHERE ID IS NOT NULL;
DELETE FROM VIVIENDAS  WHERE ID IS NOT NULL;


DELETE FROM SEGUROS  WHERE ID IS NOT NULL;
DELETE FROM SE_HAN_QUEDADO  WHERE ID_ALOJAMIENTO IS NOT NULL;
DELETE FROM SERVICIOS_ADICIONALES  WHERE ID_RESERVA IS NOT NULL;
DELETE FROM SERVICIOS_OFRECIDOS  WHERE ID_ALOJAMIENTO IS NOT NULL;
DELETE FROM LES_GUSTA  WHERE ID_CLIENTE IS NOT NULL;









----------------------------RELACIONES-----------------------------
-----------------DATA
Insert into RELACIONES(ID,TIPO,CARNET) VALUES('1','ESTUDIANTE','20163692');
Insert into RELACIONES(ID,TIPO,CARNET) VALUES('2','ESTUDIANTE','201418068');
Insert into RELACIONES(ID,TIPO,CARNET) VALUES('3','PROFESOR','24038');


Insert into RELACIONES(ID,TIPO,CARNET) VALUES('4','PADRE ESTUDIANTE','20163692');
Insert into RELACIONES(ID,TIPO) VALUES('5','FENICIA');
Insert into RELACIONES(ID,TIPO) VALUES('6','HOTEL');
Insert into RELACIONES(ID,TIPO) VALUES('7','HOSTAL');
Insert into RELACIONES (ID,TIPO) VALUES('8','ADMININISTRADOR VIVIENDA UNIVERSITARIA');
Insert into RELACIONES(ID,TIPO,CARNET) VALUES('9','PROFESOR','24089');



----------------------OPERADORES----------------------

-----------------DATA
Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('98247453','CC','CACERES','DIRECCION:CARRERA 69 CALLE169  ','4');
Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('23582001','CC','EL VECI','DIRECCION:CARRERA 1 CALLE20  ','5');
Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('4520202','NIT','BOGOTA REAL','DIRECCION:CALLE 19 Y AJA  ','6');
Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('967202','NIT','MAMBO','DIRECCION:EL CHORRO ','7');

Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('282345','NIT','CITY U','DIRECCION:CITY U  ','8');
Insert into OPERADORES(ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION) VALUES('132456','CC','EDNA','DIRECCION:CALLE 26 Y 7A  ','9');

--------------CLIENTES----------------------

---------DATA
Insert into CLIENTES (ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION)  VALUES('1020567','CC','EL DT','DIRECCION:CALLE 173 A, TEL:31158222','1');
Insert into CLIENTES (ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION)  VALUES('1101567896','CC','MAICOLSITO','TEL:310267','2');
Insert into CLIENTES (ID,TIPO_ID,NOMBRE,CONTACTO,ID_RELACION)  VALUES('183456','CC','MAURICIO','TEL:310267','3');


------SERVICIOS----------------------------------
Insert into SERVICIOS (ID,NOMBRE,DESCRIPCION,COSTO_ADICIONAL) VALUES ('1','wi-fi','conexion a internet','10000');
Insert into SERVICIOS (ID,NOMBRE,DESCRIPCION,COSTO_ADICIONAL) VALUES ('2','GASEOSA','consumo de bebidas en la habitacion','5000');
Insert into SERVICIOS (ID,NOMBRE,DESCRIPCION,COSTO_ADICIONAL) VALUES ('3','ALmuerzo','almuerzo, valor diario. halp nico','10000');
Insert into SERVICIOS (ID,NOMBRE,DESCRIPCION,COSTO_ADICIONAL) VALUES ('4','lavado de ropa','lavado de ropa:valormensual ','1000000');

--ALOOJAMIENTOS

--HOTEL
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('1','en el bog re','85000', '2','4520202','HAB HOTEL');
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('2','en el bog re','45000', '1','4520202','HAB HOTEL');
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('3','en el bog re','110500', '4','4520202','HAB HOTEL');
---LA PIEZA

Insert into Alojamientos(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('4','en la primera','450000', '1','23582001','VIVIENDA');

---APARTAMENTO
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('5','SANTO DOMINGO','110500', '4','98247453','APARTAMENTO');
--EL HOSTAL
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('6','el chorro','15000', '1','967202','HAB HOSTAL');
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('7','el chorro','20000', '2','967202','HAB HOSTAL');
--EL CEDIDO
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('10','26 Y7','340000','4','132456','VIVIENDA');

----LA UNIVERSITARIA
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('8','TORRE1 ','680000', '1','282345','HAB UNIVERSITARIA');
Insert into ALOJAMIENTOS(ID,UBICACION,COSTO_BASICO,CAPACIDAD,ID_OPERADOR,TIPO) values('9','TORRE 2','20000', '1','282345','HAB UNIVERSITARIA');

-----RESERVAS-------------------------
Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE)
values('1','30/08/18','04/09/18','2','85000,12','27/08/18','1','1020567');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE)
values('2','20/10/18','22/10/18','1','45000,12','17/10/18','2','1020567');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE)
values('4','20/10/18','20/11/18','1','450000','13/10/18','4','1101567896');


Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE)
values('5','20/10/18','24/11/18','3','4509000','13/10/18','7','1101567896');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('20000','30/08/17','04/09/17','2','85000,12','27/08/17','1','1020567','T');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('20002','20/10/17','22/10/17','1','45000,12','17/10/17','2','1020567','T');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('20004','20/10/17','20/11/17','1','450000','13/10/17','4','1101567896','T');


Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('20005','20/10/17','24/11/17','3','4509000','13/10/17','7','1101567896','T');


--------------CONTRATOS-----------------------------------
Insert into CONTRATOS(ID,NUM_DIAS,PAGO_ANTICIPADO,INCLUYE_SERVICIOS,INCLUYE_ADMON,FECHA,COSTO_DEFINITIVO,ID_CLIENTE, ID_ALOJAMIENTO)
values('1','30','F','T','F','01/04/18','450000','183456','4');

Insert into CONTRATOS(ID,NUM_DIAS,PAGO_ANTICIPADO,INCLUYE_SERVICIOS,INCLUYE_ADMON,FECHA,COSTO_DEFINITIVO,ID_CLIENTE, ID_ALOJAMIENTO)
values('2','4','T','F','F','01/04/18','4509000','1101567896','7');

-------------------VIVIENDAS----------------------------


Insert into VIVIENDAS(ID,CEDIDO,COMPARTIDO)values('4','F','T'); 

--------------APARTAMENTO------


Insert into APARTAMENTOS(ID,AMOBLADO)values('5','T');
------HAB HOTEL---------------

Insert into HABITACIONES_HOTEL(ID,TIPO_HABITACION) VALUES('1','SEMISUITE');
Insert into HABITACIONES_HOTEL(ID,TIPO_HABITACION) VALUES('2','ESTANDAR');
Insert into HABITACIONES_HOTEL(ID,TIPO_HABITACION) VALUES('3','SUITE');

-----HAB HOSTAL--------------------
Insert into HABITACIONES_HOSTAL(ID,HORARIO_APERTURA,HORARIO_CIERRE,COMPARTIDA) VALUES('6',NULL,NULL,'F');
Insert into HABITACIONES_HOSTAL(ID,HORARIO_APERTURA,HORARIO_CIERRE,COMPARTIDA) VALUES('7', to_date('1999/01/01:9:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),to_date('1999/01/01:6:00:00pM', 'yyyy/mm/dd:hh:mi:ssam'),'T');


-----------VIVIENDA UNIVERSITARIA--------------------------------


Insert into HABITACIONES_VIV_UNI(ID,DURACION_DE_HAB) VALUES('8','1');
Insert into HABITACIONES_VIV_UNI(ID,DURACION_DE_HAB) VALUES('9','180');
------------SEGUROS---------------------------------

Insert into SEGUROS(ID,VALOR,ID_CONTRATO) VALUES('1','3900000','2');

-----------SE HAN QUEDADO---------
Insert into SE_HAN_QUEDADO (ID_ALOJAMIENTO,ID_CLIENTE) values('9','183456');
------LES GUSTA

Insert into LES_GUSTA (ID_SERVICIO,ID_CLIENTE) values('2','183456');

---------SERVICIOS OFRECIDOS----
Insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO,ID_SERVICIO) values('8','4');


-----------SERVICIOS ADICIONALES---------------------------------

Insert into SERVICIOS_ADICIONALES(ID_RESERVA, ID_SERVICIO) VALUES('4','3');

-----RESERVAS LIKE MUCHAS----

insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (302, '25/3/2018', '26/3/2018', '15/4/2018', 65, 9, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (302, '16/5/2017', '18/5/2017', '4/6/2017', 95, 9, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (303, '20/3/2018', '23/3/2018', '2/4/2018', 71, 8, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (304, '29/1/2018', '3/2/2018', '17/2/2018', 73, 1, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (305, '26/2/2018', '27/2/2018', '15/3/2018', 90, 3, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (306, '20/10/2017', '26/10/2017', '21/11/2017', 7, 4, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (307, '20/1/2018', '24/1/2018', '11/2/2018', 92, 5, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (308, '5/8/2017', '8/8/2017', '15/8/2017', 95, 7, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (309, '17/3/2018', '24/3/2018', '12/4/2018', 14, 4, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (310, '20/12/2017', '24/12/2017', '14/1/2018', 41, 5, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (311, '3/4/2018', '5/4/2018', '23/4/2018', 29, 8, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (312, '10/2/2018', '17/2/2018', '24/2/2018', 60, 8, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (313, '23/12/2017', '30/12/2017', '21/1/2018', 33, 4, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (314, '19/1/2018', '24/1/2018', '29/1/2018', 84, 1, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (315, '19/2/2018', '24/2/2018', '25/3/2018', 38, 7, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (316, '6/9/2017', '12/9/2017', '6/10/2017', 44, 2, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (317, '25/8/2017', '28/8/2017', '11/9/2017', 80, 8, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (318, '11/2/2018', '14/2/2018', '17/2/2018', 4, 7, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (319, '17/9/2017', '22/9/2017', '21/10/2017', 10, 2, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (320, '9/3/2018', '14/3/2018', '4/4/2018', 70, 3, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (321, '14/6/2017', '16/6/2017', '23/6/2017', 45, 5, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (322, '27/12/2017', '1/1/2018', '5/1/2018', 87, 8, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (323, '11/12/2017', '14/12/2017', '17/12/2017', 76, 9, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (324, '18/2/2018', '23/2/2018', '19/3/2018', 35, 6, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (325, '20/11/2017', '25/11/2017', '7/12/2017', 79, 6, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (326, '30/1/2018', '5/2/2018', '8/2/2018', 61, 9, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (327, '30/12/2017', '1/1/2018', '15/1/2018', 64, 4, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (328, '7/7/2017', '10/7/2017', '20/7/2017', 45, 8, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (329, '16/8/2017', '17/8/2017', '20/8/2017', 47, 9, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (330, '14/1/2018', '20/1/2018', '4/2/2018', 29, 7, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (331, '13/9/2017', '19/9/2017', '25/9/2017', 87, 6, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (332, '15/1/2018', '18/1/2018', '7/2/2018', 60, 9, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (333, '16/7/2017', '21/7/2017', '22/7/2017', 16, 1, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (334, '20/4/2017', '22/4/2017', '20/5/2017', 83, 4, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (335, '10/7/2017', '11/7/2017', '28/7/2017', 7, 7, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (336, '4/11/2017', '5/11/2017', '27/11/2017', 38, 7, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (337, '15/6/2017', '16/6/2017', '7/7/2017', 78, 8, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (338, '8/9/2017', '13/9/2017', '16/9/2017', 51, 5, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (339, '13/8/2017', '15/8/2017', '24/8/2017', 90, 2, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (340, '3/12/2017', '8/12/2017', '12/12/2017', 52, 4, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (341, '17/8/2017', '21/8/2017', '17/9/2017', 80, 9, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (342, '3/1/2018', '10/1/2018', '4/2/2018', 5, 2, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (343, '16/9/2017', '19/9/2017', '30/9/2017', 21, 1, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (344, '11/5/2017', '18/5/2017', '9/6/2017', 21, 1, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (345, '1/1/2018', '8/1/2018', '12/1/2018', 79, 7, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (346, '2/8/2017', '4/8/2017', '25/8/2017', 91, 3, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (347, '6/12/2017', '7/12/2017', '4/1/2018', 1, 6, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (348, '9/9/2017', '11/9/2017', '8/10/2017', 30, 8, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (349, '21/11/2017', '27/11/2017', '12/12/2017', 99, 3, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (350, '7/1/2018', '14/1/2018', '18/1/2018', 83, 5, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (351, '7/8/2017', '8/8/2017', '14/8/2017', 97, 1, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (352, '27/2/2018', '6/3/2018', '3/4/2018', 94, 2, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (353, '16/6/2017', '20/6/2017', '14/7/2017', 22, 9, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (354, '7/6/2017', '12/6/2017', '14/6/2017', 96, 2, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (355, '13/12/2017', '16/12/2017', '10/1/2018', 51, 1, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (356, '19/6/2017', '23/6/2017', '17/7/2017', 77, 9, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (357, '25/7/2017', '1/8/2017', '18/8/2017', 88, 6, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (358, '28/4/2017', '1/5/2017', '24/5/2017', 32, 5, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (359, '16/4/2018', '18/4/2018', '4/5/2018', 73, 5, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (360, '3/6/2017', '8/6/2017', '18/6/2017', 3, 9, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (361, '30/7/2017', '3/8/2017', '28/8/2017', 41, 2, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (362, '5/10/2017', '12/10/2017', '5/11/2017', 97, 2, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (363, '3/7/2017', '4/7/2017', '1/8/2017', 49, 3, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (364, '17/2/2018', '21/2/2018', '3/3/2018', 53, 2, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (365, '28/3/2018', '4/4/2018', '17/4/2018', 2, 7, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (366, '24/7/2017', '27/7/2017', '29/7/2017', 87, 8, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (367, '25/4/2017', '28/4/2017', '10/5/2017', 45, 7, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (368, '16/9/2017', '20/9/2017', '13/10/2017', 93, 4, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (369, '5/5/2017', '11/5/2017', '30/5/2017', 71, 4, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (370, '18/5/2017', '20/5/2017', '1/6/2017', 35, 2, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (371, '13/6/2017', '15/6/2017', '6/7/2017', 23, 7, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (372, '13/12/2017', '20/12/2017', '21/12/2017', 100, 2, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (373, '21/7/2017', '26/7/2017', '17/8/2017', 53, 5, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (374, '7/9/2017', '14/9/2017', '29/9/2017', 78, 8, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (375, '7/12/2017', '10/12/2017', '20/12/2017', 5, 7, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (376, '20/7/2017', '25/7/2017', '24/8/2017', 6, 4, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (377, '1/1/2018', '7/1/2018', '24/1/2018', 68, 7, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (378, '12/8/2017', '14/8/2017', '9/9/2017', 36, 7, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (379, '14/6/2017', '15/6/2017', '10/7/2017', 79, 4, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (380, '9/3/2018', '10/3/2018', '1/4/2018', 1, 9, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (381, '26/12/2017', '2/1/2018', '18/1/2018', 9, 2, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (382, '6/5/2017', '10/5/2017', '23/5/2017', 88, 6, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (383, '26/5/2017', '2/6/2017', '5/6/2017', 49, 3, 1020567, 8);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (384, '13/11/2017', '14/11/2017', '3/12/2017', 9, 9, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (385, '16/6/2017', '18/6/2017', '3/7/2017', 39, 2, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (386, '16/5/2017', '22/5/2017', '17/6/2017', 93, 3, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (387, '11/6/2017', '16/6/2017', '20/6/2017', 30, 1, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (388, '10/9/2017', '12/9/2017', '9/10/2017', 25, 1, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (389, '26/10/2017', '1/11/2017', '11/11/2017', 50, 8, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (390, '21/2/2018', '22/2/2018', '19/3/2018', 22, 8, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (391, '28/4/2017', '5/5/2017', '1/6/2017', 67, 9, 1020567, 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (392, '3/5/2017', '9/5/2017', '25/5/2017', 60, 3, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (393, '25/2/2018', '28/2/2018', '20/3/2018', 86, 4, 1020567, 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (394, '9/2/2018', '12/2/2018', '14/2/2018', 94, 4, 1020567, 6);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (395, '19/9/2017', '21/9/2017', '16/10/2017', 79, 3, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (396, '27/2/2018', '6/3/2018', '22/3/2018', 39, 9, 1020567, 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (397, '7/1/2018', '13/1/2018', '12/2/2018', 39, 3, 1020567, 7);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (398, '19/6/2017', '24/6/2017', '12/7/2017', 75, 8, 1020567, 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (399, '28/10/2017', '4/11/2017', '10/11/2017', 53, 9, 1020567, 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS) values (400, '5/4/2018', '8/4/2018', '29/4/2018', 24, 9, 1020567, 3);
-----------




---------reservas OLECTIVAS----


insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (601, '15/1/2018', '24/1/2018', '31/1/2018', 74, 5, 1101567896, 5, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (602, '7/12/2017', '19/12/2017', '22/12/2017', 14, 4, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (603, '4/2/2018', '12/2/2018', '24/2/2018', 43, 2, 1101567896, 2, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (604, '18/5/2017', '22/5/2017', '29/5/2017', 33, 2, 1101567896, 3, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (605, '25/1/2018', '6/2/2018', '10/2/2018', 53, 5, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (606, '12/7/2017', '14/7/2017', '27/7/2017', 6, 7, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (607, '25/2/2018', '8/3/2018', '11/3/2018', 57, 8, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (608, '28/11/2017', '8/12/2017', '15/12/2017', 68, 7, 1101567896, 1, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (609, '29/11/2017', '1/12/2017', '12/12/2017', 18, 7, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (610, '27/11/2017', '7/12/2017', '12/12/2017', 73, 1, 1101567896, 2, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (611, '11/8/2017', '23/8/2017', '2/9/2017', 1, 3, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (612, '16/7/2017', '21/7/2017', '24/7/2017', 74, 6, 1101567896, 5, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (613, '2/8/2017', '8/8/2017', '21/8/2017', 60, 3, 1101567896, 5, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (614, '19/2/2018', '3/3/2018', '6/3/2018', 72, 8, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (615, '25/2/2018', '6/3/2018', '8/3/2018', 90, 3, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (616, '23/7/2017', '31/7/2017', '8/8/2017', 93, 3, 1101567896, 2, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (617, '27/10/2017', '4/11/2017', '13/11/2017', 13, 9, 1101567896, 5, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (618, '15/8/2017', '24/8/2017', '2/9/2017', 94, 9, 1101567896, 1, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (619, '20/11/2017', '2/12/2017', '9/12/2017', 72, 4, 1101567896, 5, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (620, '15/3/2018', '28/3/2018', '1/4/2018', 95, 2, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (621, '9/9/2017', '13/9/2017', '20/9/2017', 24, 2, 1101567896, 4, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (622, '30/9/2017', '3/10/2017', '7/10/2017', 61, 2, 1101567896, 4, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (623, '10/1/2018', '19/1/2018', '1/2/2018', 96, 6, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (624, '29/1/2018', '31/1/2018', '9/2/2018', 71, 4, 1101567896, 4, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (625, '20/9/2017', '3/10/2017', '14/10/2017', 56, 9, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (626, '20/5/2017', '30/5/2017', '10/6/2017', 88, 4, 1101567896, 5, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (627, '9/9/2017', '16/9/2017', '24/9/2017', 52, 9, 1101567896, 2, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (628, '21/3/2018', '29/3/2018', '30/3/2018', 8, 1, 1101567896, 3, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (629, '19/6/2017', '2/7/2017', '13/7/2017', 82, 4, 1101567896, 1, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (630, '17/3/2018', '29/3/2018', '8/4/2018', 46, 3, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (631, '30/12/2017', '9/1/2018', '10/1/2018', 17, 5, 1101567896, 3, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (632, '16/11/2017', '27/11/2017', '5/12/2017', 85, 7, 1101567896, 2, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (633, '28/10/2017', '10/11/2017', '20/11/2017', 43, 9, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (634, '5/12/2017', '16/12/2017', '23/12/2017', 20, 6, 1101567896, 1, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (635, '6/10/2017', '12/10/2017', '23/10/2017', 49, 8, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (636, '6/9/2017', '7/9/2017', '16/9/2017', 7, 5, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (637, '6/3/2018', '9/3/2018', '23/3/2018', 94, 6, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (638, '23/3/2018', '3/4/2018', '7/4/2018', 35, 1, 1101567896, 3, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (639, '4/7/2017', '15/7/2017', '22/7/2017', 16, 5, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (640, '26/10/2017', '31/10/2017', '8/11/2017', 65, 6, 1101567896, 5, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (641, '31/12/2017', '6/1/2018', '10/1/2018', 99, 6, 1101567896, 5, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (642, '25/12/2017', '30/12/2017', '31/12/2017', 58, 8, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (643, '12/1/2018', '16/1/2018', '26/1/2018', 97, 7, 1101567896, 2, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (644, '4/2/2018', '7/2/2018', '16/2/2018', 96, 1, 1101567896, 1, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (645, '22/5/2017', '5/6/2017', '10/6/2017', 25, 2, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (646, '2/2/2018', '11/2/2018', '22/2/2018', 53, 6, 1101567896, 5, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (647, '16/7/2017', '26/7/2017', '3/8/2017', 12, 8, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (648, '10/10/2017', '17/10/2017', '18/10/2017', 84, 7, 1101567896, 2, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (649, '7/11/2017', '15/11/2017', '21/11/2017', 80, 6, 1101567896, 2, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (650, '31/8/2017', '5/9/2017', '10/9/2017', 10, 2, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (651, '10/8/2017', '13/8/2017', '15/8/2017', 58, 5, 1101567896, 5, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (652, '23/8/2017', '5/9/2017', '19/9/2017', 70, 4, 1101567896, 3, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (653, '6/2/2018', '15/2/2018', '1/3/2018', 92, 5, 1101567896, 5, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (654, '8/7/2017', '15/7/2017', '23/7/2017', 82, 6, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (655, '25/12/2017', '5/1/2018', '19/1/2018', 4, 9, 1101567896, 4, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (656, '26/3/2018', '27/3/2018', '28/3/2018', 95, 2, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (657, '22/1/2018', '26/1/2018', '29/1/2018', 71, 4, 1101567896, 2, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (658, '17/7/2017', '31/7/2017', '1/8/2017', 17, 6, 1101567896, 5, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (659, '29/10/2017', '6/11/2017', '8/11/2017', 30, 7, 1101567896, 5, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (660, '29/1/2018', '6/2/2018', '9/2/2018', 74, 4, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (661, '27/11/2017', '9/12/2017', '19/12/2017', 39, 9, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (662, '17/2/2018', '22/2/2018', '3/3/2018', 73, 9, 1101567896, 2, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (663, '8/11/2017', '21/11/2017', '30/11/2017', 76, 2, 1101567896, 5, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (664, '23/9/2017', '24/9/2017', '28/9/2017', 80, 6, 1101567896, 3, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (665, '2/5/2017', '8/5/2017', '11/5/2017', 87, 3, 1101567896, 5, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (666, '30/9/2017', '9/10/2017', '21/10/2017', 35, 4, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (667, '30/11/2017', '3/12/2017', '15/12/2017', 25, 4, 1101567896, 5, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (668, '11/11/2017', '25/11/2017', '9/12/2017', 12, 7, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (669, '10/6/2017', '14/6/2017', '16/6/2017', 11, 8, 1101567896, 5, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (670, '4/1/2018', '6/1/2018', '8/1/2018', 57, 5, 1101567896, 1, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (671, '28/2/2018', '5/3/2018', '9/3/2018', 1, 6, 1101567896, 1, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (672, '7/1/2018', '19/1/2018', '28/1/2018', 56, 1, 1101567896, 3, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (673, '23/2/2018', '9/3/2018', '14/3/2018', 85, 9, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (674, '22/3/2018', '30/3/2018', '13/4/2018', 91, 2, 1101567896, 3, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (675, '24/12/2017', '2/1/2018', '7/1/2018', 26, 6, 1101567896, 2, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (676, '13/9/2017', '25/9/2017', '4/10/2017', 72, 7, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (677, '14/7/2017', '27/7/2017', '5/8/2017', 60, 8, 1101567896, 5, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (678, '17/11/2017', '25/11/2017', '4/12/2017', 17, 5, 1101567896, 4, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (679, '2/12/2017', '14/12/2017', '26/12/2017', 53, 1, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (680, '23/9/2017', '30/9/2017', '13/10/2017', 80, 6, 1101567896, 3, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (681, '13/5/2017', '17/5/2017', '28/5/2017', 31, 4, 1101567896, 3, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (682, '29/11/2017', '8/12/2017', '11/12/2017', 39, 2, 1101567896, 3, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (683, '21/9/2017', '25/9/2017', '6/10/2017', 68, 3, 1101567896, 4, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (684, '13/8/2017', '18/8/2017', '19/8/2017', 38, 7, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (685, '21/10/2017', '30/10/2017', '11/11/2017', 44, 2, 1101567896, 2, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (686, '20/8/2017', '24/8/2017', '29/8/2017', 52, 1, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (687, '25/2/2018', '4/3/2018', '10/3/2018', 67, 1, 1101567896, 5, 'T', 4);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (688, '6/4/2018', '18/4/2018', '23/4/2018', 22, 8, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (689, '4/7/2017', '18/7/2017', '1/8/2017', 23, 4, 1101567896, 3, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (690, '27/9/2017', '11/10/2017', '18/10/2017', 9, 2, 1101567896, 3, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (691, '21/6/2017', '27/6/2017', '1/7/2017', 43, 9, 1101567896, 2, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (692, '8/1/2018', '18/1/2018', '24/1/2018', 23, 6, 1101567896, 1, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (693, '21/6/2017', '5/7/2017', '14/7/2017', 35, 1, 1101567896, 2, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (694, '17/4/2018', '19/4/2018', '22/4/2018', 40, 2, 1101567896, 3, 'T', 2);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (695, '26/12/2017', '29/12/2017', '10/1/2018', 36, 2, 1101567896, 4, 'T', 3);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (696, '12/12/2017', '13/12/2017', '17/12/2017', 1, 7, 1101567896, 1, 'T', 5);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (697, '1/8/2017', '10/8/2017', '20/8/2017', 26, 4, 1101567896, 1, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (698, '12/8/2017', '14/8/2017', '22/8/2017', 96, 3, 1101567896, 2, 'T', 0);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (699, '18/12/2017', '30/12/2017', '5/1/2018', 63, 1, 1101567896, 1, 'T', 1);
insert into RESERVAS (ID, tiempo_oportuno, fecha_inicio, fecha_fin, COSTO_DEFINITIVO, ID_ALOJAMIENTO, ID_CLIENTE, NUM_PERSONAS, colectiva, id_colectiva) values (700, '6/8/2017', '15/8/2017', '19/8/2017', 5, 8, 1101567896, 2, 'T', 3);
-------
-----
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (10, 3);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (5, 2);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (6, 3);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (9, 2);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (2, 4);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (1, 3);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (4, 1);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (9, 1);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (7, 1);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (3, 4);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (1, 1);
insert into SERVICIOS_OFRECIDOS (ID_ALOJAMIENTO, ID_SERVICIO) values (10, 4);

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('208','30/08/18','04/09/18','2','85000,12','27/08/18','1','1020567','T');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('209','20/10/18','22/10/18','1','45000,12','17/10/18','2','1020567','T');

Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('210','20/10/18','20/11/18','1','450000','13/10/18','4','1101567896','T');


Insert into RESERVAS(ID,FECHA_INICIO,FECHA_FIN,NUM_PERSONAS,COSTO_DEFINITIVO,TIEMPO_OPORTUNO,ID_ALOJAMIENTO,ID_CLIENTE,TERMINADA)
values('211','20/10/18','24/11/18','3','4509000','13/10/18','7','1101567896','T');