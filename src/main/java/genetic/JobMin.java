package genetic;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/12 19:42
 */
public class JobMin {
    //工序和对应最小完成时间

    private List<Job> jobs;
    private int minTime;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }
}
