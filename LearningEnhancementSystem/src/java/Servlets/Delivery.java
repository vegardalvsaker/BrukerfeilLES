/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.DeliveryDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Classes.User;

/**
 *
 * @author Filip
 */
@WebServlet(name = "Delivery", urlPatterns = {"/Delivery"})
public class Delivery extends SuperServlet {

    BootstrapTemplate bst = new BootstrapTemplate();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String moduleid = request.getParameter("id");
        String inInterview = request.getParameter("module_inInterview");
        
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Modules", out);
        
            
        bst.containerOpen(out);
        bst.containerClose(out);
        bst.bootstrapFooter(out);
        
        DeliveryDb deliver = new DeliveryDb();
        if(request.getMethod().equals("POST")){
            String link = request.getParameter("link");
            User user = (User)request.getSession().getAttribute("userLoggedIn");
            deliver.addDelivery(user.getUserId(),moduleid , link, "1");
            
        }
        
        deliver.getDeliveryForm(moduleid,out);
        System.out.println(inInterview);
        
        /*if (request.getMethod().equals("POST")) {
            
            String deliveryContent = request.getParameter("link");
            /*int studentId = Integer.parseInt(StudentId);
            int moduleId = Integer.parseInt(ModuleId);
             String annoContent = request.getParameter("Content");
            int worklistId = Integer.parseInt(WorklistId);
            
            deliver.addDelivery("1", "1", deliveryContent, "2")
            //db.Delivery(StudentID, ModuleId, annocontent, WorklistID);*/
        
        
        
        }
    }
    protected void printDeliveryform(PrintWriter out, HttpServletRequest request) {
        //User user = (User)request.getSession().getAttribute("userLoggedIn");
        //Strb ning userId = user.getUserId();
        String moduleid = request.getParameter("id");
        DeliveryDb deliver = new DeliveryDb();
        deliver.getDeliveryForm(moduleid, out);
       
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
