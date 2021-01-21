<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/javascript; charset=UTF-8">
<title>ユーザー管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>ユーザー管理</h1>
    <div class="main">
        <h2>新規登録</h2>
        <form name="form1" action="UserServlet" method="post"
            onsubmit="return funcConfirm()">
            <table>
                <tr>
                    <td class="title">ログイン名</td>
                    <td><input type="text" name="login" maxlength="20"></td>
                </tr>
                <tr>
                    <td class="title">氏名</td>
                    <td><input type="text" name="user_name" maxlength="20"></td>
                </tr>
                <tr>
                    <td class="title">ユーザーレベル</td>
                    <td><select name="user_level">
                            <option value="0" selected>一般ユーザー
                            <option value="1">顧客管理者
                            <option value="2">システム管理者
                    </select></td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td><input type="password" name="password1" maxlength="15"></td>
                </tr>
                <tr>
                    <td class="title">パスワード（確認用）</td>
                    <td><input type="password" name="password2" maxlength="15"></td>
                </tr>
            </table>
            <p>
                <!--
                <input type="submit" value="送信">
             -->
                <button name="state" value="add">送信</button>
                <input type="button" value="戻る" onclick="history.back()">
            </p>
        </form>
    </div>
</body>
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
    </script>
</html>
