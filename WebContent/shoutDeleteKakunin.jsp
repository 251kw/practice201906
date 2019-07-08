<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>書き込み削除確認画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%

	%>
	<div class="bg-warning padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>こちらの書き込みを削除しますか？</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-left">
			<strong class="color-main">こちらの叫びを削除しますか？</strong><br> <strong
				class="color-error">※削除した叫びは元に戻すことができません。</strong>
		</div>
	</div>

	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<form action="SDD" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${shoutDlt.icon} pe-3x pe-va"></span></td>
						<td>${shoutDlt.userName}</td>
					</tr>
					<tr>
						<td>${shoutDlt.date}</td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="5" class="form-control"
								readonly>${shoutDlt.writing}</textarea></td>
					</tr>
					<tr>
						<th class="text-center"><input type="hidden"
							name="shoutsId" value="${shoutDlt.shoutsId }"> <input
							type="hidden" name="shouterName" value="${shoutDlt.userName }">
							<input type="hidden" name="shouterIcon" value="${shoutDlt.icon }">
							<input type="hidden" name="shouterDate" value="${shoutDlt.date }">
							<button class="btn btn-error" type="submit" name="shouterWriting"
								value="${shoutDlt.writing }">削除する</button></th>
						<th class="text-center">
							<button class="btn" type="button" onclick="history.back()">戻る</button>
						</th>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>