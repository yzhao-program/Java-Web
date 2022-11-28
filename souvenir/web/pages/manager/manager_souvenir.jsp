<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Souvenir Management</title>

    <%-- include base tag, css, jquery --%>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {

            $("a.deleteClass").click(function () {
                return confirm("Are you sure to delete [" + $(this).parent().parent().find("td:first").text() + "] ?");
            });
        });
    </script>

</head>
<body>

<div id="header">
    <%@ include file="/pages/common/logo_image.jsp"%>
    <span class="wel_word">&nbsp;Souvenir</span>
    <%@include file="/pages/common/menu_manager.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>Name</td>
            <td>Price</td>
            <td>Sales</td>
            <td colspan="2">Operations</td>
        </tr>

        <c:forEach items="${requestScope.page.items}" var="souvenir">
            <tr>
                <td>${souvenir.name}</td>
                <td>${souvenir.price}</td>
                <td>${souvenir.sales}</td>
                <td><a href="manager/souvenirServlet?action=getSouvenir&id=${souvenir.id}&pageNo=${requestScope.page.pageNo}">Update</a></td>
                <td><a class="deleteClass" href="manager/souvenirServlet?action=delete&id=${souvenir.id}&pageNo=${requestScope.page.pageNo}">Delete</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td colspan="2"><a href="pages/manager/souvenir_edit.jsp?pageNo=${requestScope.page.pageTotal}">Add New Souvenir</a></td>
        </tr>
    </table>

    <%-- include page_nav --%>
    <%@include file="/pages/common/page_nav.jsp"%>

</div>

<%-- include footer --%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>
