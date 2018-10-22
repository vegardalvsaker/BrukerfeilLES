package Servlets;

import Classes.Module;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ModuleDb;
import HtmlTemplates.BootstrapTemplate;
/**
 *
 * @author Gorm-Erik
 */

@WebServlet(name = "EditModule", urlPatterns = {"/EditModule"})
public class EditModule extends HttpServlet {

    BootstrapTemplate bootstrap = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            
            ModuleDb db = new ModuleDb();
            db.init();
            
            
            if (request.getMethod().equals("POST")) {
                
                String modulName = request.getParameter("Modulnavn");
                String modulDesc = request.getParameter("Beskrivelse");
                String modulContent = request.getParameter("Innhold");
                
                db.editModule(out, request, modulName, modulDesc, modulContent);
                
            }
            
            bootstrap.bootstrapHeader(out, "Modules");
            
            bootstrap.bootstrapNavbar(out, "Modules");
            
            bootstrap.containerOpen(out);
            
            bootstrap.containerClose(out);
            
            editModuleForm(out, request);
              
            bootstrap.bootstrapFooter(out);
            
        }   
}
 
    private void editModuleForm(PrintWriter out, HttpServletRequest request)    {
            
            String module_id = request.getParameter("id");  
            
            ModuleDb db = new ModuleDb();
            db.init();
            
            Module module = db.getModuleWithLearningGoals(module_id);
            
            String modulName = module.getName();
            String modulDesc = module.getDesc();
            String modulContent = module.getContent();
            
            out.println("<h1>Rediger modul</h1>");
            out.println("<form action=\"EditModule?moduleID="+module_id+"\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type=\"text\" name=\"Modulnavn\" value="+ modulName +">");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\" value="+ modulDesc +"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<input type=\"text\" name=\"Innhold\" value="+ modulContent + "><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Rediger modul\"><br>");     
            out.println("</form>");

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