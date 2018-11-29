/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Notification;
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
import Database.NotificationDb;
import java.util.ArrayList;
import HtmlTemplates.BootstrapTemplate;
import java.nio.charset.StandardCharsets;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String currentTab, PrintWriter out)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BootstrapTemplate bst = new BootstrapTemplate();
        //Hvis systemet ikke greier å legge Useren i session så blir du bedt om å logge inn på nytt
        if (!setUserLoggedIn(request)) {
            out.println("<h1 You are not logged in</h1>");
            out.println("<a href=\"Index\"> Please log in </a>");
            return;
        }
        //Henter notifications til useren
        String notifications = getNotificationHtml(request); 
        bst.bootstrapHeader(out, currentTab);
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        bst.bootstrapNavbar(out, currentTab, notifications, user.getUserName(), user.getUserId());
        
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
        if (email == null) {
            return false;
        }
        
        UserDb uDb = new UserDb();
        User user = uDb.getUser(email);
        if (user == null) {
            return false;
        }
        session.setAttribute("userLoggedIn", user);
        return true;
    }
    
    protected void redirectHeader(PrintWriter out) {
        out.println("<head>\n" +
                    "<meta http-equiv=\"refresh\" content=\"0;url=Modules\" />\n" +
                    "</head");
    }
    
    protected String getNotificationHtml(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        NotificationDb nDb = new NotificationDb();
        
        ArrayList<Notification> notifications = nDb.getUsersNotification(user.getUserId());
        
        StringBuilder sbf = new StringBuilder();
        
        if (notifications.size() == 0){
            sbf.append("<a class=\"dropdown-item\">No notifcations</a>\n" +
                            "<div class=\"dropdown-divider\"></div>\n");
        }
        
        else if (notifications.size() < 5 && notifications.size() > 0) {
            for (Notification not : notifications) {
                if (not.isIsNotificationSeen()) {
                    sbf.append(
    "                   <a class=\"dropdown-item\">"+ not.getNotificationContent() +"</a>\n");
                
            }   else {
                    sbf.append("<div style=\"background-color:#f3f3f3;\">" +
    "                            <a class=\"dropdown-item\" href=\"Notifications\"><p style=\"color: red;\">◉</p >"+ not.getNotificationContent() +"</a>\n" +
    "                               </div> \n" +
                            "<div class=\"dropdown-divider\"></div>\n");
                }
            }
            sbf.append("<a style=\"float: right;\"class=\"btn btn-primary\" href=\"Notifications\">See all</a>");
        } else {
            for (int i = 0; i < 5; i++) {
                if (notifications.get(i).isIsNotificationSeen()) {
                    sbf.append(
    "                   <a class=\"dropdown-item\">"+ notifications.get(i).getNotificationContent() +"</a>\n" +
                            "<div class=\"dropdown-divider\"></div>\n");

                } else {
                    sbf.append("<div style=\"background-color:#f3f3f3;\">" +
    "                            <a class=\"dropdown-item\"><p style=\"color: red;\">◉</p >"+ notifications.get(i).getNotificationContent() +"</a>\n" +
    "                               </div> \n" +
                            "<div class=\"dropdown-divider\"></div>\n");
                }
            }
            int moreNoti = notifications.size() - 5;
            sbf.append("<a style=\"float: right;\"class=\"btn btn-primary\" href=\"Notifications\">See "+ moreNoti +" more</a>");
        }
        
        return sbf.toString();
    }
    
    protected String encodeToUTF8(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
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
