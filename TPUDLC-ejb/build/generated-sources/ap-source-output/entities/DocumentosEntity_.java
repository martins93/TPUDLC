package entities;

import entities.VocabularioEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-04T16:12:56")
@StaticMetamodel(DocumentosEntity.class)
public class DocumentosEntity_ { 

    public static volatile ListAttribute<DocumentosEntity, VocabularioEntity> vocabularioEntityList;
    public static volatile SingularAttribute<DocumentosEntity, Date> fechaCarga;
    public static volatile SingularAttribute<DocumentosEntity, Integer> id;
    public static volatile SingularAttribute<DocumentosEntity, String> nombre;

}