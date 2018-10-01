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
public class LearningGoal {
    
    private String learn_goal_id;
    private int points;
    private String text;
    
    public void setLearn_goal_id(String learn_goal_id){
        this.learn_goal_id = learn_goal_id;
    }
    
    public String getLearn_goal_id() {
        return learn_goal_id;
    }
    
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
