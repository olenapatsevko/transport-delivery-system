<%--
  Created by IntelliJ IDEA.
  User: olena
  Date: 03-Feb-20
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- LINEARICONS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/linearicons/style.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<div class="wrapper">
    <div class="inner">
        <img src="images/image-1.png" alt="" class="image-1">
        <form action="/register" method="post">
            <h3>New Account?</h3>

            <div class="form-holder">
                <span class="lnr lnr-user"></span>
                <input type="text" name="name" class="form-control" placeholder="Name">
            </div>

            <div class="form-holder">
                <span class="lnr lnr-user"></span>
                <input type="text" name="surname" class="form-control" placeholder="Surname">
            </div>

            <div class="form-holder">
                <span class="lnr lnr-phone-handset"></span>
                <input type="text" name="phone" class="form-control" placeholder="Phone Number">
            </div>

            <div class="form-holder">
                <span class="lnr lnr-envelope"></span>
                <input type="text" name="email" class="form-control" placeholder="Mail">
            </div>

            <div class="form-holder">
                <span class="lnr lnr-lock"></span>
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>

            <div class="form-holder">
                <span class="lnr lnr-lock"></span>
                <input type="password" name="confirm_password" class="form-control" placeholder="Confirm Password">
            </div>
            <button>

                <span>Register</span>
            </button>
        </form>
        <img src="images/image-2.png" alt="" class="image-2">
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
