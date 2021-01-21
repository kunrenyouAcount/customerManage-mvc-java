<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>ユーザー管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>パスワード変更</h1>
    <div class="main">
        <form name="form1" action="PasswordChangeServlet" method="post"
            onsubmit="return funcConfirmPassword()">
            <table>
                <tr>
                    <td class="title">旧パスワード</td>
                    <td><input type="password" name="old_password" maxlength="15"></td>
                </tr>
                <tr>
                    <td class="title">新パスワード</td>
                    <td><input type="password" name="new_password1" maxlength="15"></td>
                </tr>
                <tr>
                    <td class="title">新パスワード（確認用）</td>
                    <td><input type="password" name="new_password2" maxlength="15"></td>
                </tr>
            </table>
            <p>
                <button name="state" value="password_change_process">送信</button>
                <input type="button" value="戻る" onclick="history.back()">
            </p>
        </form>
    </div>
</body>
<script type="text/javascript">
    function funcConfirmPassword() {
        if (!document.form1.new_password1.value.match(/^[\x20-\x7E]+$/)) {
            alert("パスワードは半角英数字と記号で入力してください");
            return false;
        }
        if (document.form1.new_password1.value != document.form1.new_password2.value) {
            alert("確認用のパスワードが一致しません");
            return false;
        }
    }
</script>
</html>
