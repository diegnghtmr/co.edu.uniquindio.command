package co.edu.uniquindio.exposicion.app_gestionarchivos.model;

public class ArchivoBase {
    private String nombreArchivo;

    public ArchivoBase(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}
