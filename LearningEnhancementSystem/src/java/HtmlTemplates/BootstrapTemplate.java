/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HtmlTemplates;
import java.io.PrintWriter;

/**
 *
 * @author Vegard
 */
public class BootstrapTemplate {
    
    /**
     * bootstrapHeader is a HTML-header with necessary Bootstrap imports.
     * @param out
     * @param title is a String which is the title of the current page.
     */
    public void bootstrapHeader(PrintWriter out, String title) {
        out.println("<!DOCTYPE html>");
        out.println("<head>");
        out.println("<html lang=\"no\">");
        out.println("<meta charset=\"utf-8\"");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
        out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://code.jquery.com/jquery-1.10.2.js\"></script>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
    }
    
    /**
     * This method ensures that the right tab is highlighted in the navbar.
     * @param out
     * @param tab is a String with the name of the active tab.
     */
    public void bootstrapNavbar(PrintWriter out, String tab, String notifications) {
        String current_tab = "active";
        switch (tab) {
            case "Home":         out.format(getBootstrapNavbar(notifications), current_tab, "", "", "", "", "", "");
                                 break;
            case "Modules":      out.format(getBootstrapNavbar(notifications), "", current_tab, "", "", "", "", "");
                                 break;
            case "Results":      out.format(getBootstrapNavbar(notifications), "", "", current_tab, "", "", "", "");
                                 break;
            case "Inbox":        out.format(getBootstrapNavbar(notifications), "", "", "", current_tab, "", "", "");
                                 break;
            case "Worklist":     out.format(getBootstrapNavbar(notifications), "", "", "", "", current_tab, "", "");
                                 break;
            case "People":       out.format(getBootstrapNavbar(notifications), "", "", "", "", "", current_tab, "");
                                 break;
            case "Notifications":out.format(getBootstrapNavbar(notifications), "", "", "", "", "", "", current_tab);
                                 break;
            default:             out.format(getBootstrapNavbar(notifications), "", "", "", "", "", "", "");
                                 break;  
        }
    }
    
    /**
     * This method returns the HTML for the navigation bar
     * %s is for which tab is active. This is used in bootstrapNavbar().
     * @return 
     */
    public String getBootstrapNavbar(String notifications) {
        return "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark \">\n" +
"    <div class=\"container\">\n" +
"    <a class=\"navbar-brand\" href=\"Index\">IS-110</a>\n" +
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"      <span class=\"navbar-toggler-icon\"></span>\n" +
"    </button>\n" +
"    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"      <ul class=\"navbar-nav mr-auto\">\n" +
"        <li class=\"nav-item %s\">\n" +
"          <a class=\"nav-link\" href=\"Index\">Home </a>\n" +
"        </li>\n" +
"        <li class=\"nav-item %s\">\n" +
"          <a class=\"nav-link\" href=\"Modules\">Modules </a>\n" +
"        </li>\n" +
"        <li class=\"nav-item %s\" >\n" +
"          <a class=\"nav-link disabled\" href=\"Results\">Results </a>\n" +
"        </li>\n" +
"        <li class=\"nav-item %s\" >\n" +
"          <a class=\"nav-link disabled\" href=\"#\">Inbox </a>\n" +
"        </li>\n" +
"        <li class=\"nav-item %s\">\n" +
"          <a class=\"nav-link disabled\" href=\"Worklist\">Worklist </a>\n" +
"        </li>\n" +       
"        <li class=\"nav-item %s dropdown\">\n" +
"          <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
"            People \n" +
"          </a>\n" +
"          <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n" +            
"            <a class=\"dropdown-item\" href=\"PeopleStudents\">Students</a>\n" +
"            <a class=\"dropdown-item\" href=\"PeopleTeachers\">Teachers</a>\n" +
"            <a class=\"dropdown-item\" href=\"People\">Everyone</a>\n" +
//"            <div class=\"dropdown-divider\"></div>\n" +
//"            <a class=\"dropdown-item \" href=\"#\">Test</a>\n" +
"          </div>\n" +
"        </li>\n" +
"      </ul>\n" +
                                "<ul class=\"navbar navbar-nav navbar-right\">\n" +
                "<li class=\"nav-item %s dropdown\">" +
                "<a class=\"nav-link dropdown-toggle\" href=\"\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
"            Notifications </a>\n" +
      "          <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n" +
                notifications +
           "</div>\n" +
                "</li>\n" +
                "</ul>\n" +
"    </div>\n" +
"  </nav>";
        
    }
    /**
     * Method for formatting modules on the front page
     * @param out
     * @param modulNo
     * @param modulName
     * @param modulDesc 
     */
    public void bootstrapCard(PrintWriter out,String modulNo, String modulName, String modulDesc) {
        out.println("<div class=\"col-4\">\n" +
"      <div class=\"card\">\n" +
"        <div class=\"card-body\">\n" +
"          <h2 class=\"card-title\">" + modulName + "</h2>\n" +
"          <p class=\"card_text\">"+ modulDesc +"</p>\n" +
"          <a href=\"OneModule?id="+ modulNo + "\" class=\"btn btn-primary\">Learn more!</a>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n");
    }
    /**
     * Necessary html footer for importin jQuery and other stuff
     * @param out 
     */
    public void bootstrapFooter(PrintWriter out) {
        out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");
        out.println("</body>");
    }
    
    /**
     * A bootstrap Jumbotron which displays announcements. placeholder at this moment {18.09}
     * @param out 
     */
    public void jumbotron(PrintWriter out) {
        out.println("<div class=\"jumbotron\">");
        out.println("<div class=\"container\">");
        out.println("<h1 class=\"display-4\">Announcements:</h1>");
        out.println("<hr class=\"my-4\">");
    }
    
    public void collapseTop(PrintWriter out) {
            out.println("<p>");
            out.println("<div class=\"jumbotron\">");
            out.println("<div class=\"container\">");
            out.println("<button class=\"btn btn-outline-secondary\" data-toggle=\"collapse\" data-target=\"#collapse\" aria-expanded=\"true\" aria-controls=\"collapse\">");
            out.println("<h4 class=\"display-4\">Kommentarer</h4>");
            out.println("</button>");
            out.println("<hr class=\"my-4\">");
            out.println("<div class=\"collapse show\" id=\"collapse\">");
            out.println("<div class=\"card-body\">");
    }
    
    public void collapseBottom(PrintWriter out) {
            out.println("</div>");
            out.println("</div>");
    }
    
    /**
     * Method for opening a bootstrap container
     * @param out 
     */
    public void containerOpen(PrintWriter out) {
        out.println("<div class=\"container\">");
    }
    
    /**
     * Method for closing a bootstrap container
     * @param out 
     */
    public void containerClose(PrintWriter out) {
        out.println("</div>");
    }
}

