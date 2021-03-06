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
                <th>Action</th>
            </tr>

            <!-- loop over and print users -->
            <c:forEach var="tempUser" items="${users}">

                <!-- construct "update" link with user id -->
                <c:url var="updateLink" value="/users/showFormForUpdate">
                    <c:param name="userId" value="${tempUser.id}"/>
                </c:url>

                <!-- construct "delete" link with user id -->
                <c:url var="deleteLink" value="/users/delete">
                    <c:param name="userId" value="${tempUser.id}"/>
                </c:url>

                <tr>
                    <td> ${tempUser.firstName} </td>
                    <td> ${tempUser.lastName} </td>
                    <td> ${tempUser.email} </td>

                    <td>
                        <!-- display update link -->
                        <a href="${updateLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
                    </td>

                </tr>

            </c:forEach>

        </table>

    </div>

</div>

</body>

</html>