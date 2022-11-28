<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Items</title>

    <%-- include base tag, css, jquery --%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>

<div id="header">
    <%@ include file="/pages/common/logo_image.jsp"%>
    <span class="wel_word">&nbsp;Order Items</span>
    <%@include file="/pages/common/menu_manager.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td colspan="3">Order Id: ${param.orderId}</td>
            <td><a href="manager/orderServlet?action=page&pageNo=${param.pageNo}">Back</a></td>
        </tr>
        <tr>
            <td>Name</td>
            <td>Quantity</td>
            <td>Unit Price</td>
            <td>Subtotal Price</td>
        </tr>
        <c:forEach items="${requestScope.orderItems}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
</div>

<%-- include footer --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
