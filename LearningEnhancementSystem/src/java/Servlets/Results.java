package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.ModuleDb;
import Database.DeliveryDb;
import Classes.User;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import Classes.Delivery;
/**
 *
 * @author Gorm-Erik
 */
@WebServlet(name = "Results", urlPatterns = {"/Results"})
public class Results extends SuperServlet {
    
    BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest(request, response, "Results", out);
            ModuleDb db = new ModuleDb();
            db.init();
         
            bst.containerOpen(out);
            bst.containerClose(out);
            results(out, request);
            bst.bootstrapFooter(out);  
         
        }
    }
    private void results(PrintWriter out, HttpServletRequest request)    {
        
        DeliveryDb deliverydb = new DeliveryDb();
        deliverydb.init();
        out.println("<h1>Dine evaluerte moduler:</h1><br>");
        setUserLoggedIn(request);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userLoggedIn");
        String userID = user.getUserId();
        ArrayList<Delivery> deliveryList = deliverydb.getDelivery(out, userID);
        
        for (Delivery delivery : deliveryList)  {
            String moduleName = delivery.getModuleName();
            String deliveryID = delivery.getDeliveryID();
            String moduleID = delivery.getModuleID();
            out.println("<ul>");
            out.println("<li><a href=\"OneResult?userID=" + userID + "&deliveryID="+ deliveryID + "&moduleID="+ moduleID + "\">" + moduleName + "</a></li>");
            out.println("</ul>");
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
