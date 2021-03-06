<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="view"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
            crossorigin="anonymous"></script>
    <title><fmt:message key="calculator.page"/></title>
</head>
<header>
    <div class="bg-light">
        <jsp:include page="calculator_header.jsp"/>
    </div>
</header>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
                <div class="card-header"><h3 class="text-center font-weight-light my-4"><fmt:message
                        key="calculator.page"/></h3></div>
                <div class="card-body">

                    <form role="form" method="post" action="${pageContext.request.contextPath}/app/calculator" >
                      <h2>  <p>
                            <%= request.getAttribute("calculationResult") != null ? request.getAttribute("calculationResult") + " ₴" : "" %>
                      </p></h2>
                        <div class="form-group">
                            <label for="departure"><fmt:message key="departure"/></label>
                            <select class="form-control" id="departure" name="departure">
                                <c:forEach items="${towns}" var="town"  >
                                <option> ${town} </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="destination"><fmt:message key="destination"/></label>
                            <select class="form-control" id="destination" name="destination">
                                <c:forEach items="${towns}" var="town">
                                    <option> <c:out value = "${town}"/></option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="material"><fmt:message key="shipment.type"/></label>
                            <select class="form-control" id="material" name="material">
                                <c:forEach items="${materials}" var="material">
                                    <option><c:out value="${material}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-row">
                            <div class="col-md-3 mb-4">
                                <label for="weight"><fmt:message key="weight"/></label>
                                <input type="number" class="form-control" name="weight" id="weight" min="0"
                                       placeholder="<fmt:message key="weight"/>" required>
                                <div class="invalid-feedback">
                                    <fmt:message key="please.provide.valid.value"/>
                                </div>
                            </div>
                            <div class="col-md-3 mb-4">
                                <label for="width"><fmt:message key="width"/></label>
                                <input type="number" class="form-control" name="width" id="width" min="0"
                                       placeholder="<fmt:message key="width"/>" required>
                                <div class="invalid-feedback">
                                    <fmt:message key="please.provide.valid.value"/>
                                </div>
                            </div>
                            <div class="col-md-3 mb-4">
                                <label for="length"><fmt:message key="length"/></label>
                                <input type="number" class="form-control" name="length" id="length" min="0"
                                       placeholder="<fmt:message key="length"/>" required>
                                <div class="invalid-feedback">
                                    <fmt:message key="please.provide.valid.value"/>
                                </div>
                            </div>

                            <div class="col-md-3 mb-4" style="height: 30px">
                                <label for="height"><fmt:message key="height"/></label>
                                <input type="number" class="form-control" name="height" id="height" min="0"
                                       placeholder="<fmt:message key="height"/>" required>
                                <div class="invalid-feedback">
                                    <fmt:message key="please.provide.valid.value"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <br>

                            </div>
                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="calculate"/>
                                </button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
