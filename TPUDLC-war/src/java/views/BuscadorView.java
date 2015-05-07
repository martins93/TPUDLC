package views;

import beans.DocumentoBean;
import ejb.BusquedaRemote;
import ejb.IndexacionRemote;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.swing.text.Document;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


@ManagedBean
@RequestScoped
public class BuscadorView implements Serializable {

    @EJB
    private IndexacionRemote indexacion;

    @EJB
    private BeanInterfazRemote bean;

    @EJB
    private BusquedaRemote busqueda;

    private UploadedFile archivo;
    private InputStream is;
    private OutputStream os;
    private File targetFile;
    private String txtBusqueda;
    private List<DocumentoBean> documentos;
    private String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<DocumentoBean> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoBean> documentos) {
        this.documentos = documentos;
    }
    

    public void buscar_texto() {
        documentos = new ArrayList<>();
        ArrayList<String> palabras = new ArrayList<>(Arrays.asList(txtBusqueda.split("[^a-zA-ZñÑá-ú�?-Ú]")));
        palabras.removeAll(Arrays.asList(null, ""));

        documentos = busqueda.buscar(palabras);
        documento = "/" + documentos.get(0).getNombre();
    }

  
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
                System.out.println("NOMBRE Y DIRECCION ABSOLUTA DEL ARCHIVO PUTO: " + targetFile.getAbsolutePath());
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
