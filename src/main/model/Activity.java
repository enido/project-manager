package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author krisli
 */
public class Activity {

    // Emrat e objekteve SimpleStringProperty me te vogla
    private final StringProperty name;
    private final StringProperty id;
    private final StringProperty duration;
    private final StringProperty budget;
    private final StringProperty unit;
    private final StringProperty price;
    private final StringProperty plannedAmount;
    private final StringProperty currentAmount;
    private final StringProperty actualAmount;
    private final StringProperty plannedProgress;
    private final StringProperty currentProgress;
    private final StringProperty pv;
    private final StringProperty ac;
    private final StringProperty ev;
    private final StringProperty cv;
    private final StringProperty sv;
    private final StringProperty cpi;
    private final StringProperty spi;

    // Emrat e variablave int,double, me te medha
    public int valID;
    public long valDUR;
    public double valBUDG;
    public double valPRICE;
    public double valPA; // Planned Amount(PA)
    public double valCA; // Current Amount(CA)
    public double valAA; // Actual Amount(AA)
    public double valPP; // Planned Progress(PP)
    public double valCP; // Current Progress(CP)
    public double valPV;
    public double valAC;
    public double valEV;
    public double valCV;
    public double valSV;
    public double valCPI;
    public double valSPI;
    public Calendar startTime;
    public Calendar endTime;

    public int parent = 0;

