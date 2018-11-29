package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Database.EvaluationDb;
import Database.ScoreDb;
import Classes.User;
import Classes.Module;
import Classes.LearningGoal;
import java.util.ArrayList;
import Classes.Evaluation;
import Classes.Score;
import HtmlTemplates.BootstrapTemplate;
import Classes.Delivery;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "AddedEvaluation", urlPatterns = {"/AddedEvaluation"})
public class AddedEvaluation extends SuperServlet {

    private final BootstrapTemplate bst = new BootstrapTemplate();
    private final EvaluationDb eDb = new EvaluationDb();;
    private final ScoreDb sDb = new ScoreDb();
    
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
        System.out.println("on servlet 'AddedEvaluation'");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Worklist", out);
            if (request.getParameterMap().containsKey("edited")) {
                editEvaluation(out, request);
                return;
            }
            
            if (!insertEvaluationAndScore(request)) {
                out.println("<h1>Her har det skjedd en feil! Kontakt databaseadmin</h1>");
                return;
            }
            
            printResult(request, out);
            out.println("<br><a href=\"EvaluateServlet\">Go back</a>");
        }
    }
    /**
     * Metode som legger inn kommentaren og poengene til evalueringen i databasen.
     */
    private boolean insertEvaluationAndScore(HttpServletRequest request) {
        Delivery delivery = (Delivery) request.getSession().getAttribute("delivery");
        String deliveryid = delivery.getDeliveryID();
        User teacher = (User)request.getSession().getAttribute("userLoggedIn");
        if(!eDb.addEvaluation(teacher.getUserId(), deliveryid)) {
            return false;
        }
        String comment = request.getParameterMap().containsKey("comment") ? request.getParameter("comment") : "";
        eDb.addComment(deliveryid, comment);
        ArrayList<String> givenPoints = new ArrayList<>();
        givenPoints = getListOfGivenPoints(request);
        if (givenPoints.isEmpty()) {
            return false;
        }
        Module module = (Module) request.getSession().getAttribute("module");
        int i = 0;
        for (LearningGoal lg : module.getLearningGoals()) {
            String lgId = lg.getLearn_goal_id();
            int points = Integer.parseInt(givenPoints.get(i));
            if (points < 0 || points > lg.getPoints()) {
                return false;
            }
            sDb.giveScore(eDb.getEvaluationId(delivery.getDeliveryID()), lgId, givenPoints.get(i));
            i++;
        }
        return true;
    }
    /**
     * Metode som returnerer ei liste med de poengene som ble gitt i evalueringen.
     * @param request
     * @param numberOfGoals
     * @return 
     */
    private ArrayList<String> getListOfGivenPoints(HttpServletRequest request){
        ArrayList<String> points = new ArrayList<>();
        Module module = (Module) request.getSession().getAttribute("module");
        int numberOfLearnGoals = module.getLearningGoals().size();
        for (int i = 1; i < numberOfLearnGoals + 1; i++) {
            points.add(request.getParameter("learngoal" + i));
        }
        return points;
    }
     
    private void editEvaluation(PrintWriter out, HttpServletRequest request) {
        Evaluation eval = (Evaluation)request.getSession().getAttribute("Evaluation");
        eDb.addComment(eval.getComment(), eval.getDeliveryid());
        int i = 1;
        for (Score score : eval.getScorelist()) {
            String pointsGiven = request.getParameter("learngoal" + i);
            sDb.updateScore(pointsGiven, score.getId());
            i++;
        }
        printResult(request, out);
    }
    
    private void printResult(HttpServletRequest request, PrintWriter out) {
        Delivery delivery = (Delivery) request.getSession().getAttribute("delivery");
        Evaluation evaluation = eDb.getEvaluationWithScore(delivery.getDeliveryID());
        
        ArrayList<Score> scores = new ArrayList<>();
        scores = evaluation.getScorelist();
        
        Module module = (Module) request.getSession().getAttribute("module");
        ArrayList<LearningGoal> lgoals = new ArrayList<>();
        lgoals = module.getLearningGoals();
        
        String student = (String)request.getSession().getAttribute("student");
        String comment = request.getParameter("comment");
        
        bst.containerOpen(out);
        out.println("<h1>Evaluation for " + student +", " + module.getName() +"</h1>");
        bst.tableOpen(out);
        
        int i = 1;
        for (LearningGoal lg : lgoals) {
            bst.tableRow(out, i, lg.getText(), Integer.toString(scores.get(i-1).getPoints()), lg.getPoints());
            i++;
        }

        bst.tableClose(out);
        out.println("<h3>" + comment + "</h3>");
        
        HttpSession session = request.getSession();
        session.setAttribute("Evaluation", evaluation);    
        printButtons(out);
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

    private void printButtons(PrintWriter out) {
        out.println("<a style=\"float: left;\" href=\"PublishedEvaluation\"><button class=\"btn btn-success\"> Publish evaluation!</button></a>");
        out.println("<a style=\"float: inherit;\" href=\"EvaluateServlet?editEvaluation\"><button class=\"btn btn-warning\">Edit Evaluation</button></a>");
        out.println("<a style=\"float: right;\" href=\"DeletedEvaluation\"><button class=\"btn btn-danger\"> Delete evaluation!</button></a>");
    }
}