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

public class KhoaDAO {
	private static SessionFactory factory;

	public KhoaDAO() {
		factory = HibernateUtil.getSessionFactory();
	}

	public long getCountKhoa(String search) {
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = null;
		Query query = null;
		long count = 0;
		try {
			tx = session.beginTransaction();
			if (search == null || search == "") {
				hql = "SELECT COUNT(K) FROM Khoa K";
				query = session.createQuery(hql);
			} else {
				hql = "SELECT COUNT(K) FROM Khoa K WHERE K.tenkhoa = :search";
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

	public List<Khoa> getListKhoa(int page, String search) {
		if (page <= 1) {
			page = 0;
		} else {
			page = (page - 1) * 5;
		}
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<Khoa> list = null;
		try {
			tx = session.beginTransaction();
			if (search == null) {
				cr = session.createCriteria(Khoa.class);
			} else {
				cr = session.createCriteria(Khoa.class);
				cr.add(Restrictions.ilike("tenkhoa", "%" + search + "%"));
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

	public List<Khoa> getAllKhoa() {
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<Khoa> list = null;
		try {
			tx = session.beginTransaction();
			cr = session.createCriteria(Khoa.class);
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

	public Khoa getKhoaByMaKhoa(int makhoa) {
		Session session = factory.openSession();
		Transaction tx = null;
		Khoa khoa = null;
		try {
			tx = session.beginTransaction();
			khoa = session.get(Khoa.class, makhoa);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return khoa;
	}

	public boolean insertKhoa(Khoa khoa) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			Khoa model = new Khoa(khoa.getTenkhoa(), khoa.getSodienthoai());
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

	public boolean updateKhoa(Khoa khoa) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			Khoa model = session.get(Khoa.class, khoa.getMakhoa());
			model.setTenkhoa(khoa.getTenkhoa());
			model.setSodienthoai(khoa.getSodienthoai());
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

	public boolean deleteKhoa(int makhoa) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			Khoa model = session.get(Khoa.class, makhoa);
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
