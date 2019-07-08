<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html" %>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー検索</strong><br>
			<hr>
			検索条件を入力してください。<br> 入力されていない場合は全検索します。<br>
		</div>
	</div>
	<div class="padding-y-5 container text-center">
		<form method="post" action="search">
			<c:if test="${searchUser != null }">
				<jsp:useBean id="searchUser" scope="session" type="dto.UserDTO" /></c:if>
			<table class="container">
				<tr>
					<td class="color-main text-right">ID&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="32" name="loginId" value="${searchUser.loginId }"></td>
				</tr>
				<tr>
					<td class="color-main text-right">ユーザ名&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="64" name="name" value="${searchUser.userName }"></td>
				</tr>
				<tr>
					<td class="color-main text-right">プロフィールに&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="128" name="profile" value="${searchUser.profile }"></td>
					<td class="color-main text-left">&nbsp;を含む</td>
				</tr>
				<tr><td></td>
					<td class="text-center">
						<button type="submit" class="btn btn-light" name="action"
							value="top">戻る</button>
					<button type="submit"
							class="btn" name="action" value="search">検索</button></td>
							<td></td>
				</tr>
			</table>
		</form>
		<hr>
	</div>

	<jsp:include page="/searchResult.jsp" />

</body>
</html>