package controller;
import service.dealcurrService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

/**
 * Servlet implementation class dealServlet
 */

@WebServlet("/current")

public class dealcurrServlet extends HttpServlet {
	private dealcurrService dealcurrService;

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dealcurrServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.dealcurrService = new dealcurrService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String jsonResponse = this.dealcurrService.findAllDeals();
	     this.outputResponse(response, jsonResponse, 200);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String reqBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        int rc = HttpServletResponse.SC_OK;
        boolean res = this.dealcurrService.createcurrDeal(reqBody);
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
}