package entities;

import entities.DocumentosEntity;
import entities.PalabrasEntity;
import entities.VocabularioEntityPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T17:11:58")
@StaticMetamodel(VocabularioEntity.class)
public class VocabularioEntity_ { 

    public static volatile SingularAttribute<VocabularioEntity, VocabularioEntityPK> vocabularioEntityPK;
    public static volatile SingularAttribute<VocabularioEntity, PalabrasEntity> palabrasEntity;
    public static volatile SingularAttribute<VocabularioEntity, DocumentosEntity> documentosEntity;
    public static volatile SingularAttribute<VocabularioEntity, Integer> apariciones;

}