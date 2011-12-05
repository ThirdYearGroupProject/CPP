package ic.doc.cpp.server.domain;

import ic.doc.cpp.server.domain.Company;
import ic.doc.cpp.server.domain.StudentUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.1.v20111018-r10243", date="2011-12-05T16:44:03")
@StaticMetamodel(CompanyUser.class)
public class CompanyUser_ { 

    public static volatile SingularAttribute<CompanyUser, String> lastName;
    public static volatile ListAttribute<CompanyUser, StudentUser> interestedStudents;
    public static volatile SingularAttribute<CompanyUser, String> email;
    public static volatile SingularAttribute<CompanyUser, Company> company;
    public static volatile SingularAttribute<CompanyUser, String> gender;
    public static volatile SingularAttribute<CompanyUser, String> login;
    public static volatile SingularAttribute<CompanyUser, String> firstName;
    public static volatile SingularAttribute<CompanyUser, String> password;
    public static volatile SingularAttribute<CompanyUser, String> salt;

}