<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%@ include file="/header.html"%>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">ユーザー情報更新</strong>
			<hr>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<jsp:useBean id="updateUser" class="dto.UserDTO" scope="session" />
			<%-- action 属性にサーブレットを指定 --%>
			<form action="update" method="post">
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
							maxlength="32" name="loginId" value="${updateUser.getLoginId()}"
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
							value="${updateUser.getPassword()}" size="20" /></td>
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
							maxlength="64" name="name" value="${updateUser.getUserName()}"
							size="20" /></td>
					</tr>
					<tr>
						<%-- アイコン選択欄の名前は icon --%>
						<td class="color-main text-right">アイコン&nbsp;</td>
						<td><select name="icon"><option value="icon-user">male</option>
								<option value="icon-user-female">female</option>
								<option value="icon-magic-wand">wand</option>
								<option value="icon-plugin">plugin</option>
								<option value="icon-rocket">rocket</option>
								<option value="icon-smile">smile</option>
								<option value="icon-wine">wine</option>
								<option value="icon-cash">cash</option>
								<option value="icon-gym">gym</option>
								<option value="icon-diamond">diamond</option>
								<option value="icon-star">star</option>
								<option value="icon-science">science</option>
								<option value="icon-film">film</option>
								<option value="icon-plane">plane</option>
								<option value="icon-joy">joy</option>
						</select></td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前は profile --%>
						<td class="color-main text-right">プロフィール&nbsp;</td>
						<td class="text-left"><input class="form-control" type="text"
							maxlength="128" name="profile" value="${updateUser.getProfile()}"
							size="20" /></td>
					</tr>
					<tr>
						<td></td>
						<td class="text-center"><button type="submit" class="btn btn-light"
								name="action" value="noUpdate">キャンセル</button>
							<button type="submit" class="btn" name="action" value="confirm">確認</button>
						</td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>