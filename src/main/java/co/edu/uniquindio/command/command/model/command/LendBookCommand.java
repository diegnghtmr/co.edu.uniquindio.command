package co.edu.uniquindio.command.command.model.command;

import co.edu.uniquindio.command.command.model.Library;
import co.edu.uniquindio.command.command.services.Command;

/**
 * Comando para prestar un libro de la biblioteca.
 * Este comando se encarga de ejecutar la acción de prestar un libro en la biblioteca.
 */
public class LendBookCommand implements Command {
    private Library library;
    private String bookId;
    private boolean wasAvailable;

    /**
     * Constructor del comando para prestar un libro.
     * @param library la biblioteca a la que pertenece el libro.
     * @param bookId el ID del libro a prestar.
     */
    public LendBookCommand(Library library, String bookId) {
        this.library = library;
        this.bookId = bookId;
    }

    /**
     * Ejecuta el comando.
     * Este método se encarga de prestar el libro en la biblioteca.
     */
    @Override
    public void execute() {
        wasAvailable = library.getBook(bookId).isAvailable();
        if (wasAvailable) {
            library.lendBook(bookId);
        }
    }

    /**
     * Deshace el comando de prestar un libro.
     * Si el libro estaba disponible cuando se ejecutó el comando, este método devuelve el libro a la biblioteca.
     */
    @Override
    public void undo() {
        if (wasAvailable) {
            library.returnBook(bookId);
        }
    }

    /**
     * Rehace el comando de prestar un libro.
     * Este método simplemente vuelve a ejecutar el comando.
     */
    @Override
    public void redo() {
        execute();
    }
}