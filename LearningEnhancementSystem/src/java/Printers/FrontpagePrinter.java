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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Vegard Alvsaker
 */
public class FrontpagePrinter {
                
    private BootstrapTemplate bs;
    private ModuleDb mdb;
    /**
     * Alltid lag ModuleDb-objekt og kall init()
     */
    public FrontpagePrinter() {
        bs = new BootstrapTemplate();
        mdb = new ModuleDb();
        mdb.init();
    }

    /**
     * Method for printing every element on the front page
     * @param out
     * @param title Title for the HTML title
     */
    public void printFrontpage(PrintWriter out, String title) {
        List<Module> modulList = mdb.getModuler();
        
        bs.bootstrapHeader(out, title);
        bs.bootstrapNavbar(out, "Home");
        bs.jumbotron(out);
        bs.containerOpen(out);
        out.println("<div class=\"row\">");
        
            for (Module modul : modulList) {
                int number = modul.getModule_no();
                String name = modul.getShort_desc();
                String desc = modul.getLong_desc();
                bs.bootstrapCard(out,number, name, desc);
            }
            
        out.println("</div>");
        bs.containerClose(out);
        bs.bootstrapFooter(out);
    }
}
