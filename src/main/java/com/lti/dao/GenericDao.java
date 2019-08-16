package com.lti.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Admin;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;

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

	public FarmerDetails loginValidationFarmer(String email, String password) {

		String query = "select f from FarmerDetails f where f.email=:em and f.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			FarmerDetails f = (FarmerDetails) q.getSingleResult();
			System.out.println(f);
			return f;

		} catch (Exception e) {
			return null;

		}
	}

	public BidderDetails loginValidationBidder(String email, String password) {
		String query = "select b from BidderDetails b where b.email=:em and b.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			BidderDetails f = (BidderDetails) q.getSingleResult();
			System.out.println(f);
			return f;

		} catch (Exception e) {
			return null;
		}
	}

	public Admin loginValidationAdmin(String email, String password) {
		String query = "select a from Admin a where a.email=:em and a.password=:pwd";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("pwd", password);

		try {

			Admin a = (Admin) q.getSingleResult();
			System.out.println(a);
			return a;

		} catch (Exception e) {
			return null;

		}
	}

	public <E> List<E> fetchDataFSR(Class<E> clazz) {
		String q = "select obj from " + clazz.getName() + " as obj where sellRequestId=121";
		return entityManager.createQuery(q).getResultList();
	}

	/*public <E> List<E> fetchAllSellingCrops(Class<E> clazz) {
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println(currentTime);
		String q = "select obj from " + clazz.getName() + " as obj where status=1 and sysdate()<endDateTime";
		return entityManager.createQuery(q).getResultList();
	}*/
	
	public List<FarmerSellRequest> currentBidDetails() {
		String q="select s from FarmerSellRequest s";
		List<FarmerSellRequest> list = entityManager.createQuery(q).getResultList();
		try {
		for(FarmerSellRequest s : list) {
			String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId = :id";
			Double max = (Double) entityManager.createQuery(qry).setParameter("id", s.getSellRequestId()).getSingleResult();
			s.setMaxBidAmount(max);
			System.out.println(max);
		}}
		catch(Exception e){
			
		}
		return list;
	}
	

	public List<FarmerSellRequest> fetchAllSellingCrops() {
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println(currentTime);
		String q = "select s from FarmerSellRequest s where s.status=1 and sysdate()<s.endDateTime";
		List<FarmerSellRequest> list = entityManager.createQuery(q).getResultList();
		try {
			for (FarmerSellRequest s : list) {

				String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId=:id";
				Double max=(Double) entityManager.createQuery(qry).setParameter("id", s.getSellRequestId()).getSingleResult();

				s.setMaxBidAmount(max);
				System.out.println(max);
				
			}
		} catch (Exception e) {

		}
		return list;
	}
	
	public <E> List<E> fetchAllUnapproved(Class<E> clazz) {
		String q = "select obj from " + clazz.getName() + " as obj where status=0";
		return entityManager.createQuery(q).getResultList();
	}
	
}
