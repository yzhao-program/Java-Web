<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--beginning of the page navigation--%>
<div id="page_nav">
    <%--larger than first page: 1--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${ requestScope.page.url }&pageNo=1">First</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo-1}">Previous</a>
    </c:if>
    <%--Find Page Numbers--%>
    <c:choose>
        <%--Condition 1: pageTotal <= 5. range: 1 - pageTotal--%>
        <c:when test="${ requestScope.page.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--Condition2: PageTotal > 5--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--Condition2.1: pageNo = 1, 2, 3. range: 1 - 5.--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--Condition2.2: pageNo = pageTotal-2, pageTotal-1, pageTotal. range: pageTotal-4 - pageTotal--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--Condition2.3: general situation. range: pageTotal-2 - pageTotal+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--Display Page Numbers--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
             [${i}]
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--smaller than last page: pageTotal --%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageNo+1}">Next</a>
        <a href="${ requestScope.page.url }&pageNo=${requestScope.page.pageTotal}">Last</a>
    </c:if>

    &nbsp;&nbsp;&nbsp;Total: ${ requestScope.page.pageTotal } page(s), ${ requestScope.page.pageTotalCount } item(s)
    Go to Page <input value="${param.pageNo}" name="pn" id="pn_input"/>
    <input id="searchPageBtn" type="button" value="Go">

    <script type="text/javascript">

        $(function () {
            // Go to the specific page
            $("#searchPageBtn").click(function () {

                let pageNo = $("#pn_input").val();

                <%--let pageTotal = ${requestScope.page.pageTotal};--%>
                <%--alert(pageTotal);--%>

                location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;

            });
        });

    </script>

</div>
<%--end of the page navigation--%>
