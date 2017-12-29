<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ptags" uri="http://web.ru/taglibs" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>
<jsp:useBean id="sheet" class="com.github.admissionCommittee.model.Sheet" scope="request"/>
<jsp:useBean id="faculty" class="com.github.admissionCommittee.model.Faculty" scope="request"/>
<jsp:useBean id="listSheets" type="java.util.List<com.github.admissionCommittee.model.Sheet>" scope="request"/>
<jsp:useBean id="listFaculty" type="java.util.List" scope="request"/>
<jsp:useBean id="now" type="java.time.LocalDate" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>

<div id="infopanel">
    <div id="left-infopanel">
        <div id="avatarcontainter">
            <div id="bavatar" style="background-image:url('${pageContext.request.contextPath}/img/admin.jpg')"></div>
        </div>
    </div>

    <div class="div-space"><!-- blank --></div>

    <div id="right-infopanel">
        <div>
            <dl>
                <dt> &nbsp</dt>
                <dd><h2>${user.lastName}</h2></dd>
                <br>
                <dt><fmt:message key="profile.firstname"/>:</dt>
                <dd>${user.firstName}</dd>
                <br>
                <dt><fmt:message key="profile.middlename"/>:</dt>
                <dd>${user.patronymic}</dd>
                <br>
                <dt><fmt:message key="profile.birthDate"/>:</dt>
                <dd><ptags:FormatLocalDate date="${user.birthDate}"/></dd>
                <br>
                <dt> &nbsp</dt>
                <dd>${user.mail}</dd>
            </dl>
        </div>

        <div class="div-none-small"><!-- blank --></div>

        <fmt:message key="profile.edit" var="editprofile"/>
        <div><ptags:Button caption="${editprofile}" url="/profile"/></div>
    </div>
</div>


<div id="faculty-info">
    <form method="get">
        <label for="faculty"><fmt:message key="admin.select"/>:</label>
        <select id="faculty" name="faculty">
            <ptags:SelectFromList list="${listFaculty}"/>
        </select>
        </p>
        <p>
            <button class="submit" type="submit"><fmt:message key="key_scheet"/></button>
        </p>
    </form>

    <c:if test="${not empty listSheets}">
    <table width=100%>
        <caption><fmt:message key="admin.shetinfo"/><b> ${faculty.name}
            ( <fmt:message key="admin.shetinfo.limit"/> ${faculty.peopleLimit}
            <fmt:message key="admin.shetinfo.submit"/> <ptags:FormatLocalDate date="${now}"/> )</b></caption>
        <tr>
            <th><b><fmt:message key="admin.shetinfo.fio"/></b></th>
            <th><b><fmt:message key="admin.shetinfo.ege"/></b></th>
            <th><b><fmt:message key="admin.shetinfo.cettificate"/></b></th>
        </tr>
        <c:forEach items="${listSheets}" var="sheet">
            <tr>
                <td> ${sheet.user.lastName} ${sheet.user.firstName} ${sheet.user.patronymic}</td>
                <td> ${sheet.sumExamCertificateScore}</td>
                <td> ${sheet.user.schoolCertificate.averageScore}</td>
            </tr>
        </c:forEach>

    </table>

    <c:set var="totalPages" value='${requestScope["totalPages"]}'/>
    <c:if test="${totalPages > 1}">
    <p>
            <fmt:message key="admin.shetinfo.page"/> ${param.page == null ? "1" : ""} ${param.page}
            <fmt:message key="admin.shetinfo.of"/> ${totalPages}
        <c:forEach var="i" begin="1" end="${totalPages}">
        | <a href="?faculty=${faculty.id}&page=${i}"> ${i} </a>
        </c:forEach>
        |
        </c:if>

    <p><a href=""><img src="${pageContext.request.contextPath}/img/print.png" width="30px"></a>
        </c:if>

</div>
<jsp:include page="template/footer.jsp"/>