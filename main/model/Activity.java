package main.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author krisli
 */
public class Activity {

	// Emrat e objekteve SimpleStringProperty me te vogla
        private final StringProperty name;
	private final StringProperty id;
	private final StringProperty duration;
	private final StringProperty budget;
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
	public int valDUR;
	public int valBUDG;
	public double valPP; // Planned Progress(PP)
	public double valCP; // Current Progress(CP)
	public int valPV;
	public int valAC;
	public int valEV;
	public int valCV;
	public int valSV;
	public double valCPI;
	public double valSPI;

        public int parent=0;
        
	public Activity() {
		this("","", "", "", "", "", "", "", "", "", "", "", "");
	}

	public Activity(String name, String id, String duration, String budget, String plannedProgress, String current_progress,
			String pv, String ac, String ev, String cv, String sv, String cpi, String spi) {
		this.name = new SimpleStringProperty(name);
                this.id = new SimpleStringProperty(id);
		this.duration = new SimpleStringProperty(duration);
		this.budget = new SimpleStringProperty(budget);
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
        public String getName(){
            return name.get();
        }
        
        public void setName(String data){
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

	public int getDuration() {
		return valDUR;
	}

	public void setDuration(int Duration) {
		this.valDUR = Duration;
	}

	public int getBudget() {
		return valBUDG;
	}

	public void setBudget(int Budget) {
		this.valBUDG = Budget;
	}

	public double getPlannedProgress() {
		return valPP;
	}

	public String getPlannedProgressPercentage() {
		return "" + valPP * 100 + "%";
	}

	public void setPlannedProgress(double PP) {
		this.valPP = PP;
	}

	public double getCurrentProgress() {
		return valCP;
	}

	public String getCurrentProgressPercentage() {
		return "" + valCP * 100 + "%";
	}

	public void setCurrentProgress(double CP) {
		this.valCP = CP;
	}

	public int getPV() {
		return valPV;
	}

	public void setPV(int PV) {
		this.valPV = PV;
	}

	public int getAC() {
		return valAC;
	}

	public void setAC(int AC) {
		this.valAC = AC;
	}

	public int getEV() {
		return valEV;
	}

	public void setEV(int EV) {
		this.valEV = EV;
	}

	public int getCV() {
		return valCV;
	}

	public void setCV(int CV) {
		this.valCV = CV;
	}

	public int getSV() {
		return valSV;
	}

	public void setSV(int SV) {
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

	public String toString(int data) {
		return "" + data;
	}

	public void Calculate() {
            if(valAC == 0 || valPV== 0){
                valCPI = 0;
                valSPI = 0;
            }
            else{
		valCV = valEV - valAC;
		valSV = valEV - valPV;
		valCPI = (double) valEV / valAC;
		valSPI = (double) valEV / valPV;
            }
	}

	/*
	 * Metoda per konvertimin e variablave int dhe double brenda klases ne
	 * SimpleStringProperty
	 */

	public void ConvertToStringProperty() {
            String iString = ""+valID;
            if(this.parent!=0){
                iString = parent+"."+valID;
   
            }
		setIdString(iString);
		setDurationString(toString(valDUR));
		setBudgetString(toString(valBUDG));
		setPlannedProgressString(getPlannedProgressPercentage());
		setCurrentProgressString(getCurrentProgressPercentage());
		setPvString(toString(valPV));
		setAcString(toString(valAC));
		setEvString(toString(valEV));
		setCvString(toString(valCV));
		setSvString(toString(valSV));
		setCpiString("" + valCPI);
		setSpiString("" + valSPI);
	}
        
        public int getParentValue(){
            return this.parent;
        }
        
        public void setParentValue(int p){
            this.parent = p;
        }
}
