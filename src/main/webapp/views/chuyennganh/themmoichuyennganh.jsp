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
							Cập nhật thông tin chuyên ngành:
							<c:out value='${chuyennganh.tenchuyennganh }' />
						</h6>
					</c:when>
					<c:otherwise>
						<h6 class="m-0 font-weight-bold text-primary">Thêm mới chuyên ngành</h6>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="card-body">
				<div class="col-sm-6">
					<form method="post" action="quan-ly-chuyen-nganh">
						<table class="table table-borderless">
							<c:if test="${update==true}">
								<tr>
									<th>Mã chuyên ngành:</th>
									<td><input type="text" name="machuyennganh"
										value="<c:out value='${chuyennganh.machuyennganh }'/>" readonly="readonly"
										class="form-control" /></td>
								</tr>
							</c:if>
							<tr>
								<th>Tên chuyên ngành:</th>
								<td><input type="text" name="tenchuyennganh"
									value="<c:out value='${chuyennganh.tenchuyennganh }'/>" class="form-control" /></td>
							</tr>
							<tr>
								<th>Khoa:</th>
								<td>
								<select name="khoa">
								<c:forEach items="${listKhoa}" var="listKhoa">
								<option value="<c:out value='${listKhoa.makhoa}'/>"><c:out value='${listKhoa.tenkhoa }'/></option>
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