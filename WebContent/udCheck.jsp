<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<table border="1">
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
		<input type="submit"value="削除">
	</form>
	<form action="./UDS" method="get">
	<input type="submit" value="戻る">
	</form>
</body>
</html>