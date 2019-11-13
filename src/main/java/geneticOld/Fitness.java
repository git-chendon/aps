package geneticOld;

import domain.Job;

import java.util.List;
/**
 * @auther chen.don
 * @date 2019/10/13 21:07
 */

public class Fitness {

    //计算个体适应度,完成时间最小
    public double[] fitness(List<int[][]> chromList, int[][] durationTime) {
        List<List<Job>> jobListScheduled;
        jobListScheduled = fitGroup(chromList, durationTime);
        //计算适应度
        return timeList(jobListScheduled);
    }

    //获取解码-排程列表集合
    //输入染色体集合列表。
    private List<List<Job>> fitGroup(List<int[][]> chromList, int[][] durationTime) {
        List<List<Job>> jobList;
        List<List<Job>> jobListScheduled;
        //解码染色体
        Decoding decoding = new Decoding();
        jobList = decoding.decoding(chromList);
        //简单排程
        Schedule schedule = new Schedule();
        jobListScheduled = schedule.scheduleGroup(jobList, durationTime);
        return jobListScheduled;
    }
    //获得适应度--全部工序完成时间
    private double[] timeList(List<List<Job>> jobListScheduled) {
        int cont = jobListScheduled.size();
        double[] fitTime = new double[cont];
        for (int i = 0; i < cont; i++) {
            List<Job> jobs = jobListScheduled.get(i);
            fitTime[i] = minTime(jobs);
        }
        return fitTime;
    }
    private double minTime(List<Job> jobList) {
        double Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }
}
