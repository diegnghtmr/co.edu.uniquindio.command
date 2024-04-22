package co.edu.uniquindio.command.command.viewController;

import co.edu.uniquindio.command.command.controller.LibraryController;
import co.edu.uniquindio.command.command.model.Book;
import co.edu.uniquindio.command.command.model.command.LendBookCommand;
import co.edu.uniquindio.command.command.model.command.ReturnBookCommand;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de la biblioteca.
 * Este controlador se encarga de manejar los eventos de la interfaz de usuario de la biblioteca.
 */
public class LibraryViewController {

    private LibraryController libraryController;
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private Book selectedBook;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLendBook;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnUndo;

    @FXML
    private Button btnRedo;

    @FXML
    private TableView<Book> tblBook;

    @FXML
    private TableColumn<Book, String> tcAuthor;

    @FXML
    private TableColumn<Book, String> tcAvailable;

    @FXML
    private TableColumn<Book, String> tcISBN;

    @FXML
    private TableColumn<Book, String> tcTitle;

    @FXML
    private TableColumn<Book, String> tcYear;

    /**
     * Manejador del evento de prestar un libro.
     * @param event el evento de acción.
     */
    @FXML
    void onLendBook(ActionEvent event) {
        lendBook();
    }

    /**
     * Manejador del evento de devolver un libro.
     * @param event el evento de acción.
     */
    @FXML
    void onReturnBook(ActionEvent event) {
        returnBook();
    }

    @FXML
    void onUndo(ActionEvent event) {
        undoLastCommand();
    }

    @FXML
    void onRedo(ActionEvent event) {
        redoLastCommand();
    }

    /**
     * Inicializa el controlador.
     * Este método se llama después de que todos los elementos de la interfaz de usuario hayan sido construidos.
     */
    @FXML
    void initialize() {
        libraryController = new LibraryController();
        initView();
    }

    /**
     * Inicializa la vista.
     * Este método se encarga de inicializar los datos y los listeners de la vista.
     */
    private void initView() {
        initDataBinding();
        getCustomerList();
        tblBook.getItems().clear();
        tblBook.setItems(bookList);
        listenerSelection();
    }

    /**
     * Inicializa el enlace de datos.
     * Este método se encarga de inicializar el enlace de datos entre los elementos de la interfaz de usuario
     * y los datos de los libros.
     */
    private void initDataBinding() {
        tcTitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        tcAuthor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        tcISBN.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIsbn()));
        tcYear.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue()
                .getYear())));
        tcAvailable.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
                .isAvailable() ? "Disponible" : "No Disponible"));
    }

    /**
     * Obtiene la lista de libros.
     * Este método se encarga de obtener la lista de libros de la biblioteca
     * y añadirla a la lista de libros de la vista.
     */
    private void getCustomerList() {
        bookList.addAll(libraryController.getBookList());
    }

    /**
     * Inicializa el listener de selección.
     * Este método se encarga de inicializar el listener que se activa cuando se selecciona un libro en la tabla.
     */
    private void listenerSelection() {
        tblBook.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedBook = newSelection;
        });
    }

    /**
     * Muestra un mensaje de confirmación.
     * @param message el mensaje a mostrar.
     * @return true si el usuario confirma, false en caso contrario.
     */
    private boolean showConfirmationMessage(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        return action.get() == ButtonType.OK;
    }

    /**
     * Muestra un mensaje.
     * @param title el título del mensaje.
     * @param header el encabezado del mensaje.
     * @param content el contenido del mensaje.
     * @param alertType el tipo de alerta del mensaje.
     */
    private void showMessage(String title, String header, String content, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(title);
        aler.setHeaderText(header);
        aler.setContentText(content);
        aler.showAndWait();
    }

    /**
     * Presta un libro.
     * Este método se encarga de prestar un libro seleccionado en la tabla.
     */
    private void lendBook() {
        if (selectedBook != null) {
            if (selectedBook.isAvailable()) {
                if (showConfirmationMessage("¿Desea prestar el libro?")) {
                    LendBookCommand lendBookCommand = new LendBookCommand(libraryController
                            .getLibrary(), selectedBook.getIsbn());
                    libraryController.executeCommand(lendBookCommand); // Utiliza executeCommand
                    selectedBook.setAvailable(false);
                    tblBook.refresh();
                    showMessage("Libro Prestado", "Libro prestado exitosamente",
                            "El libro fue prestado exitosamente", Alert.AlertType.INFORMATION);
                }
            } else {
                showMessage("Libro no disponible", "Libro no disponible",
                        "El libro no se encuentra disponible", Alert.AlertType.WARNING);
            }
        } else {
            showMessage("Libro no seleccionado", "Libro no seleccionado",
                    "Por favor seleccione un libro", Alert.AlertType.WARNING);
        }
    }

    /**
     * Devuelve un libro.
     * Este método se encarga de devolver un libro seleccionado en la tabla.
     */
    private void returnBook() {
        if (selectedBook != null) {
            if (!selectedBook.isAvailable()) {
                if (showConfirmationMessage("¿Desea devolver el libro?")) {
                    ReturnBookCommand returnBookCommand = new ReturnBookCommand(libraryController
                            .getLibrary(), selectedBook.getIsbn());
                    libraryController.executeCommand(returnBookCommand); // Utiliza executeCommand
                    selectedBook.setAvailable(true);
                    tblBook.refresh();
                    showMessage("Libro Devuelto", "Libro devuelto exitosamente",
                            "El libro fue devuelto exitosamente", Alert.AlertType.INFORMATION);
                }
            } else {
                showMessage("Libro no prestado", "Libro no prestado",
                        "El libro no se encuentra prestado", Alert.AlertType.WARNING);
            }
        } else {
            showMessage("Libro no seleccionado", "Libro no seleccionado",
                    "Por favor seleccione un libro", Alert.AlertType.WARNING);
        }
    }

    /**
     * Deshace el último comando ejecutado.
     * Si no hay comandos ejecutados, muestra un mensaje de advertencia.
     */
    private void undoLastCommand() {
        if (!libraryController.isExecutedCommandsEmpty()) {
            libraryController.undoCommand();
            tblBook.refresh();
        } else {
            showMessage("No hay comandos para deshacer", "No hay comandos para deshacer",
                    "No puedes deshacer un comando porque no hay comandos que hayan sido ejecutados previamente.", Alert.AlertType.WARNING);
        }
    }

    /**
     * Rehace el último comando deshecho.
     * Si no hay comandos deshechos, muestra un mensaje de advertencia.
     */
    private void redoLastCommand() {
        if (!libraryController.isUndoneCommandsEmpty()) {
            libraryController.redoCommand();
            tblBook.refresh();
        } else {
            showMessage("No hay comandos para rehacer", "No hay comandos para rehacer",
                    "No puedes rehacer un comando porque no hay comandos que hayan sido deshechos previamente.", Alert.AlertType.WARNING);
        }
    }
}