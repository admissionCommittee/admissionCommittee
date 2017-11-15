<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ptags" uri="http://web.ru/taglibs" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>
<jsp:useBean id="certificate" class="com.github.admissionCommittee.model.ExamCertificate" scope="request"/>
<jsp:useBean id="faculty" class="com.github.admissionCommittee.model.Faculty" scope="request"/>
<jsp:useBean id="listSubjects" type="java.util.List<com.github.admissionCommittee.model.Subject>" scope="request"/>
<jsp:useBean id="mapSubjectsScores" type="java.util.Map<com.github.admissionCommittee.model.Subject,java.lang.Integer>" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>

<div id="certificate-info">

    <form class="login_form" action="${pageContext.request.contextPath}/examinations?save=1" method="post"
          name="register_form">
        <input type="hidden" name="faculty" value="${faculty.id}"/>
        <ul>
            <li>
                <h2><fmt:message key="exam.certificateinfo"/><br> <fmt:message key="exam.faculty"/> ${faculty.name}</h2>
            </li>
            <li>
                <div class="diverror">${sessionScope['errCertificate']}</div>
                <fmt:message key="exam.year"/> <input type="number" name="year" value="${certificate.year}"
                       class="score" placeholder="ХХХХ" required/>
            </li>
        </ul>

        <table width=80% align=center>
            <tr><td><b>Предметы</b></td> <td><b>Баллы</b></td></tr>

            <c:forEach items="${listSubjects}" var="subject" >
                <tr><td> ${subject.name}</td> <td><input type="number" class="score" name="sub_${subject.id}" value="${mapSubjectsScores.get(subject)}" required/> </td></tr>
            </c:forEach>
        </table>
        <p>
            <button class="submit" type="submit">подать на поступление</button>
            <br>
    </form>
</div>

<jsp:include page="template/footer.jsp"/>
