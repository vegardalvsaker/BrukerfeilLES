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
    
    public void bootstrapNavbar(PrintWriter out) {
        out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark \">\n" +
"    <div class=\"container\">\n" +
"    <a class=\"navbar-brand\" href=\"Index\">IS-110</a>\n" +
"    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"      <span class=\"navbar-toggler-icon\"></span>\n" +
"    </button>\n" +
"    <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"      <ul class=\"navbar-nav mr-auto\">\n" +
"        <li class=\"nav-item active\">\n" +
"          <a class=\"nav-link\" href=\"Index\">Home <span class=\"sr-only\">(current)</span></a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link\" href=\"Modules\">Modules</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item\" >\n" +
"          <a class=\"nav-link\" href=\"#\">Inbox</a>\n" +
"        </li>\n" +
"        <li class=\"nav-item dropdown\">\n" +
"          <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
"            People\n" +
"          </a>\n" +
"          <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n" +            
"            <a class=\"dropdown-item\" href=\"#\">Students</a>\n" +
"            <a class=\"dropdown-item\" href=\"#\">Teachers</a>\n" +
"            <a class=\"dropdown-item\" href=\"#\">Everyone</a>\n" +
"            <div class=\"dropdown-divider\"></div>\n" +
"            <a class=\"dropdown-item \" href=\"#\">Test</a>\n" +
"          </div>\n" +
"        </li>\n" +
"        <li class=\"nav-item\">\n" +
"          <a class=\"nav-link disabled\" href=\"#\">Press here for free A (coming soon!)</a>\n" +
"        </li>\n" +
"      </ul>\n" +
"    </div>\n" +
"  </nav>\n");
    }
    
    public void bootstrapCard(PrintWriter out,int modulNo, String modulName, String modulDesc) {
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
    
    public void bootstrapFooter(PrintWriter out) {
        out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>");
        out.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>");
        out.println("</body>");
    }
    
    public void jumbotron(PrintWriter out) {
        out.println("<div class=\"jumbotron\">");
        out.println("<div class=\"container\">");
        out.println("<h1 class=\"display-4\">Announcements:</h1>");  
        out.println("<hr class=\"my-4\">");
        out.println("<p>This is a dummy announcement. Do not take this text seriously</p>");
        out.println("<hr class=\"my-4\">");
        out.println("<p>This is another dummy announcement. Do not take this text seriously, either</p>");

        out.println("<p class=\"lead\">");
        out.println("<a class=\"btn btn-primary btn-lg\" href=\"#\" role=\"button\">Learn more</a>");
        out.println("</p>");
        out.println("</div>");
        out.println("</div>");
    }
    
    public void containerOpen(PrintWriter out) {
        out.println("<div class=\"container\">");
    }
    
    public void containerClose(PrintWriter out) {
        out.println("</div>");
    }
}
