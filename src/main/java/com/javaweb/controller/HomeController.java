package com.javaweb.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaweb.model.TaiKhoan;

@WebServlet(urlPatterns = { "/trang-chu" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		TaiKhoan admin = (TaiKhoan) session.getAttribute("Admin");
		String action = req.getParameter("action");
		if(action != null) {
			if(action.equalsIgnoreCase(("dangxuat"))) {
				admin = null;
				session.invalidate();
			}
		}
		
		if (admin == null) {
			resp.sendRedirect("dang-nhap");
		} else {
			String user = admin.getTendangnhap();
			session.setAttribute("user", user);
			RequestDispatcher view = req.getRequestDispatcher("/views/home.jsp");
			view.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
