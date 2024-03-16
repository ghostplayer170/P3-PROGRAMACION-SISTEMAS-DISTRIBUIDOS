import Biblioteca.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

import java.util.InputMismatchException;
import java.util.Scanner; // Importar la clase Scanner

public class ClienteBiblioteca {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in); // Crear un objeto Scanner

        try {
            // Inicializar el ORB (Object Request Broker)
            ORB orb = ORB.init(args, null);

            // Obtener referencia al servicio de nombres
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Buscar la referencia del objeto (servidor) en el servicio de nombres
            String name = "GestionBiblioteca";
            GestionBiblioteca gestionBiblioteca = GestionBibliotecaHelper.narrow(ncRef.resolve_str(name));

            // Menú de opciones
            boolean salir = false;
            while (!salir) {

                // Mostrar el menú
                mostrarMenu();

                int opcion = 0; // Opcion seleccionada por el usuario
                boolean entradaValida = false; // Bandera para controlar la validez de la entrada

                while (!entradaValida) {
                    try {
                        System.out.print("Seleccione una opcion: ");
                        opcion = scanner.nextInt();
                        entradaValida = true; // La entrada es válida
                    } catch (InputMismatchException e) {
                        System.out.println("Opcion no valida. Por favor, introduce un numero entero.");
                        scanner.nextLine(); // Limpiar el buffer del scanner
                    }
                }
                scanner.nextLine(); // Consumir el salto de línea pendiente después de leer un número

                switch (opcion) {
                    case 1:
                        // Pedir al usuario que introduzca el título del libro que desea buscar
                        System.out.print("Introduzca el titulo del libro: ");
                        String tituloLibro = scanner.nextLine();

                        // Usar la interfaz para llamar a las operaciones del servidor
                        Libro libro = gestionBiblioteca.buscarLibro(tituloLibro);

                        if (libro != null && libro.titulo != null && !libro.titulo.equals("No encontrado")) {
                            System.out.println("Libro encontrado: " + libro.titulo + ", " + libro.autor + ", ISBN: " + libro.ISBN + ", Calificacion: " + libro.calificacion + ", Disponible: " + libro.estaDisponible);
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                        break;
                    case 2:
                        // Pedir al usuario que introduzca el ISBN del libro que desea prestar
                        System.out.print("Introduzca el ISBN del libro: ");
                        String ISBNLibro = scanner.nextLine();

                        // Usar la interfaz para llamar a las operaciones del servidor
                        boolean resultadoPrestamo = gestionBiblioteca.prestarLibro(ISBNLibro);
                        if (resultadoPrestamo) {
                            System.out.println("Libro prestado con exito.");
                        } else {
                            System.out.println("El libro no está disponible para prestamo.");
                        }
                        break;
                    case 3:
                        // Pedir al usuario que introduzca el ISBN del libro que desea devolver
                        System.out.print("Introduzca el ISBN del libro: ");
                        String ISBNLibroDevolver = scanner.nextLine();

                        // Usar la interfaz para llamar a las operaciones del servidor
                        boolean resultadoDevolucion = gestionBiblioteca.devolverLibro(ISBNLibroDevolver);
                        if (resultadoDevolucion) {
                            System.out.println("Libro devuelto con exito.");
                        } else {
                            System.out.println("El libro no se ha podido devolver.");
                        }
                        break;
                    case 4:
                        // Usar la interfaz para llamar a las operaciones del servidor
                        Libro[] libros = gestionBiblioteca.listarLibros();
                        int num = 1;
                        for (Libro value : libros) {
                            System.out.println(num + " " + value.titulo + ", " + value.autor + ", ISBN: " + value.ISBN + ", Calificacion: " + value.calificacion + ", Disponible: " + value.estaDisponible);
                            num++;
                        }
                        break;
                    case 5:
                        // Pedir al usuario que introduzca el ISBN del libro al que desea añadir un comentario y calificación
                        System.out.print("Introduzca el ISBN del libro: ");
                        String ISBNLibroComentario = scanner.nextLine();

                        // Pedir al usuario que introduzca un comentario
                        System.out.print("Introduzca un comentario: ");
                        String comentario = scanner.nextLine();

                        // Pedir al usuario que introduzca una calificación
                        int calificacion = 0;
                        entradaValida = false; // Restablecer la bandera de entrada válida
                        while (!entradaValida) {
                            try {
                                System.out.print("Introduzca una calificacion (0-5): ");
                                calificacion = scanner.nextInt();
                                if (calificacion < 0 || calificacion > 5) {
                                    System.out.println("La calificacion debe estar entre 0 y 5.");
                                } else {
                                    entradaValida = true; // La entrada es válida
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Calificacion no valida. Por favor, introduce un numero entero.");
                                scanner.nextLine(); // Limpiar el buffer del scanner
                            }
                        }
                        scanner.nextLine(); // Consumir el salto de línea pendiente después de leer un número

                        // Usar la interfaz para llamar a las operaciones del servidor
                        boolean resultadoComentario = gestionBiblioteca.anadirComentarioYCalificacion(ISBNLibroComentario, comentario, calificacion);
                        if (resultadoComentario) {
                            System.out.println("Comentario y calificacion anadidos con exito.");
                        } else {
                            System.out.println("El libro no se ha podido encontrar.");
                        }
                        break;
                    case 6:
                        // Pedir al usuario que introduzca el ISBN del libro del que desea mostrar los comentarios
                        System.out.print("Introduzca el ISBN del libro: ");
                        String ISBNLibroComentarios = scanner.nextLine();

                        // Usar la interfaz para llamar a las operaciones del servidor
                        String[] comentarios = gestionBiblioteca.mostrarComentarios(ISBNLibroComentarios);
                        if (comentarios != null) {
                            System.out.println("Comentarios:");
                            if (comentarios.length == 0) {
                                System.out.println("No hay comentarios para este libro.");
                            } else {
                                for (String value : comentarios) {
                                    System.out.println(value);
                                }
                            }
                        } else {
                            System.out.println("El libro no se ha podido encontrar.");
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace(System.out);
        } finally {
            scanner.close(); // Cerrar el scanner
        }
    }

    // Mostrar el menú de opciones
    public static void mostrarMenu() {
        System.out.println("\n -- Biblioteca --");
        System.out.println("1. Buscar un libro por titulo");
        System.out.println("2. Prestar un libro");
        System.out.println("3. Devolver un libro");
        System.out.println("4. Listar libros");
        System.out.println("5. Anadir comentario y calificacion");
        System.out.println("6. Mostrar comentarios de un libro");
        System.out.println("0. Salir\n");
    }
}
