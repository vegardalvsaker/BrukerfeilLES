/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import Database.AnnouncementDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Servlets.AddAnnouncement;
/**
 *
 * @author Marius
 */
@WebServlet(name = "RedirectAnnouncement", urlPatterns = {"/RedirectAnnouncement"})
public class RedirectAnnouncement extends HttpServlet {

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
  
    protected void addToDb(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            AnnouncementDb db = new AnnouncementDb();
             db.init();
             bst.bootstrapHeader(out,"Announcement");
             bst.bootstrapNavbar(out,"Announcement");
             bst.containerOpen(out);
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String author = request.getParameter("author");
            
            out.println("<h2>" + title + "</h2>");
            out.println("<h2>" + description + "</h2>");
            out.println("<h2>" + author + "</h2>");
            
            bst.containerClose(out);
            bst.bootstrapFooter(out);
        }
            
    }
    
    }
