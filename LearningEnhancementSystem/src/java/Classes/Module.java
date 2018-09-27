/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
/**
 *
 * @author Vegard
 */
public class Module {
        
    private int module_no;
    private String short_desc;
    private String long_desc;
    private boolean published;
    private ArrayList<LearningGoal> lgoals = new ArrayList<>();
    
    public int getModule_no() {
        return module_no;
    }

    public void setModule_no(int module_no) {
        this.module_no = module_no;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getLong_desc() {
        return long_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public void addLearningGoal(LearningGoal lg) {
        lgoals.add(lg);
    }
    
    public ArrayList<LearningGoal> getLearningGoals(){
        return lgoals;
    }
    }
