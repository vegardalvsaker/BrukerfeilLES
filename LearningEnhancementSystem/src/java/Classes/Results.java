package Classes;

/**
 *
 * @author Gorm-Erik
 */
public class Results {
    
    private int moduleID;
    private int scoreID;
    private int learngoalID;
    private String moduleName;
    private int scorePoints;
    private String learngoal;
    
    public int getScoreID() {
        
        return scoreID;  
    }
    
    public int getLearngoalID()   {
    
    return learngoalID;
    
}
    
    public String getModuleName()   {
        return moduleName;
    }
    
    public int getModuleID() {
        return moduleID;
    }
    
    public int getScorePoints() {
        return scorePoints;
    }
    
    public String getLearngoal()    {
        return learngoal;
    }
    
    public void setScoreID(int scoreID) {
        
        this.scoreID = scoreID;
    }
    
    public void setLearngoalID(int learngoalID) {
        this.learngoalID = learngoalID;
        
    }
    
    public void setScorePoints(int scorePoints) {
        
        this.scorePoints = scorePoints;
    }
    
    public void setLearngoal(String learngoal)  {
        
        this.learngoal = learngoal;
    }
    
    public void setModuleID(int moduleID)   {
        this.moduleID = moduleID;
    }
    
    public void setModuleName(String moduleName)    {
        
        this.moduleName = moduleName;
    }
    
}
