<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="padding-y-5 text-center">
	<div style="width:" class="container padding-y-5 text-center">
		<%-- リクエストスコープに alert があれば --%>
		<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
			<%-- リクエストスコープの alert の値を出力 --%>
			<p class="color-error">
				<c:out value="${requestScope.alert}" />
			</p>
		</c:if>
		<%--検索結果があれば表示する --%>
		<c:if test="${resultUsers != null}">
			<jsp:useBean id="resultUsers" scope="session"
				type="java.util.ArrayList<dto.UserDTO>" />
			<form name="form" action="update" method="post">
				<div class="text-right"><label><input type="checkbox" name="all"
					onClick="AllChecked();" />全て選択
				</label></div>
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
								value="${user.loginId }" onClick="DisChecked();"></td>
						</tr>
					</c:forEach>
				</table>
				<div class="text-right">
					選択したユーザを
					<button type="submit" class="btn" name="action" value="update">更新</button>
					<button type="submit" class="btn btn-light" name="action"
						value="deleteConfirm">削除</button>
				</div>
			</form>

			<script>
  // 「全て選択」チェックで全てにチェック付く
  function AllChecked(){
    var all = document.form.all.checked;
    for (var i=0; i<document.form.check.length; i++){
      document.form.check[i].checked = all;
    }
  }

  // 一つでもチェックを外すと「全て選択」のチェック外れる
  function DisChecked(){
    var checks = document.form.check;
    var checksCount = 0;
    for (var i=0; i<checks.length; i++){
      if(checks[i].checked == false){
        document.form.all.checked = false;
      }else{
        checksCount += 1;
        if(checksCount == checks.length){
          document.form.all.checked = true;
        }
      }
    }
  }
</script>

		</c:if>
	</div>
</div>

