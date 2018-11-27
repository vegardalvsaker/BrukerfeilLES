package Servlets;

import Classes.Evaluation;
import Classes.LearningGoal;
import Classes.Module;
import Classes.Score;
import Classes.User;
import Classes.Results;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import Database.EvaluationDb;
import Database.ModuleDb;
import Database.ResultsDb;
import HtmlTemplates.BootstrapTemplate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gorm-Erik
 */
@WebServlet(name = "OneResult", urlPatterns = {"/OneResult"})
public class OneResult extends SuperServlet {

    BootstrapTemplate bst = new BootstrapTemplate();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest(request, response, "Results", out);
            User user = (User)request.getSession().getAttribute("userLoggedIn");
            bst.containerOpen(out);
            if (request.getMethod().equals("POST")) {
                String evalId = request.getParameter("evaluationId");
                oneResult(out,evalId);
            }
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            //deliveries2(out, request);
            
            
        }
            
    }
    
    private void oneResult(PrintWriter out, String evalId)   {
        
        ResultsDb resultsDb = new ResultsDb();
        resultsDb.init();
        
        ArrayList<Results> resultsList = resultsDb.getResults(evalId);
        
            out.println("<table class=\"table table-hovere\">");
            out.println("<thead>");
            out.println("<tr class=\"table-active\">");
            out.println("<th scope=\"col\">Læringsmål</th>");
        
            out.println("<th scope=\"col\">Dine poeng</th> ");
            out.println("<th scope=\"col\">Totale poeng</th> ");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
        for (Results results : resultsList)  {
              
            String learnGoal = results.getLearnGoal();
            String LGPoints = results.getLGPoints();
            String ScorePoints = results.getScorePoints();
            
            
        out.println("<td>"+learnGoal+"</td>");
        out.println("<td>"+ScorePoints+"</td>");
        out.println("<td>"+LGPoints+"</td>");
        out.println("</tr>");
        
        }

        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
        
        
        String comment = resultsList.get(0).getEvalComment();
        out.println("<h4>Kommentar fra lærer: </h4>");
        out.println("<p>" + comment + "</p>");
            
        }
        
    
    
    private void deliveries2(PrintWriter out, HttpServletRequest request)   {
        
        
        EvaluationDb evaluationdb = new EvaluationDb();
        ModuleDb moduledb = new ModuleDb();
        moduledb.init();
        String deliveryId = request.getParameter("deliveryID");
        String evaluationid = evaluationdb.getEvaluationId(deliveryId);
        Evaluation eval = evaluationdb.getEvaluationWithScore(evaluationid);
        
        ArrayList<Score> scores = new ArrayList<>();
        scores = eval.getScorelist();
        ArrayList<LearningGoal> lgoals = new ArrayList<>();
        String moduleID = request.getParameter("moduleID");
        
        Module module = moduledb.getModuleWithLearningGoals(moduleID);
        lgoals = module.getLearningGoals();        
                int i = 0;
                for (LearningGoal lg : lgoals) {
                    out.println("<li> Learning goal: " + lg.getText() + " | <p> "+ scores.get(i).getPoints() +"/"+ lg.getPoints() +"</li>");
                    i++;
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