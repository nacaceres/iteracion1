# Iteracion1
Grupo B-205 iteracion 1 de sistemas transaccionales
Para usar la aplicacion es necesario importar el proyecto con eclipse, correr la aplicacion como un servidor a partir de
WildFly 10.1.
Una vez desplegada la aplicacion, se deben crear y reiniciar las tablas, esto se puede hacer mediante el archivo 
reiniciarYCrearTablas.sql disponible en la carpeta docs.
Posterior a esto se puede importar las colecciones de postman disponibles en la carpeta docs.
Se pueden correr todas las colecciones y ver los resultados de los diferentes tests.
Para todos las pruebas en postman que empiecen con RFC#, se debera poblar la tabla para que estos resultados 
puedan cobrar sentido. Las tablas se pueden poblar con ayuda del archivo Poblar.sql disponible en la carpeta docs.




Para realizar las pruebas de foreign key unicidad de tuplas y de constraints se debe ejecutar el archivo ReiniciarYCrearTablas.sql, tambien ejecutar el archivo Poblado.sql.


Luego abrir el archivo PRUEBAS_DB y ejecutar para cada tabla su data correspondiente.Luego ejecutar el codigo que se encuentre bajo el comentario pruebas unicidad y luego observar en la salida de script los resultados y confirmar que ocurre lo descirto en los comentarios para cada linea.


----------------------TABLA:----------------------

---data
---1)pruebas unicidad
---2)pruebas foreign key
--borrado tuplas maestras
--borrado tuplas hijas
---3)pruebas de  check.
--borrado tuplas maestras
--borrado tuplas hijas


