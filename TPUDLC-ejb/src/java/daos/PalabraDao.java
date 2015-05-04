/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.PalabraBean;
import entities.PalabrasEntity;
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
    
    
    public List<PalabrasEntity> obtenerPalabras()
    {
        return em.createNamedQuery("PalabrasEntity.findAll").getResultList();
    }
    
    public void insertarPalabras(PalabraBean palBean)
    {
        Palabra pal = new Palabra(palBean);
        em.persist(pal.getEntity());
    }
    
}
