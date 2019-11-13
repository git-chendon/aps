package ganttChart;



import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
/**
 * @auther chen.don
 * @date 2019/11/6 18:12
 */

public class GanttChart{

    public static void main(String[] args) {
        IntervalCategoryDataset dataset = createSampleDataset();
        JFreeChart chart = ChartFactory.createGanttChart("任务管理系统",
                "任务各阶段详细实施计划",
                "任务周期",
                dataset,
                false,
                false,
                false);

        CategoryPlot plot=chart.getCategoryPlot();

        chart.getTitle().setFont(new Font("新宋体",Font.BOLD,20));
        CategoryAxis domainAxis=plot.getDomainAxis();
        //水平底部列表
        domainAxis.setLabelFont(new Font("新宋体",Font.BOLD,14));
        //水平底部标题
        domainAxis.setTickLabelFont(new Font("新宋体",Font.BOLD,12));
        //垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("新宋体",Font.BOLD,16));
        //用来控制时间轴的显示,防止乱码
        DateAxis da = (DateAxis)plot.getRangeAxis(0);
        da.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

        FileOutputStream fop = null;
        try{
            System.out.println("Danny>> begin.");
            fop = new FileOutputStream("D:\\gantt.jpg");
            ChartUtilities.writeChartAsJPEG(fop,1f, chart, 800, 600,null);
            System.out.println("Danny>> end..");
            System.out.println("Danny>> successful...");
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                fop.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /** *//**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day 日
     * @param month 月
     * @param year 年
     *
     * @return a date.
     */
    private static Date date(final int day, final int month, final int year){

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        final Date result = calendar.getTime();
        return result;

    }

    /**
     *
     * @return The dataset.
     */
    private static IntervalCategoryDataset createSampleDataset() {

        final TaskSeries s1 = new TaskSeries("SCHEDULE");

        final Task t1 = new Task("任务1", date(1, Calendar.JANUARY, 2001), date(5, Calendar.APRIL, 2001));
        t1.setPercentComplete(0.8);
        s1.add(t1);

        // 创建一个任务并插入两个子任务
        final Task t3 = new Task("任务2", date(10, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001));
        final Task st31 = new Task("需求1", date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
        st31.setPercentComplete(0.50);
        final Task st32 = new Task("需求2", date(1, Calendar.MAY, 2001), date(5, Calendar.MAY, 2001));
        st32.setPercentComplete(1.0);
        t3.addSubtask(st31);
        t3.addSubtask(st32);
        s1.add(t3);

        final Task t5 = new Task( "任务3", date(2, Calendar.JUNE, 2001), date(2, Calendar.JUNE, 2001));
        s1.add(t5);

        final Task t6 = new Task("任务4", date(3, Calendar.MARCH, 2001), date(31, Calendar.JULY, 2001));
        t6.setPercentComplete(0.60);

        s1.add(t6);

        final Task t8 = new Task("任务结束", date(10, Calendar.AUGUST, 2001), date(10, Calendar.AUGUST, 2001));
        t8.setPercentComplete(0.0);
        s1.add(t8);

        final Task t9 = new Task("任务试用", date(12, Calendar.AUGUST, 2001), date(12, Calendar.SEPTEMBER, 2001));
        t9.setPercentComplete(0.0);
        s1.add(t9);

        final Task t10 = new Task("任务测试", date(13, Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001));
        t10.setPercentComplete(0.2);
        s1.add(t10);

        final Task t12 = new Task("全部结束", date(28, Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001));
        t12.setPercentComplete(0.0);
        s1.add(t12);

        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }
}
