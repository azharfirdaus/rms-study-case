<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parking Car</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Id</th>
            <th scope="col">Registration No</th>
            <th scope="col">Colour</th>
        </tr>
        </thead>
        <tbody>
            <% int i = 0; %>
            <c:forEach items="${cars}" var="car">
                <tr>
                    <th scope="row"><%=++i%></th>
                    <td>${car.id}</td>
                    <td>${car.registrationNo}</td>
                    <td>${car.colour}</td>

                    <c:set var="linkForUpdate" value="http://localhost:8080/cars/${car.id}?method=put" />
                    <c:set var="linkForDelete" value="http://localhost:8080/cars/${car.id}"/>

                    <td><a class="btn btn-warning" href=${linkForUpdate} role="button">Update</a></td>
                    <%--<td><a class="btn btn-danger" href=${linkForDelete} role="button">Delete</a></td>--%>

                    <td>
                        <form class="form-horizontal" method="POST" action=${linkForDelete} >
                            <input type="hidden" id="method" name="method" value="delete">
                            <button type="submit" class="btn btn-primary">Delete</button>
                        </form>
                    </tr>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>