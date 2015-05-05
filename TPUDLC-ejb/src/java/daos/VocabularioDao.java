/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.VocabularioBean;
import entities.VocabularioEntity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import logics.Vocabulario;

/**
 *
 * @author Martin
 */
public class VocabularioDao {

    @PersistenceContext(name = "TPUDLC-ejbPU")

    private EntityManager em;

    public List<VocabularioBean> obtenerVocabulario() {
        List<VocabularioEntity> entidades = em.createNamedQuery("VocabularioEntity.findAll").getResultList();
        LinkedList<VocabularioBean> lista = new LinkedList<>();
        for (VocabularioEntity entidad : entidades) {
            lista.add(new Vocabulario(entidad).getBean());
        }
        return lista;
    }

    public void insertarVocabularios(VocabularioBean vb) {
        Vocabulario voc = new Vocabulario(vb);
        Query q = em.createNamedQuery("VocabularioEntity.findByCompositeId").setParameter("palabraId", vb.getPalabra_id()).setParameter("documentoId", vb.getDocumento_id());
        if(q.getResultList().isEmpty())
        {
            em.persist(voc.getEntity());
       
        }
    }

}
