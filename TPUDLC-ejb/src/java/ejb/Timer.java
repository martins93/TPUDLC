/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.DocumentoBean;
import daos.DocumentoDao;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.ejb.TransactionManagement;
import static javax.ejb.TransactionManagementType.CONTAINER;
import javax.inject.Inject;

/**
 *
 * @author Martin
 */
@Stateless
@TransactionManagement(CONTAINER)
public class Timer implements TimerRemote {
    
    
    String sDirectorio = "C:\\TextosAIndexar";
    File f = new File(sDirectorio);
    File[] files;
    HashMap<String, DocumentoBean> docs;
    
    @Inject
    DocumentoDao dDao;
    
    @EJB
    IndexacionRemote index;
    
    String extension;
   

    @Schedule(dayOfWeek = "*", month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "*", second = "0")
    @Override
    @TransactionAttribute(REQUIRED)
    public void myTimer() {
        System.out.println("Timer event: " + new Date());
 
        
        System.out.println("EJECUTANDO TIMER...");
        /*
       if (f.exists())
                {
                  files = f.listFiles();
                  docs = dDao.cargarHashDocs();
                  if(files.length>0)
                  {
                      for (int i = 0; i < files.length; i++) {
                          
                          extension = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1, files[i].getName().length());
                          if((!docs.containsKey(files[i].getName())) && extension.equals("txt"))
                          {
                              System.out.println("NUEVO DOCUMENTO ENCONTRADO");
                              System.out.println("DOCUMENTO:" + files[i].getName());
                              index.init(files[i]);
                              index.leerArchivo();
                              System.out.println("SE CARGO ARCHIVO:" + files[i].getName());
                          }    
                      }
                     }
                }
                */
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
