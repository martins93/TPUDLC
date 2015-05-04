/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics;

import beans.PalabraBean;
import entities.PalabrasEntity;
import entities.VocabularioEntity;

/**
 *
 * @author Martin
 */
public class Palabra {
    
    private PalabrasEntity entidad;
    
    public Palabra()
    {
        entidad = new PalabrasEntity();
    }
    public Palabra(PalabrasEntity pe)
    {
        this.entidad = pe;
    }
    
    public Palabra(PalabraBean palabra)
    {
        this();
        if(palabra.getId()!=0)
        {
            this.entidad.setId(palabra.getId());
        }
        
        this.entidad.setCantDocumentos(palabra.getCant_documentos());
        this.entidad.setMaxAparicion(palabra.getMax_aparicion());
        this.entidad.setPalabra(palabra.getPalabra());
    }
    
    public int getId()
    {
        return this.entidad.getId();
    }
    public void setId(int id)
    {
       this.entidad.setId(id);
    }
    public int getMax_aparicion() {
        return this.entidad.getMaxAparicion();
    }

    public void setMax_aparicion(int max_aparicion) {
        this.entidad.setMaxAparicion(max_aparicion);
    }

    public int getCant_documentos() {
        return this.entidad.getCantDocumentos();
    }

    public void setCant_documentos(int cant_documentos) {
        this.entidad.setCantDocumentos(cant_documentos);
    }

    public String getPalabra() {
        return this.entidad.getPalabra();
    }

    public void setPalabra(String palabra) {
        this.entidad.setPalabra(palabra);;
    }

    
    public PalabraBean getBean()
    {
    PalabraBean pb = new PalabraBean (getId(), getMax_aparicion(), getCant_documentos(), getPalabra());
    return pb;
    }
    
    public PalabrasEntity getEntity()
    {
        return entidad;
    }
    
    
}
