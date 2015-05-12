package daos;

import beans.PalabraBean;
import beans.VocabularioBean;
import entities.VocabularioEntity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import logics.Vocabulario;

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

    public List<VocabularioBean> obtenerVocabulario(PalabraBean palabra) {
        List<VocabularioEntity> entidades = em.createNamedQuery("VocabularioEntity.findByPalabraId").setParameter("palabraId", palabra.getId()).getResultList();
        LinkedList<VocabularioBean> lista = new LinkedList<>();
        for (VocabularioEntity entidad : entidades) {
            lista.add(new Vocabulario(entidad).getBean());
        }
        return lista;
    }

    public Integer obtenerFrecuenciaTotal(Integer id) {
        List<VocabularioEntity> entidades = em.createNamedQuery("VocabularioEntity.findByPalabraId").setParameter("palabraId", id).getResultList();
        Integer res = 0;
        for (VocabularioEntity v : entidades) {
            res += v.getApariciones();
        }
        return res;
    }

    public void insertarVocabularios(VocabularioBean vb) {

        Vocabulario voc = new Vocabulario(vb);

        em.persist(voc.getEntity());

    }

}
