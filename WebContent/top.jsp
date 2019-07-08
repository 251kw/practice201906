<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - みんなの心の叫び -</title>
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
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
	<form action="SerchUsers.jsp" method="post">
	<table style="width:37%" class="container padding-y-5">
	<tr>
		<td class="text-left"><%-- <div style="width: 40%" class="container padding-y-5 text-left">--%>
			<strong class="color-main">ログインユーザー情報</strong>

		<%-- </div> --%></td>
		<td class="text-right"><input class="btn" type="submit" value="ユーザー検索はこちら"></td>
		</tr>
		</table>
		</form>


	</div>
	<%-- セッションスコープにある UserDTO 型のオブジェクトを参照 --%>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./logout" method="post">
				<table class="table table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${user.icon} pe-3x pe-va"></span></td>
						<td width="256">${user.userName}</td>
						<td><input class="btn btn-light" type="submit" value="ログアウト" /></td>
					</tr>
					<tr>
						<td colspan="2">${user.profile}</td>
					</tr>
				</table>
				<input type="hidden" name="topId" value="${user.loginId}">
				<%-- <input type="hidden" name="icon" value="${shout.icon}"> --%>
			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">今の気持ちを叫ぼう</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./bbs" method="post">
				<table class="table">
					<tr>
						<%-- 今の気持ち入力欄の名前は shout --%>
						<td><input class="form-control" type="text" name="shout"
							value="" size="60" />
						<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
							<%-- リクエストスコープの alert の値を出力 --%>
							<br><div class="color-error text-left"><c:out
									value="${requestScope.alert}" /></div>
					</c:if></td>
						<td><input class="btn" type="submit" value="叫ぶ" /></td>
					</tr>
					<%-- <c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
							<%-- リクエストスコープの alert の値を出力 --%><%--
							<tr><td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
									</tr>
					</c:if> --%>
				</table>
			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">みんなの叫び</strong>
		</div>
	</div>
	<%-- セッションスコープにある ArrayList 型のオブジェクトを参照 --%>
	<jsp:useBean id="shouts" scope="session"
		type="java.util.ArrayList<dto.ShoutDTO>" />
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="shout" items="${shouts}">
				<form action="DeleteShout.jsp" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${shout.icon} pe-3x pe-va"></span></td>
						<td><span class="text-left">${shout.userName}</span><c:if test="${(user.loginId).equals(shout.loginId)}">
						<span <%-- align="right"--%>style="float: right"><input class="btn" type="submit" value="削除" /></span></c:if></td>
					</tr>
					<tr>
						<td style="clear: right">${shout.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control" readonly>${shout.writing}</textarea>
						<%-- <div  align="right"><input class="btn" type="submit" value="削除" /></div> --%>
						</td>
					</tr>
				</table>
				<input type="hidden" name="username" value="${shout.userName}">
				<input type="hidden" name="icon" value="${shout.icon}">
				<input type="hidden" name="date" value="${shout.date}">
				<input type="hidden" name="writing" value="${shout.writing}">
				<input type="hidden" name="key" value="${shout.key}">
				</form>
			</c:forEach>
			<a></a>
		</div>
	</div>
</body>
</html>
