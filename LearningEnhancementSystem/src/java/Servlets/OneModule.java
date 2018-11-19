/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Comment;
import Classes.CommentReply;
import Classes.User;

import Database.LearningGoalDb;
import Database.CommentDb;
import Database.CommentReplyDb;
import Database.DeliveryDb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import java.util.List;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "OneModule", urlPatterns = {"/OneModule"})
public class OneModule extends SuperServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Modules", out);
            
            BootstrapTemplate bst = new BootstrapTemplate();
            LearningGoalDb db = new LearningGoalDb();
            CommentDb cdb = new CommentDb();
            CommentReplyDb crdb = new CommentReplyDb();
            db.init();
            cdb.init();
            crdb.init();

            DeliveryDb ddb = new DeliveryDb();
            ddb.init();
            bst.containerOpen(out);

            ddb.getNrOfDeliveries(id,out);


        

             if (request.getMethod().equals("POST"))  {
                if (request.getParameter("edit")!=(null)) {
                    String deliveryId = request.getParameter("deliveryId");
                    String deliveryContent = request.getParameter("deliveryContent");
                    ddb.editDelivery(deliveryContent, deliveryId);
                }else {
                if (request.getParameter("delete")!=(null)){
                    String commId = request.getParameter("comment_id");

                    crdb.deleteAll(commId);
                    cdb.deleteComment(commId);
                }
                if (request.getParameter("deleteR")!=(null)){
                    String repid = request.getParameter("reply_id");
                    crdb.deleteSingle(repid);
                }
                if (request.getParameter("addC")!=(null)) {
                    String comText = request.getParameter("comment");
                    if (comText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    cdb.addComment(id, user.getUserId(), comText);
                }
                if (request.getParameter("addR")!=(null)){
                    String commId = request.getParameter("comment_id");
                    String repText = request.getParameter("reply");
                    if (repText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    crdb.addReply(commId, user.getUserId(), repText);
                }
            }
             }

            editModuleButtonForm(out,request);



            db.printLearningGoals(id, out);
            
            List<Classes.Delivery> deliveryList = ddb.getDeliveryWithUserIdAndModuleId(id, user.getUserId());
            if (deliveryList.size() != 0) {
                for (Classes.Delivery delivery : deliveryList){
                     String deliveryId = delivery.getDeliveryID();
                Editdelivery(out,deliveryId, id);
                }
            } else {
                
                deliver(out,request);
            }
            
                
            
            List<Comment> commentList = cdb.getComments(id);
            bst.collapseTop(out);
            for (Comment comment : commentList){
                String commentId = comment.getCommentId();
                String commentText = comment.getCommentText();
                String commentUserName = comment.getUserName();
                String commentUserId = comment.getUserId();
                String commentModuleId = comment.getModuleId();
                    out.println("<p>" + commentText + "</p>");
                    out.println("<p>" + commentUserName + "</p>");
                    if (user.getUserIsTeacher()||(user.getUserId().equals(commentUserId))){
                    deleteComment(out,commentModuleId,commentId);
                    }
                    List<CommentReply> replyList = crdb.getCommentReplys(commentId);
                    for (CommentReply reply : replyList){
                        String replyId = reply.getReplyId();
                        String replyText = reply.getReplyText();
                        String replyUserName = reply.getUserName();
                        String replyUserId = reply.getUserId();
                            out.println("<hr class=\"my-4\">");
                            out.println("<p style=\"margin-left:2.5em;\">" + replyText + "</p>");
                            out.println("<p style=\"margin-left:2.5em;\">" + replyUserName + "</p>");
                            if (user.getUserIsTeacher()||(user.getUserId().equals(replyUserId))){
                            deleteReply(out,commentModuleId,replyId);
                            }
                        }
                        addReply(out,commentModuleId,commentId);
                        out.println("<hr class=\"my-4\">");
            }
            addComment(out,id);
            bst.collapseBottom(out);

            bst.containerClose(out);
            bst.bootstrapFooter(out); 
        }
        }
    
    
    private void editModuleButtonForm(PrintWriter out, HttpServletRequest request)    {
        String id = request.getParameter("id");
        
            out.println("<a href=\"EditModule?id="+ id +"\">"
                    + "<button>Rediger modul</button>"
  

                    + "</a>");                    

    }
    
    private void deliver(PrintWriter out, HttpServletRequest request){
            String id = request.getParameter("id");
            out.println("<a href=\"Delivery?id="+ id +" \"a class=\"btn btn-info\">Deliver!</button></a>");
    }
    
    private void addComment(PrintWriter out, String moduleId){
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ moduleId +"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"addC\" value=\"TRUE\">");
            out.println("<h3>Legg til kommentar</h3><br>");
            out.println("<textarea rows=\"4\" cols=\"30\" name=\"comment\"></textarea>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");        
            out.println("</form>");
            out.println("</div>");
    }
    
    private void deleteComment(PrintWriter out, String moduleId, String commentId) {
            out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"delete\" value=\"TRUE\">");
            out.println("<input type=\"hidden\" name=\"comment_id\" value=\""+ commentId +"\"/>");
            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete comment\">");
            out.println("</form>");
    }
    
    private void addReply(PrintWriter out, String moduleId,String commentId){
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ moduleId +"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"addR\" value=\"TRUE\">");
            out.println("<p style=\"margin-left:2.5em;\"> Svar:</p>");
            out.println("<textarea style=\"margin-left:2.5em;\" rows=\"2\" cols=\"30\" name=\"reply\"></textarea>"); 
            out.println("<input style=\"margin-left:2.5em;\" type=\"hidden\" name=\"comment_id\" value=\""+ commentId +"\"/><br>");
            out.println("<input style=\"margin-left:2.5em;\" type=\"submit\" value=\"Legg til\">");        
            out.println("</form>");
            out.println("</div>");
    }
    private void Editdelivery(PrintWriter out, String deliveryId, String moduleId){
            out.println("<form action=\"EditDelivery?id="+ deliveryId+"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"moduleId\" value=\""+ moduleId +"\">");
            out.println("<input type=\"hidden\" name=\"deliveryId\" value=\""+ deliveryId +"\">");
            out.println("<input type =\"hidden\" name=\"comment\"><br>");           
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Edit delivery\"><br>");        
            out.println("<br>");
            out.println("</form>");
    }
private void deleteReply(PrintWriter out, String moduleId, String replyid )  {
            out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
            out.println("<input style=\"margin-left:2.5em;\" type=\"hidden\" name=\"deleteR\" value=\"TRUE\">");
            out.println("<input style=\"margin-left:2.5em;\" type=\"hidden\" name=\"reply_id\" value=\""+ replyid +"\"/>");
            out.println("<input style=\"margin-left:2.5em;\" type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete reply\">");
            out.println("</form>");
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
