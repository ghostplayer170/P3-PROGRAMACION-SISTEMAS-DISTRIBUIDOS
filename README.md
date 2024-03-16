# Biblioteca CORBA

## Versiones Utilizadas
- JDK 8
- JacORB 3.9 (o la versión de ORB que estés utilizando)
- Cualquier otra herramienta o biblioteca relevante

## Ejecución de la Práctica
Para ejecutar la práctica, sigue los siguientes pasos:
1. Compila los archivos `.java` generados por `idlj` desde el archivo `Biblioteca.idl`.
2. Inicia el servicio de nombres ORB (por ejemplo, `orbd -ORBInitialPort 1050` para ORB estándar de Java o similar en otros ORB).
3. Ejecuta el servidor: `java -cp . ServidorBiblioteca -ORBInitialPort 1050 -ORBInitialHost localhost`.
4. En otro terminal, ejecuta el cliente: `java -cp . ClienteBiblioteca -ORBInitialPort 1050 -ORBInitialHost localhost`.

## Funcionalidades Añadidas
Se han implementado las siguientes funcionalidades:
- **Buscar Libro**: Busca un libro por título y devuelve los detalles si está disponible.
- **Prestar Libro**: Cambia el estado de un libro a no disponible.
- **Devolver Libro**: Cambia el estado de un libro a disponible.
- **Listar Libros**: Devuelve una lista de todos los libros en la biblioteca.
- **Añadir Comentario y Calificación**: Permite añadir un comentario y una calificación a un libro específico.
- **Mostrar Comentarios**: Muestra todos los comentarios asociados a un libro.

## Diagrama del Sistema
(Aquí puedes incluir un diagrama de cómo interactúa el cliente con el servidor, cómo se almacenan los libros, y cualquier otra parte relevante de la arquitectura del sistema).

![Diagrama del Sistema](ruta/al/diagrama.png)
