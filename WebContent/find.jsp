<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page import="dto.ShoutDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - みんなの心の叫び -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter &nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報</strong>
			<div align="right"></div>
		</div>
	</div>
	<%-- セッションスコープにある UserDTO 型のオブジェクトを参照 --%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー一覧</strong>
		</div>
	</div>
	<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
	<jsp:useBean id="searchuser" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="user" items="${searchuser}">
				<input type="checkbox" name="serch" value="">
				<c:if test="checked"><input type="hidden" name="userName" value="${user.userName}"></c:if>
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${user.icon} pe-3x pe-va"></span></td>
						<td>${user.userName}</td>
					</tr>
					<tr>
						<td>${user.profile}</td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</div>
	<div style="text-align: center">
		<form action="./.jsp" method="post">
			<span style="margin-left: 100px">
			<input class="btn" type="submit" value="更新" /></span>
		</form>
		<form action="./DeleteuserServlet" method="post">
			<span style="margin-left: 100px"><input class="btn"
				type="submit" value="削除" /></span>
		</form>
	</div>
</body>
</body>
</html>
