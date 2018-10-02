/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printers;

import HtmlTemplates.BootstrapTemplate;
import java.io.PrintWriter;
import Database.ModuleDb;
import Classes.Module;
import Database.AnnouncementDb;
import Classes.Announcement;
import java.util.ArrayList;
import java.util.List;
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
        mdb.init();
        adb.init();
    }

    /**
     * Method for printing every element on the front page
     * @param out
     * @param title Title for the HTML title
     */
    public void printFrontpage(PrintWriter out, String title) {
        List<Module> modulList = mdb.getModuler();
        List<Announcement> announcementList = adb.getAnnouncement();
        bs.bootstrapHeader(out, title);
        bs.bootstrapNavbar(out, "Home");
        bs.containerOpen(out);
        out.println("<div class=\"jumbotron\">");
        out.println("<div class=\"container\">");
        out.println("<h1 class=\"display-4\">Announcements:</h1>");
        out.println("<hr class=\"my-4\">");
        int i= 0;
            for (Announcement announcement : announcementList){
                if (i < 2){
                String atitle = announcement.getSubject();
                String adesc = announcement.getBody();
                int id = announcement.getId();
                String ID = String.valueOf(id);
                       
                bs.jumbotron(out,adesc,atitle,ID);
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
                int number = modul.getNumber();
                String name = modul.getName();
                String desc = modul.getDescription();
                bs.bootstrapCard(out,number, name, desc);
            }
            
        out.println("</div>");
        bs.containerClose(out);
        bs.bootstrapFooter(out);
    }
}
