package co.edu.uniquindio.exposicion.app_gestionarchivos.viewController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.exposicion.app_gestionarchivos.adapter.AdaptadorArchivoWord;
import co.edu.uniquindio.exposicion.app_gestionarchivos.controller.GestorArchivosController;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoBase;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoWordDocx;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestorArchivosViewController {

    ObservableList<ArchivoBase> listaArchivoTexto = FXCollections.observableArrayList();
    ArchivoBase archivoTextoSeleccionado;

    GestorArchivosController gestorArchivosController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdaptarArchivoWord;

    @FXML
    private TableView<ArchivoBase> tableGestionArchivos;

    @FXML
    private TableColumn<ArchivoBase, String> tbArchivos;

    @FXML
    private TableColumn<ArchivoBase, String> tbArchivosWord;

    @FXML
    private TextField txtNombreArchivo;

    @FXML
    void onAdaptarArchivoAWord(ActionEvent event) {

        adaptarArchivo();

    }


    @FXML
    void initialize() {
        gestorArchivosController = new GestorArchivosController();
        initView();

    }

    private void initView() {
        initDataBinding();
        obtenerArchivos();
        tableGestionArchivos.getItems().clear();
        tableGestionArchivos.setItems(listaArchivoTexto);
        listenerSeleccion();
    }




    private void initDataBinding() {
        tbArchivos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreArchivo()));
    }

    private void obtenerArchivos() {
        listaArchivoTexto.addAll(gestorArchivosController.obtenerArchivos());
    }

    private void listenerSeleccion() {
        tableGestionArchivos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
         archivoTextoSeleccionado = newSelection;
         mostrarInformacionArchivo(archivoTextoSeleccionado);
        });


    }

    private void mostrarInformacionArchivo(ArchivoBase archivoTextoSeleccionado) {
        if(archivoTextoSeleccionado!=null){
            txtNombreArchivo.setText(archivoTextoSeleccionado.getNombreArchivo());
        }
    }


    private void adaptarArchivo() {
        // Obtener el texto ingresado en el campo de texto
        String textoIngresado = txtNombreArchivo.getText().trim();
        // Verificar si el texto no está vacío
        if (!textoIngresado.isEmpty()) {
            // Verificar si el nombre del archivo ya tiene la extensión ".docx"
            if (!textoIngresado.toLowerCase().endsWith(".docx")) {
                // Crear un adaptador para el archivo temporal
                AdaptadorArchivoWord adaptador = new AdaptadorArchivoWord(null, gestorArchivosController);
                // Adaptar el nombre del archivo a Word
                String nombreArchivoWord = adaptador.adaptarNombreAWord(textoIngresado);
                // Agregar el nombre adaptado a la lista y actualizar la tabla
                listaArchivoTexto.add(new ArchivoBase(nombreArchivoWord));
                // Actualizar la visualización de la tabla
                tableGestionArchivos.refresh();
                // Mostrar un mensaje de éxito
                mostrarMensaje("Éxito", null, "Archivo adaptado correctamente a Word: " + nombreArchivoWord, Alert.AlertType.INFORMATION);
                // Limpiar los campos después de la adaptación exitosa
                limpiarCampos();
            } else {
                // Mostrar un mensaje de error si el archivo ya está en formato Word
                mostrarMensaje("Error", null, "El archivo ya está en formato Word.", Alert.AlertType.ERROR);
            }
        } else {
            // Mostrar un mensaje de error si el campo de texto está vacío
            mostrarMensaje("Error", null, "Por favor, ingrese un nombre de archivo.", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombreArchivo.setText("");
    }


    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

}



