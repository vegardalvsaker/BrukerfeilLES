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
import java.sql.Timestamp;
import java.util.List;
/**
 *
 * @author Marius
 */
@WebServlet(name = "Announcement", urlPatterns = {"/Announcement"})
public class Announcement extends SuperServlet {
    BootstrapTemplate bst = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Announcement", out);
             AnnouncementDb adb = new AnnouncementDb();
             adb.init();
             
             List<Classes.Announcement> announcementList = adb.getAnnouncement();
             
             if (request.getMethod().equals("POST"))  {
                if (request.getParameter("delete").equals("TRUE")) {
                    String annId = request.getParameter("annId");
                    adb.deleteAnnouncement(annId);  
                }
             }
             if (user.getUserIsTeacher()){
             out.println("<a href=\"AddAnnouncement\"a class=\"btn btn-primary\">Add more</button></a>");
             }
             out.println("<div class=\"jumbotron\">");
             out.println("<div class=\"container\">");
             out.println("<h1 class=\"display-4\">Announcements:</h1>");  
             out.println("<hr class=\"my-4\">");
             for (Classes.Announcement announcement : announcementList){
                 String annId = announcement.getAnnId();
                 String annSubject = announcement.getAnnSubject();
                 String annBody = announcement.getAnnBody();
                 String annUser = announcement.getAnnUserName();
                 Timestamp annTime = announcement.getAnnTime();
                 out.println("<h2>"+ annSubject + "</h2>");
                out.println("<p>" + annBody + "</p>");
                out.println("<p>" + annUser + "</p>");
                out.println("<small>" + annTime + "</small>");
                out.println("<form action=\"Announcement\" method=\"POST\">");
                 out.println("<input type=\"text\" name=\"delete\" value=\"TRUE\"style=\"visibility:hidden;\">");
                 out.println("<input type=\"text\" name=\"annId\" value=\""+ annId +"\"style=\"visibility:hidden;\">");
                 out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete\"style=\">");
                 out.println("</form>");
                out.println("<hr class=\"my-4\">");
             }
    }
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
