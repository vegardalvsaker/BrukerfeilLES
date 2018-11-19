package Servlets;

import Database.UserDb;
import Database.DeliveryDb;
import Database.ModuleDb;
import HtmlTemplates.BootstrapTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Classes.User;
import Classes.Delivery;

/**
 *
 * @author Fosse
 */
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends SuperServlet {

  BootstrapTemplate bst = new BootstrapTemplate();
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    
    
    String userID = request.getParameter("id"); 
    
    
    
    try (PrintWriter out = response.getWriter()) {
        super.processRequest(request, response, "People", out);
        
            
        bst.containerOpen(out);
        bst.containerClose(out);
        bst.bootstrapFooter(out);
              
        UserDb profile = new UserDb();
        DeliveryDb delivery = new DeliveryDb();
        
        profile.init();
        delivery.init();
        
        profile.getProfile(out,userID);
     //   delivery.getOneStudentsDeliveries(out, userID);
        
        setUserLoggedIn(request);                                 //Calls super method, fills in user-data from database into session   

        HttpSession session = request.getSession();               //Sets variable "session" to current session
        User user = (User)session.getAttribute("userLoggedIn");   //Sets variable "user" to the current user logged in  
        
        
        if (request.isUserInRole("Teacher")) {
            out.println("Du er logget inn som en lærer: ");
            profile.printProfile(out);
            printProgressbarForm(out, userID);
            //Eventuelt print PROGRESS/RESULTS
        } else if (user.getUserId().equals(userID) ) {            //IF user logged in = user profile requested
            out.println("Dette er din profil: ");
            profile.printProfile(out);
            printProgressbarForm(out, userID);
            //Eventuelt print PROGRESS/RESULTS
        } else {
            out.println("Du er logget inn som en student: ");
            profile.printProfileLimited(out);
        }
    //  profileForm(out);
    }    
}

public void printProgressbarForm(PrintWriter out, String id) {
    String userID = id; 
    
    ModuleDb module = new ModuleDb();
    DeliveryDb delivery = new DeliveryDb();
    UserDb user = new UserDb();
    
    delivery.init();
    module.init();
    user.init();
    
    int studentCount = user.getStudentCount(out);
    int moduleCount = module.getModuleCount(out);
    int evaluatedDeliveriesCount = delivery.getEvaluatedDeliveries(out, userID);
    int allEvaluatedDeliveries = delivery.getAllEvaluatedDeliveries(out);
    
    //Sets "percent" to the percentage of deliveries a student has delivered
    int percent = evaluatedDeliveriesCount * 100 / moduleCount;
    
    //Sets "totalDeliveries" to the max possible deliveries for all students
    //Sets "avgpercent" to the percentage of an average student
    int totalDeliveries = studentCount * moduleCount;
    int avgpercent = allEvaluatedDeliveries * 100 / totalDeliveries;
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<title>W3.CSS</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
    out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
    out.println("<body>");
    out.println("<div class=\"w3-container\">");

    out.println("<h2>Progress Bar</h2>");
    out.println("<p>This is your progress towards the end of the year.</p>");

    out.println("<div class=\"w3-light-grey\">");
    out.println("<div class=\"w3-container w3-green w3-center\" style=\"width:" + percent + "%\">50%</div>");
    out.println("</div><br>");
    out.println("<p>This the average progress of your classmates.</p>");
    out.println("<div class=\"w3-light-grey\">");
    out.println("<div class=\"w3-container w3-blue\" style=\"width:50%\">" + avgpercent + "%</div>");
    out.println("</div><br>");

    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
    
}



/*
public void profileForm(PrintWriter out) {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>People</title>");            
    out.println("</head>");
    out.println("<body>");
    out.println("<div>");
    out.println("<form action=\"Profile\" method=\"POST\">");
    out.println("<br>");
    out.println("<input type=\"submit\" value=\"Placeholder\">");
    out.println("</form>");
    out.println("</div>"); 
}
*/

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