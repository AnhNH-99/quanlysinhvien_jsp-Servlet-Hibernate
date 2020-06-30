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
import com.javaweb.model.ChuyenNganh;
import com.javaweb.model.Khoa;

public class ChuyenNganhDAO {
	private static SessionFactory factory;

	public ChuyenNganhDAO() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	public long getCountChuyenNganh(String search) {
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = null;
		Query query = null;
		long count = 0;
		try {
			tx = session.beginTransaction();
			if (search == null || search == "") {
				hql = "SELECT COUNT(CN) FROM ChuyenNganh CN";
				query = session.createQuery(hql);
			} else {
				hql = "SELECT COUNT(CN) FROM ChuyenNganh CN WHERE CN.tenchuyennganh= :search";
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
	
	public List<ChuyenNganh> getListChuyenNganh(int page, String search) {
		if (page <= 1) {
			page = 0;
		} else {
			page = (page - 1) * 5;
		}
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<ChuyenNganh> list = null;
		try {
			tx = session.beginTransaction();
			if (search == null) {
				cr = session.createCriteria(ChuyenNganh.class);
			} else {
				cr = session.createCriteria(ChuyenNganh.class);
				cr.add(Restrictions.ilike("tenchuyennganh", "%" + search + "%"));
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
	
	public List<ChuyenNganh> getAllChuyenNganh() {
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<ChuyenNganh> list = null;
		try {
			tx = session.beginTransaction();
			cr = session.createCriteria(ChuyenNganh.class);
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
	
	public ChuyenNganh getChuyenNganhByMaChuyenNganh(int macn) {
		Session session = factory.openSession();
		Transaction tx = null;
		ChuyenNganh chuyennganh = null;
		try {
			tx = session.beginTransaction();
			chuyennganh = session.get(ChuyenNganh.class, macn);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return chuyennganh;
	}
	
	public boolean insertChuyenNganh(ChuyenNganh chuyennganh) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			ChuyenNganh model = new ChuyenNganh(chuyennganh.getTenchuyennganh(), chuyennganh.getKhoa());
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
	
	public boolean updateChuyenNganh(ChuyenNganh chuyennganh) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			ChuyenNganh model = session.get(ChuyenNganh.class, chuyennganh.getMachuyennganh());
			model.setTenchuyennganh(chuyennganh.getTenchuyennganh());
			model.setKhoa(chuyennganh.getKhoa());
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
	
	public boolean deleteChuyenNganh(int macn) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			ChuyenNganh model = session.get(ChuyenNganh.class, macn);
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
