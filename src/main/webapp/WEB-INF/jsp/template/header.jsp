<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="welcome"/></title>
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
</head>
<body>

<div id="header">
    <div id="left_header"></div>
    <div id="center_header"></div>
    <div id="logout_header"><a href="/logout"><fmt:message key="logout"/> </a></div>
</div>

<div class="none"></div>

<div id="contentconteiner">
