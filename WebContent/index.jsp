<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ログイン -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ログイン</strong>
			<hr>
			IDとパスワードを入力してください。
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./login" method="post">
				<table style="width: 400px">
					<tr>
						<td colspan="2" class="color-error text-right">
							<%-- リクエストスコープに alert があれば --%> <c:if
								test="${requestScope.alert != null && requestScope.alert != ''}">
								<%-- リクエストスコープの alert の値を出力 --%>
								<c:out value="${requestScope.alert}" />
							</c:if>
						</td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-right">ログインID&nbsp;</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="${ID}" size="20" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-right">パスワード&nbsp;</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="" size="20" /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
							<button type="submit" class="btn" name="action" value="login">ログイン</button>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="text-right">
							<button type="submit" class="btn btn-success" name="action"
								value="new">新規登録</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
