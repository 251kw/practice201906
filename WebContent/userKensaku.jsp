<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ja">
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
			<h1>気になるワードでユーザーを検索できます</h1>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="DUK" method="post">
				<table style="width: 500px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインIDで検索</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="${uk }" size="20" /></td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">表示名で検索</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="${uk2}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン</td>

						<td><input type="checkbox" name="icon" id="icon" value="icon-user"
							${uI }>
							<span class="icon-user pe-2x pe-va"></span><input
							type="checkbox" name="icon2" id="icon2" value="icon-user-female" ${uI2 }><span
							class="icon-user-female pe-2x pe-va"></span> <input type="checkbox"
							name="icon3" id="icon3" value="icon-study" ${uI3 }><span
							class="icon-study pe-2x pe-va"></span> <input type="checkbox"
							name="icon4" id="icon4" value="icon-male" ${uI4 }><span
							class="icon-male pe-2x pe-va"></span> <input type="checkbox"
							name="icon5" value="icon-female" ${uI5 }><span
							class="icon-female pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィールで検索</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" value="${uk3 }" size="20" /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /></td>
					</tr>

					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>

					</c:if>
				</table>
			</form>
			<div class="padding-y-5 text-center">
				<div style="width: 60%" class="container padding-y-5 text-center">
					<%-- action 属性にloginサーブレットを指定 --%>
					<form action="./login" method="post">
						<h2>叫び画面へ戻る</h2>
						<input type="hidden" name="loginId" value="${user.loginId }" /> <input
							type="hidden" name="password" value="${user.password }" /> <input
							class="btn" type="submit" value="もっと叫ぼう" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>