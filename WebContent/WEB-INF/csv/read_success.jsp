<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="customer.*"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<!doctype html>
<html>
<head>
<title>顧客管理（CSV）</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>顧客管理（CSV）</h1>
    <div class="main">
        <h2>一括処理完了</h2>
        <form action="CustomerCsvServlet" method="post">
            <p>
                <button name="state" value="customer_csv">CSV操作画面</button>
            </p>
        </form>
    </div>
</body>
</html>
