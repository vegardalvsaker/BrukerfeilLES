package Servlets;
import Classes.User;
import Classes.Comment;
import Classes.CommentReply;

import Database.LearningGoalDb;
import Database.CommentDb;
import Database.CommentReplyDb;
import Database.DeliveryDb;

import java.util.Map;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "OneModule", urlPatterns = {"/OneModule"})
public class OneModule extends SuperServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        Map<String, String[]> paramap = request.getParameterMap();
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Modules", out);
            
            BootstrapTemplate bst = new BootstrapTemplate();
            LearningGoalDb db = new LearningGoalDb();
            CommentDb cdb = new CommentDb();
            CommentReplyDb crdb = new CommentReplyDb();
            DeliveryDb ddb = new DeliveryDb();
            db.init();
            cdb.init();
            crdb.init();
            ddb.init();
            
            bst.containerOpen(out);

            ddb.getNrOfDeliveries(id,out);
            
             if (request.getMethod().equals("POST"))  {
                if (paramap.containsKey("delete")) {
                    if (paramap.get("delete")[0].equals("TRUE")) {
                    String comid = request.getParameter("comment_id");
                    cdb.deleteComment(comid);
                    crdb.deleteAll(comid);
                    }
                } 
                if (paramap.containsKey("deleteR")) {
                    String repid = request.getParameter("reply_id");
                    crdb.deleteSingle(repid);
                }
                if (paramap.containsKey("comment")) {
                    String comText = request.getParameter("comment");
                    if (comText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    cdb.addComment(id, user.getUserId(), comText);

                }
                if (paramap.containsKey("reply") && paramap.containsKey("comment_id")){
                    String comId = request.getParameter("comment_id");
                    String repText = request.getParameter("reply");
                    if (repText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    crdb.addReply(comId, user.getUserId(), repText);
                }
            }

            editModuleButtonForm(out,request);

            db.printLearningGoals(id, out);
            deliver(out,request);
            
            List<Comment> commentList = cdb.getComments();
            List<CommentReply> replyList = crdb.getCommentReplys();

            out.println("<p>");
            out.println("<div class=\"jumbotron\">");
            out.println("<div class=\"container\">");
            out.println("<button class=\"btn btn-outline-secondary\" data-toggle=\"collapse\" data-target=\"#collapse\" aria-expanded=\"true\" aria-controls=\"collapse\">");
            out.println("<h4 class=\"display-4\">Kommentarer</h4>");
            out.println("</button>");
            out.println("<hr class=\"my-4\">");
            out.println("<div class=\"collapse\" id=\"collapse\">");
            out.println("<div class=\"card-body\">");
            for (Comment comment : commentList){
                String commentId = comment.getCommentId();
                String commentText = comment.getCommentText();
                String commentUserName = comment.getUserName();
                String commentModuleId = comment.getModuleId();
                if (commentModuleId.equals(id)){
                    out.println("<p>" + commentText + "</p>");
                    out.println("<p>" + commentUserName + "</p>");
                    deleteComment(out,commentModuleId,commentId);
                    for (CommentReply reply : replyList){
                        String replyId = reply.getReplyId();
                        String replyText = reply.getReplyText();
                        String replyUserName = reply.getUserName();
                        String replyCommentId = reply.getCommentId();
                        if (commentId.equals(replyCommentId)){
                            out.println("<hr class=\"my-4\">");
                            out.println("<p style=\"margin-left:2.5em;\">" + replyText + "</p>");
                            out.println("<p style=\"margin-left:2.5em;\">" + replyUserName + "</p>");
                            deleteReply(out,commentModuleId,replyId);
                        }
                    }
                    addReply(out,commentModuleId,commentId);
                    out.println("<hr class=\"my-4\">");
                }
            }
            addComment(out,request);
            out.println("</div>");
            out.println("</div>");
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
    
    private void addReply(PrintWriter out, String moduleId,String commentId){
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ moduleId +"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"delete\" value=\"FALSE\"");
            out.println("<p>Svar:</p>");
            out.println("<input type =\"text\" name=\"reply\">"); 
            out.println("<input type=\"text\" name=\"comment_id\" value=\""+ commentId +"\"style=\"visibility:hidden;\"/><br>");
            out.println("<input type=\"submit\" value=\"Legg til\">");        
            out.println("</form>");
            out.println("</div>");
    }

    private void deleteReply(PrintWriter out, String moduleId, String replyid )  {
            out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"deleteR\" value=\"TRUE\"style=\"visibility:hidden;\">");
            out.println("<input type=\"text\" name=\"reply_id\" value=\""+ replyid +"\"style=\"visibility:hidden;\"/>");
            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete reply\">");
            out.println("</form>");
    }  

    private void deleteComment(PrintWriter out, String moduleId, String commentId) {
            out.println("<form action=\"OneModule?id="+ moduleId+"\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"delete\" value=\"TRUE\"style=\"visibility:hidden;\">");
            out.println("<input type=\"text\" name=\"comment_id\" value=\""+ commentId +"\"style=\"visibility:hidden;\"/>");
            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Delete comment\">");
            out.println("</form>");
    }
    
    private void addComment(PrintWriter out, HttpServletRequest request){
            String id = request.getParameter("id");
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ id+"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"delete\" value=\"FALSE\"");
            out.println("<h3>Legg til kommentar</h3><br>");
            out.println("<input type =\"text\" name=\"comment\"><br>");
            out.println("<input type=\"submit\" value=\"Legg til\"><br>");
            out.println("</form>");
            out.println("</div>");
    }

    private void deliver(PrintWriter out, HttpServletRequest request){
            String id = request.getParameter("id");
            out.println("<a href=\"Delivery?id="+ id +" \"a class=\"btn btn-info\">Deliver!</button></a>");
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
