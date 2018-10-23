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
import HtmlTemplates.BootstrapTemplate;
import java.util.ArrayList;
import Classes.Notification;
import Database.NotificationDb;
import Classes.User;
import java.sql.Timestamp;

/**
 *
 * @author Vegard
 */
@WebServlet(name = "Notifications", urlPatterns = {"/Notifications"})
public class Notifications extends SuperServlet {
    
    private BootstrapTemplate bst = new BootstrapTemplate();
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
            super.processRequest(request, response, "Home", out);
            updateIsSeen(request);
            bst.containerOpen(out);
            
            printNotifications(out, request);
            bst.containerClose(out);
            bst.bootstrapFooter(out);
        }
    }
    
    protected void printNotifications(PrintWriter out, HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        String userId = user.getUserId();
        NotificationDb nDb = new NotificationDb();
        ArrayList<Notification> notifications = nDb.getUsersNotification(userId);
        
        if (notifications.isEmpty()) {
            out.println("<h1>There are no notifications for " + user.getUserName() + "<h1>");
        } else {
            
            out.println("<h1>Notifications for " + user.getUserName() + "</h1>");
        
            out.println("<form action=\"Notifications\" method=\"POST\">");
            out.println("<input type=\"hidden\" name=\"updateSeen\" value=\"\"></input>");
            out.println("<table class=\"table\">\n" +
    "    <thead>\n" +
    "      <tr class=\"table-info\">\n" +
    "        <th scope=\"col\">Mark as seen<br><input type=\"checkbox\" name=\"all\" class=\"selectall\"/></th>\n" +
    "        <th scope=\"col\">Notification text</th>\n" +
    "        <th scope=\"col\">Time and date</th>\n" +
    "        <th scope=\"col\">Seen?</th>\n" +
    "      </tr>\n" +
    "    </thead>");
            out.println("<tbody>");
            for (Notification not : notifications) {
                Timestamp ts = not.getTimestamp();
                System.out.println(ts.getTime());
                System.out.println(ts.toLocalDateTime());
                if (!not.isIsNotificationSeen()) {
                    out.println("<tr class=\"table-secondary\">");
                    out.println("<td><input type=\"checkbox\" name=\"updateSeen\" value=\""+ not.getNotificationId() +"\"></input></td>");
                    out.println("<td>"+ not.getNotificationContent() +"</td>");
                    out.println("<td>"+ not.getTimestamp() +"</td>");
                    out.println("<td> No</td>");
                    out.println("</tr>");
                } else {
                    out.println("<tr>");
                    out.println("<td></td>");
                    out.println("<td>"+ not.getNotificationContent() +"</td>");
                    out.println("<td>"+ not.getTimestamp() +"</td>");
                    out.println("<td> Yes</td>");
                    out.println("</tr>");
                }
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<input class=\"btn btn-secondary\" type=\"submit\" value=\"Update mark as seen\"></input>");
            out.println("</form>");
            jsSelectAll(out);
        }
    }
    
    private void updateIsSeen(HttpServletRequest request) {
        if (request.getMethod().equals("POST")) {
            
            String[] values = request.getParameterValues("updateSeen");
            NotificationDb nDb = new NotificationDb();
            for (int i = 1; i < values.length; i++) {
                nDb.setNotifcationIsSeen(values[i]);
            }
        }
    }
    
    private void jsSelectAll(PrintWriter out) {
        out.println("<script> \n"+
                "$('.selectall').click(function() {\n" +
"    if ($(this).is(':checked')) {\n" +
"        $('td input').attr('checked', true);\n" +
"    } else {\n" +
"        $('td input').attr('checked', false);\n" +
"    }\n" +
"});\n" +
                "</script>");
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
