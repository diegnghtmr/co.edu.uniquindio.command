package co.edu.uniquindio.exposicion.app_gestionarchivos.controller;

import co.edu.uniquindio.exposicion.app_gestionarchivos.factory.ModelFactory;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoBase;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;

import java.util.List;

public class GestorArchivosController {

    ModelFactory modelFactory;

    public GestorArchivosController(){
        modelFactory = ModelFactory.getInstance();

    }


    public List<ArchivoBase> obtenerArchivos() {
        return modelFactory.obtenerArchivos();
    }

    public String adaptarNombreAWord(String nombreArchivo) {
        return modelFactory.adaptarNombreAWord(nombreArchivo);
    }

    // public ArchivoBase adaptarArchivoWord(ArchivoBase archivoTexto) {


}
