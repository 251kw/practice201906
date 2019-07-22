<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,dto.UserDTO"%>
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
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				検索結果&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
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
				<c:set var="x" value="${userL.loginId}"/>
					<%
						String s = (String)pageContext.getAttribute("x");
						String [] c_users = (String [])request.getAttribute("Cusers");

						String a = "ok";
						String b = "";
						//最初の一回目を考慮
						//全解除ボタンを押された時もここに入らない。
						if(c_users != null){
							for(String c : c_users){
								if(s.equals(c)){
									b = "ok";
								}
							}
						}
						request.setAttribute("B", b);
						request.setAttribute("A", a);
					%>
					<c:choose>
					<c:when test="${A == B}">
					<td><input type="checkbox" name="d_user"
						value="${userL.loginId}" checked></td>
					</c:when>
					<c:otherwise>
					<td><input type="checkbox" name="d_user"
						value="${userL.loginId}"></td>
					</c:otherwise>
					</c:choose>

					<td><c:out value="${userL.loginId}" /></td>
					<td><c:out value="${userL.password}" /></td>
					<td><c:out value="${userL.userName}" /></td>
					<td><span class="${userL.icon} pe-3x pe-va"></span></td>
					<td><c:out value="${userL.profile}" /></td>
				</tr>
			</c:forEach>
						<tr>
			<td><button type="submit" class="btn" formaction="./ACS">全選択</button></td>
			<td><button type="submit" class="btn" formaction="./ACS2">全解除</button></td>
			</tr>
			<tr>
				<!-- どちらも一回UdeleteServlet.javaを経由して分岐させている。 -->
				<td><input type="submit" class="btn btn-success" name="btn" value="削除"></td>
				<td><input type="submit" class="btn btn-success" name="btn" value="更新"></td>
			</tr>
		</table>
	</form>
	<form action="./US" method="get">
		<input type="submit"value="戻る">
	</form>
</body>
</html>