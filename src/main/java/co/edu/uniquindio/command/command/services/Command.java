package co.edu.uniquindio.command.command.services;

/**
 * Interfaz para los comandos.
 * Esta interfaz define un método para ejecutar un comando.
 */
public interface Command {
    /**
     * Ejecuta el comando.
     * Este método debe ser implementado por cada comando específico.
     */
    void execute();
    void undo();
    void redo();
}