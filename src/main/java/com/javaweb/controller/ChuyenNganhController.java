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
import com.javaweb.dao.KhoaDAO;
import com.javaweb.model.ChuyenNganh;
import com.javaweb.model.Khoa;

@WebServlet(urlPatterns = { "/quan-ly-chuyen-nganh" })
public class ChuyenNganhController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INSERT = "/views/chuyennganh/themmoichuyennganh.jsp";
	private String LIST = "/views/chuyennganh/quanlychuyennganh.jsp";
	private String ERROR = "/views/error.jsp";
	private KhoaDAO khoaDao;
	private ChuyenNganhDAO chuyenNganhDao;

	public ChuyenNganhController() {
		chuyenNganhDao = new ChuyenNganhDAO();
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
		List<ChuyenNganh> listChuyenNganh = null;
		listPage = (int) chuyenNganhDao.getCountChuyenNganh(search);
		if (action == null) {
			forward = LIST;
			if (search != null) {
				req.setAttribute("search", search);
			}
		} else {
			if (action.equalsIgnoreCase("themmoichuyennganh")) {
				forward = INSERT;
				List<Khoa> listKhoa = khoaDao.getAllKhoa();
				req.setAttribute("listKhoa", listKhoa);
			} else if (action.equalsIgnoreCase("suachuyennganh")) {
				int machuyennganh = Integer.parseInt(req.getParameter("machuyennganh"));
				forward = INSERT;
				req.setAttribute("update", true);
				ChuyenNganh chuyennganh = chuyenNganhDao.getChuyenNganhByMaChuyenNganh(machuyennganh);
				req.setAttribute("chuyennganh", chuyennganh);
				List<Khoa> listKhoa = khoaDao.getAllKhoa();
				req.setAttribute("listKhoa", listKhoa);
			} else if (action.equalsIgnoreCase("xoachuyennganh")) {
				int machuyennganh = Integer.parseInt(req.getParameter("machuyennganh"));
				boolean result = chuyenNganhDao.deleteChuyenNganh(machuyennganh);
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
		listChuyenNganh = chuyenNganhDao.getListChuyenNganh(page, search);
		req.setAttribute("listChuyenNganh", listChuyenNganh);
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
		List<ChuyenNganh> listChuyenNganh = null;
		ChuyenNganh chuyennganh = null;
		listPage = (int) chuyenNganhDao.getCountChuyenNganh(null);
		String machuyennganh = req.getParameter("machuyennganh");
		String tenchuyennganh = req.getParameter("tenchuyennganh");
		int makhoa = Integer.parseInt(req.getParameter("khoa"));

		if (machuyennganh == null) {
			chuyennganh = new ChuyenNganh();
			chuyennganh.setTenchuyennganh(tenchuyennganh);
			Khoa khoa = khoaDao.getKhoaByMaKhoa(makhoa);
			chuyennganh.setKhoa(khoa);
			boolean result = chuyenNganhDao.insertChuyenNganh(chuyennganh);
			if (!result) {
				forward = ERROR;
			} else {
				forward = LIST;
			}
		} else {
			int macn = Integer.parseInt(machuyennganh);
			chuyennganh = chuyenNganhDao.getChuyenNganhByMaChuyenNganh(macn);
			if (chuyennganh != null) {
				chuyennganh.setTenchuyennganh(tenchuyennganh);
				Khoa khoa = khoaDao.getKhoaByMaKhoa(makhoa);
				chuyennganh.setKhoa(khoa);
				boolean result = chuyenNganhDao.updateChuyenNganh(chuyennganh);
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
		listChuyenNganh = chuyenNganhDao.getListChuyenNganh(page, null);
		req.setAttribute("listChuyenNganh", listChuyenNganh);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

}
