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
        
    private int id;
    private int student_id;
    private int module_id;
    private String delivery_content;
    private int worklist_id;
    private int delivery_timestamp; 
    private boolean delivery_isEvaluated;
    private ArrayList<Worklist> worklist_1 = new ArrayList<>();
    private ArrayList<Worklist> worklist_2 = new ArrayList<>();

    public int getId() {
        return id;
    }

    public int getStudent_id() {
        return student_id;
    }
    
    public int module_id() {
        return module_id;
    }
    
    public String delivery_content() {
        return delivery_content;
    }
    
    public int worklist_id() {
           return worklist_id;
    }
    
    public int delivery_timestamp() {
        return delivery_timestamp;
    }
    
    public boolean delivery_isEvaluated() {
        return delivery_isEvaluated;
    }
    
    public ArrayList<Worklist> getWorklistNo1(){
        return worklist_1;
    }
    public ArrayList<Worklist> getWorklistNo2(){
        return worklist_2;
    }
    }