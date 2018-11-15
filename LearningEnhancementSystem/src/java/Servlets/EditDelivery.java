/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import Database.DeliveryDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filip
 */
@WebServlet(name = "EditDelivery", urlPatterns = {"/EditDelivery"})
public class EditDelivery extends SuperServlet {
    
    BootstrapTemplate bst = new BootstrapTemplate();
    
           
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         
        
        
        
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest(request, response, "Modules", out);
            User user = (User)request.getSession().getAttribute("userLoggedIn");
            bst.containerOpen(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            
            DeliveryDb db = new DeliveryDb();
            db.init();
            
            if (request.getMethod().equals("POST")) {
               // if (request.getParameter("moduleId")!=(null)) {
                String moduleId = request.getParameter("moduleId");
                //String deliveryId = request.getParameter("deliveryId");
                List<Classes.Delivery> deliveryList = db.getDeliveryWithUserIdAndModuleId(moduleId, user.getUserId());
                for (Classes.Delivery delivery : deliveryList){
                    String deliveryId = delivery.getDeliveryID();
                    String deliveryContent = delivery.getDeliveryContent();
                    
                    out.println("<h3>Her er deliverycontent</h3><br>");
                    
                    out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
                    out.println("<input type=\"text\" name=\"moduleId\" value=\""+ moduleId +"\">");
                    out.println("<input type=\"text\" name=\"deliveryId\" value=\""+ deliveryId +"\">");
                    //out.println("<textarea style=\"margin-left:2.5em;\" rows=\"2\" cols=\"30\" name=\"deliveryContent\"> "+deliveryContent+"</textarea>");
                    out.println("<textarea style=\"margin-left:2.5em;\" rows=\"2\" cols=\"30\" name=\"deliveryContent\">"+deliveryContent+"</textarea>");
                    //out.println("</textarea>");
                    out.println("<input type=\"submit\" value=\"Edit delivery\"><br>");        
                    out.println("<br>");
                    out.println("</form>");
               // }
                }
                
                
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
    

