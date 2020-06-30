package com.javaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.dao.KhoaDAO;
import com.javaweb.model.Khoa;

@WebServlet(urlPatterns = { "/quan-ly-khoa" })
public class KhoaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String INSERT = "/views/khoa/themmoikhoa.jsp";
	private String LIST = "/views/khoa/quanlykhoa.jsp";
	private String ERROR = "/views/error.jsp";
	private KhoaDAO khoaDao;

	public KhoaController() {
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
		List<Khoa> listKhoa = null;
		listPage = (int) khoaDao.getCountKhoa(search);
		if (action == null) {
			forward = LIST;
			if (search != null) {
				req.setAttribute("search", search);
			}
		} else {
			if (action.equalsIgnoreCase("themmoikhoa")) {
				forward = INSERT;
			} else if (action.equalsIgnoreCase("suakhoa")) {
				int makhoa = Integer.parseInt(req.getParameter("makhoa"));
				forward = INSERT;
				req.setAttribute("update", true);
				Khoa khoa = khoaDao.getKhoaByMaKhoa(makhoa);
				req.setAttribute("khoa", khoa);
			} else if (action.equalsIgnoreCase("xoakhoa")) {
				int makhoa = Integer.parseInt(req.getParameter("makhoa"));
				boolean result = khoaDao.deleteKhoa(makhoa);
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
		listKhoa = khoaDao.getListKhoa(page, search);
		req.setAttribute("listKhoa", listKhoa);
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
		List<Khoa> listKhoa = null;
		Khoa khoa = null;
		listPage = (int) khoaDao.getCountKhoa(null);
		String makhoa = req.getParameter("makhoa");
		String tenkhoa = req.getParameter("tenkhoa");
		String sodienthoai = req.getParameter("sodienthoai");

		if (makhoa == null) {
			khoa = new Khoa();
			khoa.setTenkhoa(tenkhoa);
			khoa.setSodienthoai(sodienthoai);
			boolean result = khoaDao.insertKhoa(khoa);
			if (!result) {
				forward = ERROR;
			} else {
				forward = LIST;
			}
		} else {
			int makh = Integer.parseInt(makhoa);
			khoa = khoaDao.getKhoaByMaKhoa(makh);
			if (khoa != null) {
				khoa.setTenkhoa(tenkhoa);
				khoa.setSodienthoai(sodienthoai);
				boolean result = khoaDao.updateKhoa(khoa);
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
		listKhoa = khoaDao.getListKhoa(page, null);
		req.setAttribute("listKhoa", listKhoa);
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

}
