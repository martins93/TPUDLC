/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.VocabularioBean;
import entities.VocabularioEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import logics.Vocabulario;

/**
 *
 * @author Martin
 */
public class VocabularioDao {
    
    @PersistenceContext(name = "TPUDLC-ejbPU")
    
    private EntityManager em;
    
    
    public List<VocabularioEntity> obtenerVocabulario()
    {
        return em.createNamedQuery("VocabularioEntity.findAll").getResultList();
    }
    
    public void insertarVocabularios(VocabularioBean vb)
    {
        Vocabulario voc = new Vocabulario(vb);
        em.persist(voc.getEntity());
    }
      
}
