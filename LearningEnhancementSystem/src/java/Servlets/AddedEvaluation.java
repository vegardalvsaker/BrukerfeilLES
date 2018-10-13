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
import Classes.Delivery;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "AddedEvaluation", urlPatterns = {"/AddedEvaluation"})
public class AddedEvaluation extends HttpServlet {

    private Module module;
    private Delivery delivery;
    private String comment;
    private Evaluation evaluation;
    private BootstrapTemplate bst = new BootstrapTemplate();
    private EvaluationDb eDb = new EvaluationDb();;
    private ScoreDb sDb = new ScoreDb();
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
            insertEvaluationAndScore(request);
            bst.bootstrapHeader(out, "Evaluation for " + module.getName());
            bst.bootstrapNavbar(out, "Home");
            printOptions(request, out);
            out.println("<br><a href=\"EvaluateServlet\">Go back</a>");
        }
    }
    /**
     * Metode som legger inn kommentaren og poengene til evalueringen i databasen.
     */
    private void insertEvaluationAndScore(HttpServletRequest request) {
        String deliveryid = delivery.getDeliveryid();
        eDb.finishEvaluation(deliveryid, comment);
        ArrayList<String> givenPoints = new ArrayList<>();
        givenPoints = getListOfGivenPoints(request);
        int i = 0;
        for (LearningGoal lg : module.getLearningGoals()) {
            String lgId = lg.getLearn_goal_id();
            sDb.giveScore(eDb.getEvaluationId(delivery.getDeliveryid()), lgId, givenPoints.get(i));
            i++;
        }
    }
    /**
     * Metode som returnerer ei liste med de poengene som ble gitt i evalueringen.
     * @param request
     * @param numberOfGoals
     * @return 
     */
    private ArrayList<String> getListOfGivenPoints(HttpServletRequest request){
        ArrayList<String> points = new ArrayList<>();
        int numberOfLearnGoals = module.getLearningGoals().size();
        for (int i = 1; i < numberOfLearnGoals + 1; i++) {
            points.add(request.getParameter("learngoal" + i));
        }
        return points;
    }
    
    private void assignVariableValues(HttpServletRequest request){
        module = (Module) request.getSession().getAttribute("module");
        delivery = (Delivery) request.getSession().getAttribute("delivery");
        comment = request.getParameter("comment");
        
        
        
    }
    
    private void printOptions(HttpServletRequest request, PrintWriter out) {
        ArrayList<Score> scores = new ArrayList<>();
        evaluation = eDb.getEvaluationWithScore(delivery.getDeliveryid());
        scores = evaluation.getScorelist();
        
        ArrayList<LearningGoal> lgoals = new ArrayList<>();
        lgoals = module.getLearningGoals();
        String student = (String)request.getSession().getAttribute("student");
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
        
        request.getSession().setAttribute("Evaluation", evaluation);    
        out.println("<a style=\"float: left;\" href=\"PublishedEvaluation\"><button class=\"btn btn-success\"> Publish evaluation!</button></a>");
        out.println("<a style=\"float: right;\" href=\"DeletedEvaluation\"><button class=\"btn btn-danger\"> Delete evaluation!</button></a>");
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