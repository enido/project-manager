    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author krisli
 */

public final class Kolona {
    
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty duration = new SimpleStringProperty("");
    private final SimpleStringProperty budget = new SimpleStringProperty("");
    private final SimpleStringProperty plannedProgress = new SimpleStringProperty("");
    private final SimpleStringProperty currentProgress = new SimpleStringProperty("");
    private final SimpleStringProperty pv = new SimpleStringProperty("");
    private final SimpleStringProperty ac = new SimpleStringProperty("");
    private final SimpleStringProperty ev = new SimpleStringProperty("");
    private final SimpleStringProperty cv = new SimpleStringProperty("");
    private final SimpleStringProperty sv = new SimpleStringProperty("");
    private final SimpleStringProperty cpi = new SimpleStringProperty("");
    private final SimpleStringProperty spi = new SimpleStringProperty("");

    
   
    
    public Kolona(){
         this("","","","","","","","","","","","");
    }
    
    
    public Kolona(String id,String duration, String budget, String planned_advance, String current_advance, String pv, String ac, String ev, String cv, String sv, String cpi, String spi){
        setID(id);
        setDuration(duration);
        setBudget(budget);
        setPlannedAdvance(planned_advance);
        setCurrentAdvance(current_advance);
        setPV(pv);
        setAC(ac);
        setEV(ev);
        setCV(cv);
        setSV(sv);
        setCPI(cpi);
        setSPI(spi);
    }
    
    //ID
    public String getID(){
        return id.get();
    }
    
    public void setID(String data){
        id.set(data);
    }
    
    //DURATION
    public String getDuration(){
        return duration.get();
    }
    
    public void setDuration(String data){
        duration.set(data);
    }
    
    //BUDGET
    public String getBudget(){
        return budget.get();
    }
    
    public void setBudget(String data){
        budget.set(data);
    }
    
    //PLANNED ADVANCE
    public String getPlannedAdvance(){
        return plannedProgress.get();
    }
    
    public void setPlannedAdvance(String data){
        plannedProgress.set(data);
    }
    
    //CURRENT ADVANCE
    public String getCurrentAdvance(){
        return currentProgress.get();
    }
    
    public void setCurrentAdvance(String data){
        currentProgress.set(data);
    }
    
    //PV
    public String getPV(){
        return pv.get();
    }
    
    public void setPV(String data){
        pv.set(data);
    }
    
    //AC
    public String getAC(){
        return ac.get();
    }
    
    public void setAC(String data){
         ac.set(data);
    }
    
    //EV
    public String getEV(){
        return ev.get();
    }
    
    public void setEV(String data){
        ev.set(data);
    }
    
    //CV
    public String getCV(){
        return cv.get();
    }
    
    public void setCV(String data){
        cv.set(data);
    }
    
    //SV
    public String getSV(){
        return sv.get();
    }
    
    public void setSV(String data){
        sv.set(data);
    }
    
    //CPI
    public String getCPI(){
        return cpi.get();
    }
    
    public void setCPI(String data){
        cpi.set(data);
    }
    
    //SPI
    public String getSPI(){
        return spi.get();
    }
    
    public void setSPI(String data){
        spi.set(data);
    }
}
