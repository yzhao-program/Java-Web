<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--does not login--%>
<c:if test="${empty sessionScope.user}">
    <%@ include file="/pages/common/menu_register_login.jsp"%>
</c:if>
<%--login successfully--%>
<c:if test="${not empty sessionScope.user}">
    <%@ include file="/pages/common/menu_login_success.jsp"%>
</c:if>
