<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<jsp:useBean class="user.UserBean" id="userEdit" scope="session" />
<!doctype html>
<html>
<head>
<title>ユーザー管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>ユーザー管理</h1>
    <div class="main">
        <h2>既存データの編集･削除</h2>
        <form name="form1" action="UserServlet" method="post"
            onsubmit="return funcConfirm()">
            <table>
                <tr>
                    <td>ID</td>
                    <td><%=userEdit.getId()%></td>
                </tr>
                <tr>
                    <td class="title">ログイン名</td>
                    <td><input type="text" name="login" maxlength="15"
                        value="<%=userEdit.getLogin()%>"></td>
                </tr>
                <tr>
                    <td class="title">氏名</td>
                    <td><input type="text" name="user_name" maxlength="20"
                        value="<%=userEdit.getName()%>"></td>
                </tr>
                <tr>
                    <td class="title">ユーザーレベル</td>
                    <td><select name="user_level">
                            <option value="0" <%if (userEdit.getLvl() == 0) {%> selected
                                <%}%>>一般ユーザー
                            <option value="1" <%if (userEdit.getLvl() == 1) {%> selected
                                <%}%>>顧客管理者
                            <option value="2" <%if (userEdit.getLvl() == 2) {%> selected
                                <%}%>>システム管理者
                    </select></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td colspan="2"><input type="checkbox" name="password_change"
                        onclick="funcPasswordChange()"> パスワードを変更</td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td><input type="password" name="password1" maxlength="20"
                        value="********" disabled></td>
                </tr>
                <tr>
                    <td class="title">パスワード（確認用）</td>
                    <td><input type="password" name="password2" maxlength="20"
                        value="********" disabled></td>
                </tr>
            </table>
            <p>
                <button name="state" value="update">送信</button>
                <input type="button" value="戻る" onclick="history.back()">
                <input type="button" value="削除" onclick="funcDelete()">
            </p>
        </form>
    </div>
    <script type="text/javascript">
        function funcConfirm() {
            if (!document.form1.login.value.match(/^[a-zA-Z0-9]+$/)) {
                alert("ログイン名を半角英数字で入力してください");
                return false;
            }
            if (document.form1.user_name.value == "") {
                alert("氏名が入力されていません。");
                return false;
            }
            if (!document.form1.password1.value.match(/^[\x20-\x7E]+$/)) {
                alert("パスワードは半角英数字と記号で入力してください");
                return false;
            }
            if (document.form1.password1.value != document.form1.password2.value) {
                alert("確認用のパスワードが一致しません");
                return false;
            }
        }
        function funcPasswordChange() {
            if (document.form1.password_change.checked == true) {
                document.form1.password1.disabled = false;
                document.form1.password1.value = "";
                document.form1.password2.disabled = false;
                document.form1.password2.value = "";
            } else {
                document.form1.password1.disabled = true;
                document.form1.password1.value = "********";
                document.form1.password2.disabled = true;
                document.form1.password2.value = "********";
            }
        }
        function funcDelete() {
            if (confirm("削除します。よろしいですか？")) {
                var form = document.createElement("form");
                form.setAttribute("action", "UserServlet");
                form.setAttribute("method", "post");
                form.style.display = "none";
                document.body.appendChild(form);
                var input = document.createElement("input");
                input.setAttribute("type","hidden");
                input.setAttribute("name", "state");
                input.setAttribute("value", "delete");
                form.appendChild(input);
                form.submit();
            }
        }
    </script>
</body>
</html>
