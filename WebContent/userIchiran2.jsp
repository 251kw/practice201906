<%@page import="java.util.ArrayList"%>
<%@page import="dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>

	<% //全選択処理のための変数
		String checkCheck ="";
		 checkCheck=(String)request.getAttribute("checkCheck2");
	 	if(checkCheck==null){
			checkCheck="";
		}
	%>
				<%--  <c:if
					test="${requestScope.checkCheck!= null && requestScope.checkCheck != ''}">
					<% checkCheck=request.getParameter("checkCheck2");%>
				</c:if>
 --%>
	<jsp:useBean id="selectedUsers2" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>検索結果一覧</h1>
		</div>
	</div>
	<div class="padding-y-5">
		<div style="width: 60%" class="container padding-y-5">
			<form action="./UDK" method="post">
			<!-- 検索画面で入力された値を保持するため。 -->
				<input type="hidden" name="loginId" value="${uk }" /> <input
					type="hidden" name="userName" value="${uk2 }" /> <input
					type="hidden" name="profile" value="${uk3 }" /> <input
					type="hidden" name="icon" value="${uI }" /> <input type="hidden"
					name="icon2" value="${uI2 }" /> <input type="hidden" name="icon3"
					value="${uI3 }" /> <input type="hidden" name="icon4"
					value="${uI4 }" /> <input type="hidden" name="icon5"
					value="${uI5 }" />
				<c:if
					test="${requestScope.alert != null && requestScope.alert != ''}">
					<tr>
						<%-- リクエストスコープの alert の値を出力 --%>
						<td colspan="3"><span class="color-error text-left"><c:out
								value="${requestScope.alert}" /></span></td>
					</tr>

				</c:if>

				<button class="btn pull-right" type="submit">ユーザー削除</button>
				<!-- JSPの設定をHTML5に変更することでボタンに遷移先を変えられるformaction属性が付与できる。 -->
				<button class="btn pull-right" type="submit" formaction="./UUK">登録内容の編集</button>

				<!-- checkCheckの状態を送信 -->
				<input type="hidden" name="checkCheck" value="<%=checkCheck %>" />
				<button class="btn pull-left" type="submit" formaction="./ACB">全選択</button>&nbsp;
				<br>

				<c:forEach var="sltdu" items="${selectedUsers2}">
					<table class="table table-bordered">
						<tr>
							<td width="32" rowspan="2" class="text-center"><input
								type="checkbox" name="sltduId" value="${sltdu.userId }" ${checkCheck2 }/></td>
							<td width="64" rowspan="2" class="text-center"><span
								class="${sltdu.icon} pe-3x pe-va"></span><br></td>
							<td width="300">ログインID<br> ${sltdu.loginId}
							</td>
							<td width="300">表示名<br> ${sltdu.userName}
							</td>

						</tr>
						<tr>
							<td colspan="2">${sltdu.profile}&nbsp;</td>
						</tr>
					</table>

				</c:forEach>
			</form>
			</div>
			</div>
			<div class="padding-y-5 text-center">
				<div style="width: 60%" class="container padding-y-5 text-center">
			<form action="./KCB" method="post">
				<input type="hidden" name="loginId" value="${uk }" /> <input
					type="hidden" name="userName" value="${uk2 }" /> <input
					type="hidden" name="profile" value="${uk3 }" /> <input
					type="hidden" name="icon" value="${uI }" /> <input type="hidden"
					name="icon2" value="${uI2 }" /> <input type="hidden" name="icon3"
					value="${uI3 }" /> <input type="hidden" name="icon4"
					value="${uI4 }" /> <input type="hidden" name="icon5"
					value="${uI5 }" />
				<button class="btn text-center" type="submit">検索画面へ戻る</button>

			</form>
		</div>
	</div>
				<div class="padding-y-5 text-center">
				<div style="width: 60%" class="container padding-y-5 text-center">
					<%-- action 属性にloginサーブレットを指定 --%>
					<form action="./login" method="post">
						<h2>叫び画面へ戻る</h2>
						<input type="hidden" name="loginId" value="${user.loginId }" /> <input
							type="hidden" name="password" value="${user.password }" /> <input
							class="btn" type="submit" value="もっと叫ぼう" />
					</form>
				</div>
			</div>
</body>
</html>