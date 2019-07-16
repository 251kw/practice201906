<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>変更内容確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>変更するユーザー情報を確認してください。</h1>
			<br>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 35%" class="container padding-y-5 text-center">
			<form action="./UUD" method="post">
				<input type="hidden" name="sltduId" value="${sltduId }" />
				<input type="hidden" name="loginId" value="${uk }" />
				<input type="hidden" name="userName" value="${uk2 }" />
				<input type="hidden" name="profile" value="${uk3 }" />
				<input type="hidden" name="icon" value="${uI }" />
				<input type="hidden" name="icon2" value="${uI2 }" />
				<input type="hidden" name="icon3" value="${uI3 }" />
				<input type="hidden" name="icon4" value="${uI4 }" />
				<input type="hidden" name="icon5" value="${uI5 }" />

					<input type="hidden" name="newerId" value="${newerUser2.loginId}" />
					<input type="hidden" name="newerPw" value="${newerUser2.password}" />
					<input type="hidden" name="newerName" value="${newerUser2.userName}" />
					<input type="hidden" name="gender" value="${newerUser2.icon}" />
					<input type="hidden" name="newerProfile" value="${newerUser2.profile}" />

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
					<th><button class="btn" type="button"  onclick="history.back()" >修正する</button>


					<button class="btn pull-right" type="submit" >登録する</button></th>
					<tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>