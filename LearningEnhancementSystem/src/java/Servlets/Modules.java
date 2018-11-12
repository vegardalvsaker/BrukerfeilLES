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
import java.util.List;
import Classes.Module;
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
    private final BootstrapTemplate bst = new BootstrapTemplate();
    private ModuleDb db = new ModuleDb();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){  
            super.processRequest(request, response, "Modules", out);
         
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
            
            printModules(request, out);
            
            
            
            addModuleForm(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            
        }
    }
    
    private void printModules(HttpServletRequest request, PrintWriter out) {
        List<Module> modules = db.getModuler();
        
        
        out.println("<table class=\"table table-hovere\">"
                + "<thead>"
                + "<tr class=\"table-active\">"
                + "<th scope=\"col\">Name</th>"
                + "<th scope=\"col\">Short description</th>");
        
        if (request.isUserInRole("Teacher")) {
            out.println("<th scope=\"col\">Delete</th>");
        }
                out.println("</tr>"
                + "</thead>"
                + "<tbody>");
                
        for (Module module : modules) {
            String moduleId = Integer.toString(module.getModuleid());
            out.println("<tr>"
                    + "<td><a href=\"OneModule?id="+ moduleId +"\">" + module.getName() +"</td>"
                    + "<td>" + module.getDesc() +"</td></a>");
            
           if (request.isUserInRole("Teacher")) {
               out.println("<td><a class=\"btn btn-danger\" href=\"RemoveModule?moduleId="+ moduleId + "\">Delete</a></td>");
           }
           
           out.println("<tr>");
        }
        out.println("</tbody>"
                + "</table>");
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