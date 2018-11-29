/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import Classes.Score;
/**
 *
 * @author Vegard
 */
public class Evaluation {
    private String evaluationid;
    private String teacherid;
    private String deliveryid;
    private String comment;
    private boolean isPublished;
    private ArrayList<Score> scorelist = new ArrayList<>();
    
    public boolean isIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }
    
    public String getEvaluationid() {
        return evaluationid;
    }

    public void setEvaluationid(String evaluationid) {
        this.evaluationid = evaluationid;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getDeliveryid() {
        return deliveryid;
    }

    public void setDeliveryid(String deliveryid) {
        this.deliveryid = deliveryid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<Score> getScorelist() {
        return scorelist;
    }

    public void addScoreToList(Score score) {
        scorelist.add(score);
    }
    
    
}
