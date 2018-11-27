package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        
        if (request.getParameterMap().containsKey("publish"))  {      
            db.makeModulePublic(request.getParameter("publish"));
            }


            bst.containerOpen(out);
            
            printModules(request, out);
            if (request.isUserInRole("Teacher")) {
                addModuleButton(out);
            }
            bst.containerClose(out);

            
            addModuleButton(out);
            
            
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
            out.println("<th scope=\"col\">Publish</th>");
        }
                out.println("</tr>"
                + "</thead>"
                + "<tbody>");
                
        for (Module module : modules) {
            if (request.isUserInRole("Student") && !module.isPublished()) {
                continue;
            }
            String moduleId = Integer.toString(module.getModuleid());
            out.println("<tr>"
                    + "<td><a href=\"OneModule?id="+ moduleId +"\">" + module.getName() +"</td>"
                    + "<td>" + module.getDesc() +"</td></a>");
            

      

           if (request.isUserInRole("Teacher")) {
               out.println("<td><a class=\"btn btn-danger\" href=\"RemoveModule?moduleId="+ moduleId + "\">Delete</a></td>");
               if (!module.isPublished()) {
                   out.println("<td><a class=\"btn btn-success\" href=\"Modules?publish=" + moduleId+ "\">Publish</a></td>");
               }
           }
           
           out.println("<tr>");
        }
        out.println("</tbody>"
                + "</table>");
    }        
            
    private void addModuleButton(PrintWriter out)  {
     out.println("<a class=\"btn btn-primary\" href=\"CreateModule\">Opprett ny modul</a>");
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