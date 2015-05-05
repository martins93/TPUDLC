/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.PalabraBean;
import entities.PalabrasEntity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        em.persist(pal.getEntity());
    }

}
