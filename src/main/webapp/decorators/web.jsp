
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Dashboard</title>

<!-- Custom fonts for this teamplate-->
<link
	href="<c:url value='teamplate/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css'/>" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this teamplate-->
<link
	href="<c:url value='teamplate/css/sb-admin-2.min.css" rel="stylesheet'/>" />

</head>

<body id="page-top">
	<div id="wrapper">
		<%@include file="/common/web/menu.jsp"%>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- header -->
				<%@include file="/common/web/header.jsp"%>
				<!-- header -->
				<div>
					<dec:body />
				</div>
			</div>
			<!-- footer -->
			<%@include file="/common/web/footer.jsp"%>
			<!-- footer -->
		</div>
	</div>


	</div>
	<a class="scroll-to-top rounded" href="#page-top"
		style="display: inline;"> <i class="fas fa-angle-up"></i>
	</a>

	<div class="modal fade" id="xoaModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thông báo!</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Bạn có muốn xóa bản gi này?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<a class="btn btn-primary" href="#" id="delete">Xóa</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thông báo!</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
				<span>Bạn có muốn đăng xuất không?</span>
				</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="trang-chu?action=dangxuat">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="<c:url value='teamplate/vendor/jquery/jquery.min.js'/>"></script>
	<script
		src="<c:url value='teamplate/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<c:url value='teamplate/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

	<!-- Custom scripts for all pages-->
	<script src="<c:url value='teamplate/js/sb-admin-2.min.js'/>"></script>

	<!-- Page level plugins -->
	<script src="<c:url value='teamplate/vendor/chart.js/Chart.min.js'/>"></script>

	<!-- Page level custom scripts -->
	<script src="<c:url value='teamplate/js/demo/chart-area-demo.js'/>"></script>
	<script src="<c:url value='teamplate/js/demo/chart-pie-demo.js'/>"></script>

</body>

</html>