package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class VocabularioEntityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "palabra_id")
    private int palabraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento_id")
    private int documentoId;

    public VocabularioEntityPK() {
    }

    public VocabularioEntityPK(int palabraId, int documentoId) {
        this.palabraId = palabraId;
        this.documentoId = documentoId;
    }

    public int getPalabraId() {
        return palabraId;
    }

    public void setPalabraId(int palabraId) {
        this.palabraId = palabraId;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(int documentoId) {
        this.documentoId = documentoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) palabraId;
        hash += (int) documentoId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof VocabularioEntityPK)) {
            return false;
        }
        VocabularioEntityPK other = (VocabularioEntityPK) object;
        if (this.palabraId != other.palabraId) {
            return false;
        }
        if (this.documentoId != other.documentoId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.VocabularioEntityPK[ palabraId=" + palabraId + ", documentoId=" + documentoId + " ]";
    }

}
