<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ptags" uri="http://web.ru/taglibs" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>
<jsp:useBean id="certificate" class="com.github.admissionCommittee.model.SchoolCertificate" scope="request"/>
<jsp:useBean id="sheet" class="com.github.admissionCommittee.model.Sheet" scope="request"/>
<jsp:useBean id="listFaculty" type="java.util.List" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>

<div id="infopanel">
    <div id="left-infopanel">
        <div id="avatarcontainter">
            <div id="bavatar" style="background-image:url('${pageContext.request.contextPath}/img/obuchenie.jpg')"></div>
        </div>
    </div>

    <div class="div-space"><!-- blank --></div>

    <div id="right-infopanel">
        <div>
            <dl>
                <dt> &nbsp</dt><dd><h2>${user.lastName}</h2></dd>
                <br>
                <dt><fmt:message key="profile.firstname"/>:</dt> <dd>${user.firstName}</dd>
                <br>
                <dt><fmt:message key="profile.middlename"/>:</dt> <dd>${user.patronymic}</dd>
                <br>
                <dt><fmt:message key="profile.birthDate"/>:</dt> <dd><ptags:FormatLocalDate date="${user.birthDate}"/></dd>
                <br>
                <dt> &nbsp</dt><dd>${user.mail}</dd>
            </dl>
        </div>

        <div class="div-none-small"><!-- blank --></div>

        <div><a href="profile"><fmt:message key="profile.edit"/></a> </div>
    </div>
</div>

<div id="certificate-info">

    <fmt:message key="profile.certificateinfo"/><br>
    <p>
    <table width="100%" border=1>
        <tr>
            <!--<td></td>-->
            <td><fmt:message key="certificate.year"/></td>
            <td><fmt:message key="certificate.averagescore"/></td>
        </tr>

        <tr>
            <!--<td></td>-->
            <td>${certificate.year}</td>
            <td>${sheet.averageSchoolCertificateScore}</td>
            <!--<td></td>-->
        </tr>
    </table>

    <a href="certificate"><fmt:message key="certificate.edit"/></a>
</div>

<div id="faculty-info">
    <form method="get">
        <label for="facylty">Для подачи документов выберите факультет:</label>
        <select id="facylty" name="facylty">
                <ptags:SelectFromList list="${listFaculty}"/>
        </select>
        </p>
        <p>
            <input type="submit" value="Отправить" />
        </p>
    </form>
</div>

<jsp:include page="template/footer.jsp"/>