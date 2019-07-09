<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 新規登録 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter 新規登録&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の情報を入力してください</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./CreateServlet" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control"
						type="text" name="loginId" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- ユーザーネーム入力欄の名前は userName --%>
						<td class="color-main text-left">ユーザーネーム</td>
						<td class="text-left"><input class="form-control"
							type="text" name="userName" value="" size="20" /></td>
					</tr>
					<tr>
						<%-- アイコン入力欄の名前は icon --%>
						<td class="color-main text-left">アイコン</td>
						<td class="text-left">
						<select name="icon" id="icon">
        				<option value="who">--- どれを選択しますか? ---</option>
        				<option value="icon-user">男の子</option>
       				 	<option value="icon-user-female">女の子</option>
        				<option value="icon-bell">ベル</option>
      					</select>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control"
							type="text" name="profile" value="" size="20" /></td>
					</tr>
					<tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="確認" /></td>
					</tr>
					<%-- リクエストスコープに alert1 があれば --%>
					<c:if
						test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert1}" /></td>
					</c:if>
					<%-- リクエストスコープに alert2 があれば --%>
					<c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert2}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに alert3 があれば --%>
					<c:if
						test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert3}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに alert4があれば --%>
					<c:if
						test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert4}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに alert5があれば --%>
					<c:if
						test="${requestScope.alert5 != null && requestScope.alert5 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert5}" /></td>
						</tr>
					</c:if>
					<%-- リクエストスコープに alert5があれば --%>
					<c:if
						test="${requestScope.alert6 != null && requestScope.alert6 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert6}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
						<form action="./index.jsp" method="post">
						<input class="btn"
							type="submit" value="キャンセル" />
						</form>

		</div>
	</div>
</body>
</html>
