<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>
<%@ page import="dto.UserDTO" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 確認 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%

	UserDTO user = (UserDTO)session.getAttribute("user");
%>

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter確認&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の情報を確認してください</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./RegServlet" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><%=user.getLoginId() %></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><%=user.getPassword() %></td>

					</tr>
					<tr>
						<%-- ユーザーネーム入力欄の名前は userName --%>
						<td class="color-main text-left">ユーザーネーム</td>
						<td class="text-left"><%=user.getUserName() %></td>

					</tr>
					<tr>
						<%-- アイコン入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left"><%=user.getIcon() %></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><%=user.getProfile() %></td>
					</tr>
					<tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="登録" /></td>
					</tr>
				</table>
			</form>
						<form action="./create.jsp" method="post">
						<input class="btn" type ="button" value="キャンセル" onclick="window.history.back()"/>
				</form>
		</div>
	</div>
</body>
</html>
