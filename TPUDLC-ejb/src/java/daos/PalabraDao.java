package daos;

import beans.PalabraBean;
import entities.PalabrasEntity;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import logics.Palabra;

public class PalabraDao {

    @PersistenceContext(name = "TPUDLC-ejbPU")

    private EntityManager em;

    public List<PalabraBean> obtenerPalabras() {

        //System.out.println("POR ARMAR LA LIST DE PALABRABEAN");
        List<PalabrasEntity> entidades = em.createNamedQuery("PalabrasEntity.findAll").getResultList();
        //System.out.println("EJECUTO LA NAMEDQUERY");
        LinkedList<PalabraBean> beans = new LinkedList<>();
        for (PalabrasEntity entidad : entidades) {
            beans.add(new Palabra(entidad).getBean());
        }

        //System.out.println("YA ARME LA LIST DE PALABRAS");

        return beans;

    }

    public void insertarPalabras(PalabraBean palBean) {
        Palabra pal = new Palabra(palBean);

        em.persist(pal.getEntity());

    }

    public Integer getIdPalabra() {
        Integer palId;
        Query q = em.createNamedQuery("PalabrasEntity.findLast");
        palId = (Integer) q.getResultList().get(0);
        return palId;
    }

    public HashMap<String, PalabraBean> cargarHashPalabras() {

        //System.out.println("POR ARMAR HASH DE PALABRAS");
        HashMap<String, PalabraBean> hashDB = new HashMap<>();
        List<PalabraBean> beans = obtenerPalabras();

        for (PalabraBean palbean : beans) {
            hashDB.put(palbean.getPalabra(), palbean);
        }

        //System.out.println("YA ARME EL HASH DE PALABRAS");
        //System.out.println(hashDB.size());
        return hashDB;
    }

}
