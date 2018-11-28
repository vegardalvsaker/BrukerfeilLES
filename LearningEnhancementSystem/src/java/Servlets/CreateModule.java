package Servlets;

import Database.LearningGoalDb;
import Database.ModuleDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gorm-Erik
 */
@WebServlet(name = "CreateModule", urlPatterns = {"/CreateModule"})
public class CreateModule extends SuperServlet {

    BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "CreateModule", out);
            
            ModuleDb db = new ModuleDb();
            LearningGoalDb learnGoal = new LearningGoalDb();
            db.init();
            
            
            if (request.getMethod().equals("POST"))  {
                
                String modulnavn = request.getParameter("Modulnavn");
                
                String beskrivelse = request.getParameter("Beskrivelse");
                
                String innhold = request.getParameter("Innhold");
                
                String leveringsform = request.getParameter("leveringsform");
                
                boolean published;

                Map map = request.getParameterMap();
                
                published = !map.containsKey("save"); 
                    
                db.addModule(out, modulnavn, beskrivelse, innhold, leveringsform, published);
                
                
                for (int i = 0; i < (map.size() - 4)/2; i++)   {    //Looper gjennom parameterMap for å finne antall læringsmål
                    
                    String learnGoalText = request.getParameter("Laringsmal" + i);
                    String learnGoalPoints = request.getParameter("Poeng" + i);
                    
                    learnGoal.addLearningGoals(out, learnGoalText, learnGoalPoints, modulnavn);
                }
                        
                  redirectHeader(out);      
              
            }
            
            bst.containerOpen(out);
            
            addModuleForm(out);
            
            bst.containerClose(out);
            
            bst.bootstrapFooter(out);
            
         }
    }
    
   
    
    private void addModuleForm(PrintWriter out)  {
            
            out.println("<html>");
            out.println("<div>");
            out.println("<h1>Opprett modul</h1>");
            out.println("<form action=\"CreateModule\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type =\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<textarea rows=\"10\" cols=\"50\" name=\"Innhold\"></textarea><br>");      
            out.println("<br>");
            out.println("<h3>Velg leveringsform</h3><br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Muntlig\">Muntlig");
            out.println("<br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Video\">Video");
            out.println("<br>");
       
            out.println("<script language=\"javascript\">");        //JavaScript for å legge til input-felt dynamisk
            out.println("var i = 0;");
            out.println("function add() {");
	    out.println("var div = document.createElement(\"div\");");
            out.println("div.innerHTML = '<textarea rows=\"1\" cols=\"45\" name=\"Laringsmal' + i + '\"></textarea> <textarea rows=\"1\" cols=\"10\" name=\"Poeng' + i + '\"></textarea><br>';");
            out.println("i++");
            out.println("var id = document.getElementById(\"inputID\");");
            out.println("id.appendChild(div);");
            out.println("}");
            out.println("</script>");
            out.println("<style>");
            out.println("h3 {display:inline;}");
            out.println("</style>");
            
            out.println("<h3>Læringsmål og Poeng</h3>");
            out.println("<span id=\"inputID\">&nbsp;</span><br>");
            out.println("<input type=\"button\" value=\"Nytt læringsmål\" onclick=\"add()\"/><br>");
            out.println("<input type=\"checkbox\" name=\"save\">Lagre modul uten å publisere</input><br>");
            out.println("<input type=\"submit\" value=\"Submit\"><br>");
            out.println("</form>");
            out.println("</div>");
            out.println("</html>");
     
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
