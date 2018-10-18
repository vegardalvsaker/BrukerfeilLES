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
import Database.UserDb;
import Classes.User;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "SuperServlet", urlPatterns = {"/SuperServlet"})
public class SuperServlet extends HttpServlet {

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
    }
    
    protected boolean checkIfTeacherLoggedIn(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLoggedIn");
        if (user.getUserIsTeacher()){
            return true;
        }
        return false;
    }
    
    protected boolean checkIfLoggedIn(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userLoggedIn");
        if(user == null) {
            return false;
        }
        return true;
    }
    
    protected boolean setUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
       
        String email = request.getRemoteUser();
        UserDb uDb = new UserDb();
        uDb.init();
        User user = uDb.getUser(email);
        if (user == null) {
            return false;
        }
        request.getSession().setAttribute("userLoggedIn", user);
        return true;
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
