<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Quản lý lớp niên
					chế</h6>
			</div>
			<div class="card-body">
				<div class="table">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row">
							<div class="col-md-3">
								<form method="get" action="quan-ly-lop-nien-che"
									class="d-none d-sm-inline-block form-inline mr-auto  my-2 my-md-0 mw-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											value="${search}" aria-label="search"
											aria-describedby="basic-addon2" name="search">
										<div class="input-group-append">
											<button class="btn btn-primary" type="submit">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div>
							<div class="col-md-9">
								<a
									href="/quanlysinhvien_hibernate/quan-ly-lop-nien-che?action=themmoilopnienche"
									class="btn btn-success">Thêm mới</a>
							</div>

						</div>
						<div class="row">
							<div class="col-sm-12">
								<hr />
								<table class="table table-bordered dataTable" id="dataTable"
									width="100%" cellspacing="0" role="grid"
									aria-describedby="dataTable_info" style="width: 100%;">
									<thead>
										<tr role="row">
											<th class="sorting_asc" tabindex="0"
												aria-controls="dataTable" rowspan="1" colspan="1"
												aria-sort="ascending"
												aria-label="Name: activate to sort column descending"
												style="width: 161px;">Mã lớp niên chế</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Position: activate to sort column ascending"
												style="width: 246px;">Tên lớp niên chế</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 116px;">Niên khóa</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 116px;">Sĩ số</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 116px;">Chuyên ngành</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 116px;">Giảng viên</th>
											<th class="sorting" tabindex="0" aria-controls="dataTable"
												rowspan="1" colspan="1"
												aria-label="Office: activate to sort column ascending"
												style="width: 116px;">Thao tác</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="listLopNienChe" items="${listLopNienChe}">
											<tr role="row" class="odd">
												<td>${listLopNienChe.malopnienche }</td>
												<td>${listLopNienChe.tenlopnienche}</td>
												<td>${listLopNienChe.nienkhoa }</td>
												<td>${listLopNienChe.siso }</td>
												<td>${listLopNienChe.chuyennganh.tenchuyennganh }</td>
												<td>${listLopNienChe.giangvien.tengiangvien }</td>
												<td><a
													href="/quanlysinhvien_hibernate/quan-ly-lop-nien-che?action=sualopnienche&malopnienche=${listLopNienChe.malopnienche }">Sửa</a>|<a
													href="#" data-id="${listLopNienChe.malopnienche }"
													class="xoa">Xóa</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-7">
								<div class="dataTables_paginate paging_simple_numbers"
									id="dataTable_paginate">
									<ul class="pagination">
										<c:forEach var="i" begin="1" end="${listPage}">
											<c:choose>
												<c:when test="${search!=null}">
													<li class="paginate_button page-item " id="${i}"><a
														href="/quanlysinhvien_hibernate/quan-ly-lop-nien-che?search=<c:out value="${search}" />&page=<c:out value="${i}" />"
														aria-controls="dataTable" data-dt-idx="${i }" tabindex="0"
														class="page-link"><c:out value="${i}" /></a></li>
												</c:when>
												<c:otherwise>
													<li class="paginate_button page-item " id="${i}"><a
														href="/quanlysinhvien_hibernate/quan-ly-lop-nien-che?page=<c:out value="${i}" />"
														aria-controls="dataTable" data-dt-idx="${i }" tabindex="0"
														class="page-link"><c:out value="${i}" /></a></li>
												</c:otherwise>
											</c:choose>

										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='teamplate/vendor/jquery/jquery.min.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var x = ${page};
			$("#" + x).attr("class", "paginate_button page-item active");
		});
		$('.xoa')
				.off('click')
				.on(
						'click',
						function(e) {
							$('#xoaModal').modal('show');
							var y = $(this).data('id');
							$('#delete')
									.attr(
											'href',
											"/quanlysinhvien_hibernate/quan-ly-lop-nien-che?action=xoalopnienche&malopnienche="
													+ y);
						})
	</script>
</body>
</html>