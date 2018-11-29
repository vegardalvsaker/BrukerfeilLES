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
import javax.servlet.http.HttpSession;
import Printers.FrontpagePrinter;
import Database.UserDb;
import Classes.User;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "Index", urlPatterns = {"/Index"})
public class Index extends SuperServlet {
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
           
            
           /* //Sjekker om emailen er i databasen
            UserDb userdb = new UserDb();
            userdb.init();
            String email = request.getParameter("email");
            if (request.getSession().getAttribute("userLoggedIn") == null) {
                if (userdb.checkUserExist(email)) {
                    HttpSession ses = request.getSession();
                    User user = userdb.getUser(email);
                    ses.setAttribute("userLoggedIn", user);
                    //Printing the frontpage after the user details are connected to the session
                    FrontpagePrinter fp = new FrontpagePrinter();
                    fp.printFrontpage(out, "LES IS-110");   
                
            }   else {
                    //Sending the client back to the login page if he/she is not logged in
                    out.println("Sorry, this user does not exist in our database");
                    request.getRequestDispatcher("index.html").include(request, response);
                }
                
            } else {
                //Printing the frontpage if the client is already logged in
                
            }  */
            FrontpagePrinter fp = new FrontpagePrinter();
            setUserLoggedIn(request);
            fp.printFrontpage(out, "LES IS-110", getNotificationHtml(request), request); 
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
