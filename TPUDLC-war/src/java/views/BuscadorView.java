
package views;

import beans.DocumentoBean;
import beans.PalabraBean;
import beans.VocabularioBean;
import ejb.BeanInterfazRemote;
import ejb.IndexacionRemote;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.UploadedFile;


@ManagedBean
@RequestScoped
public class BuscadorView implements Serializable {
    
   @EJB
   private IndexacionRemote indexacion;

    //@EJB
  //  private BeanInterfazRemote bean;
    
   // @EJB
   // private BusquedaRemote busqueda;
    
    private UploadedFile archivo;
    private InputStream is;
    private OutputStream os;
    private File targetFile;
    private String txtBusqueda;
    

  /*  public void buscar_texto(){
        
        ArrayList<String> palabras = new ArrayList<>(Arrays.asList(txtBusqueda.split("[^a-zA-ZñÑá-úÁ-Ú]")));
        palabras.removeAll(Arrays.asList(null,""));
        
        System.out.println("Palabras Ingresadas: " + palabras);
        busqueda.buscar(palabras);
    }*/
    

    public void upload() {
        if (archivo != null) {
            try {
                is = archivo.getInputstream();
                targetFile = new File(archivo.getFileName());
                os = new FileOutputStream(targetFile);

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }

                indexacion.init(targetFile);
                indexacion.leerArchivo();
              //  Files.copy(is, targetFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        // outputStream.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }
    
    
    
    // ----------- GETTERS & SETTERS ----------- 
   
    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
    
    public String getTxtBusqueda() {
        return txtBusqueda;
    }

    public void setTxtBusqueda(String txtBusqueda) {
        this.txtBusqueda = txtBusqueda;
    }
    

}
