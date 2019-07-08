<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 新規登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<style>
.small {font-size: 80%;}
</style>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
String loginId = (String)request.getAttribute("ID");
if(loginId == null){
	loginId = "";
}
String password = (String)request.getAttribute("PASS");
if(password == null){
	password = "";
}
String username = (String)request.getAttribute("NAME");
if(username == null){
	username = "";
}
String icon = (String)request.getAttribute("ICON");
if(icon == null){
	icon = "";
}
String profile = (String)request.getAttribute("PRO");
if(profile == null){
	profile = "";
}

String i1 = "";
String i2 = "";
String i3 = "";
String i4 = "";
String i5 = "";
String i6 = "";
String i7 = "";
String i8 = "";
String i9 = "";
if(icon.equals("icon-user-female")){
	i2 = "checked";
} else if(icon.equals("icon-car")){
	i3 = "checked";
} else if(icon.equals("icon-study")){
	i4 = "checked";
} else if(icon.equals("icon-star")){
	i5 = "checked";
} else if(icon.equals("icon-plane")){
	i6 = "checked";
} else if(icon.equals("icon-camera")){
	i7 = "checked";
} else if(icon.equals("icon-bicycle")){
	i8 = "checked";
} else if(icon.equals("icon-ball")){
	i9 = "checked";
} else {
	i1 = "checked";
}
String hiddenId = (String)request.getAttribute("hiddenId");
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
			<strong class="color-main">会員情報を入力してください</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./NAS" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID<br><span class="color-error">※必須</span></td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="<%= loginId %>" size="20" maxlength="32" placeholder="32文字以内"/>
							<c:if
						test="${requestScope.idErr1 != null && requestScope.idErr1 != ''}">
							<div class="color-error text-right small">${idErr1}</div>
							</c:if>
							<c:if
						test="${requestScope.idErr2 != null && requestScope.idErr2 != ''}">
							<div class="color-error text-right small">${idErr2}</div>
							</c:if>
							<c:if
						test="${requestScope.idErr3 != null && requestScope.idErr3 != ''}">
							<div class="color-error text-right small">${idErr3}</div>
							</c:if></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード<br><span class="color-error">※必須</span></td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="<%= password %>" size="20" maxlength="32" placeholder="32文字以内"/>
							<c:if
						test="${requestScope.passErr1 != null && requestScope.passErr1 != ''}">
							<div class="color-error text-right small">${passErr1}</div>
							</c:if>
							<c:if
						test="${requestScope.passErr2 != null && requestScope.passErr2 != ''}">
							<div class="color-error text-right small">${passErr2}</div>
							</c:if></td>
					</tr>
					<tr>
						<%-- 名前入力欄の名前は username --%>
						<td class="color-main text-left">名前<br><span class="color-error">※必須</span></td>
						<td class="text-left"><input class="form-control"
							type="text" name="username" value="<%= username %>" size="20" maxlength="64" placeholder="64文字以内"/>
							<c:if
						test="${requestScope.nameErr != null && requestScope.nameErr != ''}">
							<div class="color-error text-right small">${nameErr}</div>
							</c:if></td>
					</tr>
					<tr>
						<%-- アイコン入力欄の名前はicon --%>
						<td class="color-main text-left">アイコン</td>
						<%-- <td class="text-left"><input class="form-control"
							type="text" name="icon" value="" size="20" /></td> --%>
						<td>
							<span class="icon-user pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-user" <%= i1 %>>
							<span class="icon-user-female pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-user-female" <%= i2 %>>
							<span class="icon-car pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-car" <%= i3 %>><br>
							<span class="icon-study pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-study" <%= i4 %>>
							<span class="icon-star pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-star" <%= i5 %>>
							<span class="icon-plane pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-plane" <%= i6 %>><br>
							<span class="icon-camera pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-camera" <%= i7 %>>
							<span class="icon-bicycle pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-bicycle" <%= i8 %>>
							<span class="icon-ball pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-ball" <%= i9 %>>
						</td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前はprofile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left">
							<textarea class="form-control" rows="4" cols="20" name="profile" maxlength="128" placeholder="128文字以内"><%= profile %></textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="確認画面へ" /></td>
					</tr>
					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert1}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<tr><td>
					<span class="color-error text-left">${alert2}</span></td></tr>
					</c:if>
				</table>
				<input type="hidden" name="hiddenId" value="<%= hiddenId %>"/>
			</form>
		</div>
		<form action="./SLI2" method="post">
				<input class="btn" type="submit" value="ログイン画面へ戻る" />
				<input type="hidden" name="hiddenId" value="<%= hiddenId %>"/>
			</form>
	</div>
</body>
</html>