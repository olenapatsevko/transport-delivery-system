package com.delivery.controller.command.actions;

import com.delivery.controller.command.Command;
import com.delivery.model.service.BillService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.delivery.controller.command.TextConstants.Routes.BASE;

public class PayCommand implements Command {
    private  final BillService billService;

    public PayCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            billService.payTheBill(Integer.parseInt(request.getParameter("id")));
        }catch (Exception e){

        }
        return BASE;
    }
}