    public Activity() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public Activity(String name, String id, String duration, String budget, String unit, String price, String plannedAmount, String currentAmount, String actualAmount, String plannedProgress, String current_progress,
                    String pv, String ac, String ev, String cv, String sv, String cpi, String spi) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.duration = new SimpleStringProperty(duration);
        this.budget = new SimpleStringProperty(budget);
        this.unit = new SimpleStringProperty(unit);
        this.price = new SimpleStringProperty(price);
        this.plannedAmount = new SimpleStringProperty(plannedAmount);
        this.currentAmount = new SimpleStringProperty(currentAmount);
        this.actualAmount = new SimpleStringProperty(actualAmount);
        this.plannedProgress = new SimpleStringProperty(plannedProgress);
        this.currentProgress = new SimpleStringProperty(current_progress);
        this.pv = new SimpleStringProperty(pv);
        this.ac = new SimpleStringProperty(ac);
        this.ev = new SimpleStringProperty(ev);
        this.cv = new SimpleStringProperty(cv);
        this.sv = new SimpleStringProperty(sv);
        this.cpi = new SimpleStringProperty(cpi);
        this.spi = new SimpleStringProperty(spi);
    }

	/*---------------METODAT PER STRINGS-----------------*/

    //Name
    public String getName() {
        return name.get();
    }

    public void setName(String data) {
        name.set(data);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // ID
    public String getIdString() {
        return id.get();
    }

    public void setIdString(String data) {
        id.set(data);
    }

    public StringProperty idProperty() {
        return id;
    }

    // DURATION
    public String getDurationString() {
        return duration.get();
    }

    public void setDurationString(String data) {
        duration.set(data);
    }

    public StringProperty durationProperty() {
        return duration;
    }

    // BUDGET
    public String getBudgetString() {
        return budget.get();
    }

    public void setBudgetString(String data) {
        budget.set(data);
    }

    public StringProperty budgetProperty() {
        return budget;
    }
    
    // UNIT
    public String getUnitString() {
        return unit.get();
    }

    public void setUnitString(String data) {
        unit.set(data);
    }

    public StringProperty unitProperty() {
        return unit;
    }
    
    // PRICE
    public String getPriceString() {
        return price.get();
    }

    public void setPriceString(String data) {
        price.set(data);
    }

    public StringProperty priceProperty() {
        return price;
    }
    
    // PLANNED AMOUNT
    public String getPlannedAmountString() {
        return plannedAmount.get();
    }

    public void setPlannedAmountString(String data) {
        plannedAmount.set(data);
    }

    public StringProperty plannedAmountProperty() {
        return plannedAmount;
    }
    
    // CURRENT AMOUNT
    public String getCurrentAmountString() {
        return currentAmount.get();
    }

    public void setCurrentAmountString(String data) {
        currentAmount.set(data);
    }

    public StringProperty currentAmountProperty() {
        return currentAmount;
    }
    
    // ACTUAL AMOUNT
    public String getActualAmountString() {
        return actualAmount.get();
    }

    public void setActualAmountString(String data) {
        actualAmount.set(data);
    }

    public StringProperty actualAmountProperty() {
        return actualAmount;
    }

    // PLANNED PROGRESS
    public String getPlannedProgressString() {
        return plannedProgress.get();
    }

    public void setPlannedProgressString(String data) {
        plannedProgress.set(data);
    }

    public StringProperty plannedProgressProperty() {
        return plannedProgress;
    }

    // CURRENT ROGRESS
    public String getCurrentProgressString() {
        return currentProgress.get();
    }

    public void setCurrentProgressString(String data) {
        currentProgress.set(data);
    }

    public StringProperty currentProgressProperty() {
        return currentProgress;
    }

    // PV
    public String getPvString() {
        return pv.get();
    }

    public void setPvString(String data) {
        pv.set(data);
    }

    public StringProperty pvProperty() {
        return pv;
    }

    // AC
    public String getAcString() {
        return ac.get();
    }

    public void setAcString(String data) {
        ac.set(data);
    }

    public StringProperty acProperty() {
        return ac;
    }

    // EV
    public String getEvString() {
        return ev.get();
    }

    public void setEvString(String data) {
        ev.set(data);
    }

    public StringProperty evProperty() {
        return ev;
    }

    // CV
    public String getCvString() {
        return cv.get();
    }

    public void setCvString(String data) {
        cv.set(data);
    }

    public StringProperty cvProperty() {
        return cv;
    }

    // SV
    public String getSvString() {
        return sv.get();
    }

    public void setSvString(String data) {
        sv.set(data);
    }

    public StringProperty svProperty() {
        return sv;
    }

    // CPI
    public String getCpiString() {
        return cpi.get();
    }

    public void setCpiString(String data) {
        cpi.set(data);
    }

    public StringProperty cpiProperty() {
        return cpi;
    }

    // SPI
    public String getSpiString() {
        return spi.get();
    }

    public void setSpiString(String data) {
        spi.set(data);
    }

    public StringProperty spiProperty() {
        return spi;
    }

	/*----------------FUND METODAT PER STRINGS------------------*/

    public int getID() {
        return valID;
    }

    public void setID(int ID) {
        this.valID = ID;
    }

    public long getDuration() {
        return valDUR;
    }

    public void setDuration(long Duration) {
        this.valDUR = Duration;
    }

    public double getBudget() {
        return valBUDG;
    }

    public void setBudget(double Budget) {
        this.valBUDG = Budget;
    }
    
    public double getPriceValue() {
        return valPRICE;
    }

    public void setPriceValue(double PRC) {
        this.valPRICE = PRC;
    }
    
    public double getPlannedAmount(){
        return valPA;
    }
    
    public void setPlannedAmount(double PA){
        this.valPA = PA;
    }
    
    public double getCurrentAmount(){
        return valCA;
    }
    
    public void setCurrentAmount(double CA){
        this.valCA = CA;
    }
    
    public double getActualAmount(){
        return valAA;
    }
    
    public void setActualAmount(double AA){
        this.valAA = AA;
    }

    public double getPlannedProgress() {
        return valPP;
    }

    public void setPlannedProgress(double PP) {
        this.valPP = PP;
    }

    public String getPlannedProgressPercentage() {
        return "" + valPP * 100 + "%";
    }
    
    public void setPlannedProgressFromPercentage(String PPP){
        String temp;
        Double val;
        int percentageIndex = PPP.indexOf("%");
        temp = PPP.substring(0, percentageIndex);
        val = Double.parseDouble(temp);
        val = val/100;
        this.valPP = val;      
    }

    public double getCurrentProgress() {
        return valCP;
    }

    public void setCurrentProgress(double CP) {
        this.valCP = CP;
    }

    public String getCurrentProgressPercentage() {
        return "" + valCP * 100 + "%";
    }
    
    public void setCurrentProgressFromPercentage(String CPP){
        String temp;
        Double val;
        int percentageIndex = CPP.indexOf("%");
        temp = CPP.substring(0, percentageIndex);
        val = Double.parseDouble(temp);
        val = val/100;
        this.valCP = val;      
    }

    public double getPV() {
        return valPV;
    }

    public void setPV(double PV) {
        this.valPV = PV;
    }

    public double getAC() {
        return valAC;
    }

    public void setAC(double AC) {
        this.valAC = AC;
    }

    public double getEV() {
        return valEV;
    }

    public void setEV(double EV) {
        this.valEV = EV;
    }

    public double getCV() {
        return valCV;
    }

    public void setCV(double CV) {
        this.valCV = CV;
    }

    public double getSV() {
        return valSV;
    }

    public void setSV(double SV) {
        this.valSV = SV;
    }

    public double getCPI() {
        return valCPI;
    }

    public void setCPI(double CPI) {
        this.valCPI = CPI;
    }

    public double getSPI() {
        return valSPI;
    }

    public void setSPI(double SPI) {
        this.valSPI = SPI;
    }

    public String toString(double data) {
        String temp;
        temp = String.format("%1$,.2f", data);
        return temp;
    }
    
    public Calendar getStartTimeValue(){
        return this.startTime;
    }
    
    public void setStartTimeValue(Calendar sT){
        this.startTime = sT;
    }
    
    public Calendar getEndTimeValue(){
        return this.endTime;
    }
    
    public void setEndTimeValue(Calendar eT){
        this.endTime = eT;
    }

    public void Calculate() {
        getDateDiff(endTime, startTime, TimeUnit.DAYS);
        if (valAC == 0 || valPV == 0) {
            valCPI = 0;
            valSPI = 0;
        } else {
            valEV = valPRICE * valAA;
            valCV = valEV - valAC;
            valSV = valEV - valPV;
            valCPI = valEV / valAC;
            valSPI = valEV / valPV;
        }

    }
    
    public void getDateDiff(Calendar date1, Calendar date2, TimeUnit timeUnit) {
        long diff = date1.getTime().getTime() - date2.getTime().getTime();
        setDuration(diff/86400000);
    }

	/*
     * Metoda per konvertimin e variablave int dhe double brenda klases ne
	 * SimpleStringProperty
	 */

    public void ConvertToStringProperty() {
        String iString = "" + valID;
        if (this.parent != 0) {
            iString = parent + "." + valID;

        }
        setIdString(iString);
        setDurationString("" + valDUR);
        setBudgetString(toString(valBUDG));
        setPriceString(toString(valPRICE));
        setPlannedAmountString(toString(valPA));
        setCurrentAmountString(toString(valCA));
        setActualAmountString(toString(valAA));
        setPlannedProgressString(getPlannedProgressPercentage());
        setCurrentProgressString(getCurrentProgressPercentage());
        setPvString(toString(valPV));
        setAcString(toString(valAC));
        setEvString(toString(valEV));
        setCvString(toString(valCV));
        setSvString(toString(valSV));
        setCpiString(toString(valCPI));
        setSpiString(toString(valSPI));
    }

    public int getParentValue() {
        return this.parent;
    }

    public void setParentValue(int p) {
        this.parent = p;
    }
}