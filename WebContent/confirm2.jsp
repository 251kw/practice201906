<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page import="dto.ShoutDTO" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter delete - 確認 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%

	String writing = (String)session.getAttribute("writing");
%>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter 削除確認&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の削除情報を確認してください</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./DeleteRegServlet" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<tr>
						<td colspan="2"><textarea rows="5" class="form-control"><%=writing %></textarea>
						</td>
					<tr>
					</tr>
					<tr>
				</table>
						<input class="btn" type="submit" value="削除">
				</form>
						<form action="./create.jsp" method="post">
						<input class="btn" type ="button" value="キャンセル" onclick="window.history.back()"/>
				</form>
		</div>
	</div>
</body>
</html>
