/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.DocumentoBean;
import entities.DocumentosEntity;
import java.util.HashMap;
import java.util.LinkedList;
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

    public List<DocumentoBean> obtenerDocumentos() {
        List<DocumentosEntity> entidades = em.createNamedQuery("DocumentosEntity.findAll").getResultList();
        LinkedList<DocumentoBean> beans = new LinkedList<>();
        for (DocumentosEntity entidad : entidades) {
            beans.add(new Documento(entidad).getBean());
        }
        
        

        return beans;
    }
    
    public HashMap<String, DocumentoBean> cargarHashDocs()
    {
        HashMap<String, DocumentoBean> hashDB = new HashMap<>();
         List<DocumentoBean> beans = obtenerDocumentos();
         for(DocumentoBean palbean : beans)
         {
             hashDB.put(palbean.getNombre(), palbean);
         }
         
         return hashDB;
    }

    public boolean obtenerDocumentos(String filtro) {
        boolean estaDocumento = true;
        List<DocumentosEntity> entidades = em.createNamedQuery("DocumentosEntity.findByNombre").setParameter("nombre", filtro).getResultList();
        if (entidades.isEmpty()) {
            estaDocumento = false;
        }

        return estaDocumento;
    }
    public List<DocumentoBean> obtenerDocumentos(Integer id) {
        List<DocumentosEntity> entidades = em.createNamedQuery("DocumentosEntity.findById").setParameter("id", id).getResultList();
        LinkedList<DocumentoBean> beans = new LinkedList<>();
        for (DocumentosEntity entidad : entidades) {
            beans.add(new Documento(entidad).getBean());
        }

        return beans;
    }

    
    /*public Integer cantidadDocumentosTotal(){
        Integer res = (Integer) em.createNamedQuery("DocumentosEntity.count").getSingleResult();
        return res;                
    }*/
    
    public void insertarDocumentos(DocumentoBean docBean) {

        Documento doc = new Documento(docBean);

        Query q = em.createNamedQuery("DocumentosEntity.findByNombre").setParameter("nombre", doc.getNombre());
        if (q.getResultList().isEmpty()) {
            em.persist(doc.getEntity());
        }
    }

    public Integer getIdDocumento() {
        Integer docId;
        Query q = em.createNamedQuery("DocumentosEntity.findLast");
        docId = (Integer) q.getResultList().get(0);

        return docId;
    }

}
