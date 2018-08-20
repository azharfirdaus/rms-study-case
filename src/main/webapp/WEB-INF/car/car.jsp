<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Parking Car</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <table class="table">
        <tbody>
            <tr>
                <td>ID</td>
                <td>${car.id}</td>
            </tr>
            <tr>
                <td>Registration No</td>
                <td>${car.registrationNo}</td>
            </tr>
            <tr>
                <td>Colour</td>
                <td>${car.colour}</td>
            </tr>
            <tr>
                <td></td>
                <td><a class="btn btn-link" href="http://localhost:8080/cars">Show All</a></td>
            </tr>
        </tbody>
    </table>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>