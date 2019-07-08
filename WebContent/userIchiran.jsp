<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<div class="padding-y-5">
		<div style="width: 40%" class="container padding-y-5">
			<%-- リストにある要素の数だけ繰り返し --%>



			<c:forEach var="sltdu" items="${selectedUsers}">
				<table class="table table-striped table-bordered">
					<tr>
						<td rowspan="2" class="text-center"><span
							class="${sltdu.icon} pe-3x pe-va"></span></td>
						<td>${sltdu.userName}<br> ${sltdu.profile }



							<form action="SDK" method="post">

								<%-- <input type="hidden" name="shouterId" value="${shout.shoutsId }">
						<input type="hidden" name="shouterName" value="${shout.userName }">
						<input type="hidden" name="shouterIcon" value="${shout.icon }">
						<input type="hidden" name="shouterDate" value="${shout.date }"> --%>
								<button class="btn pull-right" type="submit"
									name="shouterWriting" value="<%-- ${sltdu.writing --%> }">削除</button>
							</form>


						</td>
					</tr>

				</table>
			</c:forEach>
			<button class="btn pull-right" type="button" onclick="history.back()">戻る</button>
		</div>
	</div>
</body>
</html>