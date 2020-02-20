package com.delivery.controller;

import com.delivery.controller.command.Command;
import com.delivery.controller.command.account.Login;
import com.delivery.controller.command.account.Logout;
import com.delivery.controller.command.account.PersonalCabinet;
import com.delivery.controller.command.account.Registration;
import com.delivery.controller.command.actions.CalculateDelivery;
import com.delivery.controller.command.actions.MakeOrder;
import com.delivery.controller.command.actions.PayCommand;
import com.delivery.controller.command.directions.CalculateMe;
import com.delivery.controller.command.directions.Home;
import com.delivery.controller.command.directions.LogMe;
import com.delivery.controller.command.directions.RegMe;
import com.delivery.model.utility.DeliveryUtility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.delivery.controller.command.TextConstants.CommandPaths.*;
import static com.delivery.controller.command.TextConstants.Parameters.CALCULATE;
import static com.delivery.controller.command.TextConstants.Routes.EMPTY_STRING;
import static com.delivery.controller.command.TextConstants.Routes.REDIRECT;
import static com.delivery.controller.injector.ApplicationInjector.*;

public class FrontController extends HttpServlet {

    private static final Map<String, Command> actions = new HashMap<>();


    @Override
    public void init(ServletConfig config) {
        actions.put(REGISTRATION,
                new Registration(getUserService()));
        actions.put(LOGIN,
                new Login(getUserService()));
        actions.put(LOGOUT,
                new Logout());
        actions.put(PERSONAL_CABINET,
                new PersonalCabinet(getOrderService(), getBillService(), getPagination()));
        actions.put(CALCULATE, new CalculateDelivery(getDeliveryCalculation()));

        actions.put(MAKE_ORDER, new MakeOrder(getOrderService(), getBillService()));
        actions.put(PAY_THE_BILL, new PayCommand(getBillService()));

        //directions
        actions.put(HOME,
                new Home());
        actions.put(REG_ME,
                new RegMe());
        actions.put(LOG_ME,
                new LogMe());
        actions.put(CALC_ME, new CalculateMe());
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(APPLICATION_PATH_REGEX, EMPTY_STRING);

        Command command = actions.getOrDefault(path, (req, resp) -> DEFAULT_PATH);
        String page = command.execute(request, response);
        request.setAttribute("towns", DeliveryUtility.getListOfTowns());
        if (page.contains(REDIRECT)) {
            response.sendRedirect(page.replace(REDIRECT, EMPTY_STRING));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
