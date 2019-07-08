<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録内容確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="newerUser" scope="session" type="dto.UserDTO" />
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>登録するユーザー情報を確認してください。</h1>
			<br>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./SK" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<td class="color-main text-left">ログインID:${newerUser.loginId}</td>
					</tr>
					<tr>
						<td class="color-main text-left">
						パスワード:
						<%
						String nPw = (String)request.getAttribute("nPw");
						for(int i=0 ;i< nPw.length();i++){
							out.println("●");
						}
						%>
						</td>
					</tr>
					<tr>
						<td class="color-main text-left">表示名:${newerUser.userName}</td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン:<span  class="${newerUser.icon} pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール:${newerUser.profile}</td>
					</tr>
					<tr>
					<th><button class="btn" type="button" onclick="history.back()">修正する</button>
					<button class="btn" type="submit" >登録する</button></th>
					<tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>