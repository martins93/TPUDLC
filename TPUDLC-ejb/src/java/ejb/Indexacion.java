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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.BEAN;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import logics.EncodingDetector;
import logics.VocabularioModel;
import javax.transaction.UserTransaction;

/**
 *
 * @author Martin
 */
@Stateless
@TransactionManagement(BEAN)
public class Indexacion implements IndexacionRemote {
    
  @Resource
  private UserTransaction u;    
     
    
    @Inject
    DocumentoDao dDao;

    @Inject
    PalabraDao pDao;

    @Inject
    VocabularioDao vDao;
    
    
   

    private File f;
    private HashMap<String, VocabularioModel> mapa;
    private String nombreDoc;
    private String encoding;

    public Indexacion() {
    }
   
    
    @Override
    public void init(File f) {
        this.f = f;
        mapa = new HashMap<>();
        nombreDoc = f.getName();
        encoding = "";
        System.out.println(nombreDoc);
    }

    public void changeF(String archivo) {
        this.f = new File(archivo);
        nombreDoc = f.getName();
        mapa.clear();
    }

    @Override
    public void leerArchivo() {
        String regex = "[^a-zA-ZñÑá-úÁ-Ú]";

        try {
            EncodingDetector ed = new EncodingDetector();
            encoding = ed.detect(f.getAbsolutePath());
            Scanner fileScan = new Scanner(f, encoding).useDelimiter(regex);
            String word;

            while (fileScan.hasNext()) {
                word = fileScan.next();
                word = word.toLowerCase();
                if (word.length() > 1) {
                    addToMap(mapa, word);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo");
        } catch (IOException e) {
            System.out.println("Error entrada/salida");
        }
        System.out.println("HASH CARGADO");
        System.out.println("POR INSERTAR...");
        
        
        insertar(mapa);
        
        System.out.println("YA INSERTE");
      

    }

    private void addToMap(HashMap mapa, String palabra) {
        VocabularioModel voc = (VocabularioModel) mapa.get(palabra);
        if (voc != null) {
            voc.setFrecuencia(voc.getFrecuencia() + 1);
            mapa.put(palabra, voc);
        } else {
            VocabularioModel nuevo = new VocabularioModel(palabra, 1, nombreDoc);
            mapa.put(palabra, nuevo);
        }
    }
    //@TransactionAttribute(REQUIRED)
    public void insertar(HashMap<String, VocabularioModel> mapa) {
        
        
        Iterator it;
        Integer idDoc;
        String key; 
        Date today = new Date();
        VocabularioModel v; 
        
        DocumentoBean documento;
        PalabraBean palabra;
        VocabularioBean vocabulario;
                
        it = mapa.keySet().iterator();
        
        HashMap<String, PalabraBean> hashDB = pDao.cargarHashPalabras();
        System.out.println("CARGUE HASH PALABRSA DE LA DB");
        
       
        
     
        try
             {
           
                      u.begin();
                      System.out.println("ARRANCO EL BEGIN");
                       int contador = 0;
                       
                       documento = new DocumentoBean(today,nombreDoc);
                       dDao.insertarDocumentos(documento);
                       idDoc = dDao.getIdDocumento();
                       System.out.println("EL HASHMAP TIENE: " + mapa.size()+ "PALABRAS");
                       while (it.hasNext()) {
         
           
               
               key = it.next().toString();
               v = (VocabularioModel) mapa.get(key);
            
               palabra = new PalabraBean(1,1,v.getPalabra());
                
               
                   if(hashDB.containsKey(palabra.getPalabra()))
                   {
                       vocabulario = new VocabularioBean(hashDB.get(palabra.getPalabra()).getId(),idDoc, v.getFrecuencia());
                       vDao.insertarVocabularios(vocabulario);
                   }
                   else
                   {
                       pDao.insertarPalabras(palabra);
                       vocabulario = new VocabularioBean(pDao.getIdPalabra(),idDoc, v.getFrecuencia());
                       vDao.insertarVocabularios(vocabulario);
                   }
                   
                   contador++;
                   System.out.println(contador);
             }
                     u.commit();
                      System.out.println("COMITIO");
                  
           
               }
               catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e)
               {
                   try {
                       System.out.println("SALTO UNA EXCEPTION DE TRANSACCION");
                       u.rollback();
                   } catch (IllegalStateException | SecurityException | SystemException ex) {
                       Logger.getLogger(Indexacion.class.getName()).log(Level.SEVERE, null, ex);    
                   }  
               }
                
        }
               
    

    public String getNombreArchivo() {
        return nombreDoc;
    }
    
  

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<DocumentoBean> listarDocumentos() {
        return dDao.obtenerDocumentos();
    }

}
