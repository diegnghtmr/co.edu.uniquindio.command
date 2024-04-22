package co.edu.uniquindio.command.command.controller;

import co.edu.uniquindio.command.command.model.Book;
import co.edu.uniquindio.command.command.factory.ModelFactory;
import co.edu.uniquindio.command.command.model.Library;
import co.edu.uniquindio.command.command.services.Command;

import java.util.List;
import java.util.Stack;

/**
 * Controlador para la biblioteca.
 * Este controlador se encarga de interactuar con el modelo de la biblioteca.
 */
public class LibraryController {
    private ModelFactory modelFactory;
    private Library library;
    private Stack<Command> executedCommands;
    private Stack<Command> undoneCommands;


    /**
     * Constructor del controlador de la biblioteca.
     * Inicializa la instancia del modelo de la biblioteca.
     */
    public LibraryController() {
        modelFactory = ModelFactory.getInstance();
        this.library = library;
        this.executedCommands = new Stack<>();
        this.undoneCommands = new Stack<>();
    }

    /**
     * Obtiene la lista de libros de la biblioteca.
     *
     * @return una lista de libros.
     */
    public List<Book> getBookList() {
        return modelFactory.getBookList();
    }

    /**
     * Obtiene la instancia de la biblioteca.
     *
     * @return la instancia de la biblioteca.
     */
    public Library getLibrary() {
        return modelFactory.getLibrary();
    }

    /**
     * Ejecuta un comando y lo agrega a la pila de comandos ejecutados.
     *
     * @param command el comando a ejecutar.
     */
    public void executeCommand(Command command) {
        command.execute();
        executedCommands.push(command);
    }

    /**
     * Deshace el último comando ejecutado y lo agrega a la pila de comandos deshechos.
     * Si no hay comandos ejecutados, este método no hace nada.
     */
    public void undoCommand() {
        if (!executedCommands.isEmpty()) {
            Command lastCommand = executedCommands.pop();
            lastCommand.undo();
            undoneCommands.push(lastCommand);
        }
    }

    /**
     * Rehace el último comando deshecho y lo agrega de nuevo a la pila de comandos ejecutados.
     * Si no hay comandos deshechos, este método no hace nada.
     */
    public void redoCommand() {
        if (!undoneCommands.isEmpty()) {
            Command lastUndoneCommand = undoneCommands.pop();
            lastUndoneCommand.redo();
            executedCommands.push(lastUndoneCommand);
        }
    }

    /**
     * Verifica si la pila de comandos ejecutados está vacía.
     *
     * @return true si la pila de comandos ejecutados está vacía, false en caso contrario.
     */
    public boolean isExecutedCommandsEmpty() {
        return executedCommands.isEmpty();
    }

    /**
     * Verifica si la pila de comandos deshechos está vacía.
     *
     * @return true si la pila de comandos deshechos está vacía, false en caso contrario.
     */
    public boolean isUndoneCommandsEmpty() {
        return undoneCommands.isEmpty();
    }
}