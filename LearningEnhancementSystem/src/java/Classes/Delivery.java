
package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Filip
 */
public class Delivery {
   
    private String moduleName;
    private String deliveryID;
    private String studentID;
    private String studentName;
    private String moduleID;
    private String deliveryContent;
    private int worklistID;
    private String deliveryTimestamp;
    private boolean deliveryIsEvaluated;

    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isDeliveryIsEvaluated() {
        return deliveryIsEvaluated;
    }

    public void setDeliveryIsEvaluated(boolean deliveryIsEvaluated) {
        this.deliveryIsEvaluated = deliveryIsEvaluated;
    }

    
    public String getModuleName()   {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
        
    }
    
    public void setDeliveryID(String deliveryID)    {
        this.deliveryID = deliveryID;
    }
    
    public String getDeliveryID()  {
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


