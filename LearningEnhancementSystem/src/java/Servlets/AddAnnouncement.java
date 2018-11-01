package Servlets;

import Classes.User;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import Database.AnnouncementDb;
/**
 *
 * @author Marius
 */
@WebServlet(name = "AddAnnouncement", urlPatterns = {"/AddAnnouncement"})
public class AddAnnouncement extends SuperServlet {
    BootstrapTemplate bst = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("userLoggedIn"); 
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Announcement", out);
             AnnouncementDb db = new AnnouncementDb();
             db.init();
             out.println("<a href=\"Announcement\"a class=\"btn btn-primary\">Go back</button></a>");
             addAnnouncement(out);
             
              if (request.getMethod().equals("POST"))  {
               String annoSubject = request.getParameter("Subject");
               String annoBody = request.getParameter("Body");
               db.addAnnouncement(user.getUserId(), annoSubject, annoBody);
            }
        }
    }  

        private void addAnnouncement(PrintWriter out)  {
            out.println("<div>");
            out.println("<h1>Legg til kunngjøring</h1>");
            out.println("<form action=\"AddAnnouncement\" method=\"POST\">");
            out.println("<h3>Subject</h3><br>");
            out.println("<input type =\"text\" name=\"Subject\"><br>");           
            out.println("<h3>Body</h3><br>");
            out.println("<input type=\"text\" name=\"Body\"><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");        
            out.println("<br>");
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
      
}