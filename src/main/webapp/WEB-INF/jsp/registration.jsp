<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>


<form class="register_form" action="${pageContext.request.contextPath}/registration?save" method="post"
      name="register_form">
    <ul>
        <li>
            <h2><fmt:message key="button_singup"/></h2>
        </li>
        <li>
            <div class="diverror">${sessionScope['errEditProfile']}</div>
            <input type="email" name="regEmail" value="${user.mail}"
                   placeholder="<fmt:message key="profile.email"/>" autocomplete="on" required/>
        </li>
        <li>
            <input type="password" name="regPassword"
                   placeholder="<fmt:message key="profile.password"/>" required/>
        </li>

        <li>
            <input type="text" name="regLastName" value="${user.lastName}"
                   placeholder="<fmt:message key="profile.lastname"/>" required/>
        </li>

        <li>
            <input type="text" name="regFirstName" value="${user.firstName}"
                   placeholder="<fmt:message key="profile.firstname"/>" required/>
        </li>

        <li>
            <input type="text" name="regMiddleName" value="${user.patronymic}"
                   placeholder="<fmt:message key="profile.middlename"/>" required/>
        </li>


        <li>
            <input type="date" name="regBirthDate" value="${user.birthDate}"
                   placeholder="<fmt:message key="profile.birthDate"/>" required/>
        </li>

        <li>
            <button class="submit" type="submit"><fmt:message key="key_save"/></button>
        </li>
    </ul>
</form>

<jsp:include page="template/footer.jsp"/>
