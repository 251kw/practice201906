<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索結果 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<style>
.btn1 {
  margin: 0 0 0 auto;
}
.demo1{
  float: right;
}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");

	String backLoginId = (String)request.getAttribute("SaveLoginId");
	String backUsername = (String)request.getAttribute("SaveUserName");
	String backProfile = (String)request.getAttribute("SaveProfile");
	String backIcon = (String)request.getAttribute("SaveIcon");
	String on = (String)request.getAttribute("button");
	if(on == null || "null".equals(on)){
		on = "";
	}
	int n = 0;
%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー検索結果</strong>
			<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
				<%-- リクエストスコープの alert の値を出力 --%>
				<br><span class="color-error text-left">
				<c:out value="${requestScope.alert}" /></span>
				</c:if>
		</div>
	</div>
	<%-- <div class="padding-y-5 text-center">
	<form action="SerchUsers.jsp" method="post">
	<table style="width:37%" class="container padding-y-5">
	<tr>
		<td class="text-left"><div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー検索結果</strong>

		</div></td>
		<td class="text-right"><input class="btn" type="submit" value="ユーザー検索はこちら"></td>
		</tr>
		</table>
		</form>


	</div> --%>
	<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
	<jsp:useBean id="List" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 50%" class="container padding-y-5">
			<form action="./DUS" method="post">
			<table>
				<tr>
					<td class="text-left"><button class="btn" type="submit" formaction="./ACS">全選択</button></td>
				</tr>
			</table>
			<table class="table table-striped table-bordered">
			<tr class="bg-success">
				<th class="text-center"><strong>選択</strong></th>
				<th class="text-center"><strong>ID</strong></th>
				<th class="text-center"><strong>ユーザー名</strong></th>
				<th class="text-center"><strong>アイコン</strong></th>
				<th class="text-center"><strong>プロフィール</strong></th>
			</tr>
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="list" items="${List}">
			<% n++; %>
				<tr><%-- チェックボックス --%>
					<td><label class="fancy-checkbox"><input type="checkbox" name="check" value="${list.loginId}" <%= on %>><span></span></label></td>
					<td class="text-center">${list.loginId}</td>
					<td class="text-center">${list.userName}</td>
					<td class="text-center"><span class="${list.icon} pe-2x pe-va"></span></td>
					<td class="text-center">${list.profile}</td>
				</tr>
				<%-- <input type="hidden" name="loginId" value="${list.loginId}">
				<input type="hidden" name="username" value="${list.userName}">
				<input type="hidden" name="icon" value="${list.icon}">
				<input type="hidden" name="profile" value="${list.profile}"> --%>
			</c:forEach>
			<!-- <tr>
				<td colspan="4" class="text-right"><input class="btn" type="submit" value="削除">
				</td>
			</tr> -->
			</table>
			<table class="table">
				<tr>
				<td class="text-left"><button class="btn text-center" type="submit" formaction="./SUS">戻る</button></td>
				<td class="text-center"><button class="btn text-center" type="submit" formaction="./UES">編集</button></td>
				<td class="text-right"><input class="btn text-center" type="submit" value="削除">
				</td>
			</tr>
			</table>
				<input type="hidden" name="backloginid" value="<%= backLoginId %>">
				<input type="hidden" name="backusername" value="<%= backUsername %>">
				<input type="hidden" name="backprofile" value="<%= backProfile %>">
				<input type="hidden" name="backicon" value="<%= backIcon %>">
				<input type="hidden" name="back" value="true">
				<input type="hidden" name="counter" value="<%= n %>">
			</form>
		</div>
	</div>
</body>
</html>