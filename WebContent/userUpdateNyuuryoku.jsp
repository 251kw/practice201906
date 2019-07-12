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
	<%
		String iC1 = "";
		String iC2 = "";
		String iC3 = "";
		String iC4 = "";
		String iC5 = "";
		String cD = (String) request.getAttribute("sUserIcon");
		if("icon-user".equals(cD)){
			iC1="checked";
		}else if("icon-user-female".equals(cD)){
			iC2="checked";
		}else if("icon-study".equals(cD)){
			iC3="checked";
		}else if("icon-male".equals(cD)){
			iC4="checked";
		}else if("icon-female".equals(cD)){
			iC5="checked";
		}

	%>
	<div class="bg-success padding-y-5">
		<div class="container padding-y-5 text-center">
			<h1>ユーザー情報変更</h1>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 60%" class="container padding-y-5 text-center">
			<strong class="color-main">変更する部分を入力してください。</strong>
		</div>
	</div>
<jsp:useBean id="sUser" scope="session" type="dto.UserDTO" />
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./UUC" method="post">
				<input type="hidden" name="sltduId" value="${sUser.userId }" />
				<input type="hidden" name="newerId" value="${sUser.loginId }" />
				<table style="width: 400px" class="table">
<%-- 					<tr>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newerId" value="${sUser.loginId }" size="20" maxlength="32" /><br>
							<h6 class="color-error text-left">半角英数最大32文字</h6>
							</td>

					</tr> --%>
					<tr>
						<td class="color-main text-left">パスワード</td>
						<td class="text-left"><input class="form-control"
							type="password" name="newerPw" value="${sUser.password }" size="20" maxlength="32" />
							<h6 class="color-error text-left">半角英数最大32文字</h6>
							</td>
					</tr>
					<tr>
						<td class="color-main text-left">表示名</td>
						<td class="text-left"><input class="form-control" type="text"
							name="newerName" value="${sUser.userName }" size="20" maxlength="64" />
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
						<td><input type="text" name="newerProfile" value="${sUser.profile }"
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