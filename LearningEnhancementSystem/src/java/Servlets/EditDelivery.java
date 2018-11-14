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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Filip
 */
@WebServlet (name ="EditDelivery", urlPatterns = {"/EditDelivery"})
public class EditDelivery extends SuperServlet {
    BootstrapTemplate bootstrap = new BootstrapTemplate();
    
      protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String deliveryId = request.getParameter("id");
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest (request, response, "Delivery", out);
            DeliveryDb db = new DeliveryDb();
            db.init();
            
             if (request.getMethod().equals("Edit")) {
                
                String deliveryContent = request.getParameter ("Link");
                //editModule tar to parametre. (String moduleId, String content)
                db.editDelivery( deliveryId, deliveryContent);
            }
        } 
               
    }
    
    
}
