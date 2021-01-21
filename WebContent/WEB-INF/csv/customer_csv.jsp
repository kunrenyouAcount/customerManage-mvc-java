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
        <h2>CSV操作</h2>
        <form name="form1" action="CustomerCsvServlet" method="post"
            enctype="multipart/form-data"
            onsubmit="return funcConfirm1()">
            <p>
                <input type="submit" value="一括処理"/>
                <input type="hidden" name="state" value="read" />
                <input type="file" name="file">
            </p>
        </form>
        <form name="form2" action="CustomerCsvServlet" method="post"
            onsubmit="return funcConfirm2()">
            <p>
                <button name="state" value="write">エクスポート</button>
            </p>
        </form>
        <form action="LoginServlet" method="post">
            <p>
                <button name="state" value="top">メニュー画面</button>
            </p>
        </form>
    </div>
</body>

<script type="text/javascript">
    function funcConfirm1() {
        if (document.form1.file.value == "") {
            alert("一括処理のファイルが選択されていません。");
            return false;
        }
    }
</script>
</html>
