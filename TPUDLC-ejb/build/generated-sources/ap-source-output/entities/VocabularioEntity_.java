package entities;

import entities.DocumentosEntity;
import entities.PalabrasEntity;
import entities.VocabularioEntityPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T19:15:39")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T18:56:45")
>>>>>>> c1d1d1d6fdf88fc25e9064947167abbe72e63cd0
@StaticMetamodel(VocabularioEntity.class)
public class VocabularioEntity_ { 

    public static volatile SingularAttribute<VocabularioEntity, VocabularioEntityPK> vocabularioEntityPK;
    public static volatile SingularAttribute<VocabularioEntity, PalabrasEntity> palabrasEntity;
    public static volatile SingularAttribute<VocabularioEntity, DocumentosEntity> documentosEntity;
    public static volatile SingularAttribute<VocabularioEntity, Integer> apariciones;

}