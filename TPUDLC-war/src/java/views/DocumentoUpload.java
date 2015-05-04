/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.File;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Martin
 */
@ManagedBean
@RequestScoped
public class DocumentoUpload implements Serializable{

    /**
     * Creates a new instance of DocumentoUpload
     */
    
    private UploadedFile archivo;
    
    public DocumentoUpload() {
    }
    
    
    
}
