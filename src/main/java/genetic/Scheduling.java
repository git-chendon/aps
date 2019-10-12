package genetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/12 15:36
 */
public class Scheduling {
    //输入工序总数；工序序列；<工序，机器> 的映射列表；一些相关参数；
    //输出各工序开始时间，结束时间；（机器负载）

    //排程一组
    public List<Job> schedulingSingle(List<Job> JobArray, int[][] workDurationTime) {
        List<Job> jobList = new ArrayList<Job>();
        int chromSize = JobArray.size();
        int[] machineTime = new int[chromSize];//初始化各机器工作时间
        //工序开始时间已经默认为零
        int MachReadyTime = 1;  //设机器准备下一道工序时间为1
        int MachEndTime = 1;   //机器结束休整时间也为1
        for (Job job : JobArray) {   //遍历工序,排出工序开始结束时间
            int jobStartTime;
            int jobEndTime;
            if (machineTime[job.getMachineNum() - 1] == 0) {      //判断一下机器是否开始使用
                jobStartTime = machineTime[job.getMachineNum() - 1];
            } else {
                jobStartTime = machineTime[job.getMachineNum() - 1] + 1;
            }
            jobEndTime = workDurationTime[job.getJobNum() - 1][job.getMachineNum() - 1] + MachEndTime + jobStartTime;
            machineTime[job.getMachineNum() - 1] = jobEndTime;  //更新机器可用时间
            job.setStartTime(jobStartTime);
            job.setEndTime(jobEndTime);
            jobList.add(job);
        }
        return jobList;
    }

    //排程群组
    public List<List<Job>> schedulingGroup(List<List<Job>> jobArrayGroup, int[][] workDurationTime) {
        List<List<Job>> jobShiftingGroup = new ArrayList<List<Job>>();
        for (List<Job> tempJobArray : jobArrayGroup) {//遍历群组
            List<Job> tempJobList;
            tempJobList = schedulingSingle(tempJobArray, workDurationTime);
            jobShiftingGroup.add(tempJobList);
        }
        return jobShiftingGroup;
    }

}
