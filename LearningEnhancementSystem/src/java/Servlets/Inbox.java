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
import java.util.Map;
import HtmlTemplates.BootstrapTemplate;
import java.nio.charset.StandardCharsets;
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
           super.processRequest(request, response, "", out);
           if (request.getMethod().equals("POST")) {
               sendMessage(out, request);
           }
           
           listMessages(out, request);
           
           if (request.getParameterMap().containsKey("newMsg")) {
               printSendMessageForm(out, request);
           } else {
               printMessage(out, request);
               out.println("</div>");
           }               
           bst.bootstrapFooter(out);
        }
    }
    
    private void sendMessage(PrintWriter out, HttpServletRequest request) {
        if (request.getParameterMap().containsKey("sendMsg")) {
            String[] messageInfo = new String[4];
            User user = (User)request.getSession().getAttribute("userLoggedIn");
            messageInfo[0] = user.getUserId();
            messageInfo[1] = request.getParameter("recipient");
            messageInfo[2] = request.getParameter("subject");      
            messageInfo[3] = request.getParameter("newMessage");

            iDb.sendMessage(messageInfo);
        }
    }
    
    private void listMessages(PrintWriter out, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        if (request.getParameterMap().containsKey("msgId")) {
            String msgId = request.getParameter("msgId");
            iDb.updateMessageIsRead(msgId);
        }
        
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
            
               if (request.getParameterMap().containsKey("sent")) {
                   for (Message message : messages) {
                       if(message.getSender().equals(user.getUserId())) {
                            mesMap.put(message.getMsgId(), message);
                            String readOrNot = message.isRead() ? "" : "table-active";
                            out.println("<tr class=\""+readOrNot+"\">\n" +
         "                      <td>"+message.getSender()+"</td>\n" +
         "                      <td>"+ message.getSubject() +"</td>\n" +
         "                      <td><a class=\"btn btn-warning\" href=\"Inbox?sent&msgId="+ message.getMsgId() +"\">Open</a></td>" +
         "                    </tr>");
                    }
                   }
                   
               } else {
                   for (Message message : messages) {
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
           out.println("<a href=\"Inbox?newMsg\"class=\"btn btn-danger\">New</a>");
           out.println("</tbody>\n" +
"                </table>\n" +
"              </div>");
           
           request.setAttribute("mesMap", mesMap);
    }
    
    private void printMessage(PrintWriter out, HttpServletRequest request) {
        
        out.println("<div id=\"inbox\" class=\"col-3 offset-md-1\">\n" +
"              <div  id=\"message\">");
           if (request.getParameterMap().containsKey("msgId")) {
               
               messageCSS(out);
               
               String msgId = request.getParameter("msgId");
               HashMap<String, Message> mesMap = (HashMap<String, Message>)request.getAttribute("mesMap");
               Message message = mesMap.get(msgId);
               
               out.println("<h2>"+ message.getSubject() +"</h2>");
               out.println("<h5>"+ message.getSender() +"</h5><hr>");
               out.println("<p>"+ message.getText() +"");

           }
           out.println("</div>"
                   + "</div>");
    }
    
    private void printOptionsButtons(PrintWriter out, HttpServletRequest request) {
        if(request.getParameterMap().containsKey("sent")) {
                    out.println("<a class=\"m-2 btn btn-secondary btn-small \" href=\"Inbox\">Show received</a><a class=\"m-2 btn btn-secondary btn-small active\" href=\"Inbox?sent\">Show sent</a>");
        } else {
                    out.println("<a class=\"m-2 btn btn-secondary btn-small active\" href=\"Inbox\">Show received</a><a class=\"m-2 btn btn-secondary btn-small\" href=\"Inbox?sent\">Show sent</a>");

        }
    }
    
    private void printSendMessageForm(PrintWriter out, HttpServletRequest request) {
        //sendMessageCSS(out);
        out.println("<div id=\"sendMessage\" class=\"col-6 m-3\">");
        out.println("<form action=\"Inbox?sendMsg\" method=\"POST\" id=\"sendMessage\">"
                + "<div class=\"form-group row\">"
                + "<label for=\"from\">From:</label><input class=\"ml-0\" id=\"from\" type=\"text\" placeholder=\""+ request.getRemoteUser() +"\" readonly></input></div>"
                + "<div class=\"form-group row\"><label for=\"recipient\">To:</label><input class=\"ml-0\" id=\"recipient\" type=\"text\" name=\"recipient\"></input></div>"
                + "<div class=\"form-group row\"><label for=\"subject\">Subject:</label><input class=\"ml-0\" id=\"subject\" type=\"text\" name=\"subject\"></input></div>"
                + "<div class=\"form-group row\">Message:<textarea class=\"form-control\" name=\"newMessage\" id=\"newMessage\" rows=\"10\"></textarea></div>"
                + "<input class=\"btn btn-primary\" type=\"submit\" value=\"Send!\"></input>");
        out.println("</form>"
                + "</div>");
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
    
    private void sendMessageCSS(PrintWriter out) {
        out.println("<style>"
                + "label {"
                + "     width: 80px;"
                + "}"
                + "</style");
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
