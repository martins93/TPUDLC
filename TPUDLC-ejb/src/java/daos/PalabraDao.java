/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import beans.DocumentoBean;
import beans.PalabraBean;
import entities.PalabrasEntity;
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
    
    
    public List<PalabrasEntity> obtenerPalabras()
    {
        return em.createNamedQuery("PalabrasEntity.findAll").getResultList();
    }
    
    public void insertarPalabras(PalabraBean palBean)
    {
        
        Palabra pal = new Palabra(palBean);
        
        Query q = em.createNamedQuery("PalabrasEntity.findByPalabra").setParameter("palabra", pal.getPalabra());
        if(q.getResultList().isEmpty())
        {
        em.persist(pal.getEntity());
        }
    }
    
     public int getIdPalabra(PalabraBean palBean)
    {
        PalabrasEntity palId;
        Palabra pal = new Palabra(palBean);
        Query q = em.createNamedQuery("PalabrasEntity.findByPalabra").setParameter("palabra", pal.getPalabra());
        palId = (PalabrasEntity) q.getResultList().get(0);
        
        return palId.getId();
    }
    
}
