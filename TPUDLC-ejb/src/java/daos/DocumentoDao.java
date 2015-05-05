/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.DocumentoBean;
import entities.DocumentosEntity;
import java.util.LinkedList;
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

    public List<DocumentoBean> obtenerDocumentos() {
        List<DocumentosEntity> entidades = em.createNamedQuery("DocumentosEntity.findAll").getResultList();
        LinkedList<DocumentoBean> beans = new LinkedList<>();
        for (DocumentosEntity entidad : entidades) {
            beans.add(new Documento(entidad).getBean());
        }

        return beans;
    }

    public void insertarDocumentos(DocumentoBean docBean) {
        Documento doc = new Documento(docBean);
        em.persist(doc.getEntity());
    }

}
