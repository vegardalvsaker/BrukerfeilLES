package Servlets;

import Database.ModuleDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ResultsDb;
import HtmlTemplates.BootstrapTemplate;

/**
 *
 * @author Gorm-Erik
 */
@WebServlet(name = "Results", urlPatterns = {"/Results"})
public class Results extends HttpServlet {

    BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            ModuleDb db = new ModuleDb();
            db.init();
         
            bst.bootstrapHeader(out, "Modules");
            
            bst.bootstrapNavbar(out, "Modules");
           
            bst.containerOpen(out);
            
            bst.containerClose(out);
            
           results(out, request);
           progress(out, request);
            
            bst.bootstrapFooter(out);
            
            
            
        }
        
        
        
    }

    private void results(PrintWriter out, HttpServletRequest request)    {
        
        
        
        
        
        out.println("<h1>Dine resultater:</h1><br>");
       
        
        ResultsDb rdb = new ResultsDb();
        rdb.fetchResults(request);
        
        
    }
    private void progress(PrintWriter out, HttpServletRequest request)    {
        
        out.println("<h1>Fremgang:</h1><br>");
        
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
