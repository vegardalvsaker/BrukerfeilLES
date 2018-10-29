package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ResultsDb;
import HtmlTemplates.BootstrapTemplate;
import Database.ModuleDb;
import Database.DeliveryDb;
import Classes.User;
import Database.UserDb;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import Classes.Delivery;
import java.util.Arrays;
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
            ModuleDb db = new ModuleDb();
            db.init();
            bst.bootstrapHeader(out, "Results");
            bst.bootstrapNavbar(out, "Results");
            bst.containerOpen(out);
            bst.containerClose(out);
            results(out, request);
            bst.bootstrapFooter(out);  
         
        }
    }
    private void results(PrintWriter out, HttpServletRequest request)    {
        
        DeliveryDb deliverydb = new DeliveryDb();
        out.println("<h1>Dine evaluerte moduler:</h1><br>");
        setUserLoggedIn(request);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userLoggedIn");
        String userID = user.getUserId();
        ArrayList<Delivery> deliveryList = deliverydb.getDelivery(out, userID);
        
        for (Delivery delivery : deliveryList)  {
            String moduleName = delivery.getModuleName();
            out.println("<ul>");
            out.println("<li><a href=\"OneResult?id=\"" + userID + "\">\"" + moduleName + "\"</a></li>\"");
            out.println("</ul>");
        }  
    }
 
    @Override
    public String toString()  {
        
        String navn = "Petter";
        return navn;
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
