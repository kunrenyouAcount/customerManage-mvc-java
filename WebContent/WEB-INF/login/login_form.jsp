<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<title>ログイン</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>ログイン</h1>
    <div class="main">
        <form action="LoginServlet" method="post">
            <table>
                <tr>
                    <td class="title">ログイン名</td>
                    <td><input type="text" name="login" maxlength="20"></td>
                </tr>
                <tr>
                    <td class="title">パスワード</td>
                    <td><input type="password" name="password" maxlength="15"></td>
                </tr>
            </table>
            <p>
                <button name="state" value="login_process">ログイン</button>
            </p>
        </form>
    </div>
</body>
</html>
