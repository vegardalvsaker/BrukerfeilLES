package Servlets;

import Classes.Evaluation;
import Classes.LearningGoal;
import Classes.Module;
import Classes.Score;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import Database.EvaluationDb;
import Database.ModuleDb;
import java.util.ArrayList;

/**
 *
 * @author Gorm-Erik
 */
@WebServlet(name = "OneResult", urlPatterns = {"/OneResult"})
public class OneResult extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            deliveries2(out, request);
        }
            
    }
    
    private void deliveries(PrintWriter out)   {
        
        out.println("<table style=\"width:50%\">");
        out.println("<tr>");
        out.println("<th>Modulnavn</th>");
        out.println("<th>Lastname</th> ");
        out.println("<th>Age</th>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>Jill</td>");
        out.println("<td>Smith</td> ");
        out.println("<td>50</td>");
        out.println("</tr>");
        out.println("</table>");
        
        
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
