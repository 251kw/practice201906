<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="padding-y-5 text-center">
	<div style="width:" class="container padding-y-5 text-center">
		<%-- リクエストスコープに alert があれば --%>
		<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
			<%-- リクエストスコープの alert の値を出力 --%>
			<c:out value="${requestScope.alert}" />
		</c:if>
		<%--検索結果があれば表示する --%>
		<c:if test="${resultUsers != null}">
			<jsp:useBean id="resultUsers" scope="session"
				type="java.util.ArrayList<dto.UserDTO>" />
			<form action="update" method="post">
				<table class="table table-striped table-bordered">
					<tr>
						<th class="text-center">ログインID</th>
						<th class="text-center">名前</th>
						<th class="text-center">アイコン</th>
						<th class="text-center">プロフィール</th>
						<th class="text-center">選択</th>
					</tr>
					<c:forEach var="user" items="${resultUsers}">
						<tr>
							<td class="text-center">${user.loginId }</td>
							<td class="text-center">${user.userName }</td>
							<td class="text-center"><span
								class="${user.icon} pe-3x pe-va"></span></td>
							<td class="text-center">${user.profile }</td>
							<td><input type="checkbox" name="check"
								value="${user.loginId }"></td>
						</tr>
					</c:forEach>
				</table>
				<div class="text-right">
					選択したユーザを
					<button type="submit" class="btn" name="action"
						value="update">更新</button>
					<button type="submit" class="btn btn-light" name="action"
						value="deleteConfirm">削除</button>
					<%-- リクエストスコープに alert があれば --%>
					<br><c:if
						test="${requestScope.alert2 != null && requestScope.alert2 != ''}">
						<%-- リクエストスコープの alert の値を出力 --%>
						<c:out value="${requestScope.alert2}" />
					</c:if>
				</div>
			</form>
		</c:if>
	</div>
</div>

