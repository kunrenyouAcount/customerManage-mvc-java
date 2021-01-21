<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>セッション・エラー</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>セッション・エラー</h1>
    <div class="main">
        <h2>セッション無効</h2>
        <h2>ログインしなおしてください。</h2>
        <p>
            <a href="LoginServlet">ログイン</a>
        </p>
    </div>
</body>
</html>
