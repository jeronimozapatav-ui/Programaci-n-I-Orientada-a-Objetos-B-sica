import java.util.*;

class Libro {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;
    }

    public String getTitulo() { return titulo; }
    public String getISBN() { return isbn; }
    public boolean isDisponible() { return disponible; }

    public void prestar() { disponible = false; }

    public void devolver() { disponible = true; }

    public String toString() {
        return titulo + " | Autor: " + autor + " | ISBN: " + isbn + " | Disponible: " + disponible;
    }
}

class Usuario {

    private String nombre;
    private String id;
    private List<Libro> librosPrestados;

    public Usuario(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.librosPrestados = new ArrayList<>();
    }

    public String getId() { return id; }

    public String getNombre() { return nombre; }

    public List<Libro> getLibrosPrestados() { return librosPrestados; }

    public boolean puedePrestar() {
        return librosPrestados.size() < 3;
    }

    public void prestarLibro(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    public String toString() {
        return nombre + " | ID: " + id + " | Libros prestados: " + librosPrestados.size();
    }
}

class Biblioteca {

    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void mostrarLibros() {

        System.out.println("\nLIBROS DISPONIBLES:");

        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public void mostrarUsuarios() {

        System.out.println("\nUSUARIOS:");

        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    public void prestarLibro(String isbn, String idUsuario) {

        for (Usuario u : usuarios) {

            if (u.getId().equals(idUsuario)) {

                if (!u.puedePrestar()) {
                    System.out.println("El usuario ya tiene 3 libros prestados.");
                    return;
                }

                for (Libro l : libros) {

                    if (l.getISBN().equals(isbn) && l.isDisponible()) {

                        u.prestarLibro(l);
                        l.prestar();

                        System.out.println("Libro prestado correctamente.");
                        return;
                    }
                }
            }
        }

        System.out.println("No se pudo prestar el libro.");
    }

    public void devolverLibro(String isbn, String idUsuario) {

        for (Usuario u : usuarios) {

            if (u.getId().equals(idUsuario)) {

                for (Libro l : u.getLibrosPrestados()) {

                    if (l.getISBN().equals(isbn)) {

                        u.devolverLibro(l);
                        l.devolver();

                        System.out.println("Libro devuelto correctamente.");
                        return;
                    }
                }
            }
        }

        System.out.println("No se pudo devolver el libro.");
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Biblioteca Biblioteca = new Biblioteca();

        int opcion;

        do {

            System.out.println("\n---- SISTEMA DE BIBLIOTECA ----");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Mostrar libros");
            System.out.println("4. Mostrar usuarios");
            System.out.println("5. Prestar libro");
            System.out.println("6. Devolver libro");
            System.out.println("7. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Titulo: ");
                    String titulo = sc.nextLine();

                    System.out.print("Autor: ");
                    String autor = sc.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                  
                    Biblioteca.agregarLibro(new Libro(titulo, autor, isbn));
                    break;

                case 2:

                    System.out.print("Nombre usuario: ");
                    String nombre = sc.nextLine();

                    System.out.print("ID usuario: ");
                    String id = sc.nextLine();

                    Biblioteca.registrarUsuario(new Usuario(nombre, id));
                    break;

                case 3:
                    Biblioteca.mostrarLibros();
                    break;

                case 4:
                    Biblioteca.mostrarUsuarios();
                    break;

                case 5:

                    System.out.print("ISBN del libro: ");
                    String isbnPrestamo = sc.nextLine();

                    System.out.print("ID del usuario: ");
                    String idPrestamo = sc.nextLine();

                    Biblioteca.prestarLibro(isbnPrestamo, idPrestamo);
                    break;

                case 6:

                    System.out.print("ISBN del libro: ");
                    String isbnDev = sc.nextLine();

                    System.out.print("ID del usuario: ");
                    String idDev = sc.nextLine();

                    Biblioteca.devolverLibro(isbnDev, idDev);
                    break;

                case 7:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opcion invalida");

            }

        } while (opcion != 7);

        sc.close();
    }
}
