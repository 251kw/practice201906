<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shouter - みんなの心の叫び -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<title>Insert title here</title>
</head>
<body>
	<form action="./UDS" method="post">
		<table class="table">
			<tr>
				<c:if
					test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alert1}" /></td>
				</c:if>
			</tr>
			<tr>
				<c:if
					test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
					<td colspan="2" class="color-error text-left"><c:out
							value="${requestScope.alert2}" /></td>
				</c:if>
			</tr>
			<tr>
				<th>チェック</th>
				<th>ログインId</th>
				<th>パスワード</th>
				<th>ユーザーネーム</th>
				<th>アイコン</th>
				<th>プロフィール</th>
			</tr>
			<!-- UsearchServlet.javaでセッションに保存しておいたやつ -->
			<c:forEach var="userL" items="${userlist}">
				<tr>
					<td><input type="checkbox" name="d_user"
						value="${userL.loginId}"></td>
					<td><c:out value="${userL.loginId}" /></td>
					<td><c:out value="${userL.password}" /></td>
					<td><c:out value="${userL.userName}" /></td>
					<td><span class="${userL.icon} pe-3x pe-va"></span></td>
					<td><c:out value="${userL.profile}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<!-- どちらも一回UdeleteServlet.javaを経由して分岐させている。 -->
				<td><input type="submit" class="btn" name="btn" value="削除"></td>
				<td><input type="submit" class="btn" name="btn" value="更新"></td>
			</tr>
		</table>
	</form>
	<form action="./US" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>