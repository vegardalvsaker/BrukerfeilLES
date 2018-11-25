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
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.EvaluationDb;
import Database.DeliveryDb;
import Classes.Evaluation;
import java.util.Enumeration;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "PublishedEvaluation", urlPatterns = {"/PublishedEvaluation"})
public class PublishedEvaluation extends HttpServlet {

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
        System.out.println("on servlet 'PublishedEvaluation'");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Evaluation evaluation = (Evaluation) request.getSession().getAttribute("Evaluation");
            if (evaluation == null) {
                out.println("<h1 style=\"text-aligned: center;\">Her skal du vel strengt tatt ikke være?</h1>");
                out.println("<a href=\"Index\">Gå hjem igjen</a>");
            } else {
                EvaluationDb eDb = new EvaluationDb();
                DeliveryDb dDb = new DeliveryDb();
                eDb.publish(true, evaluation.getEvaluationid());
                dDb.updateIsEvaluated(evaluation.getDeliveryid());
                
                HttpSession session = request.getSession();
                session.removeAttribute("Evaluation");
                //debug for å sjekke hvilke objekter som er i session.
                Enumeration enumm = session.getAttributeNames();
                while (enumm.hasMoreElements()) {
                    String namn = (String)enumm.nextElement();
                    session.removeAttribute(namn);
                    System.out.println(namn);
                }
                Enumeration enumm2 = session.getAttributeNames();
                while (enumm2.hasMoreElements()) {
                    System.out.println("Etter fjerning:");
                    System.out.println((String) enumm2.nextElement());
                }
                //out.println("<h1>Evalueringen er offtentliggjort!</h1>");
                //out.println("<a href=\"Index\">Gå hjem</a>");
                out.println("<script>alert(\"Evaluering publisert!\");</script>");
                request.getRequestDispatcher("Index").include(request, response);
            }
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
