# Parcial-1-JAVA-Almacenador-de-videojuegos
PARCIAL 1 PROFESORA NAVEDA
Alumno: Luna Marcelo Joaquin

[Le recomiendo abrir con intellij que para dar los ultimos retoques lo importe en el mismo :) ]

Almacenador de Juegos Retro es una aplicación de consola desarrollada en Java que permite gestionar una base de datos de consolas y videojuegos clásicos. Utilizando JDBC con la base de datos H2 en modo archivo, la aplicación proporciona una interfaz sencilla para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre dos entidades principales:

Consola: representa una consola de videojuegos retro (por ejemplo, Super Nintendo, Sega Genesis).

Videojuego: representa un título asociado a una consola específica, incluyendo su nombre y año de lanzamiento.

Pasos a seguir para ejecutar correctamente: 

1 - Iniciar el ejecutable de la carpeta Main en SRC llamado "App".
2 - Al inciciar el ejecutable automaticmente se corrobora si la base de datos ya fue creada o se crea automaticamente (En este caso yo le deje una base de datos pre-creada dentro de la carpeta, no hace falta que haga nada mas, solo ejecutar el programa y si quiere puede ver que videojuegos o consolas le deje precargadas en la base)
3 - en pantalla figuran varias opciones las cuales son sencillas y en el siguiente texto le dejo las demas funcionalidades: 

Funcionalidades:
Agregar, listar, buscar, actualizar y eliminar consolas.
Agregar, listar, buscar, actualizar y eliminar videojuegos vinculados a una consola.

Persistencia automática en archivo local (la base de datos se crea si no existe y se inicia en caso de que ya exista).

Estructura organizada en capas: modelo, dao, util, y main.

Uso de patrón DAO para separar la lógica de acceso a datos.

Fácil de extender y mantener.

Tecnologías
Java 17
JDBC
H2 Database (modo archivo)
Gradle
IntelliJ IDEA y NetBeans

Gracias por su atencion profe :)


