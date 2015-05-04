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
import entities.DocumentosEntity;
import entities.PalabrasEntity;
import entities.VocabularioEntity;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import logics.Documento;
import logics.Palabra;
import logics.Vocabulario;

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
    public List <DocumentoBean> listarDocumentos() {
        
        List<DocumentosEntity> list = dDao.obtenerDocumentos();
        return entitiesToBeansDoc(list);
    }
    
    @Override
    public List<PalabraBean> listarPalabras() {
        
        List<PalabrasEntity> list = pDao.obtenerPalabras();
        return entitiesToBeansPal(list);
    }
    
    @Override
    public List <VocabularioBean> listarVocabulario() {
        List<VocabularioEntity> list = vDao.obtenerVocabulario();
        return entitiesToBeansVoc(list);
    }
    
    @Override
    public void insertarDocumentos(DocumentoBean doc)
    {
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

    
    
      
    private List<VocabularioBean> entitiesToBeansVoc(List <VocabularioEntity> entidades)
    {
        LinkedList<VocabularioBean> lista = new LinkedList<>();
        for(VocabularioEntity entidad : entidades)
        {
            lista.add(new Vocabulario(entidad).getBean());
        }    
        return lista;
            
    }
    
 
    private List<PalabraBean> entitiesToBeansPal (List<PalabrasEntity> entidades)
    {
        LinkedList<PalabraBean> beans = new LinkedList<>();
        for (PalabrasEntity entidad: entidades) 
        {
            beans.add(new Palabra(entidad).getBean());
        }
        
        return beans;
    }
    
    private List<DocumentoBean> entitiesToBeansDoc(List<DocumentosEntity> entidades)
    {
        LinkedList<DocumentoBean> beans = new LinkedList<>();
        for(DocumentosEntity entidad : entidades)
        {
           beans.add(new Documento(entidad).getBean());
        }
        
        return beans;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

  
   
}
