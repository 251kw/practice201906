<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String loginId = (String)request.getAttribute("ID");
	//String outloginId = loginId;
	String password = (String)request.getAttribute("PASS");
	String username = (String)request.getAttribute("NAME");
	String icon = (String)request.getAttribute("ICON");
	String profile = (String)request.getAttribute("PRO");
	String hiddenId = (String)request.getAttribute("hiddenId");
	request.setAttribute("ID", loginId);
	request.setAttribute("PASS", password);
	request.setAttribute("NAME", username);
	request.setAttribute("ICON", icon);
	request.setAttribute("PRO", profile);

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
			<strong class="color-main">確認画面</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./NLS" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><div class="form-control"><%= loginId %></div></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><div class="form-control"><% for(int i = 0; i < password.length();i++){
							out.println("●");
						} %></div></td>
					</tr>
					<tr>
						<%-- 名前入力欄の名前は username --%>
						<td class="color-main text-left">名前</td>
						<td class="text-left"><div class="form-control"><%= username %></div></td>
					</tr>
					<tr>
						<%-- アイコン入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left"><div class="form-control"><span class="<%= icon %> pe-2x pe-va"></span></div></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><div class="form-control"><%= profile %></div></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="登録する" /></td>
					</tr>
				</table>
				<input type="hidden" name="loginId" value="<%= loginId %>">
				<input type="hidden" name="password" value="<%= password %>">
				<input type="hidden" name="username" value="<%= username %>">
				<input type="hidden" name="icon" value="<%= icon %>">
				<input type="hidden" name="profile" value="<%= profile %>">
				<input type="hidden" name="hiddenId" value="<%= hiddenId %>">
			</form>
			<%-- <form action="./NAS" method="get">
			<input class="btn" type="submit" value="訂正する"/>
			</form> --%>
		</div>
		<div style="width: 30%" class="container padding-y-5">
			<a href="./NAS?link=1&loginId=<%= loginId %>&username=<%= username %>&icon=<%= icon %>&profile=<%= profile %>&hiddenId=<%= hiddenId %>">訂正する</a>
		</div>
	</div>

</body>
</html>