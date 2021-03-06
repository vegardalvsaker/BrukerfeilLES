/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printers;

import HtmlTemplates.BootstrapTemplate;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import Database.ModuleDb;
import Classes.Module;
import Database.AnnouncementDb;
import Classes.Announcement;
import java.util.ArrayList;
import Classes.Notification;
import Classes.User;
import Database.NotificationDb;
import java.util.List;
import java.lang.StringBuilder;
/**
 *
 * @author Vegard Alvsaker
 */
public class FrontpagePrinter {
                
    private BootstrapTemplate bs;
    private ModuleDb mdb;
    private AnnouncementDb adb;
    /**
     * Alltid lag ModuleDb-objekt og kall init()
     */
    public FrontpagePrinter() {
        bs = new BootstrapTemplate();
        mdb = new ModuleDb();
        adb = new AnnouncementDb();
    }

    /**
     * Method for printing every element on the front page
     * @param out
     * @param title Title for the HTML title
     */
    public void printFrontpage(PrintWriter out, String title, String notifications, HttpServletRequest request) {
        List<Module> modulList = mdb.getModuler();
        List<Announcement> announcementList = adb.getAnnouncement();
        User user = (User)request.getSession().getAttribute("userLoggedIn");
        bs.bootstrapHeader(out, title);
        bs.bootstrapNavbar(out, "Home", notifications, user.getUserName(), user.getUserId());
        
        bs.containerOpen(out);
        bs.jumbotron(out);
        int i= 0;
            for (Announcement announcement : announcementList){
                if (i < 2){
                String annSubject = announcement.getAnnSubject();
                String annBody = announcement.getAnnBody();
                String annUser = announcement.getAnnUserName();
                out.println("<h2>"+ annSubject + "</h2>");
                out.println("<p>" + annBody + "</p>");
                out.println("<small>" + annUser + "</small>");
                out.println("<hr class=\"my-4\">");
                i++;
                }
            }
        out.println("<a class=\"btn btn-primary btn-lg\" href=\"Announcement\" role=\"button\">View all announcements</a>");
        out.println("<p class=\"lead\">");
        out.println("</p>");
        out.println("</div>");
        out.println("</div>");
        bs.containerClose(out);
        bs.containerOpen(out);
        out.println("<div class=\"row\">");
            for (Module modul : modulList) {
                if (modul.isPublished()) {
                    String number = Integer.toString(modul.getModuleid());
                    String name = modul.getName();
                    String desc = modul.getDesc();
                    bs.bootstrapCard(out, number, name, desc);
                }              
            }
            
        out.println("</div>");
        bs.containerClose(out);
        bs.bootstrapFooter(out);
    }
}
