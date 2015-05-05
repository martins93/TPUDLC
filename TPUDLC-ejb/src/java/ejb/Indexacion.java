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
import java.util.Scanner;
import javax.ejb.Stateless;
import javax.inject.Inject;
import logics.EncodingDetector;
import logics.VocabularioModel;

/**
 *
 * @author Martin
 */
@Stateless
public class Indexacion implements IndexacionRemote {

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
        System.out.println("Estoy por Insertar");
        insertar(mapa);
        System.out.println("Ya inserte");

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

    
    public void insertar(HashMap<String, VocabularioModel> mapa) {
        
        
        Iterator it = mapa.keySet().iterator();
        String key = it.next().toString();
        Date today = new Date();
        VocabularioModel v = (VocabularioModel) mapa.get(key);
        
        DocumentoBean documento;
        PalabraBean palabra;
        VocabularioBean vocabulario;
                
        it = mapa.keySet().iterator();
        while (it.hasNext()) {
            key = it.next().toString();
            v = (VocabularioModel) mapa.get(key);
            documento = new DocumentoBean(today,v.getDocumento());
            palabra = new PalabraBean(1,1,v.getPalabra());
            
       
            dDao.insertarDocumentos(documento);
   
            
      
            pDao.insertarPalabras(palabra);
     
            
         
            vocabulario = new VocabularioBean(pDao.getIdPalabra(palabra),dDao.getIdDocumento(documento), v.getFrecuencia());
            
            vDao.insertarVocabularios(vocabulario);
       

        }
    }

    public String getNombreArchivo() {
        return nombreDoc;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
