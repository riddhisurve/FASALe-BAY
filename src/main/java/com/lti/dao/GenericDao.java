package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;

//@Component
@Repository // recommended when writing Dao class
public class GenericDao {

	Query q;
	String query;
	int i = 0;

	@PersistenceContext
	protected EntityManager entityManager;

	public Object save(Object obj) {
		return entityManager.merge(obj);
	}

	public <E> E fetchById(Class<E> clazz, Object pk) {
		return entityManager.find(clazz, pk);
	}

	public <E> List<E> fetchAll(Class<E> clazz) {
		String q = "select obj from " + clazz.getName() + " as obj";
		return entityManager.createQuery(q).getResultList();
	}

	public String loginValidationFarmer(String email, String password) {

		String query = "select f from FarmerDetails f where f.email=:em and f.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			FarmerDetails f = (FarmerDetails) q.getSingleResult();

			return "farmer";

		} catch (Exception e) {
			return "false";

		}
	}
	public String loginValidationBidder(String email, String password) {
		String query = "select b from BidderDetails b where b.email=:em and b.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			BidderDetails f = (BidderDetails) q.getSingleResult();

			return "bidder";

		} catch (Exception e) {
			return "false";
		}
	}
	public String loginValidationAdmin(String email, String password) {
		String query = "select a from Admin a where a.email=:em and a.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			Admin a = (Admin) q.getSingleResult();

			return "admin";

		} catch (Exception e) {
			return "false";

		}
	}
	public <E> List<E> fetchDataFSR(Class<E> clazz) {
		String q = "select obj from " + clazz.getName() + " as obj where sellRequestId=121";
		return entityManager.createQuery(q).getResultList();
	}
}
