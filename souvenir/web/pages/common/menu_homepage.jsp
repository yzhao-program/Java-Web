<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div>
    <%--does not login--%>
    <c:if test="${empty sessionScope.user}">
        <%@ include file="/pages/common/link_login.jsp"%>
        <%@ include file="/pages/common/link_register.jsp"%>
    </c:if>
    <%--login successfully--%>
    <c:if test="${not empty sessionScope.user}">
        <%@ include file="/pages/common/welcome_user.jsp"%>
        <%@ include file="/pages/common/link_myorder.jsp"%>
        <%@ include file="/pages/common/link_logout.jsp"%>
    </c:if>
        <%@ include file="/pages/common/link_cart.jsp"%>
        <%@ include file="/pages/common/link_admin.jsp"%>
</div>
