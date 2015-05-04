/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logics;

import beans.VocabularioBean;
import entities.VocabularioEntity;
import entities.VocabularioEntityPK;

/**
 *
 * @author Martin
 */
public class Vocabulario {
    
  private VocabularioEntity entidad;
  private VocabularioEntityPK pk;
  
  public Vocabulario()
  {
      entidad = new VocabularioEntity();
      pk = new VocabularioEntityPK();
  }
  
  public Vocabulario(VocabularioEntity ve)
  {
      entidad = ve;
      pk = entidad.getVocabularioEntityPK();
  }
  
  public Vocabulario(VocabularioBean vb)
  {
      this();
      if(vb.getDocumento_id()!=0 && vb.getPalabra_id()!=0)
      {
          this.pk.setDocumentoId(vb.getDocumento_id());
          this.pk.setPalabraId(vb.getPalabra_id());
          this.entidad.setVocabularioEntityPK(pk);
                 
      }
          this.entidad.setApariciones(vb.getApariciones());
  }
  
  
    public int getApariciones() {
        return this.entidad.getApariciones();
    }

    public void setApariciones(int apariciones) {
        this.entidad.setApariciones(apariciones);
    }
    
    public VocabularioEntityPK getVocabularioEntityPK()
    {
        return this.entidad.getVocabularioEntityPK();
    }
    
    public void setVocabularioEntityPK (VocabularioEntityPK vpk)
    {
        this.entidad.setVocabularioEntityPK(vpk);
    }
    
    
    public VocabularioBean getBean()
    {
        VocabularioBean vb = new VocabularioBean(getVocabularioEntityPK().getPalabraId(), getVocabularioEntityPK().getDocumentoId(), getApariciones());
        return vb;
    }
    
    public VocabularioEntity getEntity()
    {
        return entidad;
    }
    
}
