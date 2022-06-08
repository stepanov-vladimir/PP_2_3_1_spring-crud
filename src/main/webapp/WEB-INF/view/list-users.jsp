<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
    <title>List Users</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2>Users List</h2>
        </div>
    </div>

    <div id="container">

        <div id="content">

            <input type="button" value="Add User"
                onclick="window.location.href='showFormForAdd'; return false;"
                   class="add-button"
            />

        <!-- add html table -->
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>

                <!-- loop over and print users -->
                <c:forEach var="tempUser" items="${users}">

                    <tr>
                        <td> ${tempUser.firstName} </td>
                        <td> ${tempUser.lastName} </td>
                        <td> ${tempUser.email} </td>
                    </tr>

                </c:forEach>

            </table>

        </div>

    </div>

</body>

</html>