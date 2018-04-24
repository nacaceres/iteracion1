# Iteracion1
Grupo B-205 iteracion 1 de sistemas transaccionales Para usar la aplicación es necesario importar el proyecto con eclipse, correr la aplicación como un servidor desde WildFly 10.1. Una vez desplegada la aplicación, se debe crear y reiniciar las tablas, esto se puede hacer mediante el archivo reiniciarYCrearTablas.sql disponible en la carpeta de documentos. Posterior a esto se puede importar las colecciones de postman disponibles en la carpeta de documentos. Se pueden correr todas las pruebas desde la RF1 hasta la RF6 y ver los resultados de las diferentes pruebas. Para todas las pruebas en postman que empiece con RFC #, o que sean del RF7 en adelante se deben poblar la tablas para que estos resultados sean cobrar sentido. Las tablas se pueden poblar con ayuda del archivo Poblar.sql disponible en la carpeta de documentos.

Para realizar las pruebas de clave externa unicidad de tuplas y de restricciones se debe ejecutar el archivo ReiniciarYCrearTablas.sql, tambien ejecutar el archivo Poblado.sql.

Luego, abrir el archivo PRUEBAS_DB y ejecutar para cada tabla con los datos correspondientes.Luego ejecutar el código que se encuentra bajo el comentario pruebas unicidad y luego observar en la salida del script los resultados y confirmar que ocurre lo que ocurre en los comentarios para cada línea.

Para consultar todas las tablas activas con sus constraints, atributos etc, se debe correr el sql contenido en el archivo dispuesto en la carptea docs llamado ConsultaTablas.sql .

---------------------- TABLA: ----------------------

--- data --- 1) pruebas unicidad --- 2) prueba foreign key --borrado tuplas maestras --borrado tuplas hijas --- 3) pruebas de check. --borrado tuplas maestras --borrado tuplas hijas



