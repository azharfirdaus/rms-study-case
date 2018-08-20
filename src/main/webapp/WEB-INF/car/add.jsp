<%--
  Created by IntelliJ IDEA.
  User: Azhar
  Date: 18-Aug-18
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parking Car</title>
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Add New Car</h2>
        <form class="form-horizontal" action="/cars" method="POST">
            <div class="form-group">
                <label class="col-sm-2 control-label">Registration No</label>
                <div class="col-sm-10">
                    <input type="text" name= "registrationNo" class="form-control" placeholder="Registration No">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Colour</label>
                <div class="col-sm-10">
                    <input type="text" name="colour" class="form-control" placeholder="Colour">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Add</button>
                </div>
            </div>
        </form>
    </div>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
