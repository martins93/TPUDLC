package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "palabras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PalabrasEntity.findAll", query = "SELECT p FROM PalabrasEntity p"),
    @NamedQuery(name = "PalabrasEntity.findById", query = "SELECT p FROM PalabrasEntity p WHERE p.id = :id"),
    @NamedQuery(name = "PalabrasEntity.findByPalabra", query = "SELECT p FROM PalabrasEntity p WHERE p.palabra = :palabra"),
    @NamedQuery(name = "PalabrasEntity.findByMaxAparicion", query = "SELECT p FROM PalabrasEntity p WHERE p.maxAparicion = :maxAparicion"),
    @NamedQuery(name = "PalabrasEntity.findLast", query = "SELECT MAX(p.id) FROM PalabrasEntity p"),
    @NamedQuery(name = "PalabrasEntity.findOrdered", query = "SELECT p FROM PalabrasEntity p ORDER BY p.id"),
    @NamedQuery(name = "PalabrasEntity.findByCantDocumentos", query = "SELECT p FROM PalabrasEntity p WHERE p.cantDocumentos = :cantDocumentos")})
public class PalabrasEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "palabra")
    private String palabra;
    @Column(name = "max_aparicion")
    private Integer maxAparicion;
    @Column(name = "cant_documentos")
    private Integer cantDocumentos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "palabrasEntity")
    private List<VocabularioEntity> vocabularioEntityList;

    public PalabrasEntity() {
    }

    public PalabrasEntity(Integer id) {
        this.id = id;
    }

    public PalabrasEntity(Integer id, String palabra) {
        this.id = id;
        this.palabra = palabra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Integer getMaxAparicion() {
        return maxAparicion;
    }

    public void setMaxAparicion(Integer maxAparicion) {
        this.maxAparicion = maxAparicion;
    }

    public Integer getCantDocumentos() {
        return cantDocumentos;
    }

    public void setCantDocumentos(Integer cantDocumentos) {
        this.cantDocumentos = cantDocumentos;
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
        if (!(object instanceof PalabrasEntity)) {
            return false;
        }
        PalabrasEntity other = (PalabrasEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PalabrasEntity[ id=" + id + " ]";
    }

}
