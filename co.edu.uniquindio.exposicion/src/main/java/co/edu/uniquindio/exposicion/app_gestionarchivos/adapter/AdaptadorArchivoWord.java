package co.edu.uniquindio.exposicion.app_gestionarchivos.adapter;

import co.edu.uniquindio.exposicion.app_gestionarchivos.controller.GestorArchivosController;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoTexto;
import co.edu.uniquindio.exposicion.app_gestionarchivos.model.ArchivoWordDocx;
import co.edu.uniquindio.exposicion.app_gestionarchivos.services.IArchivo;

public class AdaptadorArchivoWord implements IArchivo {
    private GestorArchivosController gestorArchivosController;
    private ArchivoTexto archivoTexto;

    public AdaptadorArchivoWord(ArchivoTexto archivoTexto, GestorArchivosController gestorArchivosController) {
        this.archivoTexto = archivoTexto;
        this.gestorArchivosController = gestorArchivosController;

    }

    @Override
    public void abrir() {
        // Aquí podríamos implementar la lógica para abrir un archivo de texto
        System.out.println("Abriendo archivo de texto: " + archivoTexto.getNombreArchivo());
    }

    @Override
    public void cerrar() {
        // Aquí podríamos implementar la lógica para cerrar un archivo de texto
        System.out.println("Cerrando archivo de texto: " + archivoTexto.getNombreArchivo());
    }



    public String adaptarNombreAWord(String nombreArchivo) {
        //agregar la extensión ".docx"
        return gestorArchivosController.adaptarNombreAWord(nombreArchivo) ;
    }



}
