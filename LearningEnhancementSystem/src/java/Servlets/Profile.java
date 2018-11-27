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
import Classes.Module;
import java.util.ArrayList;

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
        
        setUserLoggedIn(request);                                 //Calls super method, fills in user-data from database into session   

        HttpSession session = request.getSession();               //Sets variable "session" to current session
        User user = (User)session.getAttribute("userLoggedIn");   //Sets variable "user" to the current user logged in  
        
        
        if (request.isUserInRole("Teacher")) {
            out.println("Du er logget inn som en lærer: ");
            printUserInfo(out, userID);
            if(!profile.getOneProfile(out, userID).getUserIsTeacher()){
                printProgressbarForm(out, userID);
            }
            
        } else if (user.getUserId().equals(userID) ) {            //IF user logged in = user profile requested
            out.println("Dette er din profil: ");
            printUserInfo(out, userID);
            printProgressbarForm(out, userID);

        } else {
            out.println("Du er logget inn som en student: ");
            printUserInfo(out, userID);
        }
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
    
    //total students
    int studentCount = user.getStudentCount(out);
    //total published modules
    int moduleCount = module.getModuleCount(out);
    //total evaluated deliveries for one student (max 1 per module)
    int evaluatedDeliveriesCount = delivery.getEvaluatedDeliveries(out, userID);
    //total evaluated deliveries for total students
    int allEvaluatedDeliveries = delivery.getAllEvaluatedDeliveries(out);
    
    //Sets "percent" to the percentage of deliveries a student has delivered
    int percent = evaluatedDeliveriesCount * 100 / moduleCount;
    //Sets "totalDeliveries" to the max possible deliveries for all students
    int totalDeliveries = studentCount * moduleCount;
    //Sets "avgpercent" to the percentage of an average student
    int avgpercent = allEvaluatedDeliveries * 100 / totalDeliveries;
    //Sets "deliveriesRemaining" to the number of modules that has yet to be evaluated.
    int deliveriesRemaining = moduleCount - evaluatedDeliveriesCount;
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<title>W3.CSS</title>");
    out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
    out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
    out.println("<body>");
    out.println("<div class=\"w3-container\">");

    out.println("<h2>Progress Bar</h2>");
    
    //Prints the progressbar for the relevant student, it shows his progress on completing the modules.
    out.println("<div>Du har blitt evaluert på " + evaluatedDeliveriesCount + " moduler, bare " + deliveriesRemaining + " moduler igjen!</div>");
    out.println("<div class=\"w3-light-grey\">");
    out.println("<div class=\"w3-container w3-green w3-center\" style=\"width:" + percent + "%\">" + percent + "%</div>");
    out.println("</div><br>");
    
    //Prints the progressbar for an average student, it shows how many % of deliveries one average student has delivered.
    out.println("<div>This the average progress of your classmates.</div>");
    out.println("<div class=\"w3-light-grey\">");
    out.println("<div class=\"w3-container w3-blue\" style=\"width:" + avgpercent + "%\">" + avgpercent + "%</div>");
    out.println("</div><br>");

    
    //For loop that runs once per published module, it retrieves a Delivery Array for every module - this includes 
    //every evaluated delivery for that 
    for (int i=1; i<=moduleCount; i++){
        int moduleNr = i;
        String moduleNrString = Integer.toString(moduleNr);
       
        Module oneModule = module.getOneModule(out, moduleNrString);
        int amountOfDeliveries = delivery.getAmountOfDeliveriesPerModule(out, moduleNrString);
        int oneModulePercent = amountOfDeliveries * 100 / studentCount;
        
        out.println("<div>" + oneModule.getName() + ":</div>");
        out.println("<div class=\"w3-light-grey\">");
        out.println("<div class=\"w3-container w3-blue\" style=\"width:" + oneModulePercent + "%\">" + oneModulePercent + "%</div>");
        out.println("</div>"); 
    }

    out.println("</div>");
    out.println("</body>");
    out.println("</html>");
}

public void printUserInfo(PrintWriter out, String id){
    UserDb user = new UserDb();
    User oneProfile = user.getOneProfile(out, id);
    
    out.println("<h2>"+"Information about "+ oneProfile.getUserName() + "</h2>");
    out.println("User ID: " + oneProfile.getUserId() + "<br>");
    out.println(" Name: " + oneProfile.getUserName() + "<br>");
    out.println(" Email: " + oneProfile.getUserEmail() + "<br>");
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