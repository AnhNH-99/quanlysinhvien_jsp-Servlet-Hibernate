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
import com.javaweb.model.Khoa;

public class GiangVienDAO {
	private static SessionFactory factory;
	public GiangVienDAO() {
		factory = HibernateUtil.getSessionFactory();
	}
	public long getCountGiangVien(String search) {
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = null;
		Query query = null;
		long count = 0;
		try {
			tx = session.beginTransaction();
			if (search == null || search == "") {
				hql = "SELECT COUNT(GV) FROM GiangVien GV";
				query = session.createQuery(hql);
			} else {
				hql = "SELECT COUNT(GV) FROM GiangVien GV WHERE GV.tengiangvien = :search";
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
	
	public List<GiangVien> getListGiangVien(int page, String search) {
		if (page <= 1) {
			page = 0;
		} else {
			page = (page - 1) * 5;
		}
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<GiangVien> list = null;
		try {
			tx = session.beginTransaction();
			if (search == null) {
				cr = session.createCriteria(GiangVien.class);
			} else {
				cr = session.createCriteria(GiangVien.class);
				cr.add(Restrictions.ilike("tengiangvien", "%" + search + "%"));
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
	
	public List<GiangVien> getAllGiangVien() {
		Session session = factory.openSession();
		Transaction tx = null;
		Criteria cr = null;
		List<GiangVien> list = null;
		try {
			tx = session.beginTransaction();
			cr = session.createCriteria(GiangVien.class);
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
	
	public GiangVien getGiangVienByMaGiangVien(int magiangvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		GiangVien giangvien = null;
		try {
			tx = session.beginTransaction();
			giangvien = session.get(GiangVien.class, magiangvien);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return giangvien;
	}
	
	public boolean insertGiangVien(GiangVien giangvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			GiangVien model = new GiangVien(giangvien.getTengiangvien(), giangvien.getNgaysinh(), giangvien.getSodienthoai(), giangvien.getGioitinh(), giangvien.getDiachi(), giangvien.getEmail(), giangvien.getKhoa());
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
	
	public boolean updateGiangVien(GiangVien giangvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			GiangVien model = session.get(GiangVien.class, giangvien.getMagiangvien());
			model.setTengiangvien(giangvien.getTengiangvien());
			model.setNgaysinh(giangvien.getNgaysinh());
			model.setSodienthoai(giangvien.getSodienthoai());
			model.setGioitinh(giangvien.getGioitinh());
			model.setDiachi(giangvien.getDiachi());
			model.setEmail(giangvien.getEmail());
			model.setKhoa(giangvien.getKhoa());
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
	
	public boolean deleteGiangVien(int magiangvien) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean result = false;
		try {
			tx = session.beginTransaction();
			GiangVien model = session.get(GiangVien.class, magiangvien);
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
