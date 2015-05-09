package entities;

import entities.VocabularioEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-08T21:33:24")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T22:32:18")
>>>>>>> e46414ba7e5119b5d8912c103e51c466289f05ca
@StaticMetamodel(DocumentosEntity.class)
public class DocumentosEntity_ { 

    public static volatile ListAttribute<DocumentosEntity, VocabularioEntity> vocabularioEntityList;
    public static volatile SingularAttribute<DocumentosEntity, Date> fechaCarga;
    public static volatile SingularAttribute<DocumentosEntity, Integer> id;
    public static volatile SingularAttribute<DocumentosEntity, String> nombre;

}