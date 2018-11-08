/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.User;
import Database.WorklistDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Espen
 */
 /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param <error>
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Objekt for å generere UI
    
@WebServlet(name = "Worklist", urlPatterns = {"/Worklist"})
public class Worklist extends SuperServlet {
        BootstrapTemplate bst = new BootstrapTemplate();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        super.processRequest(request, response, "Worklist", out);
        request.getRemoteUser();
        
	User user = (User)request.getSession().getAttribute("userLoggedIn");
	String userEmail = user.getUserEmail();
	
       /* if(user.getUserEmail().equals("Even@uia.no")) {
            WorklistDb db = new WorklistDb();
            db.init();
            bst.containerOpen(out);
                db.getWorklistNotEvalTeacher1(out);
                db.getWorklistEvaluated(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
        
    }else if(user.getUserEmail().equals("hallgeiren@uia.no")) {
            WorklistDb db = new WorklistDb();
            db.init();
            bst.containerOpen(out);
                db.getWorklistNotEvalTeacher2(out);
                db.getWorklistEvaluated(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            } else if (checkIfTeacherLoggedIn(request)) {
                }else {
                out.println("You do not have access to this page!");
                request.getRequestDispatcher("Index").include(request, response);
            }
        }
    }
//WorklistDb db = new WorklistDb();
            //db.init();
            //bst.bootstrapHeader(out, "Worklist");
            //bst.bootstrapNavbar(out, "Worklist");
            
            //bst.containerOpen(out);
            
            //db.getWorklistNotEvalTeacher1(out);
            
            //db.getWorklistEvaluated(out);*/
            
            //bst.containerClose(out);
            //bst.bootstrapFooter(out);
        
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
        
    
