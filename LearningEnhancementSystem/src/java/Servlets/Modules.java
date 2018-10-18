package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.ModuleDb;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "Modules", urlPatterns = {"/Modules"})
public class Modules extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Objekt for å generere UI
    BootstrapTemplate bst = new BootstrapTemplate();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {  
            
         ModuleDb db = new ModuleDb();
         db.init();
        
        if (request.getMethod().equals("POST"))  {
                
                
                
                String modulnr = request.getParameter("Modulnummer");
                
                String modulnavn = request.getParameter("Modulnavn");
         
                String beskrivelse = request.getParameter("Beskrivelse");
                           
                db.addModule(out, modulnr, modulnavn, beskrivelse);
                
            }
            
            bst.bootstrapHeader(out, "Modules");
            
            bst.bootstrapNavbar(out, "Modules");
            
            
            /*out.format(bst.hei(), "en", "to", "", "");
            out.println();
            out.format(bst.hei(), "", to, "", "4");*/
            
            
            bst.containerOpen(out);
            
            db.skrivModuler(out);
            
            bst.containerClose(out);
            
            addModuleForm(out, response);
            
            bst.bootstrapFooter(out);
            
        }
        
    }
        private void addModuleForm(PrintWriter out, HttpServletResponse response)  {
            
            out.println("<div>");
            out.println("<a href=\"Modules\">");
            out.println("</a>");
            out.println("<h1>Legg til modul</h1>");
            out.println("<form action=\"Modules\" method=\"POST\">");
            out.println("<h3>Modulnummer</h3><br>");
            out.println("<input type =\"text\" name=\"Modulnummer\"><br>");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type=\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse av læringsmål</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til modul\"><br>");        
            out.println("<br>");
            out.println("</form>");
            out.println("</div>");
           
 
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