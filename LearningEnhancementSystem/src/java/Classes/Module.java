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
        
    private int moduleid;
    private String name;
    private String desc;
    private String content;
    private boolean published;
    private boolean interview;
    private ArrayList<LearningGoal> lgoals = new ArrayList<>();

    public int getModuleid() {
        return moduleid;
    }

    public void setId(int moduleid) {
        this.moduleid = moduleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
    
    public boolean getInInterview() {
        return interview;
    }

    public void setinInterview(boolean interview) {
        this.interview = interview;
    }
    
   
    public void addLearningGoal(LearningGoal lg) {
        lgoals.add(lg);
    }
    
    public ArrayList<LearningGoal> getLearningGoals(){
        return lgoals;
    }
}
