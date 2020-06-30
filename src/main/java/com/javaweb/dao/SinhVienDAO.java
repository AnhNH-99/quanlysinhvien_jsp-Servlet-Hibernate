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
import com.javaweb.model.GiangVien;
import com.javaweb.model.SinhVien;

public class SinhVienDAO {
	private static SessionFactory factory;
	public SinhVienDAO() {
		factory = HibernateUtil.getSessionFactory();
	}
	public long getCountSinhVien(String search) {
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = null;
		Query query = null;
		long count = 0;
		try {
			tx = session.beginTransaction();
			if (search == null || search == "") {
				hql = "SELECT COUNT(SV) FROM SinhVien SV";
				query = session.createQuery(hql);
			} else {
				hql = "SELECT COUNT(SV) FROM SinhVien SV WHERE SV.hoten = :search";
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
	
	public List<SinhVien> getListSinhVien(int page, String search) {
		if (page <= 1) {
			page = 0;
		} else {
			page = (page - 1) * 5;
		}
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<SinhVien> list = null;
		try {
			tx = session.beginTransaction();
			if (search == null) {
				cr = session.createCriteria(SinhVien.class);
			} else {
				cr = session.createCriteria(SinhVien.class);
				cr.add(Restrictions.ilike("hoten", "%" + search + "%"));
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
	
	public SinhVien getSinhViennByMaSinhVien(int masinhvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		SinhVien sinhvien = null;
		try {
			tx = session.beginTransaction();
			sinhvien = session.get(SinhVien.class, masinhvien);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return sinhvien;
	}
	
	public boolean insertSinhVien(SinhVien sinhvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			SinhVien model = new SinhVien(sinhvien.getHoten(), sinhvien.getNgaysinh(), sinhvien.getGioitinh(), sinhvien.getDiachi(), sinhvien.getEmail(), sinhvien.getSodienthoai(), sinhvien.getLopnienche());
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
	
	public boolean updateSinhVien(SinhVien sinhvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			SinhVien model = session.get(SinhVien.class, sinhvien.getMasinhvien());
			model.setHoten(sinhvien.getHoten());
			model.setNgaysinh(sinhvien.getNgaysinh());
			model.setGioitinh(sinhvien.getGioitinh());
			model.setDiachi(sinhvien.getDiachi());
			model.setEmail(sinhvien.getEmail());
			model.setSodienthoai(sinhvien.getSodienthoai());
			model.setLopnienche(sinhvien.getLopnienche());
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
	
	public boolean deleteSinhVien(int masinhvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			SinhVien model = session.get(SinhVien.class, masinhvien);
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
