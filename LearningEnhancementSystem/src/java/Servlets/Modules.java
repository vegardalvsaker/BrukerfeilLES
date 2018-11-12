package Servlets;


import Classes.Module;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.ModuleDb;
import Classes.User;
import java.util.List;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "Modules", urlPatterns = {"/Modules"})
public class Modules extends SuperServlet {

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
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){  
            super.processRequest(request, response, "Modules", out);
         ModuleDb db = new ModuleDb();
         db.init();
        
        if (request.getMethod().equals("POST"))  {
                
                String modulnavn = request.getParameter("Modulnavn");
                
                String beskrivelse = request.getParameter("Beskrivelse");
         
                String innhold = request.getParameter("Innhold");
                
                boolean leveringsform = Boolean.parseBoolean(request.getParameter("leveringsform"));
                
                //boolean video = Boolean.parseBoolean(request.getParameter("Video"));
                           
                db.addModule(out, modulnavn, beskrivelse, innhold, leveringsform);
                
            }
            
            bst.containerOpen(out);
            
            //db.skrivModuler(out);
            
            List<Module> moduleList = db.getModuler();
            for (Module modul : moduleList){
                String moduleId = modul.getModuleId();
                String moduleName = modul.getModuleName();
                String moduleDesc = modul.getModuleDesc();
                //String moduleContent = modul.getModuleContent();
                //Boolean moduleIsPublished = modul.getIsPublished();
                //Boolean moduleInInterview = modul.getInInterview();
                out.println("<div class=\"row\">");
                bst.bootstrapCard(out,moduleId, moduleName, moduleDesc);
                //out.println("<a href=\"OneModule?id="+ moduleId+"\">"+ moduleName + ", " + moduleDesc +"</a><br>");
                if(user.getUserIsTeacher()){
                deleteModule(out,moduleId);
                }
                out.println("</div>");
            }
            bst.containerClose(out);
            
            if(user.getUserIsTeacher()){
                addModuleForm(out);
            }
            bst.bootstrapFooter(out);
        }
    }
        private void addModuleForm(PrintWriter out)  {
            
            out.println("<div>");
          //  out.println("<a href=\"Modules\">");
           // out.println("</a>");
            out.println("<h1>Legg til modul</h1>");
            out.println("<form action=\"Modules\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type =\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<input type=\"text\" name=\"Innhold\"><br>");      
            out.println("<br>");
            out.println("<h3>Velg leveringsform</h3>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Muntlig\">Muntlig");
            out.println("<br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Video\">Video");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til modul\"><br>");
            out.println("</form>");
            out.println("</div>");
            
            out.println("<h1>Legg til læringsmål</h1><br>");
            out.println("<form action=\"Modules\" method=\"POST\">");
            out.println("<button onclick=\"newLearnGoal()\">Nytt læringsmål</button>");
            out.println("");
            out.println("<input type=\"text\" name=\"Læringsmål\"<br>");
            out.println("</form>");
 
        }
        private void deleteModule(PrintWriter out, String id){
      
        out.println("<form action=\"RemoveModule\" method=\"POST\">");
        out.println("<input name=\"remove\" value=\"TRUE\" style=\"visibility:hidden;\"></input>");
        out.println("<input name=\"id\" value=\""+ id +"\" style=\"visibility:hidden;\"></input>");
        out.println("<input type=\"submit\" value=\"Remove module\"></submit>");
        out.println("</form><br>");
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