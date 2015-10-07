package main.util;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

/**
 * Created by Marin Kaçaj on 10/6/2015.
 * Converts activities into the expected category data set format.
 */
public class IntervalBuilder {


    public static IntervalCategoryDataset buildDataSet() {

        TaskSeries s1 = new TaskSeries("Scheduled");

        Task t1 = new Task(
                "Write Proposal", CalendarUtil.parse("01-01-2015").getTime(), CalendarUtil.parse("01-02-2015").getTime()
        );
        t1.setPercentComplete(1.00);
        s1.add(t1);

        Task t2 = new Task(
                "Write Proposal", CalendarUtil.parse("05-01-2015").getTime(), CalendarUtil.parse("04-02-2015").getTime()
        );
        t2.setPercentComplete(0.8);
        s1.add(t2);

        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }
}
