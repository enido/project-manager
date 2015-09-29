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
public class Activity {
    
    //Emrat e objekteve SimpleStringProperty me te vogla
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
    
    //Emrat e variablave int,double, me te medha
    public int ID;
    public int DUR;
    public int BUDG;
    public double PP;  //Planned Progress(PP)
    public double CP; //Current Progress(CP)
    public int PV;
    public int AC;
    public int EV;
    public int CV;
    public int SV;
    public double CPI;
    public double SPI;
    
    public Activity(){
        this("","","","","","","","","","","","");
    }
    
    public Activity(String id,String duration, String budget, String planned_progress, String current_progress, String pv, String ac, String ev, String cv, String sv, String cpi, String spi){
        setIdString(id);
        setDurationString(duration);
        setBudgetString(budget);
        setPlannedProgressString(planned_progress);
        setCurrentProgressString(current_progress);
        setPvString(pv);
        setAcString(ac);
        setEvString(ev);
        setCvString(cv);
        setSvString(sv);
        setCpiString(cpi);
        setSpiString(spi);
    }
    
    /*---------------METODAT PER STRINGS-----------------*/
    
    //ID
    public String getIdString(){
        return id.get();
    }
    
    public void setIdString(String data){
        id.set(data);
    }
    
    //DURATION
    public String getDurationString(){
        return duration.get();
    }
    
    public void setDurationString(String data){
        duration.set(data);
    }
    
    //BUDGET
    public String getBudgetString(){
        return budget.get();
    }
    
    public void setBudgetString(String data){
        budget.set(data);
    }
    
    //PLANNED PROGRESS
    public String getPlannedProgressString(){
        return plannedProgress.get();
    }
    
    public void setPlannedProgressString(String data){
        plannedProgress.set(data);
    }
    
    //CURRENT ROGRESS
    public String getCurrentProgressString(){
        return currentProgress.get();
    }
    
    public void setCurrentProgressString(String data){
        currentProgress.set(data);
    }
    
    //PV
    public String getPvString(){
        return pv.get();
    }
    
    public void setPvString(String data){
        pv.set(data);
    }
    
    //AC
    public String getAcString(){
        return ac.get();
    }
    
    public void setAcString(String data){
         ac.set(data);
    }
    
    //EV
    public String getEvString(){
        return ev.get();
    }
    
    public void setEvString(String data){
        ev.set(data);
    }
    
    //CV
    public String getCvString(){
        return cv.get();
    }
    
    public void setCvString(String data){
        cv.set(data);
    }
    
    //SV
    public String getSvString(){
        return sv.get();
    }
    
    public void setSvString(String data){
        sv.set(data);
    }
    
    //CPI
    public String getCpiString(){
        return cpi.get();
    }
    
    public void setCpiString(String data){
        cpi.set(data);
    }
    
    //SPI
    public String getSpiString(){
        return spi.get();
    }
    
    public void setSpiString(String data){
        spi.set(data);
    }
    
    /*----------------FUND METODAT PER STRINGS------------------*/
    
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getDuration(){
        return DUR;
    }
    
    public void setDuration(int Duration){
        this.DUR = Duration;
    }
    
    public int getBudget(){
        return BUDG;
    }
    
    public void setBudget(int Budget){
        this.BUDG = Budget;
    }
    
    public double getPlannedProgress(){
        return PP;
    }
    
    public String getPlannedProgressPercentage(){
        return ""+PP*100+"%";
    }
    
    public void setPlannedProgress(double PP){
        this.PP = PP;
    }
    
    public double getCurrentProgress(){
        return CP;
    }
    
    public String getCurrentProgressPercentage(){
        return ""+CP*100+"%";
    }
    
    public void setCurrentProgress(double CP){
        this.CP = CP;
    }
    
    public int getPV(){
        return PV;
    }
    
    public void setPV(int PV){
        this.PV = PV;
    }
    
    public int getAC(){
        return AC;
    }
    
    public void setAC(int AC){
        this.AC = AC;
    }
    
    public int getEV(){
        return EV;
    }
    
    public void setEV(int EV){
        this.EV = EV;
    }
    
    public int getCV(){
        return CV;
    }
    
    public void setCV(int CV){
        this.CV = CV;
    }
    
    public int getSV(){
        return SV;
    }
    
    public void setSV(int SV){
        this.SV = SV;
    }
    
    public double getCPI(){
        return CPI;
    }
    
    public void setCPI(double CPI){
        this.CPI = CPI;
    }
    
    public double getSPI(){
        return SPI;
    }
    
    public void setSPI(double SPI){
        this.SPI = SPI;
    }
    
    public String toString(int data){
        return ""+data;
    }
    
    public void Calculate(){
        CV = EV-AC;
        SV = EV-PV;
        CPI = (double) EV/AC;
        SPI = (double) EV/PV;
    }
    
    /*Metoda per konvertimin e variablave int dhe double brenda klases
                        ne SimpleStringProperty
                                                                     */
    
    public void ConvertToStringProperty(){
        setIdString(toString(ID));
        setDurationString(toString(DUR));
        setBudgetString(toString(BUDG));
        setPlannedProgressString(getPlannedProgressPercentage());
        setCurrentProgressString(getCurrentProgressPercentage());
        setPvString(toString(PV));
        setAcString(toString(AC));
        setEvString(toString(EV));
        setCvString(toString(CV));
        setSvString(toString(SV));
        setCpiString(""+CPI);
        setSpiString(""+SPI);
    }
}
