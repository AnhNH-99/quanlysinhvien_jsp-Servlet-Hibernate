<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<ul
		class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
		id="accordionSidebar">

		<!-- Sidebar - Brand -->
		<a
			class="sidebar-brand d-flex align-items-center justify-content-center"
			href="#">
			<div class="sidebar-brand-icon rotate-n-15">
				<i class="fas fa-laugh-wink"></i>
			</div>
			<div class="sidebar-brand-text mx-3">
				SB Admin <sup>2</sup>
			</div>
		</a>

		<!-- Divider -->
		<hr class="sidebar-divider my-0">

		<!-- Nav Item - Dashboard -->
		<li class="nav-item active"><a class="nav-link"
			href="/quanlysinhvien_hibernate/trang-chu"> <i
				class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span>
		</a></li>

		<!-- Divider -->
		<hr class="sidebar-divider">

		<!-- Heading -->

		<!-- Nav Item - Pages Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="/quanlysinhvien_hibernate/quan-ly-khoa"> <i
				class="fas fa-fw fa-table"></i> <span>Khoa</span>
		</a></li>

		<!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="/quanlysinhvien_hibernate/quan-ly-chuyen-nganh"> <i
				class="fas fa-fw fa-table"></i> <span>Chuyên Ngành</span>
		</a></li>
		
		<!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="/quanlysinhvien_hibernate/quan-ly-giang-vien"> <i
				class="fas fa-fw fa-table"></i> <span>Giảng viên</span>
		</a></li>
		
		<!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="/quanlysinhvien_hibernate/quan-ly-lop-nien-che"> <i
				class="fas fa-fw fa-table"></i> <span>Lớp niên chế</span>
		</a></li>
		
		<!-- Nav Item - Utilities Collapse Menu -->
		<li class="nav-item"><a class="nav-link collapsed"
			href="/quanlysinhvien_hibernate/quan-ly-sinh-vien"> <i
				class="fas fa-fw fa-table"></i> <span>Sinh viên</span>
		</a></li>

		
		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		<!-- Sidebar Toggler (Sidebar) -->
		<div class="text-center d-none d-md-inline">
			<button class="rounded-circle border-0" id="sidebarToggle"></button>
		</div>

	</ul>
</body>
</html>