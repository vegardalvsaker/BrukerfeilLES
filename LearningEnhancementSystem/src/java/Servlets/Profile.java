package Servlets;

import Database.UserDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Classes.User;

/**
 *
 * @author Fosse
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends SuperServlet {

  BootstrapTemplate bst = new BootstrapTemplate();
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    
    String userID = request.getParameter("id"); 
    
    
    
    try (PrintWriter out = response.getWriter()) {
        super.processRequest(request, response, "People", out);
        
            
        bst.containerOpen(out);
        bst.containerClose(out);
        bst.bootstrapFooter(out);
              
        UserDb profile = new UserDb();
        profile.init();
        
        profile.getProfile(out,userID);
        //Eventuelt get PROGRESS/RESULTS
        
        setUserLoggedIn(request);                                 //Calls super method, fills in user-data from database into session   

        HttpSession session = request.getSession();               //Sets variable "session" to current session
        User user = (User)session.getAttribute("userLoggedIn");   //Sets variable "user" to the current user logged in  
        
        
        if (request.isUserInRole("Teacher")) {
            out.println("Du er logget inn som en lærer: ");
            profile.printProfile(out);
            //Eventuelt print PROGRESS/RESULTS
        } else if (user.getUserId().equals(userID) ) {            //IF user logged in = user profile requested
            profile.printProfile(out);
            //Eventuelt print PROGRESS/RESULTS
        } else {
            out.println("Du er logget inn som en student: ");
            profile.printProfileLimited(out);
        }
        profileForm(out);
    }    
}

public void profileForm(PrintWriter out) {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>People</title>");            
    out.println("</head>");
    out.println("<body>");
    out.println("<div>");
    out.println("<form action=\"Profile\" method=\"POST\">");
    out.println("<br>");
    out.println("<input type=\"submit\" value=\"Placeholder\">");
    out.println("</form>");
    out.println("</div>"); 

            }

 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}