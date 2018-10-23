/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.LearningGoalDb;
import Database.CommentDb;
import Database.CommentReplyDb;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import java.util.Map;

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
        Map<String, String[]> paramap = request.getParameterMap();
        
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        
        try (PrintWriter out = response.getWriter()) {
            BootstrapTemplate bst = new BootstrapTemplate();
            LearningGoalDb db = new LearningGoalDb();
            CommentDb cdb = new CommentDb();
            CommentReplyDb crdb = new CommentReplyDb();
            db.init();
            cdb.init();
            crdb.init();
            int mId = Integer.parseInt(id);
            
             if (request.getMethod().equals("POST"))  {
                if (paramap.containsKey("delete")) {
                    if (paramap.get("delete")[0].equals("TRUE")) {
                    String comid = request.getParameter("comment_id");
                    int commId = Integer.parseInt(comid);
                    cdb.deleteComment(commId);
                    crdb.deleteAll(commId);
                    }
                } if (paramap.containsKey("deleteR")) {
                    String repid = request.getParameter("reply_id");
                    int replyId = Integer.parseInt(repid);
                    crdb.deleteSingle(replyId);
                }
                if (paramap.containsKey("comment")) {
                    String comText = request.getParameter("comment");
                    if (comText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    cdb.addComment(mId, "1", comText);
                }
                if (paramap.containsKey("reply") && paramap.containsKey("comment_id")){
                    int comId = Integer.parseInt(request.getParameter("comment_id"));
                    String repText = request.getParameter("reply");
                    if (repText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    crdb.addReply(comId, "1", repText);
                }
            }
            
            bst.bootstrapHeader(out, "Module " + id);
            bst.bootstrapNavbar(out, "Modules");

            db.printLearningGoals(id, out);
            cdb.printComments(mId,out);
            cdb.addCommentForm(out,mId);
            bst.bootstrapFooter(out); 
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
