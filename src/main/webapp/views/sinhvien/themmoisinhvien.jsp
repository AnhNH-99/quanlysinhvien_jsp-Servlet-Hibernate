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
							Cập nhật thông tin sinh viên:
							<c:out value='${sinhvien.hoten }' />
						</h6>
					</c:when>
					<c:otherwise>
						<h6 class="m-0 font-weight-bold text-primary">Thêm mới sinh
							viên</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<div class="col-sm-6">
					<form method="post" action="quan-ly-sinh-vien">
						<table class="table table-borderless">
							<c:if test="${update==true}">
								<tr>
									<th>Mã sinh viên:</th>
									<td><input type="text" name="masinhvien"
										value="<c:out value='${sinhvien.masinhvien }'/>"
										readonly="readonly" class="form-control" /></td>
								</tr>
							</c:if>
							<tr>
								<th>Tên sinh viên:</th>
								<td><input type="text" name="hoten"
									value="<c:out value='${sinhvien.hoten }'/>"
									class="form-control" /></td>
							</tr>

							<tr>
								<th>Giới tính:</th>
								<td><c:choose>
										<c:when test="${sinhvien.gioitinh==1}">
											<input type="radio" name="gioitinh" value="1"
												checked="checked" />Nam
										</c:when>
										<c:otherwise>
											<input type="radio" name="gioitinh" value="1" />Nam
										</c:otherwise>
									</c:choose> <c:choose>
										<c:when test="${sinhvien.gioitinh==0}">
											<input type="radio" name="gioitinh" value="0"
												checked="checked" />Nữ
										</c:when>
										<c:otherwise>
											<input type="radio" name="gioitinh" value="0" />Nữ
										</c:otherwise>
									</c:choose></td>
							</tr>
							<tr>
								<th>Ngày sinh:</th>
								<td><input type="date" name="ngaysinh"
									value="<c:out value='${sinhvien.ngaysinh }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Địa chỉ:</th>
								<td><input type="text" name="diachi"
									value="<c:out value='${sinhvien.diachi }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Email:</th>
								<td><input type="text" name="email"
									value="<c:out value='${sinhvien.email }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td><input type="text" name="sodienthoai"
									value="<c:out value='${sinhvien.sodienthoai }'/>"
									class="form-control" /></td>
							</tr>
							<tr>
								<th>Lớp niên chế:</th>
								<td><select name="lopnienche">
										<c:forEach items="${listLopNienChe}" var="listLopNienChe">
											<option value="<c:out value='${listLopNienChe.malopnienche}'/>"><c:out
													value='${listLopNienChe.tenlopnienche }' /></option>
										</c:forEach>
								</select></td>
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