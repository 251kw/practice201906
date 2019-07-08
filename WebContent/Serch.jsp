<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./ss" method="post">
			<strong class="color-main">ユーザー検索</strong>
			<h5>ユーザー名を入力してください</h5>
				<table class="table">
					<tr>
						<td><input class="form-control" type="text" name="uName"
							value="" size="60" /></td>
						<td><input class="btn" type="submit" value="検索" /></td>

					<c:if
						test="${requestScope.result != null && requestScope.result != ''}">
						<jsp:useBean id="results" scope="request" type="java.util.ArrayList<dto.UserDTO>" />
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="result" items="${results}">
			${result.icon}${result.userName}${result.date}
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${result.icon} pe-3x pe-va"></span></td>
						<td>${result.userName}
						</td>
					</tr>
					<tr>
						<td>${result.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control">${result.writing}</textarea>
						</td>
					</tr>
				</table>
			</c:forEach>
			</c:if>
							<%-- リクエストスコープに alert_s があれば --%>
					<c:if
						test="${requestScope.alert_s != null && requestScope.alert_s != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert_s}" /></td>

						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>