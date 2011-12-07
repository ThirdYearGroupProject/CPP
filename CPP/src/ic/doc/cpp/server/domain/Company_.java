package ic.doc.cpp.server.domain;

import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.Event;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.1.v20111018-r10243", date="2011-12-06T12:46:06")
@StaticMetamodel(Company.class)
public class Company_ { 

    public static volatile SingularAttribute<Company, String> logo;
    public static volatile SingularAttribute<Company, CompanyCategory> category;
    public static volatile SingularAttribute<Company, String> website;
    public static volatile SingularAttribute<Company, String> description;
    public static volatile ListAttribute<Company, Event> events;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, Long> companyId;

}