<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Parking Car</title>
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Update Car</h2>

        <c:set var="registrationNo" value="${car.registrationNo}" />
        <c:set var="colour" value="${car.colour}" />
        <c:set var="actionForm" value="/cars/${car.id}"/>

        <form class="form-horizontal" method="POST" action=${actionForm} >
            <div class="form-group">
                <label class="col-sm-2 control-label">Registration No</label>
                <div class="col-sm-10">
                    <input type="text" name= "registrationNo" value="${registrationNo}" class="form-control" placeholder="Registration No">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Colour</label>
                <div class="col-sm-10">
                    <input type="text" name="colour" value="${colour}" class="form-control" placeholder="Colour">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Update</button>
                </div>
            </div>
        </form>
    </div>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
