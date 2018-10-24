package Classes;
import java.util.ArrayList;
/**
 *
 * @author Espen
 */
//public class Worklist {
    
  //  private int worklistid;
    //private int teacherid;
    
   // private ArrayList<Evaluation> Todo = new ArrayList<>();
   // private ArrayList<Evaluation> Evaluated = new ArrayList<>();
   
public class WorklistNotEval {
        
    private String delivery_id;
    private String student_id;
    private String module_id;
    private String delivery_content;
    private String worklist_id;
    private String delivery_timestamp; 
    private boolean delivery_isEvaluated;
    
    public WorklistNotEval(String delivery_id, String student_id, String module_id, String delivery_content, String worklist_id, String delivery_timestamp, boolean delivery_isEvaluated) {
    
    this.delivery_id = delivery_id;
    this.student_id = student_id;
    this.module_id = module_id;
    this.delivery_content = delivery_content;
    this.worklist_id = worklist_id;
    this.delivery_timestamp = delivery_timestamp;
    this.delivery_isEvaluated = delivery_isEvaluated;
    }
   
  
    public String getDelId() {
        return delivery_id;
    }
    public String getStudentId() {
        return student_id;
    }
    
    public String getModuleId() {
        return module_id;
    }
    
    public String getDesc() {
        return delivery_content;
    }
    
    public String getTimestamp() {
           return delivery_timestamp;
    }
    
    public boolean delivery_isEvaluated() { 
        return boolean.class.equals(delivery_isEvaluated);
    }
    
public static class WorklistEvaluated  {
        
    private String delivery_id;
    private String student_id;
    private String module_id;
    private String delivery_content;
    private String worklist_id;
    private String delivery_timestamp; 
    private boolean delivery_isEvaluated;
    
public WorklistEvaluated(String delivery_id, String student_id, String module_id, String delivery_content, String delivery_timestamp, boolean delivery_isEvaluated) {
    
    this.delivery_id = delivery_id;
    this.student_id = student_id;
    this.module_id = module_id;
    this.delivery_content = delivery_content;
    this.worklist_id = worklist_id;
    this.delivery_timestamp = delivery_timestamp;
    this.delivery_isEvaluated = delivery_isEvaluated;
    }
   
  
    public String getDelId() {
        return delivery_id;
    }
    public String getStudentId() {
        return student_id;
    }
    
    public String getModuleId() {
        return module_id;
    }
    
    public String getDesc() {
        return delivery_content;
    }
    
    public String getTimestamp() {
           return delivery_timestamp;
    }
    
    public boolean delivery_isEvaluated() { 
        return boolean.class.equals(delivery_isEvaluated);
    }
        
    }
    
    
    
    
}