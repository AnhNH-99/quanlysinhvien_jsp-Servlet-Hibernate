package com.javaweb.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.dao.GiangVienDAO;
import com.javaweb.dao.KhoaDAO;
import com.javaweb.model.GiangVien;
import com.javaweb.model.Khoa;

@WebServlet(urlPatterns = { "/quan-ly-giang-vien" })
public class GiangVienController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INSERT = "/views/giangvien/themmoigiangvien.jsp";
	private String LIST = "/views/giangvien/quanlygiangvien.jsp";
	private String ERROR = "/views/error.jsp";
	private GiangVienDAO giangVienDao;
	private KhoaDAO khoaDao;

	public GiangVienController() {
		giangVienDao = new GiangVienDAO();
		khoaDao = new KhoaDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String forward = "";
		String action = req.getParameter("action");
		String search = req.getParameter("search");
		String pagestr = req.getParameter("page");
		int page = 0;
		int listPage = 0;
		List<GiangVien> listGiangVien = null;
		listPage = (int) giangVienDao.getCountGiangVien(search);
		if (action == null) {
			forward = LIST;
			if (search != null) {
				req.setAttribute("search", search);
			}
		} else {
			if (action.equalsIgnoreCase("themmoigiangvien")) {
				forward = INSERT;
				List<Khoa> listKhoa = khoaDao.getAllKhoa();
				req.setAttribute("listKhoa", listKhoa);
			} else if (action.equalsIgnoreCase("suagiangvien")) {
				int magiangvien = Integer.parseInt(req.getParameter("magiangvien"));
				forward = INSERT;
				req.setAttribute("update", true);
				GiangVien giangvien = giangVienDao.getGiangVienByMaGiangVien(magiangvien);
				req.setAttribute("giangvien", giangvien);
				List<Khoa> listKhoa = khoaDao.getAllKhoa();
				req.setAttribute("listKhoa", listKhoa);
			} else if (action.equalsIgnoreCase("xoagiangvien")) {
				int magiangvien = Integer.parseInt(req.getParameter("magiangvien"));
				boolean result = giangVienDao.deleteGiangVien(magiangvien);
				if (!result) {
					forward = ERROR;
				} else {
					forward = LIST;
				}
			}
		}
		if (pagestr == null) {
			page = 1;
		} else {
			page = Integer.parseInt(pagestr);
		}
		req.setAttribute("page", page);
		if (listPage < 5) {
			listPage = 1;
		} else {
			if (listPage % 5 > 0) {
				listPage = (listPage / 5) + 1;
			} else {
				listPage = (listPage / 5);
			}
		}

		req.setAttribute("listPage", listPage);
		listGiangVien = giangVienDao.getListGiangVien(page, search);
		req.setAttribute("listGiangVien", listGiangVien);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String forward = "";
		int page = 1;
		int listPage = 0;
		List<GiangVien> listGiangVien = null;
		GiangVien giangVien = null;
		listPage = (int) giangVienDao.getCountGiangVien(null);
		String magiangvien = req.getParameter("magiangvien");
		String tengiangvien = req.getParameter("tengiangvien");
		byte gioitinh =Byte.parseByte(req.getParameter("gioitinh"));
		Date ngaysinh;
		try {
			ngaysinh = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("ngaysinh"));
		} catch (ParseException e) {
			ngaysinh = null;
		}
		java.sql.Date sDate = new java.sql.Date(ngaysinh.getTime());
		String diachi = req.getParameter("diachi");
		String email = req.getParameter("email");
		String sodienthoai = req.getParameter("sodienthoai");
		int makhoa = Integer.parseInt(req.getParameter("khoa"));

		if (magiangvien == null) {
			giangVien = new GiangVien();
			giangVien.setTengiangvien(tengiangvien);
			giangVien.setGioitinh(gioitinh);
			giangVien.setNgaysinh(sDate);
			giangVien.setDiachi(diachi);
			giangVien.setEmail(email);
			giangVien.setSodienthoai(sodienthoai);
			Khoa khoa = khoaDao.getKhoaByMaKhoa(makhoa);
			giangVien.setKhoa(khoa);
			boolean result = giangVienDao.insertGiangVien(giangVien);
			if (!result) {
				forward = ERROR;
			} else {
				forward = LIST;
			}
		} else {
			int magv = Integer.parseInt(magiangvien);
			giangVien = giangVienDao.getGiangVienByMaGiangVien(magv);
			if (giangVien != null) {
				giangVien.setTengiangvien(tengiangvien);
				giangVien.setGioitinh(gioitinh);
				giangVien.setNgaysinh(sDate);
				giangVien.setDiachi(diachi);
				giangVien.setEmail(email);
				giangVien.setSodienthoai(sodienthoai);
				Khoa khoa = khoaDao.getKhoaByMaKhoa(makhoa);
				giangVien.setKhoa(khoa);
				boolean result = giangVienDao.updateGiangVien(giangVien);
				if (!result) {
					forward = ERROR;
				} else {
					forward = LIST;
				}
			} else {
				forward = ERROR;
			}
		}
		req.setAttribute("page", page);
		if (listPage < 5) {
			listPage = 1;
		} else {
			if (listPage % 5 > 0) {
				listPage = (listPage / 5) + 1;
			} else {
				listPage = (listPage / 5);
			}
		}

		req.setAttribute("listPage", listPage);
		listGiangVien = giangVienDao.getListGiangVien(page, null);
		req.setAttribute("listGiangVien", listGiangVien);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}
}
