package entities;

import entities.VocabularioEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T17:11:58")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-04T22:18:38")
>>>>>>> 09dec1d227b893addc2e2e992b7550808ea63874
@StaticMetamodel(PalabrasEntity.class)
public class PalabrasEntity_ { 

    public static volatile ListAttribute<PalabrasEntity, VocabularioEntity> vocabularioEntityList;
    public static volatile SingularAttribute<PalabrasEntity, String> palabra;
    public static volatile SingularAttribute<PalabrasEntity, Integer> id;
    public static volatile SingularAttribute<PalabrasEntity, Integer> cantDocumentos;
    public static volatile SingularAttribute<PalabrasEntity, Integer> maxAparicion;

}