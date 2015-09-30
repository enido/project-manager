/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

/**
 *
 * @author krisli
 */
public class Parametra {
    //Parametrat
    public int id;
    public int duration;
    public int budget;
    public double plannedAdvance;
    public double currentAdvance;
    public int pv;
    public int ac;
    public int ev;
    public int cv;
    public int sv;
    public double cpi;
    public double spi;
    
    public Parametra(int id, int duration, int budget, double plannedAdvance, double currentAdvance, int pv, int ac, int ev){
        this.id = id;
        this.duration = duration;
        this.budget = budget;
        this.plannedAdvance = plannedAdvance;
        this.currentAdvance = currentAdvance;
        this.pv = pv;
        this.ac = ac;
        this.ev = ev;
        
        cv = ev-ac;
        sv = ev-pv;
        cpi = (double) ev/ac;
        spi = (double) ev/pv;
    }
    
    public Parametra(){
        this.id = -1;
    }
    
    public int getID(){
        return id;
    }
    
    public void setID(int ID){
        this.id = ID;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public void setDuration(int Duration){
        this.duration = Duration;
    }
    
    public int getBudget(){
        return budget;
    }
    
    public void setBudget(int Budget){
        this.budget = Budget;
    }
    
    public double getPlannedProgress(){
        return plannedAdvance;
    }
    
    public String getPlannedProgressString(){
        return ""+plannedAdvance*100+"%";
    }
    
    public void setPlannedProgress(double PA){
        this.plannedAdvance = PA;
    }
    
    public double getCurrentProgress(){
        return currentAdvance;
    }
    
    public String getCurrentProgressString(){
        return ""+currentAdvance*100+"%";
    }
    
    public void setCurrentProgress(double SA){
        this.currentAdvance = SA;
    }
    
    public int getPV(){
        return pv;
    }
    
    public void setPV(int PV){
        this.pv = PV;
    }
    
    public int getAC(){
        return ac;
    }
    
    public void setAC(int AC){
        this.ac = AC;
    }
    
    public int getEV(){
        return ev;
    }
    
    public void setEV(int EV){
        this.ev = EV;
    }
    
    public int getCV(){
        return cv;
    }
    
    public void setCV(int CV){
        this.cv = CV;
    }
    
    public int getSV(){
        return sv;
    }
    
    public void setSV(int SV){
        this.sv = SV;
    }
    
    public double getCPI(){
        return cpi;
    }
    
    public void setCPI(double CPI){
        this.cpi = CPI;
    }
    
    public double getSPI(){
        return spi;
    }
    
    public void setSPI(double SPI){
        this.spi = SPI;
    }
    
    public String toString(int data){
        return ""+data;
    }
    
    public void Calculate(){
        cv = ev-ac;
        sv = ev-pv;
        cpi = (double) ev/ac;
        spi = (double) ev/pv;
    }
    
    public Kolona konvertoParametra(){
        Kolona kolona = new Kolona(
            toString(id), toString(duration), toString(budget), getPlannedProgressString(),
            getCurrentProgressString(), toString(pv), toString(ac), toString(ev), toString(cv),
            toString(sv), ""+cpi, ""+spi);
        
        return kolona;
    }
}
