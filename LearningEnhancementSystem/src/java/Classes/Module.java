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
        
    private String moduleId;
    private String moduleName;
    private String moduleDesc;
    private String moduleContent;
    private boolean isPublished;
    private boolean inInterview;
    private ArrayList<LearningGoal> lgoals = new ArrayList<>();

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String ModuleName) {
        this.moduleName = ModuleName;
    }

    public String getModuleDesc() {
        return moduleDesc;
    }

    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getModuleContent() {
        return moduleContent;
    }

    public void setModuleContent(String moduleContent) {
        this.moduleContent = moduleContent;
    }
    
    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }
    
    public boolean getInInterview() {
        return inInterview;
    }

    public void setInInterview(boolean inInterview) {
        this.inInterview = inInterview;
    }

    public void addLearningGoal(LearningGoal lg) {
        lgoals.add(lg);
    }
    
    public ArrayList<LearningGoal> getLearningGoals(){
        return lgoals;
    }
}
