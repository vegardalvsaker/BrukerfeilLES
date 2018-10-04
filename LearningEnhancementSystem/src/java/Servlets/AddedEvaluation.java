package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.EvaluationDb;
import Database.ScoreDb;
import Classes.Module;
import Classes.LearningGoal;
import java.util.ArrayList;
import Classes.Evaluation;
import Classes.Score;
import HtmlTemplates.BootstrapTemplate;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "AddedEvaluation", urlPatterns = {"/AddedEvaluation"})
public class AddedEvaluation extends HttpServlet {

    
    private String deliveryid;
    private String moduleid;
    private int numberOfLearnGoals;
    private String comment;
    private String evaluationId;
    private EvaluationDb eDb;
    private ScoreDb sDb;
    private Module module;
    private ArrayList<String> givenPoints = new ArrayList<>();
    private BootstrapTemplate bst = new BootstrapTemplate();
    private Evaluation evaluation;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            assignVariableValues(request);
            eDb.finishEvaluation(deliveryid, comment);
            
            int i = 0;
            for (LearningGoal lg : module.getLearningGoals()) {
                String lgId = lg.getLearn_goal_id();
                sDb.giveScore(evaluationId, lgId, givenPoints.get(i) );
                i++;
            }
            bst.bootstrapHeader(out, moduleid);
            bst.bootstrapNavbar(out, "Home");
            printOptions(request, out);
            out.println("<br><a href=\"EvaluateServlet\">Go back</a>");
        }
    }
    
    private ArrayList<String> makeListOfGivenPoints(HttpServletRequest request, int numberOfGoals){
        ArrayList<String> points = new ArrayList<>();
        for (int i = 1; i < numberOfGoals + 1; i++) {
            points.add(request.getParameter("learngoal" + i));
        }
        return points;
    }
    
    private void assignVariableValues(HttpServletRequest request){
        module = (Module) request.getSession().getAttribute("module");
        deliveryid = request.getParameter("deliveryid");
        moduleid = request.getParameter("module_id");
        numberOfLearnGoals = Integer.parseInt(request.getParameter("numberOfLearnGoals"));
        givenPoints = makeListOfGivenPoints(request, numberOfLearnGoals);
        comment = request.getParameter("comment");
        eDb = new EvaluationDb();
        evaluationId = eDb.getEvaluationId(deliveryid);
        sDb = new ScoreDb();
    }
    
    private void printOptions(HttpServletRequest request, PrintWriter out) {
        Evaluation eval = eDb.getEvaluationWithScore(evaluationId);
        ArrayList<Score> scores = new ArrayList<>();
        scores = eval.getScorelist();
        ArrayList<LearningGoal> lgoals = new ArrayList<>();
        lgoals = module.getLearningGoals();
        String student = (String)request.getSession().getAttribute("student");
        bst.containerOpen(out);
                out.println("<h1>Evaluation for " + student +", " + module.getName() +"</h1>");
                bst.tableOpen(out);
                int i = 1;
                for (LearningGoal lg : lgoals) {
                    bst.tableRow(out, i, lg.getText(), Integer.toString(scores.get(i-1).getPoints()), lg.getPoints());
                    //out.println("<li> Learning goal: " + lg.getText() + " | <input type=\"text\" name=\"learngoal" + i + "\"/>/"+ lg.getPoints() +"</li>");
                    i++;
                }
                bst.tableClose(out);
                out.println("<h3>" + eval.getComment() + "</h3>");
                /**
                int i = 0;
                for (LearningGoal lg : lgoals) {
                    out.println("<li> Learning goal: " + lg.getText() + " | <p> "+ scores.get(i).getPoints() +"/"+ lg.getPoints() +"</li>");
                    i++;
                }
                **/
                request.getSession().setAttribute("Scores", scores);
        out.println("<a style=\"float: left;\" href=\"PublishedEvaluation?evaluationid="+ evaluationId +"\"><button class=\"btn btn-success\"> Publish evaluation!</button></a>");
        out.println("<a style=\"float: right;\" href=\"DeletedEvaluation?evaluationid="+ evaluationId +"\"><button class=\"btn btn-danger\"> Delete evaluation!</button></a>");
        bst.containerClose(out);
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