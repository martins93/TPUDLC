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
import java.util.HashMap;
import java.util.List;
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
    private  VocabularioDao vocDao;
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
        List<DocumentoBean> documentos = new ArrayList<>();
        List<VocabularioBean> vocabularios = new ArrayList<>();
        
        for (String pal : palabras_e) 
            if (palabras.get(pal) != null) 
                lista_palabras.add(palabras.get(pal));
            
        
        vocabularios = vocDao.obtenerVocabulario(lista_palabras.get(0));
        for(VocabularioBean voc : vocabularios) 
            documentos.add(docDao.obtenerDocumentos(voc.getDocumento_id()).get(0));
        
        return documentos;
    }

    public HashMap<String, PalabraBean> getPalabras() {
        return palabras;
    }

    public void setPalabras(HashMap<String, PalabraBean> palabras) {
        this.palabras = palabras;
    }

}
