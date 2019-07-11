<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー削除確認</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-warning padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1 class="color-error">こちらのユーザーを削除しますか？</h1>
			<strong
				class="color-error">※ユーザーを削除すると投稿された叫びも削除されます。</strong>
		</div>
	</div>
		<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">
				<form action="UDD" method="post">

 			<c:forEach var="uList" items="${uList}">
 			<table class="table table-bordered">
					<tr>
						<td width="64" rowspan="2" class="text-center"><span
							class="${uList.icon} pe-3x pe-va"></span><br>
							<input type="hidden" name="uListLoginId" value="${uList.loginId }" />
						</td>
						<td width="300">ログインID<br>
						${uList.loginId}</td>
						<td width="300">表示名<br>
						${uList.userName}</td>

					</tr>
					<tr>
						<td colspan="2">${uList.profile}&nbsp;</td>
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
					<button class="btn pull-right" type="submit">ユーザー削除</button>
			</form>

			<form action="DUK" method="post">
				<button class="btn pull-right" type="button" onclick="history.back()">戻る</button><br>
				<!-- <button class="btn pull-right" type="submit" >戻る</button> -->
			</form>

		</div>
	</div>
</body>
</html>