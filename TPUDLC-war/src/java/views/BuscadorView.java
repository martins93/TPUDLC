/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import beans.DocumentoBean;
import beans.PalabraBean;
import beans.VocabularioBean;
import ejb.BeanInterfazRemote;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Martin
 */
@ManagedBean
@RequestScoped
public class BuscadorView implements Serializable{

    /**
     * Creates a new instance of DocumentoView
     */
    public BuscadorView() {
    }
    
    @EJB
    private BeanInterfazRemote bean;
    
    private List<DocumentoBean> docs;
    private List<PalabraBean> pals;
    private List<VocabularioBean> vocs;
    
    private Date today = new Date();
    //private DocumentoBean document = new DocumentoBean(1,today,"DocumentoDesdeBeanActual");
   //private PalabraBean palabra = new PalabraBean(1,1,1,"PalabraDesdeElBeanActual2");
   // private VocabularioBean vocabulario = new VocabularioBean(16,18,11);
    
    @PostConstruct
    public void init()
    {
        docs = bean.listarDocumentos();
        pals = bean.listarPalabras();
        vocs = bean.listarVocabulario();
       // bean.insertarDocumentos(document); 
        //bean.insertarPalabras(palabra);
      //  bean.insertarVocabularios(vocabulario);
    }

    public List<DocumentoBean> getDocs() {
        return docs;
    }

    public void setDocs(List<DocumentoBean> docs) {
        this.docs = docs;
    }

    public List<PalabraBean> getPals() {
        return pals;
    }

    public void setPals(List<PalabraBean> pals) {
        this.pals = pals;
    }

    public List<VocabularioBean> getVocs() {
        return vocs;
    }

    public void setVocs(List<VocabularioBean> vocs) {
        this.vocs = vocs;
    }

  
    
    
}
