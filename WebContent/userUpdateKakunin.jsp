<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>変更内容確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="newerUser2" scope="session" type="dto.UserDTO" />
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>変更するユーザー情報を確認してください。</h1>
			<br>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./UUD" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<td class="color-main text-left">ログインID:${newerUser2.loginId}</td>
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
						<td class="color-main text-left">表示名:${newerUser2.userName}</td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン:<span  class="${newerUser2.icon} pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール:${newerUser2.profile}</td>
					</tr>
					<tr>
					<th><button class="btn" type="submit" onclick="history.back()">修正する</button>
					<input type="hidden" name="sltduId" value="${sltduId }" />
					<input type="hidden" name="newerId" value="${newerUser2.loginId}" />
					<input type="hidden" name="newerPw" value="${newerUser2.password}" />
					<input type="hidden" name="newerName" value="${newerUser2.userName}" />
					<input type="hidden" name="gender" value="${newerUser2.icon}" />
					<input type="hidden" name="newerProfile" value="${newerUser2.profile}" />
					<button class="btn" type="submit" >登録する</button></th>
					<tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>