/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import Classes.Delivery;
import java.util.ArrayList;
import Database.WorklistDb;
import Database.DeliveryDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Espen
 */
 /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param <error>
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Objekt for å generere UI
    
@WebServlet(name = "Worklist", urlPatterns = {"/Worklist"})
public class Worklist extends SuperServlet {
        BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        super.processRequest(request, response, "Worklist", out);
        bst.containerOpen(out);
        WorklistDb db = new WorklistDb();
        db.init();
	User user = (User)request.getSession().getAttribute("userLoggedIn");
        String worklistId = db.getWorklistId(user.getUserId());
	
        DeliveryDb dDb = new DeliveryDb();
        ArrayList<Delivery> deliveries = dDb.getUnevaluatedDeliveriesWithinWorklist(worklistId);
        
        if (deliveries.isEmpty()) {
            out.println(" <font size=\"8\"> No deliveres waiting for evaluation! </font> ");
        }
        
        printTable(out);
        for (Delivery del : deliveries) {
            out.println("<tr>");
            out.println("<td>" + del.getDeliveryID());
            out.println("<td>" + del.getModuleID());
            out.println("<td>" + del.getStudentID());
            out.println("<td>" + del.getDeliveryContent());
            out.println("<td>" + del.getDeliveryTimestamp());
            out.println("<a href=\"EvaluateServlet?deliveryId=" + del.getDeliveryID() +"\" class=\"btn btn-primary\">Evaluate!</a>");
            out.println("</tr>");
            
        }
        out.println("</tbody>");
        out.println("</table>");
        
        bst.containerClose(out);
        bst.bootstrapFooter(out);
        
        }
        
    }
    
     private void printTable(PrintWriter out) {
        out.println("<table>");
        out.println("<thead>");
        out.println("<th> Delivery ID</th>");
        out.println("<th> Module ID</th>");
        out.println("<th> Student ID</th>");
        out.println("<th> Delivery Content</th>");
        out.println("<th> Timestamp</th>");
        out.println("</thead>");
        out.println("<tbody>");
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
        
    
