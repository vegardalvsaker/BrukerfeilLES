package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.ResultsDb;
import Classes.User;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Marius
 */
@WebServlet(name = "Results", urlPatterns = {"/Results"})
public class Results extends SuperServlet {
    
    BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            super.processRequest(request, response, "Results", out);
            
            bst.containerOpen(out);
            results(out, request);
            bst.containerClose(out);
            bst.bootstrapFooter(out);  
         
        }
    }
    private void results(PrintWriter out, HttpServletRequest request)    {
        
        ResultsDb rdb = new ResultsDb();
        //out.println("<h1>Dine evaluerte moduler:</h1><br>");
        setUserLoggedIn(request);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("userLoggedIn");
        String userID = user.getUserId();
        List<Classes.Results> resultsList = rdb.getSResults(userID);
        
            out.println("<table class=\"table table-hovere\">");
            out.println("<thead>");
            out.println("<tr class=\"table-active\">");
            out.println("<th scope=\"col\">Modulnavn</th>");
            out.println("<th scope=\"col\">Evaluert av</th>");
            out.println("<th scope=\"col\">Se Evaluering</th> ");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
        for (Classes.Results results : resultsList)  {
            String moduleName = results.getModuleName();
            String moduleID = results.getModuleId();
            String teacherName = results.getTeacherName();
            String evalId = results.getEvalId();

            out.println("<td>"+moduleName+"</td>");
            out.println("<td>"+teacherName+"</td>");
            out.println("<td>");
            ViewResult(out, evalId, moduleID, moduleName);
            out.println("</td>");
            out.println("</tr>");
        }  
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
    }
 private void ViewResult(PrintWriter out, String evalId, String moduleId, String moduleName){
            out.println("<form action=\"OneResult?id="+ evalId+"\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"moduleId\" value=\""+ moduleId +"\">");
            out.println("<input type=\"hidden\" name=\"evaluationId\" value=\""+ evalId +"\">");       
            out.println("<br>");
            out.println("<input type=\"submit\" value=\""+moduleName+"\"><br>");        
            out.println("<br>");
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