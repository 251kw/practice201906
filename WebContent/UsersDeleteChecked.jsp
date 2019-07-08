<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - 検索結果 -</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<style>
.btn1 {
  margin: 0 0 0 auto;
}
.demo1{
  float: right;
}
</style>
</head>
<body>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 50%" class="container padding-y-5 text-left">
			<strong class="color-main">以下のユーザーを削除します。よろしいですか？</strong>
				<br><span class="color-error text-left">
				※叫びも削除されます</span>
		</div>
	</div>
	<jsp:useBean id="checklist" scope="session"
		type="java.util.ArrayList<dto.UserDTO>" />
	<div class="padding-y-5">
		<div style="width: 50%" class="container padding-y-5">
			<form action="./RUS" method="post">
			<table class="table table-striped table-bordered">
			<tr class="bg-success">
				<th class="text-center"><strong>ID</strong></th>
				<th class="text-center"><strong>ユーザー名</strong></th>
				<th class="text-center"><strong>アイコン</strong></th>
				<th class="text-center"><strong>プロフィール</strong></th>
			</tr>
			<%-- リストにある要素の数だけ繰り返し --%>
			<c:forEach var="list" items="${checklist}">
				<tr>
					<td class="text-center">${list.loginId}</td>
					<td class="text-center">${list.userName}</td>
					<td class="text-center"><span class="${list.icon} pe-2x pe-va"></span></td>
					<td class="text-center">${list.profile}</td>
				</tr>
				<%-- <input type="hidden" name="loginId" value="${list.loginId}">
				<input type="hidden" name="username" value="${list.userName}">
				<input type="hidden" name="icon" value="${list.icon}">
				<input type="hidden" name="profile" value="${list.profile}"> --%>
			</c:forEach>
			<!-- <tr>
				<td colspan="4" class="text-right"><input class="btn" type="submit" value="削除">
				</td>
			</tr> -->
			</table>
			<table class="table">
				<tr>
				<td class="text-left"><button class="btn text-center" type="submit" formaction="./ResultUsers.jsp">戻る</button></td>
				<td class="text-right"><input class="btn text-center" type="submit" value="削除">
				</td>
			</tr>
			</table>
			</form>
		</div>
	</div>

</body>
</html>