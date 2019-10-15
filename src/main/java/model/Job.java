package model;

/**
 * @auther chen.don
 * @date 2019/10/12 10:53
 */
public class Job {
    /**
     * 工序编号
     */
    private int jobNum;
    /**
     * 机器
     */
    private int MachineNum;
    /**
     * 工序开始时间
     */
    private int StartTime;
    /**
     * 工序结束时间
     */
    private int endTime;

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public int getMachineNum() {
        return MachineNum;
    }

    public void setMachineNum(int machineNum) {
        MachineNum = machineNum;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int startTime) {
        StartTime = startTime;
    }
}
