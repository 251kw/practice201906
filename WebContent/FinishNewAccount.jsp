<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String loginId = request.getParameter("loginId");
	String password = request.getParameter("password");
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
			<strong class="color-main">登録が完了しました。</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
	<form action="./login" method="post">
		<%-- <table style="width: 400px" class="table">
			<tr>
				<td colspan="2" class="text-right">
					<input class="btn"
					type="submit" value="topへ" />
				</td>
			</tr>
		</table>
		 --%>
		 <input class="btn"
					type="submit" value="topへ" />
		<input type="hidden" name="loginId" value="<%= loginId %>">
		<input type="hidden" name="password" value="<%= password %>">
	</form>
	</div>

</body>
</html>