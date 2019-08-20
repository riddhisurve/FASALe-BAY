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

@Repository
public class Dao {

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

			return a;

		} catch (Exception e) {
			return null;

		}
	}

	public <E> List<E> fetchDataFSR(Class<E> clazz, int fid) {
		String query = "select obj from " + clazz.getName() + " as obj where sellRequestId=:id";
		Query q = entityManager.createQuery(query);
		q.setParameter("id", fid);
		return q.getResultList();
	}

	public <E> List<E> fetchDataFSH(Class<E> clazz, int fid) {
		String query = "select obj from " + clazz.getName()
				+ " as obj where obj.farmerDetails.farmerId=:id and obj.sold=1 ";

		Query q = entityManager.createQuery(query);
		q.setParameter("id", fid);
		return q.getResultList();
	}

	public List<FarmerSellRequest> currentBidDetails() {
		String q = "select s from FarmerSellRequest s where s.sold=0 and sysdate()<s.endDateTime";
		List<FarmerSellRequest> list = entityManager.createQuery(q).getResultList();
		try {
			for (FarmerSellRequest s : list) {
				String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId = :id ";
				Double max = (Double) entityManager.createQuery(qry).setParameter("id", s.getSellRequestId())
						.getSingleResult();
				s.setMaxBidAmount(max);

			}
		} catch (Exception e) {

		}
		return list;
	}

	public List<FarmerSellRequest> fetchAllSellingCrops(int farmerId) {
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println(currentTime);

		String q = "select s from FarmerSellRequest s where s.status=1 and sold=0 and sysdate()<s.endDateTime and s.farmerDetails.farmerId=:farmerId";
		Query query = entityManager.createQuery(q);
		query.setParameter("farmerId", farmerId);
		List<FarmerSellRequest> list = query.getResultList();

		try {
			for (FarmerSellRequest s : list) {

				String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId=:id";
				Double max = (Double) entityManager.createQuery(qry).setParameter("id", s.getSellRequestId())
						.getSingleResult();

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

	public List<FarmerSellRequest> fetchAllRequestByFarmerId(int farmerId) {
		FarmerSellRequest fsr = new FarmerSellRequest();

		String query = "select s from FarmerSellRequest s where s.farmerDetails.farmerId=:id and sold=0";
		Query q = entityManager.createQuery(query);
		q.setParameter("id", farmerId);
		int Status = fsr.getStatus();
		System.out.println(Status);
		return q.getResultList();

	}

	public double getMaxBid(int sellId) {
		String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId=:id";

		Double max = (Double) entityManager.createQuery(qry).setParameter("id", sellId).getSingleResult();
		return max;

	}

	public FarmerDetails forgetValidationFarmer(String email, String aadhar) {
		String query = "select f from FarmerDetails f where f.email=:em and f.aadharcard=:aadhar";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("aadhar", aadhar);

		try {

			FarmerDetails f = (FarmerDetails) q.getSingleResult();
			System.out.println(f);

			return f;

		} catch (Exception e) {
			return null;
		}
	}

	public BidderDetails forgetValidationBidder(String email, String aadhar) {
		String query = "select b from BidderDetails b where b.email=:em and b.aadharcard=:aadhar";
		Query q = entityManager.createQuery(query);

		q.setParameter("em", email);
		q.setParameter("aadhar", aadhar);

		try {

			BidderDetails b = (BidderDetails) q.getSingleResult();

			return b;

		} catch (Exception e) {
			return null;

		}
	}

	public List<FarmerSellRequest> fetchBidOverDetails() {
		String q = "select s from FarmerSellRequest s where s.sold=0 and sysdate() > s.endDateTime";
		List<FarmerSellRequest> list = entityManager.createQuery(q).getResultList();
		try {
			for (FarmerSellRequest s : list) {
				String qry = "select max(b.bidAmount) from BiddingDetails b where b.farmerSellRequest.sellRequestId = :id ";
				Double max = (Double) entityManager.createQuery(qry).setParameter("id", s.getSellRequestId())
						.getSingleResult();
				s.setMaxBidAmount(max);

			}
		} catch (Exception e) {

		}
		return list;
	}
}
