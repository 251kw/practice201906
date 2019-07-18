<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div style="width: 40%" class="container padding-y-5 text-center">
			<strong class="color-main">検索情報 入力フォーム</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./US" method="post">
				<table>
				<tr>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="${requestScope.loginId}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">ユーザーネーム</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="${requestScope.userName}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" value="${requestScope.profile}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン</td>
						<td><select name="icon" class="form-control">
								<c:choose>
									<c:when test="${requestScope.icon == 'pe-7s-user-female'}">
										<option value="pe-7s-user">男性</option>
										<option value="pe-7s-user-female" selected>女性</option>
									</c:when>
									<c:otherwise>
										<option value="pe-7s-user" selected>男性</option>
										<option value="pe-7s-user-female">女性</option>
									</c:otherwise>
								</c:choose>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input type="submit"
							class="btn" value="検索"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<form action="top.jsp" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>