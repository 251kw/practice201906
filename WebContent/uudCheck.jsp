<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新情報の確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<form action="./UUDS" method="get">
		<div class="bg-success padding-y-5">
			<div class="container padding-y-5 text-center">
				<h1>
					確認画面&nbsp;<span class="pe-7s-add-user"></span>
				</h1>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-center">
				<strong class="color-main">以下の内容で更新します。</strong>
			</div>
		</div>
		<div class="padding-y-5 text-center">
			<div style="width: 40%" class="container padding-y-5 text-center">
				<table style="width: 400px" class="table">
					<tr>
						<th>パスワード</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${password}" /></td>
					</tr>
					<tr>
						<th>ユーザーネーム</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${userName}" /></td>
					</tr>
					<tr>
						<th>アイコン</th>
						<td colspan="2" class="color-main text-left"><span
							class="${icon} pe-3x pe-va"></span></td>
					</tr>
					<tr>
						<th>プロフィール</th>
						<td colspan="2" class="color-main text-left"><c:out
								value="${profile}" /></td>
					</tr>
				</table>

				<button type="submit" class="btn" formaction="UUDS2">更新</button>
			</div>
		</div>
		<input type="submit" value="戻る">

	</form>
</body>
</html>