<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Confirm</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html" %>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">登録情報確認</strong><hr>
			以下の内容で登録します。
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 30%" class="container padding-y-5 text-center">
			<jsp:useBean id="user" class="dto.UserDTO" scope="session" />
			<%-- action 属性にサーブレットを指定 --%>
			<form action="register" method="post">
				<table style="width: 400px" class="table table-bordered table-striped">
					<tr>
						<th class="color-main text-center">ログインID&nbsp;</th>
						<td>${user.getLoginId()}</td>
					</tr>
					<tr>
						<th class="color-main text-center">パスワード&nbsp;</th>
						<td>${user.getPassword()}</td>
					</tr>
					<tr>
						<th class="color-main text-center">名前&nbsp;</th>
						<td>${user.getUserName()}</td>
					</tr>
					<tr>
						<th class="color-main text-center">アイコン&nbsp;</th>
						<td><span class="${user.getIcon()} pe-3x pe-va"></span></td>
					</tr>
					<tr>
						<th class="color-main text-center">プロフィール&nbsp;</th>
						<td>${user.getProfile()}</td>
					</tr>
					<tr></table>
							<button type="submit" class="btn btn-light" name="action" value="fix">修正</button>
							<button type="submit" class="btn" name="action" value="register">確定</button>

			</form>
		</div>
	</div>
</body>
</html>