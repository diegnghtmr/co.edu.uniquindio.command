package co.edu.uniquindio.command.command.factory;

import co.edu.uniquindio.command.command.model.Book;
import co.edu.uniquindio.command.command.model.Library;

import java.util.List;

/**
 * Fábrica de modelos para la biblioteca.
 * Esta clase se encarga de inicializar y proporcionar acceso a los modelos de la biblioteca.
 */
public class ModelFactory {
    private static ModelFactory modelFactory;
    private Library library;

    /**
     * Constructor privado para la fábrica de modelos.
     * Inicializa la biblioteca y los datos iniciales.
     */
    private ModelFactory() {
        library = new Library();
        initializeData();
    }

    /**
     * Obtiene la instancia única de la fábrica de modelos.
     * @return la instancia única de la fábrica de modelos.
     */
    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    /**
     * Inicializa los datos de la biblioteca.
     */
    private void initializeData() {
        // Crear libros
        Book book1 = new Book("Harry Potter", "J.K. Rowling", "1234", 2000);
        Book book2 = new Book("The Lord of the Rings", "J.R.R. Tolkien", "5678", 1954);
        Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", "91011", 1937);
        Book book4 = new Book("The Catcher in the Rye", "J.D. Salinger", "121314", 1951);
        Book book5 = new Book("To Kill a Mockingbird", "Harper Lee", "151617", 1960);
        Book book6 = new Book("1984", "George Orwell", "181920", 1949);

        // Agregar libros a la biblioteca
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
    }

    /**
     * Obtiene la lista de libros de la biblioteca.
     * @return una lista de libros.
     */
    public List<Book> getBookList() {
        return library.getBookList();
    }

    /**
     * Presta un libro de la biblioteca.
     * @param isbn el ISBN del libro a prestar.
     */
    public void lendBook(String isbn) {
        library.lendBook(isbn);
    }

    /**
     * Obtiene la instancia de la biblioteca.
     * @return la instancia de la biblioteca.
     */
    public Library getLibrary() {
        return library;
    }
}