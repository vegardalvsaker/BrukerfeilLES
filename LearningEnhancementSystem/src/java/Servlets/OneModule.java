/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.LearningGoalDb;
import Database.CommentDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "OneModule", urlPatterns = {"/OneModule"})
public class OneModule extends HttpServlet {

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
        String id = request.getParameter("id");
       // String user_id = "Hallgeir";
       // String comment_text = "hei alle sammen";
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            LearningGoalDb db = new LearningGoalDb();
            CommentDb cdb = new CommentDb();
            db.init();
            cdb.init();
            System.out.println(id);
            db.printLearningGoals(id, out);
            
            int mId = Integer.parseInt(id);
            if (request.getMethod().equals("POST"))  {
                String comText = request.getParameter("comment");
                cdb.addComment(mId, "1", comText);
            }
            
            cdb.printComments(mId,out,request);
            addComment(out,request);
            
            
        }
    }
private void addComment(PrintWriter out, HttpServletRequest request){
            String id = request.getParameter("id");
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ id+"\" method=\"POST\">");
            out.println("<h3>Legg til kommentar</h3><br>");
            out.println("<input type =\"text\" name=\"comment\"><br>");           
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");        
            out.println("<br>");
            out.println("</form>");
            out.println("</div>");
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
