package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosEntity.findAll", query = "SELECT d FROM DocumentosEntity d"),
    @NamedQuery(name = "DocumentosEntity.findById", query = "SELECT d FROM DocumentosEntity d WHERE d.id = :id"),
    @NamedQuery(name = "DocumentosEntity.findByNombre", query = "SELECT d FROM DocumentosEntity d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DocumentosEntity.findByFechaCarga", query = "SELECT d FROM DocumentosEntity d WHERE d.fechaCarga = :fechaCarga"),
    @NamedQuery(name = "DocumentosEntity.findLast", query = "SELECT MAX(d.id) FROM DocumentosEntity d")})
public class DocumentosEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_carga")
    @Temporal(TemporalType.DATE)
    private Date fechaCarga;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "documentosEntity")
    private List<VocabularioEntity> vocabularioEntityList;

    public DocumentosEntity() {
    }

    public DocumentosEntity(Integer id) {
        this.id = id;
    }

    public DocumentosEntity(Integer id, String nombre, Date fechaCarga) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCarga = fechaCarga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    @XmlTransient
    public List<VocabularioEntity> getVocabularioEntityList() {
        return vocabularioEntityList;
    }

    public void setVocabularioEntityList(List<VocabularioEntity> vocabularioEntityList) {
        this.vocabularioEntityList = vocabularioEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentosEntity)) {
            return false;
        }
        DocumentosEntity other = (DocumentosEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DocumentosEntity[ id=" + id + " ]";
    }

}
