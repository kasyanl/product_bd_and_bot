<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Система учета продуктов</title>
    <style>
        body {
            background-color: #CCFFCC;
            background-image: url(${pageContext.request.contextPath}/download/xls/bg.jpg);
        }
    </style>
</head>
<body align="center">
<%@include file="../header.jsp"%>
<%@include file="navguest.jsp"%>
<h1>Выберите категориию:</h1>
<div face="arial" size="+1">
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/FRUITS">FRUITS</a>
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/BERRIES">BERRIES</a>
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/VEGETABLES">VEGETABLES</a>
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/MILK_PRODUCT">MILK_PRODUCT</a>
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/MEAT">MEAT</a>
    <li><a href="${pageContext.request.contextPath}/product/finefruitsguest/ALCOHOLIC_BEVERAGES">ALCOHOLIC_BEVERAGES</a>
</div>
<br>
<br>
<a href="${pageContext.request.contextPath}/contentguest">Back</a>
<br>
<br>
<%@include file="../footer.jsp"%>
<br>
<br>
</body>
</html>