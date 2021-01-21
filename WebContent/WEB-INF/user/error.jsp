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
        <h2>エラー</h2>
        <h2><%=session.getAttribute("errMessage")%></h2>
    </div>
</body>
</html>
