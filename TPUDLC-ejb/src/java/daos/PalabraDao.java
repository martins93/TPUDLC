/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.DocumentoBean;
import beans.PalabraBean;
import entities.PalabrasEntity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import logics.Documento;
import logics.Palabra;

/**
 *
 * @author Martin
 */
public class PalabraDao {

    @PersistenceContext(name = "TPUDLC-ejbPU")

    private EntityManager em;

    public List<PalabraBean> obtenerPalabras() {
        List<PalabrasEntity> entidades = em.createNamedQuery("PalabrasEntity.findAll").getResultList();
        LinkedList<PalabraBean> beans = new LinkedList<>();
        for (PalabrasEntity entidad : entidades) {
            beans.add(new Palabra(entidad).getBean());
        }

        return beans;

    }

    public void insertarPalabras(PalabraBean palBean) {
        Palabra pal = new Palabra(palBean);

        Query q = em.createNamedQuery("PalabrasEntity.findByPalabra").setParameter("palabra", pal.getPalabra());
        if (q.getResultList().isEmpty()) {
            em.persist(pal.getEntity());
        }
    }

    public int getIdPalabra(PalabraBean palBean) {
        PalabrasEntity palId;
        Palabra pal = new Palabra(palBean);
        Query q = em.createNamedQuery("PalabrasEntity.findByPalabra").setParameter("palabra", pal.getPalabra());
        palId = (PalabrasEntity) q.getResultList().get(0);

        return palId.getId();
    }

}
