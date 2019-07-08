<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Shouter - ユーザー検索画面 - </title>
<link rel="stylesheet" href="./css/skyblue.css">
<link rel="stylesheet" href="./css/pe-icon-7-stroke.css">
<link rel="stylesheet" href="./css/helper.css">
<style>
.small {font-size: 50%;}
</style>

</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)request.getAttribute("bid");
	String name = (String)request.getAttribute("bun");
	String profile = (String)request.getAttribute("bpr");
	String[] icon = (String[])request.getAttribute("bic");
	String ic1 = "";
	String ic2 = "";
	String ic3 = "";
	String ic4 = "";
	String ic5 = "";
	String ic6 = "";
	String ic7 = "";
	String ic8 = "";
	String ic9 = "";
	/* if(icon == null){
		icon = new String[1];
		icon[0] = "";
	} */
	if(icon != null){
		for(String i : icon){
			if("icon-user".equals(i)){
				ic1 = "checked";
			}
			if("icon-user-female".equals(i)){
				ic2 = "checked";
			}
			if("icon-car".equals(i)){
				ic3 = "checked";
			}
			if("icon-study".equals(i)){
				ic4 = "checked";
			}
			if("icon-star".equals(i)){
				ic5 = "checked";
			}
			if("icon-plane".equals(i)){
				ic6 = "checked";
			}
			if("icon-camera".equals(i)){
				ic7 = "checked";
			}
			if("icon-bicycle".equals(i)){
				ic8 = "checked";
			}
			if("icon-ball".equals(i)){
				ic9 = "checked";
			}
		}
	}
	if(id == null || "null".equals(id)){
		id = "";
	}
	if(name == null || "null".equals(name)){
		name = "";
	}
	if(profile == null || "null".equals(profile)){
		profile = "";
	}
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
			<strong class="color-main">検索検索ぅ！</strong>
		</div>
	</div>
	<div class="padding-y-5 text-center">
		<div style="width: 40%" class="container padding-y-5 text-center">
			<%-- action 属性にサーブレットを指定 --%>
			<form action="./SUS" method="post">
				<table style="width: 400px" class="table">
					<tr>
						<%-- ログインID 入力欄の名前は loginId --%>
						<td class="color-main text-left">ログインID</td>
						<td class="text-left"><input class="form-control" type="text"
							name="loginId" value="<%= id %>" size="20" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<%-- 名前入力欄の名前は username --%>
						<td class="color-main text-left">名前<br></td>
						<td class="text-left"><input class="form-control"
							type="text" name="username" value="<%= name %>" size="20" maxlength="64"/>
						</td>
					</tr>
					<tr>
						<%-- アイコン入力欄の名前はicon --%>
						<td class="color-main text-left">アイコン</td>
						<%-- <td class="text-left"><input class="form-control"
							type="text" name="icon" value="" size="20" /></td> --%>
						<td>
							<span class="icon-user pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-user" <%= ic1 %>>
							<span class="icon-user-female pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-user-female" <%= ic2 %>>
							<span class="icon-car pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-car" <%= ic3 %>><br>
							<span class="icon-study pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-study" <%= ic4 %>>
							<span class="icon-star pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-star" <%= ic5 %>>
							<span class="icon-plane pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-plane" <%= ic6 %>><br>
							<span class="icon-camera pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-camera" <%= ic7 %>>
							<span class="icon-bicycle pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-bicycle" <%= ic8 %>>
							<span class="icon-ball pe-2x pe-va"></span>
							<input type="checkbox" name="icon" value="icon-ball" <%= ic9 %>>
						</td>
					</tr>
					<tr>
						<%-- プロフィール入力欄の名前はprofile --%>
						<td class="color-main text-left">プロフィール</td>
						<td class="text-left">
							<textarea class="form-control" rows="4" cols="20" name="profile" maxlength="128"><%= profile %></textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="text-right"><input class="btn"
							type="submit" value="検索" /></td>
					</tr>
				</table>
			</form>
		</div>
		<form action="top.jsp" method="post">
				<input class="btn" type="submit" value="topへ" />
			</form>
	</div>

</body>
</html>