/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.List;

/**
 *
 * @author Marius
 */
public class Announcement {
    
    private int anNumber;
    private String title;
    private String description;
    private String author;
    private String date;

    public int getAnNumber() {
        return anNumber;
    }
    
    public String getTitle() {
        return title;
       }
    
    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }
    
    public void setAnNumber (int anNumber) {
        this.anNumber = anNumber;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setDate (String date){
        this.date = date;
    }

    public void add(List<Announcement> announcements) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
