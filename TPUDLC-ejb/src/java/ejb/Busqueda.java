/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.DocumentoBean;
import beans.PalabraBean;
import beans.VocabularioBean;
import daos.DocumentoDao;
import daos.PalabraDao;
import daos.VocabularioDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Salvador
 */
@Stateless
public class Busqueda implements BusquedaRemote {

    @Inject
    private PalabraDao palDao;
    @Inject
    private VocabularioDao vocDao;
    @Inject
    private DocumentoDao docDao;

    private HashMap<String, PalabraBean> palabras = new HashMap<>();

    @PostConstruct
    public void init() {
        List<PalabraBean> pals = palDao.obtenerPalabras();
        for (PalabraBean pal : pals) {
            palabras.put(pal.getPalabra(), pal);
        }
    }

    @Override
    public List<DocumentoBean> buscar(List<String> palabras_e) {
        List<PalabraBean> lista_palabras = new ArrayList<>();
        List<documento> documentos2 = new ArrayList<>();
        List<DocumentoBean> documentos = new ArrayList<>();
        List<VocabularioBean> vocabularios = new ArrayList<>();
        HashMap<Integer, Integer> palas = new HashMap<>();
        TreeMap<Integer, Integer> docs = new TreeMap<>();

        System.out.println("PALABRAS DE ENTRADA: " + palabras_e);
        for (String pal : palabras_e) {
            pal=pal.toLowerCase(Locale.ENGLISH);
            System.out.println("PALABRA TOLOWER EN CICLO: " + pal);
            if (palabras.get(pal) != null) {
                lista_palabras.add(palabras.get(pal));
            }
        }

        System.out.println("LISTA DE PALABRAS QUE SI: " + lista_palabras);
        for (PalabraBean pa : lista_palabras) {
            vocabularios.addAll(vocDao.obtenerVocabulario(pa).subList(0, 2)); // 0-10.
            palas.put(pa.getId(), 100000000 * 1 / (pa.getCant_documentos() * pa.getCant_documentos() * pa.getMax_aparicion())); //CD SOLO?         
        }
        int totalApariciones;
        System.out.println("VOCABULARIO: " + vocabularios);
        for (VocabularioBean voc : vocabularios) {
            totalApariciones = vocDao.obtenerFrecuenciaTotal(voc.getPalabra_id());
            palas.put(voc.getPalabra_id(), (palas.get(voc.getPalabra_id()) * (totalApariciones / voc.getApariciones())));
            if (docs.get(voc.getDocumento_id()) == null) {
                docs.put(voc.getDocumento_id(), palas.get(voc.getPalabra_id()));
            } else {
                docs.put(
                        voc.getDocumento_id(),
                        docs.get(voc.getDocumento_id())
                        + palas.get(
                                voc.getPalabra_id()));
            }
        }

        Iterator it = docs.keySet().iterator();
        Iterator it2 = docs.keySet().iterator();
        documento doc;

        while (it.hasNext()) {
            doc = new documento(((Integer) it.next()), docs.get((Integer) it2.next()));
            documentos2.add(doc);
        }

        System.out.println("DOCUMENTOS PRE ORDEN: " + documentos2);
        Collections.sort(documentos2, (documento o1, documento o2) -> o2.getPeso().compareTo(o1.getPeso()));
        System.out.println("DOCUMENTOS POST ORDEN: " + documentos2);

        for (documento d : documentos2) {
            System.out.println("DOCUMENTO id: " + d.getId() + "Peso: " + d.getPeso());
            documentos.add(docDao.obtenerDocumentos(d.getId()).get(0));
        }
        return documentos;
    }

    public HashMap<String, PalabraBean> getPalabras() {
        return palabras;
    }

    public void setPalabras(HashMap<String, PalabraBean> palabras) {
        this.palabras = palabras;
    }

    private class documento {

        private Integer peso;
        private Integer id;

        public documento() {
        }

        public documento(Integer id, Integer peso) {
            this.peso = peso;
            this.id = id;
        }

        public Integer getPeso() {
            return peso;
        }

        public void setPeso(Integer peso) {
            this.peso = peso;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

}
