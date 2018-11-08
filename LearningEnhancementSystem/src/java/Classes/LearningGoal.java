/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Marius
 */
public class LearningGoal {
    
    private String learnGoalId;
    private String learnGoalText;
    private String learnGoalPoints;
    private String moduleId;
    
    public void setLearnGoalId(String learnGoalId){
        this.learnGoalId = learnGoalId;
    }
    
    public String getLearnGoalId() {
        return learnGoalId;
    }
    
    public String getLearnGoalText() {
        return learnGoalText;
    }

    public void setLearnGoalText(String learnGoalText) {
        this.learnGoalText = learnGoalText;
    }
    
    public String getLearnGoalPoints() {
        return learnGoalPoints;
    }

    public void setLearnGoalPoints(String learnGoalPoints) {
        this.learnGoalPoints = learnGoalPoints;
    }

    public void setModuleId(String moduleId)    {
        this.moduleId = moduleId;
    }
    
    public String getModuleId() {
        return moduleId;
    }
}
