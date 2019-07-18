<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.UserDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新情報の入力</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="D_users" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				更新情報画面&nbsp;<span class="pe-7s-add-user"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">更新するユーザー情報</strong>
		</div>
	</div>
	<div class="padding-y-6 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<table class="table table-bordered">
				<tr>
					<th>ログインId</th>
					<th>パスワード</th>
					<th>ユーザーネーム</th>
					<th>アイコン</th>
					<th>プロフィール</th>
				</tr>
				<c:forEach var="dusers" items="${D_users}">
					<tr>
						<td><c:out value="${dusers.loginId}" /></td>
						<td><c:out value="${dusers.password}" /></td>
						<td><c:out value="${dusers.userName}" /></td>
						<td><span class="${dusers.icon} pe-3x pe-va"></span></td>
						<td><c:out value="${dusers.profile}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">更新する情報を入力してください</strong>
		</div>
	</div>
	<!-- UdeleteServlet.javaでセッションに入れておいたもの -->
	<jsp:useBean id="updateUser" scope="request" type="dto.UserDTO" />
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./UUDS" method="post">
				<table border="1">
					<tr>
						<c:if
							test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert1}" /></td>
						</c:if>
					</tr>
					<tr>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="newPW" value="${updateUser.password}"
							size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">ユーザーネーム</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newUN" value="${updateUser.userName}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン</td>
						<td><select name="newIC" class="form-control">
								<c:choose>
									<c:when test="${updateUser.icon == 'pe-7s-user-female'}">
										<%-- <c:when test=<%Icon.equals("pe-7s-user-female");%>> --%>
										<option value="pe-7s-user">男性</option>
										<option value="pe-7s-user-female" selected>女性</option>
									</c:when>
									<c:otherwise>
										<option value="pe-7s-user" selected>男性</option>
										<option value="pe-7s-user-female">女性</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newPF" value="${updateUser.profile}" size="20" /></td>
				</table>
				<!-- <button type="submit" class="btn" formaction="top.jsp">戻る</button> -->
				<input type="submit" class="btn" value="確認画面へ">
			</form>
		</div>
	</div>
	<form action="./UDS" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>