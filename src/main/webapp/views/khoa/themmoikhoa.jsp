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
				<c:choose>
					<c:when test="${update==true}">
						<h6 class="m-0 font-weight-bold text-primary">Cập nhật thông tin khoa: <c:out value='${khoa.tenkhoa }'/></h6>
					</c:when>
					<c:otherwise>
						<h6 class="m-0 font-weight-bold text-primary">Thêm mới khoa</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<div class="col-sm-6">
					<form method="post" action="quan-ly-khoa">
						<table class="table table-borderless">
							<c:if test="${update==true}">
								<tr>
									<th>Mã khoa:</th>
									<td><input type="text" name="makhoa"
										value="<c:out value='${khoa.makhoa }'/>" readonly="readonly"
										class="form-control" /></td>
								</tr>
							</c:if>
							<tr>
								<th>Tên khoa:</th>
								<td><input type="text" name="tenkhoa"
									value="<c:out value='${khoa.tenkhoa }'/>" class="form-control" /></td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td><input type="text" name="sodienthoai"
									value="<c:out value='${khoa.sodienthoai }'/>"
									class="form-control" /></td>
							</tr>

						</table>
						<button type="submit" class="btn btn-success">Lưu</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>