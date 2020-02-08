
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="view"/>

<nav class="navbar navbar-default" role="navigation" style="border-radius: 5px">
    <!-- Brand and toggle get grouped for better mobile display -->

    <div class="navbar-center navbar-brand  shadow p-3 mb-5 bg-white rounded "
         style="width: 100%; display: flex; align-content: center; justify-content: space-between;">
        <a class="navbar-brand" style="display: flex; align-items: center;"
           href="${pageContext.request.contextPath}/app/log-me"><fmt:message key="login.page"/></a>
        <a class="navbar-brand" style="display: flex; align-items: center;"
           href="${pageContext.request.contextPath}/app/reg-me"><fmt:message key="register.page"/></a>

        <a class="navbar-brand" style="font-size: 3rem"><fmt:message key="delivery.system"/></a>

        <div class="navbar-center navbar-brand" style="display: flex; align-items: center;">


            <a class="navbar-brand ua-icon" href="${pageContext.request.contextPath}?sessionLocale=en">
                <option data-content='<span class="flag-icon flag-icon-us"></span> English'><fmt:message
                        key="lang.en"/></option>
            </a>
            <a class="navbar-brand ua-icon" href="${pageContext.request.contextPath}?sessionLocale=ua">
                <option data-content='<span class="flag-icon flag-icon-ua"></span> Ukrainian'><fmt:message
                        key="lang.ua"/></option>
            </a>


        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-1">

        </div><!-- /.navbar-collapse -->

    </div>
</nav>




