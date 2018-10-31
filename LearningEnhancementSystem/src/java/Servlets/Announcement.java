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
@WebServlet(name = "Announcement", urlPatterns = {"/Announcement"})
public class Announcement extends SuperServlet {
    BootstrapTemplate bst = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Announcement", out);
             AnnouncementDb db = new AnnouncementDb();
             db.init();
             
             if (request.getMethod().equals("POST"))  {
                if (request.getParameter("delete").equals("TRUE")) {
                    String aid = request.getParameter("annId");
                    int annId = Integer.parseInt(aid);
                    db.deleteAnnouncement(annId);  
                }
             }
             if (user.getUserIsTeacher()){
             out.println("<a href=\"AddAnnouncement\"a class=\"btn btn-primary\">Add more</button></a>");
             }
             db.skrivAnnouncement(out);
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
