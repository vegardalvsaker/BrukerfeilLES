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
import HtmlTemplates.BootstrapTemplate;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "OneModule", urlPatterns = {"/OneModule"})
public class OneModule extends SuperServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "Modules", out);
            BootstrapTemplate bst = new BootstrapTemplate();
            LearningGoalDb db = new LearningGoalDb();
            CommentDb cdb = new CommentDb();
            db.init();
            cdb.init();
            int mId = Integer.parseInt(id);
         
             if (request.getMethod().equals("POST"))  {
                if (request.getParameter("delete").equals("TRUE")) {
                    String comid = request.getParameter("comment_id");
                    int commId = Integer.parseInt(comid);
                    cdb.deleteComment(commId);
                    
                } else {
                    String comText = request.getParameter("comment");
                    if (comText.equals("")){
                        out.println("Enter text before posting");
                    } else 
                    cdb.addComment(mId, "1", comText);
                }
            }
            

           
            editModuleButtonForm(out,request);


            db.printLearningGoals(id, out);
            cdb.printComments(mId,out);
            addComment(out,request);
            
            bst.bootstrapFooter(out); 
        }
    }
    
    private void editModuleButtonForm(PrintWriter out, HttpServletRequest request)    {
        String id = request.getParameter("id");
            out.println("<a href=\"EditModule?id="+ id +"\">"
                    + "<button>Rediger modul</button>"
                    + "</a>");
            
         /*    out.println("<form action=\"EditModule?id=" + id + "\">");
             out.println("<button>Rediger modul</button>");
             out.println("</form>");*/
             
                     
                             
    }

    
private void addComment(PrintWriter out, HttpServletRequest request){
            String id = request.getParameter("id");
            out.println("<div>");
            out.println("<form action=\"OneModule?id="+ id+"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"delete\" value=\"FALSE\"");
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
