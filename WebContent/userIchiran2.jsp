<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>検索結果一覧</h1>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>

		<form action="UDK" method="post">
		<%

		%>
<!--  		<input type="hidden" name="selectedUsers" value=selectedUsers/> -->

			<button class="btn pull-right" type="submit">ユーザー削除</button>
 			<c:forEach var="sltdu" items="${selectedUsers}">
 			<table class="table table-bordered">
					<tr>
						<td width="32" rowspan="2" class="text-center">

							<input type="checkbox" name="sltduId" value="${sltdu.userId }" />
						</td>
						<td width="64" rowspan="2" class="text-center"><span
							class="${sltdu.icon} pe-3x pe-va"></span><br>

						</td>
						<td width="300">ログインID<br>
						${sltdu.loginId}</td>
						<td width="300">表示名<br>
						${sltdu.userName}</td>

					</tr>
					<tr>
						<td colspan="2">${sltdu.profile}&nbsp;</td>
					</tr>
				</table>

			</c:forEach>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>

					</c:if>


			</form>
			<button class="btn pull-right" type="button" onclick="history.back()">戻る</button>


		</div>
	</div>
</body>
</html>