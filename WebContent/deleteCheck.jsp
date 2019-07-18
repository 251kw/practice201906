<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除の確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				削除の確認&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">この書き込みを削除します。</strong>
		</div>
	</div>
	<jsp:useBean id="shouts" scope="session"
		type="java.util.ArrayList<dto.ShoutDTO>" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<form action="DS2" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${dC_icon} pe-3x pe-va"></span></td>
						<td><c:out value="${dC_userName}" /></td>
						<td><input type="submit" value="削除"></td>
					</tr>
					<tr>
						<td><c:out value="${dC_date}" /></td>
					</tr>
					<tr>
						<td colspan="2"class="text-center"><c:out value="${dC_writing}" /></td>
					</tr>
				</table>
				<!-- <input type="submit" value="削除"> -->
			</form>
			<!-- <form action="top.jsp" method="get">
				<input type="submit" value="戻る">
			</form> -->
		</div>
	</div>
	<form action="top.jsp" method="get">
				<input type="submit" value="戻る">
			</form>
</body>
</html>