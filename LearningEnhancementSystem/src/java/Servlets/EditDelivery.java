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

/**
 *
 * @author Filip
 */
@WebServlet (name ="EditDelivery", urlPatterns = {"/EditDelivery"})
public class EditDelivery extends SuperServlet {
    BootstrapTemplate bootstrap = new BootstrapTemplate();
    
    protect void processRequest (httpServletRequest request, HttpServletRespons respons)
            throws ServletException, IOException {
        respons-setContentType();
        
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest (request, response, "Delivery", out);
            DeliveryDb db = new DeliveryDb();
            db.init();
            
            if (request.getMethod().equls("Edit")) {
                
                String deliveryContent = request.getParameter ("Link");
                
                db.editModule(out, request, deliveryContent);
            }
        } 
               
    }
    
    
}
