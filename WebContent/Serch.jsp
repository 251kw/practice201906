<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー検索</title>
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
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./ss" method="post">
			<strong class="color-main">ユーザー検索</strong>
			<h5>ユーザー情報を入力してください</h5>
						<input class="form-control" type="text" name="uName"
							value="" size="20" />
						<input class="btn" type="submit" value="検索" /><br>
						</form>
						<%-- リクエストスコープに results_a があれば --%>
					<c:if
						test="${requestScope.results_a != null && requestScope.results_a != ''}">
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="検索結果が${requestScope.results_a}件見つかりました" /></td>
					</c:if>
					 <c:if
						test="${requestScope.results != null && requestScope.results != ''}">
					<jsp:useBean id="results" scope="session" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<form action="./cdas" method="post">
			<table class="table table-striped table-bordered">
  <tr bgcolor="#7fffd4">
    <th></th><th>ログインID</th><th>ユーザー名</th><th>アイコン</th><th>プロフィール</th><th></th>
  </tr>
<c:forEach var="result" items="${results}">
  <tr>
  	<td><label class="fancy-checkbox"><input type="checkbox" name="user" value="${result.loginId}"><span></span></label></td>
    <td>${result.loginId}</td>
    <td>${result.userName}</td>
    <td><span class="${result.icon} pe-2x pe-va"></span></td>
	<td>${result.profile}</td>
	<td><a href="Editing.jsp?loginId=${result.loginId}&userName=${result.userName}&icon=${result.icon}&profile=${result.profile}">編集</a></td>
  </tr>
</c:forEach>
</table>
<input class="btn" type="submit" value="削除" />
</form>
			 </c:if>
					<%-- リクエストスコープに alert_s があれば --%>
					<table>
					<c:if
						test="${requestScope.alert_s != null && requestScope.alert_s != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_s}" /></td>

						</tr>
						</c:if>
							<%-- リクエストスコープに alert_n があれば --%>
					<c:if
						test="${requestScope.alert_n != null}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_n}" /></td>
						</tr>
					</c:if>
				</table>
			<div style="text-align:center"><input class="btn" type="button"
			onclick="location.href='top.jsp'"value="TOPページへ"></div>
		</div>
	</div>
</body>
</html>