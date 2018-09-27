/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import Database.AnnouncementDb;
/**
 *
 * @author Marius
 */
@WebServlet(name = "AddAnnouncement", urlPatterns = {"/AddAnnouncement"})
public class AddAnnouncement extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    BootstrapTemplate bst = new BootstrapTemplate();
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             AnnouncementDb db = new AnnouncementDb();
             db.init();
             bst.bootstrapHeader(out,"Announcement");
             bst.bootstrapNavbar(out,"Announcement");
             bst.containerOpen(out);
             
            out.println("<form method=\"post\" action=\"register>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"titleheader\">Title</label>");
            out.println("<input type=\"text\" class=\"form-control\" id=\"title\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"descheader\">Description</label>");
            out.println("<textarea type=\"text\" class=\"form-control\" id=\"description\" rows=\"3\"></textarea>");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"authorheader\">Author</label>");
            out.println("<input type=\"text\" class=\"form-control\" id=\"author\">");
            out.println("</div>");
            out.println("<button type=\"submit\"button href=\"Announcement\" class=\"btn btn-primary\">Submit</button>");
            out.println("</form>");
            
            
            String text = request.getParameter("title");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            db.addAnnouncement(request);
            out.println();
             bst.containerClose(out);
             bst.bootstrapFooter(out);
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
