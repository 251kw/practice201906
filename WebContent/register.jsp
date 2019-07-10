<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー登録</strong>
			<hr>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<jsp:useBean id="user" class="dto.UserDTO" scope="session" />
			<%-- action 属性にサーブレットを指定 --%>
			<form action="register" method="post">
				<table style="width: 400px">
					<tr>
						<td></td>
						<%-- IDの入力エラーがあれば表示 --%>
						<td colspan="2" class="color-error text-left">${alert.idErr}</td>
					</tr>
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-right">ログインID&nbsp;</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="32" name="loginId" value="${user.getLoginId()}"
							size="20" /></td>

					</tr>
					<tr>
						<td></td>
						<%-- パスワードの入力エラーがあれば表示 --%>
						<td class="color-error text-left">${alert.passErr}</td>
					</tr>
					<tr>
						<%-- パスワード入力欄の名前は password --%>
						<td class="color-main text-right">パスワード&nbsp;</td>
						<td class="text-left"><input class="form-control"
							type="password" maxlength="32" name="password"
							value="${user.getPassword()}" size="20" /></td>
					</tr>
					<tr>
						<td></td>
						<%-- 名前の入力エラーがあれば表示 --%>
						<td colspan="2" class="color-error text-left">${alert.nameErr}</td>
					</tr>
					<tr>
						<%-- 名前入力欄の名前は name --%>
						<td class="color-main text-right">名前&nbsp;</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="64" name="name" value="${user.getUserName()}"
							size="20" /></td>
					</tr>
					<tr>
						<%-- アイコン選択欄の名前は icon --%>
						<td class="color-main text-right">アイコン&nbsp;</td>
						<td><select name="icon"><option value="icon-user">male</option>
								<option value="icon-user-female" <c:if test="${user.getIcon() == 'icon-user-female'}">selected</c:if>>female</option>
								<option value="icon-magic-wand" <c:if test="${user.getIcon() == 'icon-magic-wand'}">selected</c:if>>wand</option>
								<option value="icon-plugin" <c:if test="${user.getIcon() == 'icon-plugin'}">selected</c:if>>plugin</option>
								<option value="icon-rocket" <c:if test="${user.getIcon() == 'icon-rocket'}">selected</c:if>>rocket</option>
								<option value="icon-smile" <c:if test="${user.getIcon() == 'icon-smile'}">selected</c:if>>smile</option>
								<option value="icon-wine" <c:if test="${user.getIcon() == 'icon-wine'}">selected</c:if>>wine</option>
								<option value="icon-cash" <c:if test="${user.getIcon() == 'icon-cash'}">selected</c:if>>cash</option>
								<option value="icon-gym" <c:if test="${user.getIcon() == 'icon-gym'}">selected</c:if>>gym</option>
								<option value="icon-diamond" <c:if test="${user.getIcon() == 'icon-diamond'}">selected</c:if>>diamond</option>
								<option value="icon-star" <c:if test="${user.getIcon() == 'icon-star'}">selected</c:if>>star</option>
								<option value="icon-science" <c:if test="${user.getIcon() == 'icon-science'}">selected</c:if>>science</option>
								<option value="icon-film" <c:if test="${user.getIcon() == 'icon-film'}">selected</c:if>>film</option>
								<option value="icon-plane" <c:if test="${user.getIcon() == 'icon-plane'}">selected</c:if>>plane</option>
								<option value="icon-joy" <c:if test="${user.getIcon() == 'icon-joy'}">selected</c:if>>joy</option>
						</select></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-right">プロフィール&nbsp;</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="128" name="profile" value="${user.getProfile()}"
							size="20" /></td>
					</tr>
				</table>
				<input class="btn btn-light" type="button"
					onclick="location.href='index.jsp'" value="キャンセル" /> <input
					type="reset" class="btn btn-warning" value="リセット">
				<button type="submit" class="btn" name="action" value="confirm">確認</button>
			</form>
		</div>
	</div>
</body>
</html>