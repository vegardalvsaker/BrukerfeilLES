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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Espen
 */
@WebServlet(name = "Worklist", urlPatterns = {"/Worklist"})
public class Worklist extends SuperServlet {
    private User user;

    
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
    
    
    
    BootstrapTemplate bst = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (!checkIfLoggedIn(request)) {
                out.println("Please log in first :)");
                request.getRequestDispatcher("index.html").include(request, response);
                return;
            }
            
 if (checkIfEvenLoggedIn(request)) {            
            WorklistDb db = new WorklistDb();
            db.init();
            bst.bootstrapHeader(out, "Worklist");
            bst.bootstrapNavbar(out, "Worklist");
            
            bst.containerOpen(out);
            
            db.getWorklistNotEvalTeacher1(out);
            
            db.getWorklistEvaluated(out);
            
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            
            } else {
//====================================================================================================================================           
   
if (checkIfHallgeirLoggedIn(request)); {
            
            WorklistDb db = new WorklistDb();
            db.init();
            bst.bootstrapHeader(out, "Worklist");
            bst.bootstrapNavbar(out, "Worklist");
            
            bst.containerOpen(out);
            
            db.getWorklistNotEvalTeacher2(out);
            
            db.getWorklistEvaluated(out);
            
            bst.containerClose(out);
            bst.bootstrapFooter(out);
            } return 
            

//WorklistDb db = new WorklistDb();
            //db.init();
            //bst.bootstrapHeader(out, "Worklist");
            //bst.bootstrapNavbar(out, "Worklist");
            
            //bst.containerOpen(out);
            
            //db.getWorklistNotEvalTeacher1(out);
            
            //db.getWorklistEvaluated(out);
            
            //bst.containerClose(out);
            //bst.bootstrapFooter(out);
        

