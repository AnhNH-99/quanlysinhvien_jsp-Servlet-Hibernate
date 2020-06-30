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
						<h6 class="m-0 font-weight-bold text-primary">
							Cập nhật thông tin lớp niên chế:
							<c:out value='${lopnienche.tenlopnienche }' />
						</h6>
					</c:when>
					<c:otherwise>
						<h6 class="m-0 font-weight-bold text-primary">Thêm mới lớp niên chế</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<div class="col-sm-6">
					<form method="post" action="quan-ly-lop-nien-che">
						<table class="table table-borderless">
							<c:if test="${update==true}">
								<tr>
									<th>Mã lớp niên chế:</th>
									<td><input type="text" name="malopnienche"
										value="<c:out value='${lopnienche.malopnienche }'/>" readonly="readonly"
										class="form-control" /></td>
								</tr>
							</c:if>
							<tr>
								<th>Tên lớp niên chế:</th>
								<td><input type="text" name="tenlopnienche"
									value="<c:out value='${lopnienche.tenlopnienche }'/>" class="form-control" /></td>
							</tr>
							<tr>
								<th>Niên khóa:</th>
								<td><input type="text" name="nienkhoa"
									value="<c:out value='${lopnienche.nienkhoa }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Sĩ số:</th>
								<td><input type="text" name="siso"
									value="<c:out value='${lopnienche.siso }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Chuyên ngành:</th>
								<td>
								<select name="chuyennganh">
								<c:forEach items="${listChuyenNganh}" var="listChuyenNganh">
								<option value="<c:out value='${listChuyenNganh.machuyennganh}'/>"><c:out value='${listChuyenNganh.tenchuyennganh }'/></option>
								</c:forEach>
								</select>
								</td>
							</tr>
							<tr>
								<th>Giảng viên:</th>
								<td>
								<select name="giangvien">
								<c:forEach items="${listGiangVien}" var="listGiangVien">
								<option value="<c:out value='${listGiangVien.magiangvien}'/>"><c:out value='${listGiangVien.tengiangvien }'/></option>
								</c:forEach>
								</select>
								</td>
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