<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>編集完了画面</title>
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
		<div style="width: 60%" class="container padding-y-5 text-center">
			<strong class="color-main">アカウントが以下の内容で編集されました！</strong>
<% 	// 文字化け対策
	request.setCharacterEncoding("UTF-8");

String loginId =  request.getParameter("loginId");
String password =  request.getParameter("password");
String userName =  request.getParameter("userName");
String icon =  request.getParameter("icon");
String profile =  request.getParameter("profile");
%>
<table style="width: 400px" class="table">
<tr><td class="color-main text-center">ログインID : ${param.loginId} </td></tr>
	<tr><td class="color-main text-center">パスワード :
	<%
	//パスワードを文字数の分だけ黒丸で表示
	for(int i=0; i < password.length(); i++){
		out.println("●");
	}
	%> </td>
	<tr><td class="color-main text-center">ユーザー名 : ${param.userName} </td>
	<tr><td class="color-main text-center">アイコン：<span class="${param.icon} pe-2x pe-va"></span></td>
	<tr><td class="color-main text-center">プロフィール: ${param.profile} </td>
</table>
			<div style="text-align:center"><input class="btn" type="button"
			onclick="location.href='top.jsp'"value="TOPページへ"></div></div>
	</div>
</body>
</html>