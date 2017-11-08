<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}" />
<fmt:setBundle basename="localization"/>

<html>
<head>
  <meta charset="utf-8">
  <title><fmt:message key="welcome"/></title>
  <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}css/styles.css" >
  <link rel="shortcut icon" href="${pageContext.request.contextPath}favicon.ico"/>
</head>
<body bgcolor=#edeef0>

</body>
</html>
