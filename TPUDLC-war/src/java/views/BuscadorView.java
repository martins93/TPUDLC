package views;

import beans.DocumentoBean;
import ejb.BusquedaRemote;
import ejb.IndexacionRemote;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class BuscadorView implements Serializable {

    @EJB
    private IndexacionRemote indexacion;

    @EJB
    private BusquedaRemote busqueda;

    private UploadedFile archivo;
    private InputStream is;
    private OutputStream os;
    private File targetFile;
    private String txtBusqueda;
    private List<DocumentoBean> documentos;
    private DocumentoBean selectedDoc;
    private String nomDoc;

    public void buscar_texto() {
        documentos = new ArrayList<>();
        ArrayList<String> palabras = new ArrayList<>(Arrays.asList(txtBusqueda.split("[^a-zA-ZñÑá-ú�?-Ú]")));
        palabras.removeAll(Arrays.asList(null, ""));

        documentos = busqueda.buscar(palabras);
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
                indexacion.init(targetFile);
                indexacion.leerArchivo();
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
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

    public void render() throws IOException {
        //System.out.println("ENTRE AL ROWSELECT");

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        String nombre = selectedDoc.getNombre();
        String url = "C:/TextosAIndexar/" + nombre;
        //System.out.println("***EL PARAM: " + url);

        BufferedReader br = null;
        String sCurrentLine;
        StringBuilder sB = new StringBuilder();

        br = new BufferedReader(new FileReader(url));

        while ((sCurrentLine = br.readLine()) != null) {
            sB.append(sCurrentLine);
            sB.append("\n");
        }

        ec.setResponseContentType("text/plain");
        ec.setResponseCharacterEncoding("UTF-8");
        ec.getResponseOutputWriter().write(sB.toString());

        fc.responseComplete();

    }

    public void onRowSelect() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().getNavigationHandler().handleNavigation(context, null, "showDoc.xhtml?faces-redirect=true");
    }

    public DocumentoBean getSelectedDoc() {
        return selectedDoc;
    }

    public void setSelectedDoc(DocumentoBean selectedDoc) {
        this.selectedDoc = selectedDoc;

    }

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

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public List<DocumentoBean> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoBean> documentos) {
        this.documentos = documentos;
    }

}
