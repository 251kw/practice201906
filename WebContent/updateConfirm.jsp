<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Confirm</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報更新</strong><br>
			<hr>
			下記の内容で更新します。<br>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width:" class="container padding-y-5 text-center">
			<jsp:useBean id="updateUser" scope="session"
				type="java.util.ArrayList<dto.UserDTO>" />
			<form action="update" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<th class="text-center">ログインID</th>
						<th class="text-center">名前</th>
						<th class="text-center">アイコン</th>
						<th class="text-center">プロフィール</th>
					</tr>
						<tr>
							<td class="text-center">${updateUser.loginId }</td>
							<td class="text-center">${updateUser.userName }</td>
							<td class="text-center"><span
								class="${updateUser.icon} pe-3x pe-va"></span></td>
							<td class="text-center">${updateUser.profile }</td>
						</tr>
				</table>
				<button type="submit" class="btn btn-light" name="action"
					value="fix">修正</button>
				<button type="submit" class="btn" name="action"
					value="delete">削除</button>
			</form>
		</div>
	</div>
</body>
</html>