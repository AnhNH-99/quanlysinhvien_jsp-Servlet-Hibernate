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

import com.javaweb.dao.LopNienCheDAO;
import com.javaweb.dao.SinhVienDAO;
import com.javaweb.model.GiangVien;
import com.javaweb.model.Khoa;
import com.javaweb.model.LopNienChe;
import com.javaweb.model.SinhVien;

@WebServlet(urlPatterns = { "/quan-ly-sinh-vien" })
public class SinhVienController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INSERT = "/views/sinhvien/themmoisinhvien.jsp";
	private String LIST = "/views/sinhvien/quanlysinhvien.jsp";
	private String ERROR = "/views/error.jsp";
	private LopNienCheDAO lopNienCheDao;
	private SinhVienDAO sinhVienDao;

	public SinhVienController() {
		sinhVienDao = new SinhVienDAO();
		lopNienCheDao = new LopNienCheDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String forward = "";
		String action = req.getParameter("action");
		String search = req.getParameter("search");
		String pagestr = req.getParameter("page");
		int page = 0;
		int listPage = 0;
		List<SinhVien> listSinhVien = null;
		listPage = (int) sinhVienDao.getCountSinhVien(search);
		if (action == null) {
			forward = LIST;
			if (search != null) {
				req.setAttribute("search", search);
			}
		} else {
			if (action.equalsIgnoreCase("themmoisinhvien")) {
				forward = INSERT;
				List<LopNienChe> listLopNienChe = lopNienCheDao.getAllLopNienChe();
				req.setAttribute("listLopNienChe", listLopNienChe);
			} else if (action.equalsIgnoreCase("suasinhvien")) {
				int masinhvien = Integer.parseInt(req.getParameter("masinhvien"));
				forward = INSERT;
				req.setAttribute("update", true);
				SinhVien sinhvien = sinhVienDao.getSinhViennByMaSinhVien(masinhvien);
				req.setAttribute("sinhvien", sinhvien);
				List<LopNienChe> listLopNienChe = lopNienCheDao.getAllLopNienChe();
				req.setAttribute("listLopNienChe", listLopNienChe);
			} else if (action.equalsIgnoreCase("xoasinhvien")) {
				int masinhvien = Integer.parseInt(req.getParameter("masinhvien"));
				boolean result = sinhVienDao.deleteSinhVien(masinhvien);
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
		listSinhVien = sinhVienDao.getListSinhVien(page, search);
		req.setAttribute("listSinhVien", listSinhVien);
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
		List<SinhVien> listSinhVien = null;
		SinhVien sinhvien = null;
		listPage = (int) sinhVienDao.getCountSinhVien(null);
		String masinhvien = req.getParameter("masinhvien");
		String tensinhvien = req.getParameter("hoten");
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
		int malopnienche = Integer.parseInt(req.getParameter("lopnienche"));

		if (masinhvien == null) {
			sinhvien = new SinhVien();
			sinhvien.setHoten(tensinhvien);
			sinhvien.setGioitinh(gioitinh);
			sinhvien.setNgaysinh(sDate);
			sinhvien.setDiachi(diachi);
			sinhvien.setEmail(email);
			sinhvien.setSodienthoai(sodienthoai);
			LopNienChe lopnienche = lopNienCheDao.getLopNienCheByMaLopNienChe(malopnienche);
			sinhvien.setLopnienche(lopnienche);
			boolean result = sinhVienDao.insertSinhVien(sinhvien);
			if (!result) {
				forward = ERROR;
			} else {
				forward = LIST;
			}
		} else {
			int masv = Integer.parseInt(masinhvien);
			sinhvien = sinhVienDao.getSinhViennByMaSinhVien(masv);
			if (sinhvien != null) {
				sinhvien.setHoten(tensinhvien);
				sinhvien.setGioitinh(gioitinh);
				sinhvien.setNgaysinh(sDate);
				sinhvien.setDiachi(diachi);
				sinhvien.setEmail(email);
				sinhvien.setSodienthoai(sodienthoai);
				LopNienChe lopnienche = lopNienCheDao.getLopNienCheByMaLopNienChe(malopnienche);
				sinhvien.setLopnienche(lopnienche);
				boolean result = sinhVienDao.updateSinhVien(sinhvien);
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
		listSinhVien = sinhVienDao.getListSinhVien(page, null);
		req.setAttribute("listSinhVien", listSinhVien);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}
	
}
