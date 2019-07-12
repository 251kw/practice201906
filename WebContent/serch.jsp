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
<title>Shouter - 検索-</title>
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
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報</strong>
		</div>
	</div>
	<%-- セッションスコープにある UserDTO 型のオブジェクトを参照 --%>
<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<c:forEach var="shout" items="${users}">
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
</body>
</html>
