<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%String Icon = (String)request.getAttribute("IC"); %>
<title>入力フォーム</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				新規登録&nbsp;<span class="pe-7s-add-user"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">登録情報を入力してください。</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<form action="./IS" method="post">
				<table border="1">
					<tr>
						<c:if
							test="${requestScope.alert1 != null && requestScope.alert1 != ''}">
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert1}" /></td>
						</c:if>
					</tr>
					<tr>
						<c:if
							test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert2}" /></td>
						</c:if>
					</tr>
					<tr>
						<c:if
							test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert3}" /></td>
						</c:if>
					</tr>
					<tr>
						<c:if
							test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert4}" /></td>
						</c:if>
					</tr>
					<tr>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="${requestScope.Id}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="password" value="" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">ユーザーネーム</td>
						<td class="text-left"><input class="form-control" type="text"
							name="userName" value="${requestScope.Un}" size="20" /></td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン</td>
						<td><select name="icon" class="form-control">
						<c:choose>
						<c:when test="${requestScope.Ic == 'pe-7s-user-female'}">
						<%-- <c:when test=<%Icon.equals("pe-7s-user-female");%>> --%>
								<option value="pe-7s-user">男性</option>
								<option value="pe-7s-user-female"selected>女性</option>
						</c:when>
						<c:otherwise>
								<option value="pe-7s-user"selected>男性</option>
								<option value="pe-7s-user-female">女性</option>
						</c:otherwise>
						</c:choose>
						</select></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left"><input class="form-control" type="text"
							name="profile" value="${requestScope.Pf}" size="20" /></td>
				</table>
				<input type="submit" value="次へ">
			</form>
			<form action="index.jsp" method="get">
				<input type="submit" value="戻る">
			</form>
		</div>
	</div>
</body>
</html>