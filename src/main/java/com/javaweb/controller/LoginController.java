package com.javaweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaweb.dao.TaiKhoanDAO;
import com.javaweb.model.TaiKhoan;
@WebServlet(urlPatterns = {"/dang-nhap"})
public class LoginController extends HttpServlet {
	private TaiKhoanDAO taiKhoanDao;
	public LoginController() {
		taiKhoanDao = new TaiKhoanDAO();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("/views/login.jsp");
		view.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tendangnhap = req.getParameter("tendangnhap");
		String matkhau = req.getParameter("matkhau");
		TaiKhoan tk = taiKhoanDao.dangNhap(tendangnhap, matkhau);
		if(tk == null) {
			req.setAttribute("ThongBao", "Tài khoản hoặc mật khẩu không chính xác!");
			RequestDispatcher view = req.getRequestDispatcher("/views/login.jsp");
			view.forward(req, resp);
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("Admin", tk);
			resp.sendRedirect("trang-chu");
		}
	}
	

}
