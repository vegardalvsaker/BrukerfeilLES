/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Vegard
 */
public class Delivery {
    private String delivery_id;
    private String user_id;
    private String module_id;
    private String delivery_content;
    private String worklist_id;
    private String delivery_timestamp; 
    private boolean delivery_isEvaluated;
    
    public Delivery (String delivery_id, String student_id, String module_id, String delivery_content, String worklist_id, String delivery_timestamp, boolean delivery_isEvaluated) {
    
    this.delivery_id = delivery_id;
    this.user_id = student_id;
    this.module_id = module_id;
    this.delivery_content = delivery_content;
    this.worklist_id = worklist_id;
    this.delivery_timestamp = delivery_timestamp;
    this.delivery_isEvaluated = delivery_isEvaluated;
    }
    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getDelivery_content() {
        return delivery_content;
    }

    public void setDelivery_content(String delivery_content) {
        this.delivery_content = delivery_content;
    }

    public String getWorklist_id() {
        return worklist_id;
    }

    public void setWorklist_id(String worklist_id) {
        this.worklist_id = worklist_id;
    }

    public String getDelivery_timestamp() {
        return delivery_timestamp;
    }

    public void setDelivery_timestamp(String delivery_timestamp) {
        this.delivery_timestamp = delivery_timestamp;
    }

    public boolean isDelivery_isEvaluated() {
        return delivery_isEvaluated;
    }

    public void setDelivery_isEvaluated(boolean delivery_isEvaluated) {
        this.delivery_isEvaluated = delivery_isEvaluated;
    }
    
    
}
