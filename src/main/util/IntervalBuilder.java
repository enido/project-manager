package main.util;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

import javafx.collections.ObservableList;
import main.model.Activity;

/**
 * Created by Marin KaÃ§aj on 10/6/2015. Converts activities into the expected
 * category data set format.
 */
public class IntervalBuilder {

	public static IntervalCategoryDataset buildDataSet(ObservableList < Activity > data) {

		TaskSeries s1 = new TaskSeries("Scheduled");
		TaskSeries s2 = new TaskSeries("Actual");

		if (!data.isEmpty() || data != null) {

			for (int i = 0; i < data.size(); i++) {
				Activity temp = data.get(i);
				if (temp.getParentValue() != 0) {
					String taskName = "" + temp.getIdString() + " - " + temp.getName();
					Task t1 = new Task(taskName, temp.getStartTimeValue().getTime(), temp.getEndTimeValue().getTime());
					// t1.setPercentComplete(temp.getCurrentProgress());
					s1.add(t1);

					Task t2 = new Task(taskName, temp.getStartTimeValue().getTime(),
					temp.getCurrentProgressValue().getTime());
					s2.add(t2);
				}
			}
		}

		TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(s1);
		collection.add(s2);

		return collection;
	}
}