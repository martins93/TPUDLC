package entities;

import entities.VocabularioEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T19:15:39")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-07T18:56:45")
>>>>>>> c1d1d1d6fdf88fc25e9064947167abbe72e63cd0
@StaticMetamodel(DocumentosEntity.class)
public class DocumentosEntity_ { 

    public static volatile ListAttribute<DocumentosEntity, VocabularioEntity> vocabularioEntityList;
    public static volatile SingularAttribute<DocumentosEntity, Date> fechaCarga;
    public static volatile SingularAttribute<DocumentosEntity, Integer> id;
    public static volatile SingularAttribute<DocumentosEntity, String> nombre;

}