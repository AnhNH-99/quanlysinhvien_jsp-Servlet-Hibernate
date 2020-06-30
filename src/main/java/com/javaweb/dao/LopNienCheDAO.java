package com.javaweb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.javaweb.connection.HibernateUtil;
import com.javaweb.model.Khoa;
import com.javaweb.model.LopNienChe;

public class LopNienCheDAO {
	private static SessionFactory factory;
	public LopNienCheDAO() {
		factory = HibernateUtil.getSessionFactory();
	}
	public long getCountLopNienChe(String search) {
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = null;
		Query query = null;
		long count = 0;
		try {
			tx = session.beginTransaction();
			if (search == null || search == "") {
				hql = "SELECT COUNT(LNC) FROM LopNienChe LNC";
				query = session.createQuery(hql);
			} else {
				hql = "SELECT COUNT(LNC) FROM LopNienChe LNC WHERE LNC.tenlopnienche = :search";
				query = session.createQuery(hql);
				query.setParameter("search", "%" + search + "%");
			}
			count = (long) query.uniqueResult();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return count;
	}
	
	public List<LopNienChe> getListLopNienChe(int page, String search) {
		if (page <= 1) {
			page = 0;
		} else {
			page = (page - 1) * 5;
		}
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<LopNienChe> list = null;
		try {
			tx = session.beginTransaction();
			if (search == null) {
				cr = session.createCriteria(LopNienChe.class);
			} else {
				cr = session.createCriteria(LopNienChe.class);
				cr.add(Restrictions.ilike("tenlopnienche", "%" + search + "%"));
			}
			cr.setFirstResult(page);
			cr.setMaxResults(5);
			list = cr.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public List<LopNienChe> getAllLopNienChe() {
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<LopNienChe> list = null;
		try {
			tx = session.beginTransaction();
			cr = session.createCriteria(LopNienChe.class);
			list = cr.list();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public LopNienChe getLopNienCheByMaLopNienChe(int malopnienche) {
		Session session = factory.openSession();
		Transaction tx = null;
		LopNienChe lopnienche = null;
		try {
			tx = session.beginTransaction();
			lopnienche = session.get(LopNienChe.class, malopnienche);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lopnienche;
	}
	
	public boolean insertLopNienChe(LopNienChe lopnienche) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			LopNienChe model = new LopNienChe(lopnienche.getTenlopnienche(), lopnienche.getNienkhoa(), lopnienche.getSiso(), lopnienche.getChuyennganh(), lopnienche.getGiangvien());
			session.save(model);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public boolean updateLopNienChe(LopNienChe lopNienChe) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			LopNienChe model = session.get(LopNienChe.class, lopNienChe.getMalopnienche());
			model.setTenlopnienche(lopNienChe.getTenlopnienche());
			model.setNienkhoa(lopNienChe.getNienkhoa());
			model.setSiso(lopNienChe.getSiso());
			model.setChuyennganh(lopNienChe.getChuyennganh());
			model.setGiangvien(lopNienChe.getGiangvien());
			session.update(model);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
	
	public boolean deleteLopNienChe(int malopnienche) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			LopNienChe model = session.get(LopNienChe.class, malopnienche);
			session.delete(model);
			tx.commit();
			result = true;
		} catch (HibernateException e) {
			result = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}
}
