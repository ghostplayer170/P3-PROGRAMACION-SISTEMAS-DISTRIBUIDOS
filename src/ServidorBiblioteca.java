import Biblioteca.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.HashMap;
import java.util.Map;

class GestionBibliotecaImpl extends GestionBibliotecaPOA {
    private ORB orb;
    private Map<String, Libro> libros = new HashMap<>();

    public GestionBibliotecaImpl(ORB orb) {
        this.orb = orb;
        // Inicializar variables comentarios y calificacion
        String[] comentarios = {};
        float calificacion = 5;
        // Inicializar algunos libros en el sistema
        libros.put("12345", new Libro("El principito", "Antoine de Saint-Exupery", "12345", calificacion, comentarios, true));
        libros.put("56781", new Libro("El senor de los anillos", "J.R.R. Tolkien", "56781", calificacion, comentarios, true));
        libros.put("91011", new Libro("Cien anos de soledad", "Gabriel García Marquez", "91011", calificacion, comentarios, true));
        libros.put("12131", new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "12131", calificacion, comentarios, true));
        libros.put("15161", new Libro("1984", "George Orwell", "15161", calificacion, comentarios, true));
        libros.put("16253", new Libro("La sombra del viento", "Carlos Ruiz Zafon", "16253", calificacion, comentarios, true));
        libros.put("17469", new Libro("Los juegos del hambre", "Suzanne Collins", "17469", calificacion, comentarios, true));
        libros.put("18472", new Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", "18472", calificacion, comentarios, true));
        libros.put("19385", new Libro("El codigo Da Vinci", "Dan Brown", "19385", calificacion, comentarios, true));
        libros.put("20741", new Libro("El alquimista", "Paulo Coelho", "20741", calificacion, comentarios, true));
    }

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    // Implementar los métodos de la interfaz GestionBiblioteca
    @Override
    public Libro buscarLibro(String titulo) {
        String [] comentarios = {};
        return libros.values().stream()
                .filter(libro -> libro.titulo.equals(titulo) && libro.estaDisponible)
                .findFirst()
                .orElse(new Libro("No encontrado", "", "",0, comentarios, false));
    }

    @Override
    public boolean prestarLibro(String ISBN) {
        if (libros.containsKey(ISBN) && libros.get(ISBN).estaDisponible) {
            libros.get(ISBN).estaDisponible = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolverLibro(String ISBN) {
        if (libros.containsKey(ISBN)) {
            libros.get(ISBN).estaDisponible = true;
            return true;
        }
        return false;
    }

    @Override
    public Libro[] listarLibros() {
        return libros.values().toArray(new Libro[0]);
    }

    @Override
    public boolean anadirComentarioYCalificacion(String ISBN, String comentario, float calificacion) {
        if (libros.containsKey(ISBN)) {
            Libro libro = libros.get(ISBN);
            // Añadir comentarios a la lista
            String[] comentarios = libro.comentarios;
            String[] newComentarios = new String[comentarios.length + 1];
            System.arraycopy(comentarios, 0, newComentarios, 0, comentarios.length);
            newComentarios[comentarios.length] = comentario;
            // Calcular la nueva calificación
            libro.comentarios = newComentarios;
            libro.calificacion = (libro.calificacion + calificacion) / 2;
            return true;
        }
        return false;
    }

    @Override
    public String[] mostrarComentarios(String ISBN) {
        if (libros.containsKey(ISBN)) {
            Libro libro = libros.get(ISBN);
            return libro.comentarios;
        }
        return null;
    }
}

public class ServidorBiblioteca {
    public static void main(String args[]) {
        try {
            // Crear e inicializar el ORB
            ORB orb = ORB.init(args, null);

            // Obtener referencia a rootpoa y activar el POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Crear el servicio e inscribirlo en el ORB
            GestionBibliotecaImpl gestionBiblioteca = new GestionBibliotecaImpl(orb);
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(gestionBiblioteca);
            GestionBiblioteca href = GestionBibliotecaHelper.narrow(ref);

            // Obtener referencia al servicio de nombres
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Vincular la referencia del objeto en el servicio de nombres
            String name = "GestionBiblioteca";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("El servidor de la biblioteca está listo y esperando ...");

            // Esperar llamadas de los clientes
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}

