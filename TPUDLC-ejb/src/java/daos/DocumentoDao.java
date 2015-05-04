/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.DocumentoBean;
import entities.DocumentosEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import logics.Documento;

/**
 *
 * @author Martin
 */
public class DocumentoDao {
    
    
    @PersistenceContext(name = "TPUDLC-ejbPU")
    
    private EntityManager em;
    
    
    public List<DocumentosEntity> obtenerDocumentos()
    {
        return em.createNamedQuery("DocumentosEntity.findAll").getResultList();
    }
    
    public void insertarDocumentos(DocumentoBean docBean)
    {
        Documento doc = new Documento(docBean);
        em.persist(doc.getEntity());
    }
    
    
    
    
}
