/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import beans.PalabraBean;
import daos.PalabraDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Salvador
 */
@Stateless
public class Busqueda implements BusquedaRemote {

    @Inject
    private PalabraDao palDao;
    
    private HashMap<String, PalabraBean> palabras = new HashMap<>();

    
   @PostConstruct
    public void init() {
        List<PalabraBean> pals = palDao.obtenerPalabras();
        for (PalabraBean pal : pals) {
            palabras.put(pal.getPalabra(), pal);
        }
    }

    @Override
    public void buscar(List<String> palabras_e) {
        List<PalabraBean> lista_palabras = new ArrayList<>();
        for(String pal : palabras_e) {
            if(palabras.get(pal)!=null){
                lista_palabras.add(palabras.get(pal));
            }
        }
    }
    
    
    public HashMap<String, PalabraBean> getPalabras() {
        return palabras;
    }

    public void setPalabras(HashMap<String, PalabraBean> palabras) {
        this.palabras = palabras;
    }
    

}
