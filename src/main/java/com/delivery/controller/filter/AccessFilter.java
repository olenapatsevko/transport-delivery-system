package com.delivery.controller.filter;



import com.delivery.model.entity.Role;
import com.delivery.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.delivery.controller.command.TextConstants.CommandPaths.*;
import static com.delivery.controller.command.TextConstants.Routes.EMPTY_STRING;
import static com.delivery.controller.command.TextConstants.Parameters.ROLE;
import static com.delivery.controller.command.TextConstants.Routes.TO_LOGIN;
import static com.delivery.controller.command.TextConstants.Routes.ACCESS_FORBIDDEN_403;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;



public class AccessFilter implements Filter {
    private Map<Role, Set<String>> allowedRoutes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedRoutes = new HashMap<>();
        allowedRoutes.put(Role.GUEST,
                Stream.of(EMPTY_STRING, REG_ME, LOG_ME, LOGIN, REGISTRATION, HOME)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));


        allowedRoutes.put(Role.USER,
                Stream.of(EMPTY_STRING, LOGOUT, HOME, LOGIN, REGISTRATION, SHOW_REPORTS, PERSONAL_CABINET,
                        MAKE_REPORT, EDIT_REPORTS, MAKE_COMPLAINT,
                        SUBMIT_REPORT, SUBMIT_COMPLAINT, SUBMIT_EDIT_REPORT)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));


        allowedRoutes.put(Role.ADMIN,
                Stream.of(EMPTY_STRING, LOGOUT, HOME, LOGIN, REGISTRATION, PERSONAL_CABINET)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI()
                .replace(request.getContextPath(), EMPTY_STRING)
                .replace(request.getServletPath(), EMPTY_STRING)
                .replace("/", EMPTY_STRING);

        if (request.getSession().getAttribute(ROLE) == null) {
            request.getSession().setAttribute(ROLE, Role.GUEST);
        }
        Role currentRole = (Role.valueOf((String) request.getSession().getAttribute(ROLE)));


        if (allowedRoutes.get(currentRole).contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //user is guest? then sign in
            if (currentRole.equals(Role.GUEST)) {
                request.getRequestDispatcher(TO_LOGIN).forward(request, response);
            } else {
                request.getRequestDispatcher(ACCESS_FORBIDDEN_403).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
}
