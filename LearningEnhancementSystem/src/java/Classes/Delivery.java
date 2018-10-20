/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Timestamp;
/**
 *
 * @author Filip
 */
public class Delivery {
    private int delivery_id;
    private int student_id;
    private int module_id;
    private String delivery_content;
    private int worklist_id;
    private timestamp delivery_timestamp;
    
    
    public getId(){
        return student_id;
    }
    
    public Timestamp getTime() {
        return timestamp;
    }
    
    public void setTime(Timestamp timestamp){
       this.timestamp= timestamp;
   }
}


