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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.UserDb;
import Database.ModuleDb;
import Database.EvaluationDb;
import Classes.*;
import java.util.ArrayList;
import HtmlTemplates.BootstrapTemplate;
import Database.DeliveryDb;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "EvaluateServlet", urlPatterns = {"/EvaluateServlet"})
public class EvaluateServlet extends SuperServlet {
    private Delivery delivery;
    private Module module;
    private String teacherId;
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
            System.out.println("on servlet 'EvaluateEvaluation'");
            //Printer en startknapp
            out.println("<a href=\"EvaluateServlet?deliveryId="+ request.getParameter("deliveryId") +"&start=TRUE\"><button  class=\"btn btn-primary\">Start</button></a>");
            //Sjekker at lærereren har trykket på start
            if (request.getParameter("start").equals("TRUE")) {
                setup(request);
            
            
                //User interface greier
                bst.bootstrapHeader(out, "Evaluation for " + module.getName());
                bst.bootstrapNavbar(out, "Home");
                bst.containerOpen(out);
                request.getSession().setAttribute("module", module);
                EvaluationDb eDb = new EvaluationDb();
                //Sjekker om det finnes en evaluering for denne studenten allerede, og oppretter en ny evaluering hvis ikke. (Parametrene i metoden under er hardkodet frem til worklist blir ferdig
                if (eDb.addEvaluation(teacherId, delivery.getDeliveryid())) {
                    out.println("<h1> Evaluation for student " + delivery.getStudent_name() + " for " + module.getName() + "</h1>");
                    
                //Henter de læringsmålene som lærereren skal evaluere etter    
                ArrayList<LearningGoal> lgoals = module.getLearningGoals();
                
                //Printer begynnelsen på tabellen som lærereren skal evaluere i
                bst.tableOpen(out);
                
                //String youtubeUrl = getYoutubeViewHash(delivery.getDeliveryContent());
                //Print for å vise en embeded YouTube-video.
               // out.println("<div class=\"row\">\n" +
               // "      <div class=\"col-md-4 offset-md-3 mb-3\">\n" +
               // "    <iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/"+ youtubeUrl +"?rel=0\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>\n" +
               // "  </div>");
                
                //Form med link til servleten hvor faktisk alle poengene i evalueringen blir plottet inn i databasen. URL-parametrene er hardkodet for nå
                out.println("<form id=\"evaluationForm\" action=\"AddedEvaluation\" method=\"POST\">");
                
                
                //Printer radene i evalueringstabellen
                int i = 1;
                for (LearningGoal lg : lgoals) {
                    bst.tableRow(out, i, lg.getText(), "<input type=\"number\" name=\"learngoal" + i + "\"/>", lg.getPoints());
                    i++;
                }
                
                //UI greier
                bst.tableClose(out);
                out.println("  <div class=\"form-group row\">\n" +
"        <div class=\"col-md-4 offset-md-4 mb-3\">\n" +
"          <label for=\"tekst\">Comment for the student</label>");
                out.println("<textarea class=\"form-control\" form=\"evaluationForm\" name=\"comment\"></textarea>");
                out.println("<button type=\"submit\" class=\"btn btn-primary\">Evaluate!</button>");
                out.println("</div>\n" +
"      </div>");
                out.println("</form>");
                
                bst.containerClose(out);
                bst.bootstrapFooter(out);
                
                } else {
                    out.println("Det er allerede en evaluering opprettet for denne studenten på denne modulen");
                }
            }  
        }
    }

    /**
     * Henter den studenten som skal evalueres, hvilken modul og deliveryen. Disse blir lagt i globale variabler.
     * @param student_id
     * @param module_id 
     */
    public void setup (HttpServletRequest request) {
        DeliveryDb dDb = new DeliveryDb();
        delivery = dDb.getDeliveryWithUser(request.getParameter("deliveryId"));
        
        ModuleDb mdb = new ModuleDb();
        mdb.init();
        module = mdb.getModuleWithLearningGoals(delivery.getModule_id());
        System.out.println(module.getName());
        
        request.getSession().setAttribute("delivery", delivery);
        
        //Lagrer den aktuelle studenten som skal bli evaluert i session
        request.getSession().setAttribute("student", delivery.getStudent_name());
        setUserLoggedIn(request);
        User teacher = (User)request.getSession().getAttribute("userLoggedIn");
        teacherId = teacher.getUserId();  
        //teacherId = "100";
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

    private String getYoutubeViewHash(String url) {
        String[] segments = url.split("=");
        System.out.println(segments);
        return segments[1];
    }
}