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

/**
 *
 * @author Vegard
 */
@WebServlet(name = "EvaluateServlet", urlPatterns = {"/EvaluateServlet"})
public class EvaluateServlet extends HttpServlet {
    private User user;
    private Delivery delivery;
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
            //setup(request.getParameter("student_id"), request.getParameter("module_id"));
            //hardkodet, metoden over vil bli brukt når worklist er ferdig
            
            
            
            setup("1000", "1");
            bst.bootstrapHeader(out, "Evaluation for" + module.getName());
            bst.bootstrapNavbar(out, "Home");
            bst.containerOpen(out);
            request.getSession().setAttribute("module", module);
            out.println("<button href=\"EvaluateServlet?start=TRUE\" class=\"btn btn-primary\">Start</button>");
            if (request.getParameter("start").equals("TRUE")) {
                EvaluationDb eDb = new EvaluationDb();
                if (eDb.addEvaluation("100", "2")) {
                    out.println("<h1> Evaluation for student " + user.getUserName() + " for " + module.getName() + "</h1>");
              
                ArrayList<LearningGoal> lgoals = module.getLearningGoals();
                bst.tableOpen(out);
                out.println("<form id=\"evaluationForm\" action=\"AddedEvaluation?deliveryid=2&studentid=1000&module_id=1&numberOfLearnGoals="+ lgoals.size() +"\" method=\"POST\">");
                int i = 1;
                
                for (LearningGoal lg : lgoals) {
                    bst.tableRow(out, i, lg.getText(), "<input type=\"text\" name=\"learngoal" + i + "\"/>", lg.getPoints());
                    //out.println("<li> Learning goal: " + lg.getText() + " | <input type=\"text\" name=\"learngoal" + i + "\"/>/"+ lg.getPoints() +"</li>");
                    i++;
                }
                bst.tableClose(out);
                
                out.println("<textarea class=\"form-control\" form=\"evaluationForm\" name=\"comment\"></textarea>");
                out.println("<button type=\"submit\" class=\"btn btn-primary\">Evaluate!</button>");
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
     * Henter den studenten som skal evalueres og hvilken modul. Disse blir lagt i globale variabler.
     * @param student_id
     * @param module_id 
     */
    public void setup (String student_id, String module_id) {
        UserDb uDb = new UserDb();
        uDb.init();
        user = uDb.getUser(student_id);
                       
        ModuleDb mdb = new ModuleDb();
        mdb.init();
        module = mdb.getModuleWithLearningGoals(module_id);
        
       
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