package main.util;

import javafx.collections.ObservableList;
import main.model.Activity;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 * Created by Marin Kaçaj on 10/6/2015.
 * Converts activities into the expected category data set format.
 */
public class IntervalBuilder {

    public static IntervalCategoryDataset buildDataSet(ObservableList<Activity> data) {
      
        TaskSeries s1 = new TaskSeries("Aktivitete");
        
        if(!data.isEmpty() || data !=null){
            
         for(int i=0;i<data.size();i++){
            Activity temp = data.get(i);
            if(temp.getParentValue() != 0){
                String taskName = ""+temp.getIdString()+" - "+temp.getName();
                Task t = new Task(taskName, temp.getStartTimeValue().getTime(), temp.getEndTimeValue().getTime());
                t.setPercentComplete(temp.getCurrentProgress());
                s1.add(t);               
                }
            }
        }

        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }
}