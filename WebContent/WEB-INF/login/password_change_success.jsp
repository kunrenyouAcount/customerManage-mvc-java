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
        <h2>パスワード変更完了</h2>
        <form action="LoginServlet" method="post">
            <p>
                <button name="state" value="top">メニュー画面</button>
            </p>
        </form>
    </div>
</body>
</html>
