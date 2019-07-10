<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Complete</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー削除</strong><br>
			<hr>
			ログインユーザを削除したのでログアウトしました。<br>
		</div>
	</div>
<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<input class="btn" type="button"
					onclick="location.href='index.jsp'" value="ログイン画面へ" />
		</div>
	</div>

</body>
</html>