<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>

    <%-- include base tag, css, jquery --%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {

            $("a.statusClass").click(function () {
                return confirm("Are you sure to change the status of the order (" + + $(this).parent().parent().find("td:first").text() +") ?");
            });
        });
    </script>

</head>
<body>

<div id="header">
    <%@ include file="/pages/common/logo_image.jsp"%>
    <span class="wel_word">&nbsp;Order</span>
    <%@include file="/pages/common/menu_manager.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td colspan="6">Status:&nbsp;&nbsp;&nbsp;0 - Processing,&nbsp;&nbsp;&nbsp;1 - Out of Delivery,&nbsp;&nbsp;&nbsp;2 - Delivered</td>
        </tr>
        <tr>
            <td colspan="6"></td>
        </tr>
        <tr>
            <td>Order Id</td>
            <td>Date</td>
            <td>Amount</td>
            <td>User Id</td>
            <td>Status</td>
            <td>Order Items</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.createTime}</td>
                <td>${order.price}</td>
                <td>${order.userId}</td>
                <td><a class="statusClass" href="manager/orderServlet?action=update&orderId=${order.orderId}&status=${order.status}&pageNo=${requestScope.page.pageNo}">${order.status}</a></td>
                <td><a href="manager/orderServlet?action=queryDetails&orderId=${order.orderId}&pageNo=${requestScope.page.pageNo}">Check Details</a></td>
            </tr>
        </c:forEach>
    </table>

    <%-- include page_nav --%>
    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<%-- include footer --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
