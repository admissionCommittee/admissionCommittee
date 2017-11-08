<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}" />
<fmt:setBundle basename="localization"/>

<html>
  <head>
  <meta charset="utf-8">
      <title><fmt:message key="welcome"/></title>
  </head>

  <body>
  <fmt:message key="registration"/>
  </body>
</html>
