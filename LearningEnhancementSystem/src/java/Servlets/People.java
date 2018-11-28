package Servlets;

import Classes.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.UserDb;
import java.util.ArrayList;
/**
 *
 * @author Ingve Fosse
 */
@WebServlet(urlPatterns = {"/People"})
public class People extends SuperServlet {
    BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            super.processRequest(request, response, "People", out);

            bst.containerOpen(out);

        UserDb users = new UserDb();

        ArrayList<User> onlyStudents = users.getArrayOfStudents(out);
        ArrayList<User> onlyTeachers = users.getArrayOfTeachers(out);
        out.println("<h1>List of all users:</h1>");

        for (User user : onlyStudents) {
            String id = user.getUserId();
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
            out.println("<a href=\"Profile?id="+ id +" \"a class=\"btn btn-info\">View Profile</button></a>");
            out.println("<br>" + "<br>");
        }
        for (User user : onlyTeachers) {
            String id = user.getUserId();
            out.println(" Name: " + user.getUserName() + "<br>");
            out.println(" Email: " + user.getUserEmail() + "<br>");
            out.println("<a href=\"Profile?id="+ id +" \"a class=\"btn btn-info\">View Profile</button></a>");
            out.println("<br>" + "<br>");
        }

        bst.containerClose(out);
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
