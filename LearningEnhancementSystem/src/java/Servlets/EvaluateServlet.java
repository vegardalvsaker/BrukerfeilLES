/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ModuleDb;
import Database.EvaluationDb;
import Classes.Delivery;
import Classes.Module;
import Classes.User;
import Classes.LearningGoal;
import Classes.Evaluation;
import Classes.Score;
import java.util.ArrayList;
import HtmlTemplates.BootstrapTemplate;
import Database.DeliveryDb;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "EvaluateServlet", urlPatterns = {"/EvaluateServlet"})
public class EvaluateServlet extends SuperServlet {
    private Module module;
    private BootstrapTemplate bst = new BootstrapTemplate();
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
            super.processRequest(request, response, "Worklist", out);
            //Kjører redigering av evaluation istedenfor en ny
            if (request.getParameterMap().containsKey("editEvaluation")) {
                editEvaluation(request, out);
                return;
            }
            
            if (!request.getParameterMap().containsKey("deliveryId")) {
                out.println("<h1>Ingen delivery valgt</h1>");
                out.println("<a href=\"Worklist\"> Gå tilbake til Worklist</a>");
                out.println("<a href=\"Index\"> Gå hjem</a>");
                return;
            }
                     
            if (setup(request)) {
                EvaluationDb eDb = new EvaluationDb();
                Delivery delivery = (Delivery)request.getAttribute("delivery");
                if (!eDb.evaluationExists(delivery.getDeliveryID())) {
                    Module module = (Module)request.getAttribute("module");
                    
                    bst.containerOpen(out);
                    out.println("<h1> Evaluation for student " + delivery.getUserName() + " for " + module.getName() + "</h1>");

                    ArrayList<LearningGoal> lgoals = module.getLearningGoals();
                    if (lgoals.isEmpty()) {
                        cancelEvaluation(out, request);
                        return;
                    }            
                    
                    //String youtubeUrl = getYoutubeViewHash(delivery.getDeliveryContent());
                    //Print for å vise en embeded YouTube-video.
                    //printEmbeddedYouTubeVideo(out, youtubeUrl);

                    bst.tableOpen(out);
                    out.println("<form id=\"evaluationForm\" action=\"AddedEvaluation\" method=\"POST\">");
                    css(out);
                    printEvaluationTable(lgoals, out);
                    //UI greier
                    bst.tableClose(out);
                    printEndForm(out, "");
                    } else {
                        out.println("Det er allerede en evaluering opprettet for denne studenten på denne modulen");
                    }
                } else {
                    out.println("<h1>Deliveryen du har valgt finnes ikke</h1>");
                    out.println("<a href=\"Worklist\">Gå til worklist</a><br>");
                    out.println("<a href=\"Index\">Gå hjem</a><br>");
                }
               bst.containerClose(out);
               bst.bootstrapFooter(out);
        }
    }

    private void printEvaluationTable(ArrayList<LearningGoal> lgoals, PrintWriter out) {
        int i = 1;
        for (LearningGoal lg : lgoals) {
            int maxPoints = lg.getPoints();
            bst.tableRow(out, i, lg.getText(), "<input type=\"number\" minlength=\"1\" required=\"true\" max=\""+ maxPoints+"\" name=\"learngoal" + i + "\"/>", maxPoints);
            i++;
        }
    }
    
    /**
     * Henter viktig informasjon og setter det i requests og session.
     * @param request
     * @return true if the setup goes well, and false if the delivery does not exist. 
     */
    private boolean setup (HttpServletRequest request) {
        DeliveryDb dDb = new DeliveryDb();
        Delivery delivery = dDb.getDeliveryWithUser(request.getParameter("deliveryId"));
        if (delivery == null) {
            return false;
        }
        request.setAttribute("delivery", delivery);
        
        ModuleDb mdb = new ModuleDb();
        Module module = mdb.getModuleWithLearningGoals(delivery.getModuleID());
        request.setAttribute("module", module);
        request.getSession().setAttribute("module", module);
        request.getSession().setAttribute("delivery", delivery);
        
        //Lagrer den aktuelle studenten som skal bli evaluert i session
        request.getSession().setAttribute("student", delivery.getUserName());
        
        return true;
    }
    /*
    Metode for når læreren vil gå tilbake for å endre på evalueringen.
    */
    private void editEvaluation(HttpServletRequest request, PrintWriter out) {
        HttpSession session = request.getSession();
        Evaluation eval = (Evaluation)session.getAttribute("Evaluation");
        ArrayList<Score> scores = eval.getScorelist();
        Module mod = (Module)session.getAttribute("module");
        
        css(out);
        bst.containerOpen(out);
        bst.tableOpen(out);
        out.println("<form id=\"evaluationForm\" action=\"AddedEvaluation\" method=\"POST\">");
        int i = 1;
        for (LearningGoal lg : mod.getLearningGoals()) {
            bst.tableRow(out, i, lg.getText(), "<input type=\"number\" minlength=\"1\" required=\"true\" max=\""+ lg.getPoints()+"\" name=\"learngoal" + i + "\" value=\""+ Integer.toString(scores.get(i-1).getPoints()) +"\"/>", lg.getPoints());
            i++;
        }
        bst.tableClose(out);
        out.println("<input type=\"hidden\" name=\"edited\"></input>");
        printEndForm(out, eval.getComment());
        bst.containerClose(out);
    }
    
    private String getYoutubeViewHash(String url) {
        String[] segments = url.split("=");
        System.out.println(segments);
        return segments[1];
    }
    
    private void printEmbeddedYouTubeVideo(PrintWriter out, String url) {
        //Print for å vise en embeded YouTube-video.
                out.println("<div class=\"row\">\n" +
                "      <div class=\"col-md-4 offset-md-3 mb-3\">\n" +
                "    <iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"+ url +"?rel=0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>\n" +
                "  </div>");
    }
    
    private void printEndForm(PrintWriter out, String comment) {
        out.println("  <div class=\"form-group row\">\n" +
"        <div class=\"col-md-4 offset-md-4 mb-3\">\n" +
"          <label for=\"tekst\">Comment for the student</label>");
                out.println("<textarea minlength=\"1\" class=\"form-control\" form=\"evaluationForm\" name=\"comment\">"+ comment +"</textarea>");
                out.println("<button type=\"submit\" class=\"btn btn-primary\">Evaluate!</button>");
                out.println("</div>\n" +
"      </div>");
                out.println("</form>");
    }
    
    private void cancelEvaluation(PrintWriter out, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("module");
        session.removeAttribute("student");
        session.removeAttribute("delivery");
        out.println("<h1>Modulen som deliveryen angår, har ingen læringsmål.<br> <a href=\"OneModule?id="+ module.getModuleid() + "\">Legg til noen læringsmål</a> før du evaluerer videre</h1>");
        bst.containerClose(out);
        bst.bootstrapFooter(out);
    }
    
    private void css(PrintWriter out) {
        out.println("<style> input:invalid {"
                + "background-color: #ff5b5b; }"
                + "input:valid {"
                + "background-color: #a4f293; }"
                + "</style>");
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