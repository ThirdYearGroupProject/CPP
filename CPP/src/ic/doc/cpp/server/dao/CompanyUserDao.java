package ic.doc.cpp.server.dao;

import ic.doc.cpp.server.domain.CompanyUser;
import ic.doc.cpp.server.domain.CompanyUser_;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CompanyUserDao extends BaseDao {
	public String createUser(CompanyUser user) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		String login = "";
		
		try {
			tx.begin();
			em.persist(user);
			login = user.getLogin();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return login;
	}
	
	public CompanyUser updateUser(CompanyUser student) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		CompanyUser student2 = null;
		
		try {
			tx.begin();
			student2 = em.merge(student);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return student2;
		
	}
	
	public CompanyUser retrieveUser(String login) {
		EntityManager em = createEntityManager();
		CompanyUser user = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CompanyUser> cq = cb.createQuery(CompanyUser.class);
			Root<CompanyUser> s = cq.from(CompanyUser.class);
			cq.where(cb.equal(s.get(CompanyUser_.login), login));
			TypedQuery<CompanyUser> query = em.createQuery(cq);
			user = query.getSingleResult();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}
}
