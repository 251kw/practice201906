<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>削除完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>叫びの削除が完了しました。</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にloginサーブレットを指定 --%>
			<form action="./login" method="post">
				<h2>また叫びますか？</h2>
				<input type="hidden" name="loginId" value="${user.loginId }" />
				<input type="hidden" name="password" value="${user.password }" />
				<input class="btn" type="submit" value="もっと叫ぶ！" />
			</form>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にloginサーブレットを指定 --%>
			<form action="./logout" method="post">
				<h4>ログアウトはこちらから</h4>
				<input class="btn" type="submit" value="また今度。" />
			</form>
		</div>
	</div>
</body>
</html>