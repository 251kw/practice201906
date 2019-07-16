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
	<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー検索</strong><br>
			<hr>
			検索条件を入力してください。<br> <br>
		</div>
	</div>
	<div class="padding-y-5 container text-center">
		<%-- リクエストスコープに alert があれば --%>
		<c:if
			test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
			<%-- リクエストスコープの alert の値を出力 --%>
			<p class="color-error">
				<c:out value="${requestScope.alert2}" />
			</p>
		</c:if>
		<form method="post" name="form" action="search">
			<c:if test="${searchUser != null }">
				<jsp:useBean id="searchUser" scope="session" type="dto.UserDTO" /></c:if>
			<table class="container">
				<tr>
					<td class="color-main text-right">ログインID&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="32" name="loginId" value="${searchUser.loginId }"></td>
				</tr>
				<tr>
					<td class="color-main text-right">ユーザ名&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="64" name="name" value="${searchUser.userName }"></td>
				</tr>
				<tr>
					<td class="color-main text-right">アイコン&nbsp;</td>
					<td class="text-left"><select name="icon">
							<option>--</option>
							<option value="icon-user"
								<c:if test="${searchUser.getIcon() == 'icon-user'}">selected</c:if>>male</option>
							<option value="icon-user-female"
								<c:if test="${searchUser.getIcon() == 'icon-user-female'}">selected</c:if>>female</option>
							<option value="icon-magic-wand"
								<c:if test="${searchUser.getIcon() == 'icon-magic-wand'}">selected</c:if>>wand</option>
							<option value="icon-plugin"
								<c:if test="${searchUser.getIcon() == 'icon-plugin'}">selected</c:if>>plugin</option>
							<option value="icon-rocket"
								<c:if test="${searchUser.getIcon() == 'icon-rocket'}">selected</c:if>>rocket</option>
							<option value="icon-smile"
								<c:if test="${searchUser.getIcon() == 'icon-smile'}">selected</c:if>>smile</option>
							<option value="icon-wine"
								<c:if test="${searchUser.getIcon() == 'icon-wine'}">selected</c:if>>wine</option>
							<option value="icon-cash"
								<c:if test="${searchUser.getIcon() == 'icon-cash'}">selected</c:if>>cash</option>
							<option value="icon-gym"
								<c:if test="${searchUser.getIcon() == 'icon-gym'}">selected</c:if>>gym</option>
							<option value="icon-diamond"
								<c:if test="${searchUser.getIcon() == 'icon-diamond'}">selected</c:if>>diamond</option>
							<option value="icon-star"
								<c:if test="${searchUser.getIcon() == 'icon-star'}">selected</c:if>>star</option>
							<option value="icon-science"
								<c:if test="${searchUser.getIcon() == 'icon-science'}">selected</c:if>>science</option>
							<option value="icon-film"
								<c:if test="${searchUser.getIcon() == 'icon-film'}">selected</c:if>>film</option>
							<option value="icon-plane"
								<c:if test="${searchUser.getIcon() == 'icon-plane'}">selected</c:if>>plane</option>
							<option value="icon-joy"
								<c:if test="${searchUser.getIcon() == 'icon-joy'}">selected</c:if>>joy</option>
					</select></td>
				</tr>
				<tr>
					<td class="color-main text-right">プロフィールに&nbsp;</td>
					<td class="text-left"><input type="text" class="form-control"
						maxlength="128" name="profile" value="${searchUser.profile }"></td>
					<td class="color-main text-left">&nbsp;を含む</td>
				</tr>
				<tr>
					<td></td>
					<td class="text-center"><button type="submit" class="btn"
							name="action" value="search">検索</button></td>
					<td></td>
				</tr>
			</table>
			<br> <br>
			<button type="submit" class="btn btn-light" name="action" value="top">トップに戻る</button>
			<button type="submit" class="btn btn-success" name="action"
				value="searchall">全ユーザ表示</button>
		</form>
		<hr>
	</div>

	<jsp:include page="/searchResult.jsp" />

</body>
</html>