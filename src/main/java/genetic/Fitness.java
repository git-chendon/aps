package genetic;

import model.Job;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/13 21:07
 */
public class Fitness {

    //计算个体适应度,完成时间最小
    public int[] fitness(List<int[][]> chromList,int[][] durationTime) {
        List<List<Job>> jobListScheduled;
        jobListScheduled = fitGroup(chromList,durationTime);
        //计算适应度
        int cont = jobListScheduled.size();
        int[] fitTime = new int[cont];
        for (int i = 0; i < cont; i++) {
            List<Job> jobs = jobListScheduled.get(i);
            fitTime[i] = minTime(jobs);
        }
        return fitTime;
    }

    //获取解码-排程列表集合
    //输入染色体集合列表。
    public List<List<Job>> fitGroup(List<int[][]> chromList,int[][] durationTime) {
        List<List<Job>> jobList;
        List<List<Job>> jobListScheduled;
        //解码染色体
        Decoding decoding = new Decoding();
        jobList = decoding.decoding(chromList);
        //简单排程
        Scheduling scheduling = new Scheduling();
        jobListScheduled = scheduling.schedulingGroup(jobList, durationTime);
        return jobListScheduled;
    }

    //获得适应度--全部工序完成时间
    public static int minTime(List<Job> jobList) {
        int Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

}
