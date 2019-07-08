<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.DBManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shouts confirmation</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
		<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
		<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">

		<strong class="color-main">この書き込みを削除してよろしいですか？</strong>
		</div>
		</div>
<% 	// 文字化け対策
	request.setCharacterEncoding("UTF-8");
%>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
		<form action="./ds" method="post">
						<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${param.icon} pe-3x pe-va"></span></td>
						<td>${param.userName}</td>
					</tr>
					<tr>
						<td>${param.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control">${param.writing}</textarea>
						</td>
					</tr>
				</table>
		<br><input type="hidden" name="date" value="${param.date}">
		<input type="submit" value="削除" class="btn">
		</form>
		</div>
		</div>
</body>
</html>