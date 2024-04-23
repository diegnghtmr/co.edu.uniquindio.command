package co.edu.uniquindio.exposicion.app_gestionarchivos.model;

import co.edu.uniquindio.exposicion.app_gestionarchivos.services.IArchivo;

public class ArchivoWordDocx extends  ArchivoBase implements IArchivo {


    public ArchivoWordDocx(String nombreArchivo) {
        super(nombreArchivo);
    }

    @Override
    public void abrir() {

    }

    @Override
    public void cerrar() {

    }
}
