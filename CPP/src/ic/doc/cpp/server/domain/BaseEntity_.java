package ic.doc.cpp.server.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.1.v20111018-r10243", date="2011-12-05T16:44:03")
@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ { 

    public static volatile SingularAttribute<BaseEntity, Date> updatedTimestamp;
    public static volatile SingularAttribute<BaseEntity, String> createdBy;
    public static volatile SingularAttribute<BaseEntity, Date> createdTimestamp;
    public static volatile SingularAttribute<BaseEntity, String> updatedBy;
    public static volatile SingularAttribute<BaseEntity, Integer> version;

}