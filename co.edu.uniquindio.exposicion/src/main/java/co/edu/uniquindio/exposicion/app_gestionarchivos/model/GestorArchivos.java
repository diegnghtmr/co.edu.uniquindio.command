package co.edu.uniquindio.exposicion.app_gestionarchivos.model;

import java.util.ArrayList;
import java.util.List;

public class GestorArchivos {

    public GestorArchivos(){

    }

    private List<ArchivoBase> listaArchivosTexto = new ArrayList<>();
    private List<ArchivoWordDocx> listaArchivosWord = new ArrayList<>();

    public GestorArchivos(List<ArchivoBase> listaArchivosTexto, List<ArchivoWordDocx> listaArchivosWord) {
        this.listaArchivosTexto = listaArchivosTexto;
        this.listaArchivosWord = listaArchivosWord;
    }

    public void agregarArchivo(ArchivoBase archivoTexto){
        listaArchivosTexto.add(archivoTexto);

    }

    public void agregarArchivoWord(ArchivoWordDocx archivoWordDocx){
        listaArchivosWord.add(archivoWordDocx);
    }

    public List<ArchivoBase> getListaArchivosTexto() {
        return listaArchivosTexto;
    }

    public void setListaArchivosTexto(List<ArchivoBase> listaArchivosTexto) {
        this.listaArchivosTexto = listaArchivosTexto;
    }

    public List<ArchivoWordDocx> getListaArchivosWord() {
        return listaArchivosWord;
    }

    public void setListaArchivosWord(List<ArchivoWordDocx> listaArchivosWord) {
        this.listaArchivosWord = listaArchivosWord;
    }

    public String adaptarNombreAWord(String nombreArchivo) {
        return nombreArchivo +".docx";
    }
}
