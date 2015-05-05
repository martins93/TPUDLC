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
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Martin
 */
@Stateless
public class BeanInterfaz implements BeanInterfazRemote {

    @Inject
    private DocumentoDao dDao;

    @Inject
    private PalabraDao pDao;

    @Inject
    private VocabularioDao vDao;

    @Override
    public List<DocumentoBean> listarDocumentos() {

        return dDao.obtenerDocumentos();

    }

    @Override
    public List<PalabraBean> listarPalabras() {

        return pDao.obtenerPalabras();
    }

    @Override
    public List<VocabularioBean> listarVocabulario() {
        return vDao.obtenerVocabulario();

    }

    @Override
    public void insertarDocumentos(DocumentoBean doc) {
        dDao.insertarDocumentos(doc);
    }

    @Override
    public void insertarPalabras(PalabraBean pal) {
        pDao.insertarPalabras(pal);
    }

    @Override
    public void insertarVocabularios(VocabularioBean voc) {
        vDao.insertarVocabularios(voc);
    }


  
   
}
