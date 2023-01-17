<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agent</title>
    </head>
    <body>
        ${directories}
        <p><b>Iterated List:</b><p>
        <ol>
            <c:forEach var="dir" items="${directories}">
                <li>${dir}</li>
            </c:forEach>
        </ol>


        </form>
    </body>
</html>