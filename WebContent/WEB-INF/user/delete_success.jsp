<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h2>削除完了</h2>
        <form action="UserServlet" method="post">
            <p>
                <button name="state" value="list">ユーザー一覧画面</button>
            </p>
        </form>
    </div>
</body>
</html>
