package logics;

import beans.DocumentoBean;
import entities.DocumentosEntity;
import java.util.Date;

public class Documento {

    private DocumentosEntity entidad;

    public Documento() {
        entidad = new DocumentosEntity();
    }

    public Documento(DocumentosEntity de) {
        this.entidad = de;
    }

    public Documento(DocumentoBean db) {
        this();
        if (db.getId() != 0) {
            this.entidad.setId(db.getId());
        }
        this.entidad.setFechaCarga(db.getFecha_carga());
        this.entidad.setNombre(db.getNombre());
    }

    public int getId() {
        return this.entidad.getId();
    }

    public void setId(int id) {
        this.entidad.setId(id);
    }

    public Date getFechaCarga() {
        return this.entidad.getFechaCarga();
    }

    public void setFechaCarga(Date fecha) {
        this.entidad.setFechaCarga(fecha);
    }

    public String getNombre() {
        return this.entidad.getNombre();
    }

    public void setNombre(String nombre) {
        this.entidad.setNombre(nombre);
    }

    public DocumentoBean getBean() {
        DocumentoBean db = new DocumentoBean(getId(), getFechaCarga(), getNombre());
        return db;
    }

    public DocumentosEntity getEntity() {
        return entidad;
    }

}
