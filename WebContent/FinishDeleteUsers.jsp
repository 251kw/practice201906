<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー削除完了 -</title>
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
<%
	request.setCharacterEncoding("UTF-8");
	String check = (String)request.getAttribute("logout");
	if(check == null){
		check = "";
	}
	request.setAttribute("check", check);
%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>
				Shouter&nbsp;<span class="icon-speaker"></span>
			</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">削除しました</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<c:if test="${1 > 0}">
			<c:choose>
				<%-- もし削除したユーザーが自分自身ならログアウトする --%>
				<c:when test="${check == 'logout'}">
					<form action="./logout" method="post">
						 <input class="btn"type="submit" value="topへ" />
					</form>
				</c:when>
				<c:otherwise>
					<form action="./DTT" method="post">
		 				<input class="btn"type="submit" value="topへ" />
					</form>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>

</body>
</html>