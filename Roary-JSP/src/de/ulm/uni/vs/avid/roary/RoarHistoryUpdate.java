package de.ulm.uni.vs.avid.roary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RoarHistoryUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoarHistoryUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter("roarText");  
        String user = request.getParameter("user");  
  
        // send response as HTML to browser  
        response.setContentType("text/html");  
  
        PrintWriter out = response.getWriter();  
  
        out.println("<html>");  
        out.println("<head><title> Social Network </title></head>");  
        out.println("<body>");  
        out.println("Hello " + user);  
        out.println("<br>");  
        out.println("your Roar has been received:<br>" + text);  
        out.println("</body></html>");  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
