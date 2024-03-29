package Biblioteca;


/**
* Biblioteca/_GestionBibliotecaStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Biblioteca.idl
* s�bado 16 de marzo de 2024 18H27' CET
*/


// Define la interfaz para el sistema de gestión de la biblioteca
public class _GestionBibliotecaStub extends org.omg.CORBA.portable.ObjectImpl implements Biblioteca.GestionBiblioteca
{


  // Busca un libro por título y devuelve los detalles del libro
  public Biblioteca.Libro buscarLibro (String titulo)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("buscarLibro", true);
                $out.write_string (titulo);
                $in = _invoke ($out);
                Biblioteca.Libro $result = Biblioteca.LibroHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return buscarLibro (titulo        );
            } finally {
                _releaseReply ($in);
            }
  } // buscarLibro


  // Presta un libro, cambiando su estado a no disponible
  public boolean prestarLibro (String ISBN)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("prestarLibro", true);
                $out.write_string (ISBN);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return prestarLibro (ISBN        );
            } finally {
                _releaseReply ($in);
            }
  } // prestarLibro


  // Devuelve un libro, cambiando su estado a disponible
  public boolean devolverLibro (String ISBN)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("devolverLibro", true);
                $out.write_string (ISBN);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return devolverLibro (ISBN        );
            } finally {
                _releaseReply ($in);
            }
  } // devolverLibro


  // Devuelve una lista de todos los libros de la biblioteca
  public Biblioteca.Libro[] listarLibros ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listarLibros", true);
                $in = _invoke ($out);
                Biblioteca.Libro $result[] = Biblioteca.ListaLibrosHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listarLibros (        );
            } finally {
                _releaseReply ($in);
            }
  } // listarLibros


  // Añade un comentario y calificación a un libro
  public boolean anadirComentarioYCalificacion (String ISBN, String comentario, float calificacion)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("anadirComentarioYCalificacion", true);
                $out.write_string (ISBN);
                $out.write_string (comentario);
                $out.write_float (calificacion);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return anadirComentarioYCalificacion (ISBN, comentario, calificacion        );
            } finally {
                _releaseReply ($in);
            }
  } // anadirComentarioYCalificacion


  // Mostrar comentarios de un libro
  public String[] mostrarComentarios (String ISBN)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("mostrarComentarios", true);
                $out.write_string (ISBN);
                $in = _invoke ($out);
                String $result[] = Biblioteca.ListaComentariosHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return mostrarComentarios (ISBN        );
            } finally {
                _releaseReply ($in);
            }
  } // mostrarComentarios

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Biblioteca/GestionBiblioteca:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _GestionBibliotecaStub
