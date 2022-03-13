package com.travelthruair.controller;

import com.travelthruair.service.DealService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

public class dealServlet extends HttpServlet {

    private DealService dealService;

    public dealServlet() {
        this.dealService = new DealService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // testig the get methods before implementation
        String jsonResponse = this.dealService.findAllDeals();
        this.outputResponse(resp, jsonResponse, 200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // add student from post body
        String reqBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int rc = HttpServletResponse.SC_OK;
        boolean res = this.dealService.createDeal(reqBody);
        if (!res) {
            rc = HttpServletResponse.SC_BAD_REQUEST;
        }
        this.outputResponse(resp, null, rc);
    }



    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////// private methods ////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private void outputResponse(HttpServletResponse response, String payload, int status) {
        response.setHeader("Content-Type", "application/json");
        try {
            response.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(payload.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

