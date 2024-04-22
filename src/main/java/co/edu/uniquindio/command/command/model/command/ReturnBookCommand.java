package co.edu.uniquindio.command.command.model.command;

import co.edu.uniquindio.command.command.model.Library;
import co.edu.uniquindio.command.command.services.Command;

/**
 * Comando para devolver un libro a la biblioteca.
 * Este comando se encarga de ejecutar la acción de devolver un libro en la biblioteca.
 */
public class ReturnBookCommand implements Command {
    private Library library;
    private String bookId;
    private boolean wasAvailable;


    /**
     * Constructor del comando para devolver un libro.
     * @param library la biblioteca a la que pertenece el libro.
     * @param bookId el ID del libro a devolver.
     */
    public ReturnBookCommand(Library library, String bookId) {
        this.library = library;
        this.bookId = bookId;
    }

    /**
     * Ejecuta el comando.
     * Este método se encarga de devolver el libro en la biblioteca.
     */
    @Override
    public void execute() {
        wasAvailable = library.getBook(bookId).isAvailable();
        if (!wasAvailable) {
            library.returnBook(bookId);
        }
    }

    /**
     * Deshace el comando de devolver un libro.
     * Si el libro no estaba disponible cuando se ejecutó el comando, este método presta el libro de la biblioteca.
     */
    @Override
    public void undo() {
        if (!wasAvailable) {
            library.lendBook(bookId);
        }
    }

    /**
     * Rehace el comando de devolver un libro.
     * Este método simplemente vuelve a ejecutar el comando.
     */
    @Override
    public void redo() {
        execute();
    }
}