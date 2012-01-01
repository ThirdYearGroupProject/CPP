package ic.doc.cpp.server.dao;

import ic.doc.cpp.server.domain.CompanyCategory;
import ic.doc.cpp.server.domain.CompanyCategory_;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public class CompanyCategoryDao extends BaseDao{
	
	public Long createCompanyCategory(CompanyCategory companyCategory) {
		
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Long categoryId = -1L;
		
		try {
			tx.begin();
			em.persist(companyCategory);
			categoryId = companyCategory.getCategoryId();
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		
		return categoryId;
		
	}
	
	public CompanyCategory retrieveCompanyCategory(Long categoryId) {
		EntityManager em = createEntityManager();
		CompanyCategory companyC = null;

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CompanyCategory> cq = cb.createQuery(CompanyCategory.class);
			Root<CompanyCategory> a = cq.from(CompanyCategory.class);
			Expression<Boolean> p = cb.equal(a.get(CompanyCategory_.categoryId),categoryId);
			cq.where(p);
			TypedQuery<CompanyCategory> query = em.createQuery(cq);
			companyC = query.getSingleResult();
		} finally {
			em.close();
		}
		return companyC;
	}
	
	public List<CompanyCategory> retrieveCompanyCategorys() {
		EntityManager em = createEntityManager();
		List<CompanyCategory> companyC = new LinkedList<CompanyCategory>();

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CompanyCategory> cq = cb.createQuery(CompanyCategory.class);
			cq.from(CompanyCategory.class);
			TypedQuery<CompanyCategory> query = em.createQuery(cq);
			companyC = query.getResultList();
		} finally {
			em.close();
		}
		return companyC;
	}
	
	public CompanyCategory updateCompanyCategory(CompanyCategory companyCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		CompanyCategory companyCategory2 = null;
		
		try {
			tx.begin();
			companyCategory2 = em.merge(companyCategory);
			tx.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return companyCategory2;
	}
	
	public void deleteCompanyCategory(CompanyCategory companyCategory) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(companyCategory));
			tx.commit();
		} catch (Throwable t){
			t.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

}
