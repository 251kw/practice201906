<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				ユーザー検索&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報 入力フォーム</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./US" method="post">
				<table>
					<tr>
						<th>ログインId</th>
						<td><input type="text" name="loginId"
							value="${requestScope.loginId}"></td>
					</tr>
					<tr>
						<th>ユーザーネーム</th>
						<td><input type="text" name="userName"
							value="${requestScope.userName}"></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input type="submit"
							value="検索"></td>
					</tr>
				</table>
				<button type="submit" class="btn" formaction="top.jsp">戻る</button>
			</form>
		</div>
	</div>
	<form action="top.jsp" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>