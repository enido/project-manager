/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author krisli
 */

@XmlRootElement(name = "activities")
public class ActivityListWrapper {
    
    private List<Activity> activities;
    
    @XmlElement(name="activity")
    public List<Activity> getActivities(){
        return activities;
    }
    
    public void setActivities(List<Activity> activities){
        this.activities = activities;
    }
    
}
