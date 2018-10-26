/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Servlets.SuperServlet;
import Classes.User;
import Database.WorklistDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Servlets.SuperServlet;
import Servlets.Index;
import Servlets.LogOut;

/**
 *
 * @author Espen
 */
@WebServlet(name = "Worklist", urlPatterns = {"/Worklist"})
public class Worklist extends SuperServlet {
    BootstrapTemplate bst = new BootstrapTemplate();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Objekt for å generere UI
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, HttpServlet session)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        request.getRemoteUser();
        
        if(request.getRemoteUser() != ("Even@uia.no")) {
            WorklistDb db = new WorklistDb();
            db.init();
            bst.bootstrapHeader(out, "Worklist");
            bst.bootstrapNavbar(out, "Worklist");
            bst.containerOpen(out);
                db.getWorklistNotEvalTeacher1(out);
                db.getWorklistEvaluated(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
        
    }else 
            if(request.getRemoteUser() != ("hallgeiren@uia.no")) {
            WorklistDb db = new WorklistDb();
            db.init();
            bst.bootstrapHeader(out, "Worklist");
            bst.bootstrapNavbar(out, "Worklist");
            bst.containerOpen(out);
                db.getWorklistNotEvalTeacher2(out);
                db.getWorklistEvaluated(out);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            } else 
                if (checkIfTeacherLoggedIn(request)) {
                }else {
                out.println("You do not have access to this page!");
                request.getRequestDispatcher("Index").include(request, response);
                }
            }
        }
    }


            

//WorklistDb db = new WorklistDb();
            //db.init();
            //bst.bootstrapHeader(out, "Worklist");
            //bst.bootstrapNavbar(out, "Worklist");
            
            //bst.containerOpen(out);
            
            //db.getWorklistNotEvalTeacher1(out);
            
            //db.getWorklistEvaluated(out);
            
            //bst.containerClose(out);
            //bst.bootstrapFooter(out);
        

