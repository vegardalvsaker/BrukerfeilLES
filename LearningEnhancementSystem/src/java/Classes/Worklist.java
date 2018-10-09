/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author Kivi_
 */
//public class Worklist {
    
  //  private int worklistid;
    //private int teacherid;
    
   // private ArrayList<Evaluation> Todo = new ArrayList<>();
   // private ArrayList<Evaluation> Evaluated = new ArrayList<>();
   
public class Worklist {
        
    private int delivery_id;
    private int student_id;
    private int module_id;
    private String delivery_content;
    private int worklist_id;
    private int delivery_timestamp; 
    private boolean delivery_isEvaluated;
    private ArrayList<Worklist> delivery = new ArrayList<>();
    private ArrayList<Worklist> worklist_2 = new ArrayList<>();

    public int setId() {
        return delivery_id;
    }

    public int setStudentId() {
        return student_id;
    }
    
    public int setModuleId() {
        return module_id;
    }
    
    public String setDesc() {
        return delivery_content;
    }
    
    public int setTimestamp() {
           return worklist_id;
    }
    
    public int setIsEvaluated() {
        return delivery_timestamp;
    }
    
    public boolean delivery_isEvaluated() { 
        return delivery_isEvaluated;
    }
    
    public ArrayList<Worklist> getWorklistNo1(){
        return delivery;
    }
    public ArrayList<Worklist> getWorklistNo2(){
        return worklist_2;
    }
    }