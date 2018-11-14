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
import HtmlTemplates.BootstrapTemplate;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "Inbox", urlPatterns = {"/Inbox"})
public class Inbox extends SuperServlet {
    private BootstrapTemplate bst = new BootstrapTemplate();
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
           request.getParameterMap();
           
           super.processRequest(request, response, "", out);
           User user = (User)request.getSession().getAttribute("userLoggedIn");
           ArrayList<Message> messages = iDb.getUsersMessages(user.getUserId());
           
           out.println("<div class=\"container-fluid\">\n" +
"            <div class=\"row\">\n" +
"              <div class=\"col-3 offset-md-1\">\n");
            printOptionsButtons(out, request);    
            
            out.println("<table class=\"table table-default\">\n" +
"                  <thead>\n" +
"                    <tr>\n" +
"                      <th scope=\"col\">From</th>\n" +
"                      <th scope=\"col\">Subject</th>\n" +
"                    </tr>\n" +
"                  </thead>\n" +
"                  <tbody>");
           HashMap<String, Message> mesMap = new HashMap();
           for (Message message : messages) {
               if (request.getParameterMap().containsKey("sent")) {
                   if(message.getSender().equals(user.getUserId())) {
                   mesMap.put(message.getMsgId(), message);
                   String readOrNot = message.isRead() ? "" : "table-active";
                   out.println("<tr class=\""+readOrNot+"\">\n" +
"                      <td>"+message.getSender()+"</td>\n" +
"                      <td>"+ message.getSubject() +"</td>\n" +
"                      <td><a class=\"btn btn-warning\" href=\"Inbox?msgId="+ message.getMsgId() +"\">Open</a></td>" +
"                    </tr>");
                    }
               } else {
                   if(message.getReceiver().equals(user.getUserId())) {
                   mesMap.put(message.getMsgId(), message);
                   String readOrNot = message.isRead() ? "" : "table-active";
                   out.println("<tr class=\""+readOrNot+"\">\n" +
"                      <td>"+message.getSender()+"</td>\n" +
"                      <td>"+ message.getSubject() +"</td>\n" +
"                      <td><a class=\"btn btn-warning\" href=\"Inbox?msgId="+ message.getMsgId() +"\">Open</a></td>" +
"                    </tr>");
                    }
               }
           }
           out.println("</tbody>\n" +
"                </table>\n" +
"              </div>");
           request.setAttribute("mesMap", mesMap);
           printMessage(out, request);
           bst.bootstrapFooter(out);
        }
    }
    
    private void printMessage(PrintWriter out, HttpServletRequest request) {
        
        out.println("<div id=\"inbox\" class=\"col-6 m-3\">\n" +
"              <div  id=\"message\">");
           if (request.getParameterMap().containsKey("msgId")) {
               
               messageCSS(out);
               String msgId = request.getParameter("msgId");
               iDb.updateMessageIsRead(msgId);
               
               HashMap<String, Message> mesMap = (HashMap<String, Message>)request.getAttribute("mesMap");
               Message message = mesMap.get(msgId);
               
               out.println("<h2>"+ message.getSubject() +"</h2>");
               out.println("<h5>"+ message.getSender() +"</h5><hr>");
               out.println("<p>"+ message.getText() +"");

           }
           out.println("</div>");
    }
    
    private void printOptionsButtons(PrintWriter out, HttpServletRequest request) {
        if(request.getParameterMap().containsKey("sent")) {
                    out.println("<a class=\"m-2 btn btn-secondary btn-small \" href=\"Inbox\">Show received</a><a class=\"m-2 btn btn-secondary btn-small active\" href=\"Inbox?sent\">Show sent</a>");
        } else {
                    out.println("<a class=\"m-2 btn btn-secondary btn-small active\" href=\"Inbox\">Show received</a><a class=\"m-2 btn btn-secondary btn-small\" href=\"Inbox?sent\">Show sent</a>");

        }
    }
    
    private void messageCSS(PrintWriter out) {
        out.println("<style>"
                + "#inbox {"
                + "     height: 500px;"
                + "     width: auto;"
                + "     border-width: 5px;"
                + "     border-style: solid;"
                + "}"
                + "</style>");
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
