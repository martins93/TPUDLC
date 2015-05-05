/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics;

/**
 *
 * @author Martin
 */
public class VocabularioModel {
    
     private String palabra;
    private Integer frecuencia;
    private String nombreDoc;


    public VocabularioModel() {
        this.palabra = "";
        this.nombreDoc = "";
        this.frecuencia = null;
    }

    public VocabularioModel(String palabra, Integer frecuencia, String nombreDoc) {
        this.palabra = palabra;
        this.nombreDoc = nombreDoc;
        this.frecuencia = frecuencia;
    }

    public VocabularioModel(String nombreDoc) {

        this.nombreDoc = nombreDoc;

    }

    public String getPalabra() {
        return palabra;
    }

    public String getDocumento() {
        return nombreDoc;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }


    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public void setDocumento(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }



    @Override
    public String toString() {
        return "Vocabulario{" + "palabra=" + palabra + ", frecuencia=" + frecuencia + ", nombreDocumento=" + nombreDoc + '}';
    }
    
}
