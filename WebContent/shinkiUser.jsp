<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規登録画面</title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
</head>
<body>
	<%
		String iC1 = "checked";
		String iC2 = "";
		String iC3 = "";
		String iC4 = "";
		String iC5 = "";
		String cD = (String) request.getAttribute("nIcon");

		if("icon-user-female".equals(cD)){
			iC1="";
			iC2="checked";
		}else if("icon-study".equals(cD)){
			iC1="";
			iC3="checked";
		}else if("icon-male".equals(cD)){
			iC1="";
			iC4="checked";
		}else if("icon-female".equals(cD)){
			iC1="";
			iC5="checked";
		}

	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>新規登録画面</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<strong class="color-main">ログインID・パスワード・表示名・アイコン・プロフィールを決めてください。</strong>
		</div>
	</div>

	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./SUS" method="post">

				<table style="width: 400px" class="table">
					<tr>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newerId" value="${nId }" size="20" maxlength="32" /><br>
							<h6 class="color-error text-left">半角英数最大32文字</h6>
							</td>

					</tr>
					<tr>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="newerPw" value="" size="20" maxlength="32" minlength="4"/>
							<h6 class="color-error text-left">半角英数4文字以上32文字以内</h6>
							</td>
					</tr>
					<tr>
						<td class="color-main text-left">表示名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newerName" value="${nName}" size="20" maxlength="64" />
							<h6 class="color-error text-left">最大64文字</h6>
							</td>
					</tr>
					<tr>
						<td class="color-main text-left">アイコン</td>

						<td><input type="radio" name="gender" value="icon-user"
							<%=iC1 %>><span class="icon-user pe-2x pe-va"></span> <input
							type="radio" name="gender" value="icon-user-female" <%=iC2 %>><span
							class="icon-user-female pe-2x pe-va"></span> <input type="radio"
							name="gender" value="icon-study" <%=iC3 %>><span
							class="icon-study pe-2x pe-va"></span> <input type="radio"
							name="gender" value="icon-male" <%=iC4 %>><span
							class="icon-male pe-2x pe-va"></span> <input type="radio"
							name="gender" value="icon-female" <%=iC5 %>><span
							class="icon-female pe-2x pe-va"></span></td>
					</tr>
					<tr>
						<td class="color-main text-left">プロフィール</td>
						<td><input type="text" name="newerProfile" value="${nProf }"
							size="20" maxlength="128">
							<h6 class="color-error text-left">最大128文字</h6>
							</td>
					</tr>

					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="入力内容を確認する" /></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><a href="index.jsp">登録をやめる</a></td>
					</tr>

					<%-- リクエストスコープに alert があれば --%>
					<c:if
						test="${requestScope.alert != null && requestScope.alert != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert2}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alert3 != null && requestScope.alert3 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert3}" /></td>
						</tr>
					</c:if>
					<c:if
						test="${requestScope.alert4 != null && requestScope.alert4 != ''}">
						<tr>
							<%-- リクエストスコープの alert の値を出力 --%>
							<td colspan="2" class="color-error text-left"><c:out
									value="${requestScope.alert4}" /></td>
						</tr>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>