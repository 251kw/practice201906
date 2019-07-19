<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<%
		String dltMyId = (String)request.getAttribute("dltMyId");
		String beanUserId = (String)request.getAttribute("beanUserId");

	%>
					<c:if
						test="${sessionScope.user != null && requestScope.user != ''}">
						<jsp:useBean id="user" scope="session" type="dto.UserDTO" />
					</c:if>
<jsp:useBean id="selectedUsers2" scope="session" type="java.util.ArrayList<dto.UserDTO>" />

	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザーの削除が完了しました。</h1>
		</div>
	</div>

	<c:if test="${dltMyId != beanUserId}">

		<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にloginサーブレットを指定 --%>
			<form action="./DUK" method="post">
				<h2>ユーザー一覧へ戻る</h2>
			<%-- 	<c:forEach var="i" items="${selectedUsers2 }"> --%>
				<!--検索サーブレットへ取得したユーザのIdを送信。消されたユーザーは検索に引っかからない。  -->
				<input type="hidden" name="loginId" value="${uk }" />
				<input type="hidden" name="userName" value="${uk2 }" />
				<input type="hidden" name="profile" value="${uk3 }" />
				<input type="hidden" name="icon" value="${uI }" />
				<input type="hidden" name="icon2" value="${uI2 }" />
				<input type="hidden" name="icon3" value="${uI3 }" />
				<input type="hidden" name="icon4" value="${uI4 }" />
				<input type="hidden" name="icon5" value="${uI5 }" />

				<%-- </c:forEach> --%>
				<input class="btn" type="submit" value="ユーザー検索一覧へ" />
			</form>
		</div>
	</div>



	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にloginサーブレットを指定 --%>
			<form action="./login" method="post">
			<input type="hidden" name="loginId" value="${user.loginId }" />
			<input type="hidden" name="password" value="${user.password }" />
				<h4>書き込み画面へ戻る</h4>
				<input class="btn" type="submit" value="ログイン" />
			</form>
		</div>
	</div>
</c:if>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にloginサーブレットを指定 --%>
			<form action="./logout" method="post">
				<h4>ログアウト</h4>
				<input class="btn" type="submit" value="ログアウト" />
			</form>
		</div>
	</div>
</body>
</html>