package Servlets;

import Classes.LearningGoal;
import Classes.Module;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ModuleDb;
import HtmlTemplates.BootstrapTemplate;
import java.util.Map;
import Database.LearningGoalDb;
import java.util.ArrayList;
import Database.NotificationDb;

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

            super.processRequest(request, response, "EditModules", out);
            ModuleDb db = new ModuleDb();
            LearningGoalDb learnGoalDb = new LearningGoalDb();

            Map map = request.getParameterMap();

            if (request.getMethod().equals("POST")) {


                String modulName = request.getParameter("Modulnavn");
                String modulDesc = request.getParameter("Beskrivelse");
                String modulContent = request.getParameter("Innhold");
                String lf = request.getParameter("leveringsform");

                boolean leveringsform = false;

                if (lf.equals("true"))   {
                    leveringsform = true;
                } else if (lf.equals("false"))  {
                    leveringsform = false;
                }


                boolean published;
                published = !map.containsKey("unpublish");

                for (int i = 0; i < (map.size()- 4)/2; i++)    {

                    String learnGoalText = request.getParameter("Laringsmal" + i);
                    String learnGoalPoints = request.getParameter("Poeng" + i);
                    String learnGoalId = request.getParameter("LearnGoalID" + i);

                    String newLearnGoalText = request.getParameter("nyttLaringsmal" + i);
                    String newLearnGoalPoint = request.getParameter("nyttPoeng" + i);

                    learnGoalDb.editLearnGoals(out, request, learnGoalText, learnGoalPoints, learnGoalId);

                    if ((newLearnGoalText != null) || (newLearnGoalPoint != null)) {
                    learnGoalDb.addLearningGoals(out, newLearnGoalText, newLearnGoalPoint, modulName);

                    }
                }

                db.editModule(out, request, modulName, modulDesc, modulContent, published, leveringsform);

                redirectHeader(out);

                NotificationDb notification = new NotificationDb();

                if (request.getParameter("varsling").equals("on"))    {

                    notification.sendNotificationsToAll(modulName + " har blitt endret.");

                }
            }


            bootstrap.containerOpen(out);

            editModuleForm(out, request);

            bootstrap.containerClose(out);

            bootstrap.bootstrapFooter(out);

        }
}

    private void editModuleForm(PrintWriter out, HttpServletRequest request)    {


            String module_id = request.getParameter("id");
            ModuleDb db = new ModuleDb();

            Module module = db.getModuleWithLearningGoals(module_id);


            String modulName = module.getName();
            String modulDesc = module.getDesc();
            String modulContent = module.getContent();
            boolean leveringsform = module.getInInterview();

            ArrayList<LearningGoal> learnGoalList = module.getLearningGoals();

            out.println("<h1>Rediger modul</h1>");
            out.println("<form action=\"EditModule?id="+module_id+"\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type=\"text\" name=\"Modulnavn\" value=\""+ modulName +"\">");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\" value=\""+ modulDesc +"\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<textarea rows=\"10\" cols=\"50\" name=\"Innhold\">" + modulContent + "</textarea><br>");
            out.println("<h3>Leveringsform</h3><br>");

            if (leveringsform == true)  {

            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"false\">Video<br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" checked value=\""+ leveringsform + "\">Muntlig<br>");

            }
            else if (leveringsform == false)    {

            out.println("<input type=\"radio\" name=\"leveringsform\" checked value=\""+ leveringsform + "\">Video<br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"true\">Muntlig<br>");

            }

            out.println("<h3>Læringsmål og poeng</h3><br>");

            int i = 0;
            for (LearningGoal learnGoal: learnGoalList) {

                String learnGoalID = learnGoal.getLearn_goal_id();
                String learnGoalText = learnGoal.getText();
                int learnGoalPoints = learnGoal.getPoints();

                out.println("<textarea rows=\"1\" cols=\"45\" name=\"Laringsmal" + i + "\">"+ learnGoalText +"</textarea>");
                out.println("<input type=\"text\" hidden=\"true\" name=\"LearnGoalID" + i + "\" value=\"" + learnGoalID + "\"></input>");
                out.println("<textarea rows=\"1\" cols=\"10\" name=\"Poeng" + i + "\">" + learnGoalPoints + "</textarea><br>");

                i++;
            }

            out.println("<script language=\"javascript\">");
            out.println("var i = 0;");
            out.println("function add() {");


	    out.println("var div = document.createElement(\"div\");");

            out.println("div.innerHTML = '<input type=\"text\" name=\"nyttLaringsmal' + i + '\"></input> <input type=\"text\" name=\"nyttPoeng' + i + '\"></input><br>';");
            out.println("i++");
            out.println("var id = document.getElementById(\"inputID\");");

            out.println("id.appendChild(div);");

            out.println("}");
            out.println("</script>");

            out.println("<span id=\"inputID\">&nbsp;</span><br>");
            out.println("<input type=\"button\" value=\"Nytt læringsmål\" onclick=\"add()\"/></input><br>");

            out.println("<input type=\"checkbox\" name=\"varsling\">Varsle studenter om endring</input><br>");
            out.println("<input type=\"checkbox\" name=\"unpublish\">Gjør modul upublisert</input><br>");

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
