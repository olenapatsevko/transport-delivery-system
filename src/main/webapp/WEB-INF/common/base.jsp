<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="view"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title><fmt:message key="personal.cabinet"/></title>
    <link href="${pageContext.request.contextPath}/base/css/styles.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet"
          crossorigin="anonymous"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"
            crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark ">
    <a class="navbar-brand"><fmt:message key="delivery.system"/></a>


    <!-- Language change -->
    <a class="navbar-brand ua-icon" href="${pageContext.request.contextPath}?sessionLocale=en">
        <option data-content='<span class="flag-icon flag-icon-us"></span> English'><fmt:message
                key="lang.en"/></option>
    </a>
    <a class="navbar-brand ua-icon" href="${pageContext.request.contextPath}?sessionLocale=ua">
        <option data-content='<span class="flag-icon flag-icon-ua"></span> Ukrainian'><fmt:message
                key="lang.ua"/></option>
    </a>

    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <em class="fas fa-user fa-fw"></em></a>

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/app/logout"><fmt:message
                        key="logout"/> </a>
            </div>
        </li>
    </ul>
</nav>

<div id="layoutSidenav">


        <main>
            <div class="container-fluid">
                <h1 class="mt-4"><fmt:message key="personal.cabinet"/></h1>
                <div class="row">
                    <div class="col-xl-6">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4"><fmt:message
                                            key="order.delivery"/></h3></div>
                                    <div class="card-body">
                                        <form role="form" method="post"
                                              action="${pageContext.request.contextPath}/app/make-order">
                                            <c:if test="${param.orderSuccess==true}">
                                                <p style="color: darkblue"><fmt:message key="param.success"/></p>
                                            </c:if>
                                            <c:if test="${param.orderSuccess==false}">
                                                <p style="color: darkblue"><fmt:message key="param.fail"/></p>
                                            </c:if>
                                            <div class="form-group">
                                                <label for="departure"><fmt:message key="departure"/></label>
                                                <select class="form-control" id="departure" name="departure">
                                                    <c:forEach items="${towns}" var="town">
                                                        <option> ${town} </option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group">
                                                <label for="destination"><fmt:message key="destination"/></label>
                                                <select class="form-control" id="destination" name="destination">
                                                    <c:forEach items="${towns}" var="town">
                                                        <option><c:out value="${town}"/></option>
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
                                                    <input type="number" class="form-control" name="weight" id="weight"
                                                           min="0" placeholder="<fmt:message
            key="weight"/>" required>
                                                    <div class="invalid-feedback">
                                                        <fmt:message key="please.provide.valid.value"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-4">
                                                    <label for="width"><fmt:message key="width"/></label>
                                                    <input type="number" class="form-control" name="width" id="width"
                                                           min="0" placeholder="<fmt:message
            key="width"/>" required>
                                                    <div class="invalid-feedback">
                                                        <fmt:message key="please.provide.valid.value"/>
                                                    </div>
                                                </div>
                                                <div class="col-md-3 mb-4">
                                                    <label for="length"><fmt:message key="length"/></label>
                                                    <input type="number" class="form-control" id="length" name="length"
                                                           min="0" placeholder="<fmt:message
            key="length"/>" required>
                                                    <div class="invalid-feedback">
                                                        <fmt:message key="please.provide.valid.value"/>
                                                    </div>
                                                </div>

                                                <div class="col-md-3 mb-4">
                                                    <label for="height"><fmt:message key="height"/></label>
                                                    <input type="number" class="form-control" id="height" name="height"
                                                           min="0" placeholder="<fmt:message
            key="height"/>" required>
                                                    <div class="invalid-feedback">
                                                        <fmt:message key="please.provide.valid.value"/>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="address"><fmt:message key="address"/></label>
                                                    <input class="form-control" id="address" name="address" type="text">
                                                </div>
                                                <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0"
                                                     style="width: 100%;">
                                                    <button type="submit" class="btn btn-primary">
                                                        <fmt:message key="order.delivery"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                <div class="card mb-4" style="width: 100%;">
                    <div class="card-header" style="width: 100%;"><em class="fas fa-table mr-1"></em><fmt:message key="user.bills"/></div>
                    <div class="card-body" style="width: 100%;" >
                        <div class="table-responsive" style="width: 100%;">
                            <table class="table table-bordered" id="dataTable" name="bills" width="100%"  cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="table.id"/></th>
                                    <th><fmt:message key="table.payment"/></th>
                                    <th><fmt:message key="table.delivery"/></th>
                                    <th><fmt:message key="table.size"/></th>
                                    <th><fmt:message key="table.weight"/></th>
                                    <th><fmt:message key="table.total.price"/></th>
                                    <th><fmt:message key="table.receive.date"/></th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th><fmt:message key="table.id"/></th>
                                    <th><fmt:message key="table.payment"/></th>
                                    <th><fmt:message key="table.delivery"/></th>
                                    <th><fmt:message key="table.size"/></th>
                                    <th><fmt:message key="table.weight"/></th>
                                    <th><fmt:message key="table.total.price"/></th>
                                    <th><fmt:message key="table.receive.date"/></th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="report" items="${bills}">
                                    <tr>
                                        <td><c:out value="${report.id}"/></td>

                                        <c:if test="${report.payment==false}">
                                            <td>
                                                <button><a
                                                        href="${pageContext.request.contextPath}/app/pay?id=${report.id}">
                                                    <c:out value="${report.payment}"/></a></button>
                                            </td>
                                        </c:if>

                                        <c:if test="${report.payment==true}">
                                            <td>
                                                <button><a class="active" href="#">
                                                    <c:out value="${report.payment}"/></a></button>
                                            </td>
                                        </c:if>

                                        <td><c:out value="${report.delivery}"/></td>
                                        <td><c:out value="${report.size}"/></td>
                                        <td><c:out value="${report.weight}"/></td>

                                        <td><a href="${pageContext.request.contextPath}/app/pay?bill=${report.id}">
                                            <c:out value="${report.total.price}"/></a></td>

                                        <td><c:out value="${report.receive.date}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            </div>
        </main>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/base/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/base/assets/demo/chart-area-demo.js"></script>
<script src="${pageContext.request.contextPath}/base/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/base/assets/demo/datatables-demo.js"></script>
</body>
</html>
