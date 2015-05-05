/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Salvador
 */
@Entity
@Table(name = "vocabulario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VocabularioEntity.findAll", query = "SELECT v FROM VocabularioEntity v"),
    @NamedQuery(name = "VocabularioEntity.findByPalabraId", query = "SELECT v FROM VocabularioEntity v WHERE v.vocabularioEntityPK.palabraId = :palabraId"),
    @NamedQuery(name = "VocabularioEntity.findByDocumentoId", query = "SELECT v FROM VocabularioEntity v WHERE v.vocabularioEntityPK.documentoId = :documentoId"),
    @NamedQuery(name = "VocabularioEntity.findByApariciones", query = "SELECT v FROM VocabularioEntity v WHERE v.apariciones = :apariciones")})
public class VocabularioEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VocabularioEntityPK vocabularioEntityPK;
    @Column(name = "apariciones")
    private Integer apariciones;
    @JoinColumn(name = "palabra_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PalabrasEntity palabrasEntity;
    @JoinColumn(name = "documento_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DocumentosEntity documentosEntity;

    public VocabularioEntity() {
    }

    public VocabularioEntity(VocabularioEntityPK vocabularioEntityPK) {
        this.vocabularioEntityPK = vocabularioEntityPK;
    }

    public VocabularioEntity(int palabraId, int documentoId) {
        this.vocabularioEntityPK = new VocabularioEntityPK(palabraId, documentoId);
    }

    public VocabularioEntityPK getVocabularioEntityPK() {
        return vocabularioEntityPK;
    }

    public void setVocabularioEntityPK(VocabularioEntityPK vocabularioEntityPK) {
        this.vocabularioEntityPK = vocabularioEntityPK;
    }

    public Integer getApariciones() {
        return apariciones;
    }

    public void setApariciones(Integer apariciones) {
        this.apariciones = apariciones;
    }

    public PalabrasEntity getPalabrasEntity() {
        return palabrasEntity;
    }

    public void setPalabrasEntity(PalabrasEntity palabrasEntity) {
        this.palabrasEntity = palabrasEntity;
    }

    public DocumentosEntity getDocumentosEntity() {
        return documentosEntity;
    }

    public void setDocumentosEntity(DocumentosEntity documentosEntity) {
        this.documentosEntity = documentosEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vocabularioEntityPK != null ? vocabularioEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VocabularioEntity)) {
            return false;
        }
        VocabularioEntity other = (VocabularioEntity) object;
        if ((this.vocabularioEntityPK == null && other.vocabularioEntityPK != null) || (this.vocabularioEntityPK != null && !this.vocabularioEntityPK.equals(other.vocabularioEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VocabularioEntity[ vocabularioEntityPK=" + vocabularioEntityPK + " ]";
    }

}
