<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.DBManager" %>
<%@ page import="dto.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>アカウント削除画面</title>
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
		<div style="width: 40%" class="container padding-y-5 text-center">
	<form action="./ads" method="post">
<%
//文字化け対策
request.setCharacterEncoding("UTF-8");
%>
<table class="table table-striped table-bordered">
<tr bgcolor="#7fffd4">
<th>ログインID</th><th>ユーザー名</th><th>アイコン</th><th>プロフィール</th>
</tr>
<c:forEach var="a" items="${accounts}">
  <tr>
    <td>${a.loginId}</td>
    <td>${a.userName}</td>
    <td><span class="${a.icon} pe-2x pe-va"></span></td>
	<td>${a.profile}</td>
  </tr>
</c:forEach>
</table>
<strong>これらのアカウントを削除してよろしいですか？</strong><br>
<input type="submit" value="削除" class="btn">
</form>
<button onclick="history.back()">戻る</button>
		</div>
	</div>
</body>
</html>