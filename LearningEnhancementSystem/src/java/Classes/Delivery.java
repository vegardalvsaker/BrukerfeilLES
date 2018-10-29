
package Classes;

/**
 *
 * @author Vegard
 */
public class Delivery {
    
    private String moduleName;
    private int deliveryID;
    private String studentID;
    private String moduleID;
    private String deliveryContent;
    private int worklistID;
    private String deliveryTimestamp;
    private boolean deliveryIsEvaluated;
    

    public String getModuleName()   {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
        
    }
    
    public void setDeliveryID(int deliveryID)    {
        this.deliveryID = deliveryID;
    }
    
    public int getDeliveryID()  {
        return deliveryID;
    }
    
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public String getStudentID()   {
        return studentID;
    }
    
    public void setModuleID(String moduleID)   {
        this.moduleID = moduleID;
    }
    
    public String getModuleID()    {
        return moduleID;
    }
    
    public void setDeliveryContent(String deliveryContent)  {
        this.deliveryContent = deliveryContent;
    }
    
    public String getDeliveryContent()  {
        return deliveryContent;
    }
    
    public void setWorklistID(int worklistID)   {
        this.worklistID = worklistID;
    }
    
    public int getWorklistID()  {
        return worklistID;
    }
    
    public void setDeliveryTimestamp(String worklistTimestamp)  {
        this.deliveryTimestamp = worklistTimestamp;
    }
    
    public String getDeliveryTimestamp()    {
        return deliveryTimestamp;
    }
    
    public void setIsEvaluated(boolean deliveryIsEvaluated) {
        this.deliveryIsEvaluated = deliveryIsEvaluated;
    }
    
    public boolean getIsEvaluted()  {
        return deliveryIsEvaluated;
    }
    
}
