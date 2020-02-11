package com.delivery.controller.filter;

import com.delivery.model.entity.Role;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.delivery.controller.command.TextConstants.CommandPaths.*;
import static com.delivery.controller.command.TextConstants.Parameters.CALCULATE;
import static com.delivery.controller.command.TextConstants.Parameters.ROLE;
import static com.delivery.controller.command.TextConstants.Routes.*;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class AccessFilter implements Filter {
    private Map<Role, Set<String>> allowedRoutes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedRoutes = new EnumMap<>(Role.class);
        allowedRoutes.put(Role.GUEST,
                Stream.of(EMPTY_STRING, REG_ME, LOG_ME, LOGIN, REGISTRATION, HOME, CALCULATE, CALC_ME)
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));

        allowedRoutes.put(Role.USER,
                Stream.of(EMPTY_STRING, LOGOUT, HOME, LOGIN, REGISTRATION,  PERSONAL_CABINET, MAKE_ORDER
                        )
                        .collect(collectingAndThen(
                                toCollection(HashSet::new), Collections::unmodifiableSet)));

        allowedRoutes.put(Role.ADMIN,
                Stream.of(EMPTY_STRING, LOGOUT, HOME, LOGIN, REGISTRATION, PERSONAL_CABINET, MAKE_ORDER)
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
            request.getSession().setAttribute(ROLE, Role.GUEST.toString());
        }
        Role currentRole = (Role.valueOf((String) request.getSession().getAttribute(ROLE)));


        if (allowedRoutes.get(currentRole).contains(path)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //user is guest? then sign in
            if (currentRole.equals(Role.GUEST.toString())) {
                request.getRequestDispatcher(TO_LOGIN).forward(request, response);
            } else {
                request.getRequestDispatcher(ACCESS_FORBIDDEN_403).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        //because it should be implemented
    }
}
