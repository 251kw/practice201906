<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 叫び削除 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String username = request.getParameter("username");
	String icon = request.getParameter("icon");
	String date = request.getParameter("date");
	String writing = request.getParameter("writing");
	String key = request.getParameter("key");
%>
<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の叫びを削除します。よろしいですか？</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./DSS" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="<%= icon %> pe-3x pe-va"></span></td>
						<td><%= username %></td>
					</tr>
					<tr>
						<td><%= date %></td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control"><%= writing %></textarea>
						</td>
					</tr>
				</table>
				<%-- <input type="hidden" name="username" value="<%= username %>">
				<input type="hidden" name="icon" value="<%= icon %>">
				<input type="hidden" name="date" value="<%= date %>">
				<input type="hidden" name="writing" value="<%= writing %>"> --%>
				<input type="hidden" name="key" value="<%= key %>">
				<div  align="right"><input class="btn" type="submit" value="削除" /></div>
			</form>
			<a href="top.jsp">戻る</a>
			<%-- <form action="top.jsp" method="post">
			<input class="btn" type="submit" value="戻る"/>
			</form> --%>
		</div>
	</div>


</body>
</html>