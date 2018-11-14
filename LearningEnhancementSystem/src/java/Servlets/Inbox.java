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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Classes.User;
import Database.InboxDb;
import java.util.ArrayList;
import Classes.Message;
import java.util.HashMap;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "Inbox", urlPatterns = {"/Inbox"})
public class Inbox extends SuperServlet {

    private InboxDb iDb = new InboxDb();
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
           printJavaScript(out);
           super.processRequest(request, response, "", out);
           User user = (User)request.getSession().getAttribute("userLoggedIn");
           ArrayList<Message> messages = iDb.getUsersMessages(user.getUserId());
           
           out.println("<div class=\"container-fluid\">\n" +
"            <div class=\"row\">\n" +
"              <div class=\"col-4\">\n" +
"                <table class=\"table table-active\">\n" +
"                  <thead>\n" +
"                    <tr>\n" +
"                      <th scope=\"col\">From</th>\n" +
"                      <th scope=\"col\">Subject</th>\n" +
"                    </tr>\n" +
"                  </thead>\n" +
"                  <tbody>");
           HashMap<String, Message> mesMap = new HashMap();
           for (Message message : messages) {
               if(message.getReceiver().equals(user.getUserId())) {
                   mesMap.put(message.getMsgId(), message);
                   out.println("<tr onClick=\"showMessage("+ message.getText() +", "+ message.getSubject() +")\">\n" +
"                      <td>"+message.getSender()+"</td>\n" +
"                      <td>"+ message.getSubject() +"</td>\n" +
"                    </tr>");
               }
           }
           out.println("</tbody>\n" +
"                </table>\n" +
"              </div>");
           out.println("<div id=\"inbox\" class=\"col-6\">\n" +
"              <div  id=\"noMessage\" class=\"text-center\"></div>");
        }
    }
    
    private void printJavaScript(PrintWriter out) {
        out.println("<script>\n" +
"        function showMessage(text, subject) {\n" +
"          var div = document.createElement('div');\n" +
"          div.className = 'rad';\n" +
"\n" +
"          div.innerHTML = '<h3>'+ subject +'</h3><br><p>'+ text + '</p>';\n" +
"          document.getElementById('noMessage').appendChild(div);\n" +
"        }"
                + "</script>");
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
