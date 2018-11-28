/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Module;
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
import Database.ModuleDb;
import java.util.List;

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
        //String inInterview = request.getParameter("module_inInterview");
        
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Modules", out);
        
            
        bst.containerOpen(out);
        bst.containerClose(out);
        bst.bootstrapFooter(out);
        
        DeliveryDb deliver = new DeliveryDb();
        ModuleDb mdb = new ModuleDb();
        if(request.getMethod().equals("POST")){
            String link = request.getParameter("link");
            User user = (User)request.getSession().getAttribute("userLoggedIn");
            deliver.addDelivery(user.getUserId(),moduleid , link, "1");
            
        }
        
        
        List<Module> DeliveryForm = mdb.getDeliveryForm(moduleid);
                for (Module module : DeliveryForm){
                     String desc = module.getDesc();
                     String content = module.getContent();
                     Boolean inInterview = module.getInInterview();
                     
                        out.println("<h2>"+ moduleid + "</h2>");
                        out.println("<p>" + desc + "</p>");
                        out.println("<p>" + content + "</p>");
                     
                     if (inInterview.equals(0)||(inInterview.equals(false))){
                            out.println("<form action=\"Delivery?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<h3>Her kan du skrive inn linken til youtube-videoen<h3>");
                            out.println("<input type=\"text\" name=\"link\">");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Upload\">");
                            //out.println("<href=\"OneModule?id="+ moduleid +">");
                            out.println("</form>");
                        } else {
                            out.println("<form action=\"Delivery?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<h3>Modulen godkjennes av lærer eller hjelpelærer</h3>");
                            out.println("<p>Trykk på knappen for å gi beskjed om at du ønsker modulgodkjenning</p><br>");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Send\">");
                            out.println("</form>");
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
