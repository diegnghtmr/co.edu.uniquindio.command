package co.edu.uniquindio.command.command.model;

/**
 * Modelo para un libro.
 * Este modelo representa un libro con su título, autor, ISBN, año de publicación y disponibilidad.
 */
public class Book {
    private String title;
    private String author;
    private String isbn;
    private int year;
    private boolean available;

    /**
     * Constructor para un libro.
     * @param title el título del libro.
     * @param author el autor del libro.
     * @param isbn el ISBN del libro.
     * @param year el año de publicación del libro.
     */
    public Book(String title, String author, String isbn, int year) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.available = true;
    }

    // Getters y setters para las propiedades del libro

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}