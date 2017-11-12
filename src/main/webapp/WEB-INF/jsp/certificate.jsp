<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ptags" uri="http://web.ru/taglibs" %>

<jsp:useBean id="user" class="com.github.admissionCommittee.model.User" scope="request"/>
<jsp:useBean id="certificate" class="com.github.admissionCommittee.model.SchoolCertificate" scope="request"/>

<fmt:setLocale value="${not empty sessionScope['lang'] ? sessionScope['lang'] : 'Ru'}"/>
<fmt:setBundle basename="localization"/>

<jsp:include page="template/header.jsp"/>

<div id="certificate-info">

    <form class="register_form" action="${pageContext.request.contextPath}/certificate?save" method="post"
          name="register_form">
        <ul>
            <li>
                <h2>Сведения об аттестате</h2>
            </li>
            <li>
                <div class="diverror">Ошибки</div>
                Год получения <input type="number" name="regEmail" value=""
                       class="score" placeholder="ХХХХ" required/>
            </li>


        </ul>
        <table width=80% align=center>
            <tr><td><b>Предметы</b></td> <td><b>Оценка</b></td></tr>

            <tr><td> Математика </td> <td> <input type="number" class="score" name="d1" required/> </td></tr>

            <tr><td> Физика </td> <td> <input type="number" class="score" name="d1" required/> </td></tr>
        </table>
        <p>
            <button class="submit" type="submit">Сохранить</button>
            <br>
    </form>
</div>

<jsp:include page="template/footer.jsp"/>
