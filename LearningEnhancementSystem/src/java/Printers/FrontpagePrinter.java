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
        
            for (Announcement announcement : announcementList){
                String atitle = announcement.getTitle();
                String adesc = announcement.getDescription();
                String author = announcement.getAuthor();       
                bs.jumbotron(out,atitle,adesc,author);
            }
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
