<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="view"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title><fmt:message key="register.page"/></title>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
</head>
<header>
    <div class="bg-light">
        <jsp:include page="header.jsp"/>
    </div>
</header>
<body>
<div id="layoutAuthentication" class="shadow-sm p-3 mb-5 bg-white rounded">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4"><fmt:message key="create.account"/></h3></div>
                            <div class="card-body">
                                <form method="post" action="${pageContext.request.contextPath}/app/registration">

                                    <c:if test="${param.dataInvalid == true}">
                                        <p style="color: orange"><fmt:message key="invalid.input"/></p>
                                    </c:if>
                                    <c:if test="${param.success == true}">
                                        <p style="color: green"><fmt:message key="reg.success"/></p>
                                    </c:if>
                                    <c:if test="${param.alreadyExist == true}">
                                        <p style="color: darkred"><fmt:message key="user.exist"/></p>
                                    </c:if>
                                    <div class="form-row">

                                        <div class="col-md-6">
                                            <div class="form-group"><label class="small mb-1" for="firstName"><fmt:message key="first.name"/></label><input class="form-control py-4" id="firstName" name="firstName" type="text" placeholder="<fmt:message key="enter"/> <fmt:message key="first.name"/>" /></div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group"><label class="small mb-1" for="lastName"><fmt:message key="last.name"/></label><input class="form-control py-4" id="lastName" name="lastName" type="text" placeholder="<fmt:message key="enter"/> <fmt:message key="last.name"/>" /></div>
                                        </div>

                                    </div>
                                    <div class="form-group"><label class="small mb-1" for="email"><fmt:message key="email"/></label><input class="form-control py-4" id="email" name="email" type="email" aria-describedby="emailHelp" placeholder="<fmt:message key="enter"/> <fmt:message key="email"/>" /></div>
                                    <div class="form-row">

                                        <div class="col-md-6">
                                            <div class="form-group"><label class="small mb-1" for="password"><fmt:message key="password"/></label><input class="form-control py-4" id="password" name="password" type="password" placeholder="<fmt:message key="enter"/> <fmt:message key="password"/>" /></div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-group"><label class="small mb-1" for="phone"><fmt:message key="phone"/></label><input class="form-control py-4" id="phone" name="phone" type="number" min="11" placeholder="<fmt:message key="phone"/>" /></div>
                                        </div>

                                    </div>
                                    <button type="submit" class="btn btn-primary">
                                        <fmt:message key="register"/>
                                    </button>
                                </form>
                            </div>
                            <div class="card-footer text-center">
                                <div class="small"><a href="${pageContext.request.contextPath}/app/log-me"><fmt:message  key="login.link"/></a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">

        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
