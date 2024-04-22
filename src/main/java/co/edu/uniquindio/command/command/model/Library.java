package co.edu.uniquindio.command.command.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo para una biblioteca.
 * Este modelo representa una biblioteca con su nombre, dirección, teléfono y lista de libros.
 */
public class Library {
    private String name;
    private String address;
    private String phone;
    private List<Book> bookList;

    /**
     * Constructor para una biblioteca.
     * Inicializa la biblioteca con un nombre, dirección y teléfono predeterminados,
     * y una lista de libros vacía.
     */
    public Library() {
        this.name = "La Libreria de Oscar";
        this.address = "Calle 123";
        this.phone = "1234567";
        this.bookList = new ArrayList<>();
    }

    // Getters y setters para las propiedades de la biblioteca

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Agrega un libro a la lista de libros de la biblioteca.
     * @param book el libro a agregar.
     */
    public void addBook(Book book) {
        bookList.add(book);
    }

    /**
     * Obtiene la lista de libros de la biblioteca.
     * @return una lista de libros.
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Presta un libro de la biblioteca.
     * @param bookId el ISBN del libro a prestar.
     */
    public void lendBook(String bookId) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(bookId)) {
                book.setAvailable(false);
            }
        }
    }

    /**
     * Devuelve un libro a la biblioteca.
     * @param bookId el ISBN del libro a devolver.
     */
    public void returnBook(String bookId) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(bookId)) {
                book.setAvailable(true);
            }
        }
    }

    /**
     * Obtiene un libro de la biblioteca por su ISBN.
     *
     * @param bookId el ISBN del libro a buscar.
     * @return el libro con el ISBN proporcionado, o null si no se encuentra ningún libro con ese ISBN.
     */
    public Book getBook(String bookId) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}