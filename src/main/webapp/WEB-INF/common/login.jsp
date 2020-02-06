<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="view"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title><fmt:message key="login.page"/></title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
</head>
<header>
    <div class="bg-light">
    <jsp:include page="header.jsp"/>
    </div>
</header>
<body >

<div id="layoutAuthentication " class="shadow-sm p-3 mb-5 bg-white rounded">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4"><fmt:message key="login"/></h3></div>
                            <div class="card-body">
                                <form>
                                    <div class="form-group"><label class="small mb-1" for="inputEmailAddress"><fmt:message key="email"/></label><input class="form-control py-4" id="inputEmailAddress" type="email" placeholder=<fmt:message key="input.email"/> /></div>
                                    <div class="form-group"><label class="small mb-1" for="inputPassword"><fmt:message key="password"/></label><input class="form-control py-4" id="inputPassword" type="password" placeholder=<fmt:message key="input.password"/> /></div>
                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0"><a class="btn btn-primary" href="index.html"><fmt:message key="login"/></a></div>
                                </form>
                            </div>
                            <div class="card-footer text-center">
                                <div class="small"><a href="${pageContext.request.contextPath}/app/reg-me"><fmt:message key="register.link"/></a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>