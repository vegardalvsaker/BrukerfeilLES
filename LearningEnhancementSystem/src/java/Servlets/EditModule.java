package Servlets;

import Classes.LearningGoal;
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
import java.util.Map;
import Database.LearningGoalDb;
/**
 *
 * @author Gorm-Erik
 */

@WebServlet(name = "EditModule", urlPatterns = {"/EditModule"})
public class EditModule extends SuperServlet {

    BootstrapTemplate bootstrap = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest(request, response, "Modules", out);
            ModuleDb db = new ModuleDb();
            LearningGoalDb learnGoalDb = new LearningGoalDb();
            db.init();
            
             
            if (request.getMethod().equals("POST")) {
                
                String modulName = request.getParameter("Modulnavn");
                String modulDesc = request.getParameter("Beskrivelse");
                String modulContent = request.getParameter("Innhold");
                String leveringsform = request.getParameter("leveringsform");
                
                Map map = request.getParameterMap();
                
                for (int i = 0; i < (map.size()- 4)/2; i++)    {
                    
                    String learnGoalText = request.getParameter("Laringsmal" + i);
                    String learnGoalPoints = request.getParameter("Points" + i);
                    
                    
                }
                
                db.editModule(out, request, modulName, modulDesc, modulContent, leveringsform);
                
            }
            

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
            LearningGoalDb lgdb = new LearningGoalDb();
            LearningGoal learnGoal = new LearningGoal();
            
            String modulName = module.getName();
            String modulDesc = module.getDesc();
            String modulContent = module.getContent();
            String learngoalText = learnGoal.getText();
            String learnGoalPoints = learnGoal.getPoints();
            
            out.println("<h1>Rediger modul</h1>");
            out.println("<form action=\"EditModule?id="+module_id+"\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type=\"text\" name=\"Modulnavn\" value=\""+ modulName +"\">");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\" value=\""+ modulDesc +"\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<input type=\"text\" name=\"Innhold\" value=\""+ modulContent + "\"><br>");
            out.println("<h3>Læringsmål og poeng</h3><br>");
            
            lgdb.learnGoalPrinter(out, request);
            
            
            
            out.println("<input type=\"submit\" value=\"Rediger modul\"><br>");     
            
            
            
            
            
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