package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.addRemoveStudent;
import HtmlTemplates.BootstrapTemplate;
/**
 *
 * @author gorm-erikaarsheim
 */
@WebServlet(urlPatterns = {"/People"})
public class People extends HttpServlet {

    
    BootstrapTemplate bst = new BootstrapTemplate();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            

            bst.bootstrapHeader(out, "People");
            bst.bootstrapNavbar(out, "People");
            
            bst.containerOpen(out);
           
            bst.containerClose(out);
            
            addStudentForm(out, response);
            removeStudentForm(out, response);
            listStudentsForm(out);
            
            bst.bootstrapFooter(out);
              
            addRemoveStudent students = new addRemoveStudent();
            students.init();
            
            if (request.getMethod().equals("POST"))  {
                
      
            String navn = request.getParameter("Navn");
            String email = request.getParameter("email");
            boolean isTeacher = Boolean.parseBoolean(request.getParameter("isTeacher"));
            String studnavn = request.getParameter("Studentnavn");
            
            students.addStudent(out, navn, email, isTeacher);
            students.removeStudent(out, studnavn);
            
            } else if (request.getMethod().equals("GET")) {
                 students.getStudentList(out);
                     
               }
        }
    }
        
        public void addStudentForm(PrintWriter out, HttpServletResponse response)    {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>People</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<a href=\"People\"></a>");
            out.println("<form action=\"People\" method=\"POST\">");
            out.println("<h1>Legg til student/lærer</h1><br>");
            out.println("<h3>Skriv inn navn</h3><br>");
            out.println("<input type=\"text\" name=\"Navn\"><br>");
            out.println("<h3>Skriv inn email</h3><br>");
            out.println("<input type=\"text\" name=\"email\"><br>");
            out.println("<h3>Er dette en lærer eller student?</h3><br>");
            out.println("<input type=\"radio\" name=\"isTeacher\" value=\"true\"> Lærer<br>");
            out.println("<input type=\"radio\" name=\"isTeacher\" value=\"false\"> Student<br>");
            out.println("<input type=\"submit\" value=\"Legg til\">");
            out.println("</form>");
            out.println("</div>");  
            
        }
        
        public void removeStudentForm(PrintWriter out, HttpServletResponse response)    {
            
           out.println("<div>"); 
            out.println("<form action=\"People\" method=\"POST\">");
            out.println("<h1>Fjern student/lærer</h1><br>");
            out.println("<input type=\"text\" name=\"Studentnavn\"><br>");
            out.println("<input type=\"submit\" value=\"Fjern student\">");
            out.println("</form>");
            out.println("<br>");
            out.println("</div>"); 

        }
        
        public void listStudentsForm(PrintWriter out)   {
            
            
            out.println("<div>");
            out.println("<h1>List studenter</h1>");
            out.println("<form action=\"People\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"List studenter\">");
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