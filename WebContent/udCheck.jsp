<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="D_users" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<form action="./US2" method="post">
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-center">
				<strong class="color-main">削除するユーザー情報</strong>
			</div>
		</div>
		<div class="padding-y-6 text-center">
			<div style="width: 60%" class="container padding-y-5 text-center">
				<table class="table table-bordered">
					<tr>
						<th>ログインId</th>
						<th>パスワード</th>
						<th>ユーザーネーム</th>
						<th>アイコン</th>
						<th>プロフィール</th>
					</tr>
					<c:forEach var="dusers" items="${D_users}">
						<tr>
							<td><c:out value="${dusers.loginId}" /></td>
							<td><c:out value="${dusers.password}" /></td>
							<td><c:out value="${dusers.userName}" /></td>
							<td><span class="${dusers.icon} pe-3x pe-va"></span></td>
							<td><c:out value="${dusers.profile}" /></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" class="btn" value="削除">
			</div>
		</div>
	</form>
		<form action="./UDS" method="get">
	<input type="submit" value="戻る">
	</form>
</body>
</html>