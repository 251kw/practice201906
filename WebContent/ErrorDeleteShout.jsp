<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
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
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">削除できませんでした</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
	<form action="top.jsp" method="post">
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
	</form>
	</div>

</body>
</html>