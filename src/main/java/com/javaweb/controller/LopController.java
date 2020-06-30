package com.javaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.dao.ChuyenNganhDAO;
import com.javaweb.dao.GiangVienDAO;
import com.javaweb.dao.LopNienCheDAO;
import com.javaweb.model.ChuyenNganh;
import com.javaweb.model.GiangVien;
import com.javaweb.model.Khoa;
import com.javaweb.model.LopNienChe;

@WebServlet(urlPatterns = {"/quan-ly-lop-nien-che"})
public class LopController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INSERT = "/views/lopnienche/themmoilopnienche.jsp";
	private String LIST = "/views/lopnienche/quanlylopnienche.jsp";
	private String ERROR = "/views/error.jsp";
	private GiangVienDAO giangVienDao;
	private ChuyenNganhDAO chuyenNganhDao;
	private LopNienCheDAO lopniencheDao;
	
	public LopController() {
		giangVienDao = new GiangVienDAO();
		chuyenNganhDao = new ChuyenNganhDAO();
		lopniencheDao = new LopNienCheDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String forward = "";
		String action = req.getParameter("action");
		String search = req.getParameter("search");
		String pagestr = req.getParameter("page");
		int page = 0;
		int listPage = 0;
		List<LopNienChe> listLopNienChe = null;
		listPage = (int) lopniencheDao.getCountLopNienChe(search);
		if (action == null) {
			forward = LIST;
			if (search != null) {
				req.setAttribute("search", search);
			}
		} else {
			if (action.equalsIgnoreCase("themmoilopnienche")) {
				forward = INSERT;
				List<ChuyenNganh> listChuyenNganh = chuyenNganhDao.getAllChuyenNganh();
				req.setAttribute("listChuyenNganh", listChuyenNganh);
				List<GiangVien> listGiangVien = giangVienDao.getAllGiangVien();
				req.setAttribute("listGiangVien", listGiangVien);
			} else if (action.equalsIgnoreCase("sualopnienche")) {
				int malopnienche = Integer.parseInt(req.getParameter("malopnienche"));
				forward = INSERT;
				req.setAttribute("update", true);
				LopNienChe lopnienche = lopniencheDao.getLopNienCheByMaLopNienChe(malopnienche);
				req.setAttribute("lopnienche", lopnienche);
				List<ChuyenNganh> listChuyenNganh = chuyenNganhDao.getAllChuyenNganh();
				req.setAttribute("listChuyenNganh", listChuyenNganh);
				List<GiangVien> listGiangVien = giangVienDao.getAllGiangVien();
				req.setAttribute("listGiangVien", listGiangVien);
			} else if (action.equalsIgnoreCase("xoalopnienche")) {
				int malopnienche = Integer.parseInt(req.getParameter("malopnienche"));
				boolean result = lopniencheDao.deleteLopNienChe(malopnienche);
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
		listLopNienChe =lopniencheDao.getListLopNienChe(page, search);
		req.setAttribute("listLopNienChe", listLopNienChe);
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
		List<LopNienChe> listLopNienChe = null;
		LopNienChe lopnienche = null;
		listPage = (int) lopniencheDao.getCountLopNienChe(null);
		String malopnienche = req.getParameter("malopnienche");
		String tenlopnienche = req.getParameter("tenlopnienche");
		String nienkhoa = req.getParameter("nienkhoa");
		int siso = Integer.parseInt(req.getParameter("siso"));
		int machuyennganh = Integer.parseInt(req.getParameter("chuyennganh"));
		int magiangvien = Integer.parseInt(req.getParameter("giangvien"));

		if (malopnienche == null) {
			lopnienche = new LopNienChe();
			lopnienche.setTenlopnienche(tenlopnienche);
			lopnienche.setNienkhoa(nienkhoa);
			lopnienche.setSiso(siso);
			ChuyenNganh chuyennganh = chuyenNganhDao.getChuyenNganhByMaChuyenNganh(machuyennganh);
			lopnienche.setChuyennganh(chuyennganh);
			GiangVien giangvien = giangVienDao.getGiangVienByMaGiangVien(magiangvien);
			lopnienche.setGiangvien(giangvien);
			boolean result = lopniencheDao.insertLopNienChe(lopnienche);
			if (!result) {
				forward = ERROR;
			} else {
				forward = LIST;
			}
		} else {
			int malnc = Integer.parseInt(malopnienche);
			lopnienche = lopniencheDao.getLopNienCheByMaLopNienChe(malnc);
			if (lopnienche != null) {
				lopnienche.setTenlopnienche(tenlopnienche);
				lopnienche.setNienkhoa(nienkhoa);
				lopnienche.setSiso(siso);
				ChuyenNganh chuyennganh = chuyenNganhDao.getChuyenNganhByMaChuyenNganh(machuyennganh);
				lopnienche.setChuyennganh(chuyennganh);
				GiangVien giangvien = giangVienDao.getGiangVienByMaGiangVien(magiangvien);
				lopnienche.setGiangvien(giangvien);
				boolean result = lopniencheDao.updateLopNienChe(lopnienche);
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
		listLopNienChe = lopniencheDao.getListLopNienChe(page, null);
		req.setAttribute("listLopNienChe", listLopNienChe);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}
}
