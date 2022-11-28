<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Souvenir</title>

    <%-- include base tag, css, jquery --%>
    <%@ include file="/pages/common/head.jsp"%>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>

<div id="header">
    <%@ include file="/pages/common/logo_image.jsp"%>
    <span class="wel_word">&nbsp;Edit Souvenir</span>
    <%@include file="/pages/common/menu_manager.jsp"%>
</div>

<div id="main">
    <form action="manager/souvenirServlet" method="get">
        <input type="hidden" name="pageNo" value="${param.pageNo}">
        <input type="hidden" name="action" value="${ empty requestScope.souvenir ? "add" : "update" }" />
        <input type="hidden" name="id" value="${ requestScope.souvenir.id }" />
        <table>
            <tr>
                <td>Name</td>
                <td>Price</td>
                <td>Sales</td>
                <td>Operations</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.souvenir.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.souvenir.price}"/></td>
                <td><input name="sales" type="text" value="${requestScope.souvenir.sales}"/></td>
                <td><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form>
</div>

<%-- include footer --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
