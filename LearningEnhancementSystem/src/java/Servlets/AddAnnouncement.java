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
public class AddAnnouncement extends SuperServlet {

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
            super.processRequest(request, response, "Announcement", out);
             AnnouncementDb db = new AnnouncementDb();
             db.init();
             

             addAnnouncement(out,response);
            
             bst.bootstrapFooter(out);
             
              if (request.getMethod().equals("POST"))  {
               
                String teacherId = request.getParameter("TeacherId");
                int teacherID = Integer.parseInt(teacherId);
                
                String annoSubject = request.getParameter("Subject");
                
                String annoBody = request.getParameter("Body");
         
              // String annoAuthor = request.getParameter("author");
             
                db.addAnnouncement(teacherID, annoSubject, annoBody);
            }
        }
    
    }  

        private void addAnnouncement(PrintWriter out, HttpServletResponse response)  {
            
            out.println("<div>");
            out.println("<a href=\"Announcement\">");
            out.println("</a>");
            out.println("<h1>Legg til kunngjøring</h1>");
            out.println("<form action=\"AddAnnouncement\" method=\"POST\">");
            out.println("<h3>Subject</h3><br>");
            out.println("<input type =\"text\" name=\"Subject\"><br>");           
            out.println("<h3>Body</h3><br>");
            out.println("<input type=\"text\" name=\"Body\"><br>");
            out.println("<h3>Lærer-nummer</h3><br>");
            out.println("<input type=\"number\" name=\"TeacherId\"><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");        
            out.println("<br>");
            out.println("</form>");
            out.println("</div>");
            /*
            out.println("<form>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"AddAnnouncement\">Overskrift</label>");
            out.println("<input type=\"text\" class=\"form-control\" name=\"Subject\">");
            out.println("</div>");
            out.println(" <div class=\"form-group\">");
            out.println("<label for=\"AddAnnouncement\">Hoveddel</label>");
            out.println("<input type=\"text\" class=\"form-control\" name=\"Body\">");
            out.println("</div>");
            out.println("<div class=\"form-group\">");
            out.println("<label for=\"AddAnnouncement\">Lærer-id</label>");
            out.println("<input type=\"number\" class=\"form-control\" name=\"TeacherId\">");
            out.println("</div>");
            out.println("<button type=\"submit\" class=\"btn btn-primary\">Submit</button>");
            out.println("</form>");*/
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