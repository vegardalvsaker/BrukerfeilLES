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
import Database.StudentDb;
import Database.ModuleDb;
import Classes.*;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "EvaluateServlet", urlPatterns = {"/EvaluateServlet"})
public class EvaluateServlet extends HttpServlet {
    private Student student;
    private Delivery delivery;
    private Module module;
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
            setup("1000", "1");
            
            out.println("<h1> Evaluation for student" + student.getStudentName() + "for the Module " + module.getModule_no());
            out.println("<ul>");
            for (LearningGoal lgs : module.getLearningGoals()) {
                int i = 0;
                out.println("<li> Learning goal: " + lgs.getText() + " | 0/"+ lgs.getPoints() +"</li>");
                i++;
            }
            out.println("</ul>");
            
        }
    }

    
    public void setup (String student_id, String module_no) {
        StudentDb sdb = new StudentDb();
        student = sdb.getStudent(student_id);
        
        System.out.println(student.getStudentName());
        System.out.println("hvor kommer denne printlinen?");
        
        ModuleDb mdb = new ModuleDb();
        mdb.init();
        module = mdb.getModuleWithLearningGoals(module_no);
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
