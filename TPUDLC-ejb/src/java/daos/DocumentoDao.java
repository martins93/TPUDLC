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
import javax.persistence.Query;
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
        Query q = em.createNamedQuery("DocumentosEntity.findByNombre").setParameter("nombre", doc.getNombre());
        if(q.getResultList().isEmpty())
        { 
            em.persist(doc.getEntity());
            
        }
    }
        
    public int getIdDocumento(DocumentoBean docBean)
    {
        DocumentosEntity docId;
        Documento doc = new Documento(docBean);
        Query q = em.createNamedQuery("DocumentosEntity.findByNombre").setParameter("nombre", doc.getNombre());
        docId = (DocumentosEntity) q.getResultList().get(0);
        
        return docId.getId();
    }
        
    
}
