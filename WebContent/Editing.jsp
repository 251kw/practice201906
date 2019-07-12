<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー情報編集</title>
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
<%
String loginId = (String)session.getAttribute("loginId");
String password = (String)session.getAttribute("password");
String userName = (String)session.getAttribute("userName");
String i = request.getParameter("icon");
String profile = (String)session.getAttribute("profile");

if(loginId == null){
	loginId = "";
}
if(password == null){
	password = "";
}
if(userName == null){
	userName = "";
}
if(profile == null){
	profile = "";
}
String i1 = "";
if(i == null){
	i1 = "checked";
}
String i2 = "";
String i3 = "";
String i4 = "";
String i5 = "";
String i6 = "";
String i7 = "";
String i8 = "";
String i9 = "";
String i10 = "";

if("icon-user".equals(i)){
	i1 = "checked";
}
if("icon-user-female".equals(i)){
	i2 = "checked";
}
if("icon-smile".equals(i)){
	i3 = "checked";
}
if("icon-star".equals(i)){
	i4 = "checked";
}
if("icon-piggy".equals(i)){
	i5 = "checked";
}
if("icon-joy".equals(i)){
	i6 = "checked";
}
if("icon-car".equals(i)){
	i7 = "checked";
}
if("icon-rocket".equals(i)){
	i8 = "checked";
}
if("icon-music".equals(i)){
	i9 = "checked";
}
if("icon-ball".equals(i)){
	i10 = "checked";
}
%>
<form action="./es" method="post">
		<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
				<table style="width: 390px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ID</td>
						<td class="text-left"><input class="form-control" type="text"
							readonly  pattern="^[0-9A-Za-z]+$" title="半角英数字で入力して下さい。" name="loginId" value="${param.loginId}" size="20" /></td>

					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control" pattern="^[0-9A-Za-z]+$"
							type="password" name="password" value="${param.password}" size="20" /></td>
					</tr>
					<tr>
						<%-- ユーザー名入力欄の名前はuserName --%>
						<td class="color-main text-left">ユーザー名</td>
						<td class="text-left"><input class="form-control"
							type="text" name="userName" value="${param.userName}" size="20" /></td>
					</tr>
					<tr>
						<%-- アイコン入力欄の名前はicon --%>
						<td class="color-main text-left">アイコン</td>
						<td>
						<div class="selection-group">
						  <input type="radio" name="icon" value="icon-user" <%=i1%>>
						    <span class="icon-user pe-2x pe-va"></span>
						  <input type="radio" name="icon" value="icon-user-female" <%=i2%>>
						    <span class="icon-user-female pe-2x pe-va"></span>
						  <input type="radio" name="icon" value="icon-smile" <%=i3%>>
							<span class="icon-smile pe-2x pe-va"></span>
						  <input type="radio" name="icon" value="icon-star" <%=i4%>>
							<span class="icon-star pe-2x pe-va"></span>
						  <input type="radio" name="icon" value="icon-piggy" <%=i5%>>
							<span class="icon-piggy pe-2x pe-va"></span>
						  <input type="radio" name="icon" value="icon-joy" <%=i6%>>
							<span class="icon-joy pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-car" <%=i7%>>
							<span class="icon-car pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-rocket" <%=i8%>>
							<span class="icon-rocket pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-music" <%=i9%>>
							<span class="icon-music pe-2x pe-va"></span>
							<input type="radio" name="icon" value="icon-ball" <%=i10%>>
							<span class="icon-ball pe-2x pe-va"></span>
						</div>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前はprofile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input type=text name="profile" class="form-control" value="${param.profile}">
						 </td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><input class="btn"
							type="submit" value="編集" /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-center"><input class="btn"
							type="button" onclick="location.href='index.jsp'"value="ログイン画面へ"></td>
					</tr>

					   <%-- リクエストスコープに alert_i があれば --%>
					<c:if
						test="${requestScope.alert_i != null && requestScope.alert_i != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_i}" /></td>
						</tr>
					</c:if>
							<%-- リクエストスコープに alert2 があれば --%>
					<c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert2}" /></td>
						</tr>
					</c:if>
							<%-- リクエストスコープに alert_p があれば --%>
					<c:if
						test="${requestScope.alert_p != null && requestScope.alert_p != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_p}" /></td>
						</tr>
					</c:if>
								<%-- リクエストスコープに alert_u があれば --%>
					<c:if
						test="${requestScope.alert_u != null && requestScope.alert_u != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_u}" /></td>
						</tr>
					</c:if>
				</table>
	</div>
</div>
</form>
</body>
</html>