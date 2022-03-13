package com.travel.controller;

import com.travel.service.DealService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deals
 * 
 * 
 */
@WebServlet(urlPatterns= {"/deals"}, name="Deals Servlet", description="Servlet for deals")

public class deals extends HttpServlet {
	private DealService dealService;

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deals() {
        super();
        // TODO Auto-generated constructor stub
        this.dealService = new DealService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String jsonResponse = this.dealService.findAllDeals();
	     this.outputResponse(response, jsonResponse, 200);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int rc = HttpServletResponse.SC_OK;
        boolean res = this.dealService.createDeal(reqBody);
        if (!res) {
            rc = HttpServletResponse.SC_BAD_REQUEST;
        }
        this.outputResponse(response, null, rc);
	}
	
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
