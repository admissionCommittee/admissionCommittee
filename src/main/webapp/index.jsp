<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>

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
    <div id="logout_header"><a
            href="${pageContext.request.contextPath}/?lang=${'en' eq sessionScope['lang'] ? 'ru' : 'en'}"><fmt:message
            key="button_switchto"/> </a></div>
</div>

<div class="none"></div>

<div id="welcome"><h2><fmt:message key="welcome"/></h2></div>

<div id="login" style="margin:0 auto; width:380px; background-color:white" align="center">
    <form class="login_form" action="${pageContext.request.contextPath}/login" method="post" name="login_form">
        <ul>
            <li>
                <div class="diverror">${sessionScope['errLogin']}</div>
                <input type="email" name="login_email" value="${user.mail}"
                       placeholder="<fmt:message key="emailplaceholder"/>" autocomplete="on" required/>
            </li>
            <li>
                <input type="password" name="password" placeholder="<fmt:message key="passwordplaceholder"/>" required/>
            </li>
            <li>
                <button class="submit" type="submit"><fmt:message key="button_login"/></button>
            </li>
        </ul>
    </form>
</div>

<div class="none"></div>

<div id="register" style="margin:0 auto; width:380px; background-color:white" align="center">
    <form class="register_form" action="${pageContext.request.contextPath}/registration" method="post"
          name="register_form">
        <ul>
            <li>
                <h2><fmt:message key="title_singup"/></h2>
                <!--<span class="required_notification"> * Required Field </span>-->
            </li>

            <li>
                <fmt:message key="body_singup"/>
            </li>

            <li>
                <button class="submit" type="submit"><fmt:message key="button_singup"/></button>
            </li>
        </ul>
    </form>
</div>

</body>
</html>
