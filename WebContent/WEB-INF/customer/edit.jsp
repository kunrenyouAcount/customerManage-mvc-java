<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<jsp:useBean class="customer.CustomerBean" id="customer" scope="session" />
<%@ page import="customer.CustomerBean" %>
<% CustomerBean cb = (CustomerBean) session.getAttribute("customer"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>顧客管理</h1>
    <div class="main">
        <h2>既存データの編集</h2>
        <form name="form1" action="CustomerServlet" method="post"
            onsubmit="return funcConfirm()">
            <table>
                <tr>
                    <td class="title">氏名</td>
                    <td><input type="text" name="name" maxlength="20" value="<%= cb.getName() %>"></td>
                </tr>
                <tr>
                    <td class="title">郵便番号</td>
                    <td><input type="text" name="zip" maxlength="20" value="<%= cb.getZip() %>"></td>
                </tr>
                <tr>
                    <td class="title">住所1</td>
                    <td><input type="text" name="address1" maxlength="100" value="<%= cb.getAddress1() %>"></td>
                </tr>
                <tr>
                    <td class="title">住所2</td>
                    <td><input type="text" name="address2" maxlength="100" value="<%= cb.getAddress2() %>"></td>
                </tr>
                <tr>
                    <td class="title">TEL</td>
                    <td><input type="text" name="tel" maxlength="20" value="<%= cb.getTel() %>"></td>
                </tr>
                <tr>
                    <td class="title">FAX</td>
                    <td><input type="text" name="fax" maxlength="20" value="<%= cb.getFax() %>"></td>
                </tr>
                <tr>
                    <td class="title">E-mail</td>
                    <td><input type="text" name="email" maxlength="100" value="<%= cb.getEmail() %>"></td>
                </tr>
            </table>
            <p>
                <button name="state" value="edit_confirm">送信</button>
                <input type="button" value="戻る" onclick="history.back()">
            </p>
        </form>
    </div>
</body>
<script type="text/javascript">
    function funcConfirm() {
        if (document.form1.name.value == "") {
            alert("氏名が入力されていません。");
            return false;
        }
        if (!/^[a-zA-Zａ-ｚＡ-Ｚ\u3040-\u309f\u30a0-\u30ff\uff65-\uff9f\u4E00-\u9FFF]+$/.test(document.form1.name.value)) {
            alert("正しい名前の値を入力してください");
            return false;
        }
        if (document.form1.zip.value == "") {
            alert("郵便番号が入力されていません。");
            return false;
        }
        if (!/^[0-9]{3}-[0-9]{4}$/.test(document.form1.zip.value)) {
            alert("ハイフンを含んだ正しい郵便番号の値を入力してください。");
            return false;
        }
        if (document.form1.address1.value == "") {
            alert("住所が入力されていません。");
            return false;
        }
        if (document.form1.tel.value == "") {
            alert("TELが入力されていません。");
            return false;
        }
        if (!/^0\d{1,3}-\d{1,4}-\d{4}$|^(070|080|090)-\d{4}-\d{4}$/.test(document.form1.tel.value)) {
            alert("ハイフンを含んだ正しいTELの値を入力してください。")
            return false;
        }
        if (!(document.form1.fax.value == "")) {
            if (!/^0\d{1,3}-\d{1,4}-\d{4}$|^(070|080|090)-\d{4}-\d{4}$/.test(document.form1.fax.value)) {
                alert("ハイフンを含んだ正しいFAXの値を入力してください。")
                return false;
            }
        }
        if (!/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/.test(document.form1.email.value)) {
            alert("正しいE-mailの値を入力してください。");
            return false;
        }
    }
</script>
</html>