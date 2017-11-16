<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ptags" uri="http://web.ru/taglibs" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>
<jsp:useBean id="certificate" class="com.github.admissionCommittee.model.SchoolCertificate" scope="request"/>

<jsp:useBean id="listSubjects" type="java.util.List<com.github.admissionCommittee.model.Subject>" scope="request"/>
<jsp:useBean id="mapSubjectsScores" type="java.util.Map<com.github.admissionCommittee.model.Subject,java.lang.Integer>" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>

<div id="certificate-info">

    <form class="register_form" action="${pageContext.request.contextPath}/certificate?save=1" method="post"
          name="register_form">
        <ul>
            <li>
                <h2><fmt:message key="profile.certificateinfo"/></h2>
            </li>
            <li>
                <div class="diverror">${sessionScope['errCertificate']}</div>
                <fmt:message key="certificate.year"/> <input type="number" name="year" value="${certificate.year}"
                       class="score" placeholder="ХХХХ" required/>
            </li>
        </ul>

        <table width=80% align=center>
            <tr>
                <td><b><fmt:message key="certificate.subjects"/></b></td>
                <td><b><fmt:message key="certificate.mark"/></b></td>
            </tr>
            <c:forEach items="${listSubjects}" var="subject" >
                <tr>
                    <td> ${subject.name}</td>
                    <td><input type="number" class="score" name="sub_${subject.id}" value="${mapSubjectsScores.get(subject)}" required/> </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <button class="submit" type="submit"><fmt:message key="key_save"/></button>
            <br>
    </form>
</div>

<jsp:include page="template/footer.jsp"/>
