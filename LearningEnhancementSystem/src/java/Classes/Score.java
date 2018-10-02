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
public class Score {
    private String id;
    private String learn_goal_id;
    private int points;
    private String evaluation_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLearn_goal_id() {
        return learn_goal_id;
    }

    public void setLearn_goal_id(String learn_goal_id) {
        this.learn_goal_id = learn_goal_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(String evaluation_id) {
        this.evaluation_id = evaluation_id;
    }
    
    
}
