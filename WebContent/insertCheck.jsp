<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<title>Insert title here</title>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				確認画面&nbsp;<span class="pe-7s-add-user"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">以下の内容で登録します。</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="IS2" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<th>ログインID</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${LoginId}" /></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${Password}" /></td>
					</tr>
					<tr>
						<th>ユーザーネーム</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${UserName}" /></td>

					</tr>
					<tr>
						<th>アイコン</th>
						<td colspan="2" class="color-main text-left"><span
							class="${Icon} pe-3x pe-va"></span></td>
					</tr>
					<tr>
						<th>プロフィール</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${Profile}" /></td>
					</tr>
				</table>
				<input type="submit" class="btn" value="登録">

			</form>
		</div>
	</div>
	<form action="IS" method="get">
		<input type="hidden" name="Id" value="${LoginId}"> <input
			type="hidden" name="Ic" value="${Icon}"> <input type="hidden"
			name="UName" value="${UserName}"> <input type="hidden"
			name="Prof" value="${Profile}"> <input type="submit"
			value="戻る">
	</form>
</body>
</html>