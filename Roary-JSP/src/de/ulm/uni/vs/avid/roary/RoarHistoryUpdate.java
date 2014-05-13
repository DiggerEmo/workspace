package de.ulm.uni.vs.avid.roary;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
		// Parameter lesen (User wird in der Session gespeichert)
		String text = request.getParameter("roarText");  
        String user = request.getSession().getAttribute("username").toString(); 
        String editRoar = request.getParameter("roarId");
        System.out.println("user: " + user + ", roarId: " + editRoar + ", text: " + text);
        
        // if text is there save roar and redirect user to roarList
        if (text != null && text != ""){
        	// Get RoarHistory from Session
        	RoarHistory sessionBean = (RoarHistory) request.getSession().getAttribute("roarHistory");
        	
        	// Prüfe ob ein Roar editiert werden soll, oder ein neuer hinzugefügt werden soll
        	if (editRoar == null || editRoar == ""){
	        	// Füge den Roar der Session hinzu
	        	Roar r = new Roar();
	        	r.setAuthor(user);
	        	r.setText(text);
	        	sessionBean.addRoar(r);
	        	
	        	response.sendRedirect("RoarList.jsp");
        	}
        	else{
        		Roar r = sessionBean.getRoar(Integer.parseInt(editRoar));
        		
        		// Wenn r leer ist, wurde die ID nicht gefunde
        		if (r == null){
        			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        		}
        		// Wenn ein anderer User den Roar verändern will
        		else if (!r.getAuthor().equals(user)){
       			 	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        		}
        		// Wenn die obigen Fälle nicht eingetreten sind, kann der roar bearbeitet werden
        		else{
        			r.setText(text);
        			
                	response.sendRedirect("RoarList.jsp");
        		}
        	}        	
        }
        // if text is empty send response as HTML to browser  
        else{
            PrintWriter out = response.getWriter();  
            
	        out.println("<html>");  
	        out.println("<head><title>Empty text</title></head>");  
	        out.println("<body>");  
	        out.println("Hello " + user);  
	        out.println("<br>");  
	        out.println("your Roar needs some text!");
	        out.println("<br>");  
	        out.println("<a href='RoaryEdit.jsp'>Nochmal versuchen</a>");
	        out.println("</body></html>");  
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
