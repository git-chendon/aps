package genetic;

import model.Job;
import model.Machine;
import process.ChangeColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/25 11:18
 */

public class Scheduling {

    //加需求后--排程全部
    public List<List<Job>> schedulingGroup(List<List<Job>> jobArrayGroup, int machineNum){
        List<List<Job>> jobShiftingGroup = new ArrayList<>();
        for (List<Job> tempJobArray : jobArrayGroup) {      //遍历群组
            List<Job> tempJobList;
            tempJobList = schedulingSingle(tempJobArray, machineNum);
            jobShiftingGroup.add(tempJobList);
        }
        return jobShiftingGroup;
    }
    //加需求后--排程一组
    private List<Job> schedulingSingle(List<Job> jobList, int machineNum) {
        //初始化
//        List<Job> result = new ArrayList<Job>();
        List<Machine> machineList = new ArrayList<>();
        for (int i = 0; i < machineNum; i++) {
            machineList.add(new Machine());
            machineList.get(i).setMachineNum(i + 1);
        }
        int changeModelTime = 3;                 //换模时间固定
        //遍历工序，排程
        for (Job job : jobList) {
            int jobStartTime;
            int jobEndTime;
            int MachReadyTime = 0;
            int punishValue = 0;

//            job.setJobProductTime(4);     //测试数据--工序加工时间都设为4

            // 处理约束
            Machine tempMachine = machineList.get(job.getMachineNum() - 1);
            if (tempMachine.getMachineUseableTime() != 0) {
                if (job.getJobModel() != tempMachine.getMachineModel()) {     //判断是否换模
                    MachReadyTime += changeModelTime;
                    punishValue += 3;
                }
                if (job.getJobMaterial() != tempMachine.getMachineMaterial()) {    //判断物料是否一致
                    ChangeColor changeColor = new ChangeColor();
                    punishValue += changeColor.changeColorPunish(job.getJobColor(), tempMachine.getMachineColor());
                }
            }
            jobStartTime = tempMachine.getMachineUseableTime() + job.getJobReadyTime() + MachReadyTime;
            jobEndTime = jobStartTime + job.getJobTakeDownTime() + job.getJobProductTime();

            //更新工序参数
            job.setStartTime(jobStartTime);
            job.setEndTime(jobEndTime);
            job.setPunishment(punishValue);

            //更新机器参数
            tempMachine.setMachineModel(job.getJobModel());
            tempMachine.setMachineMaterial(job.getJobMaterial());
            tempMachine.setMachineColor(job.getJobColor());
            tempMachine.setMachineUseableTime(jobEndTime);
        }
        return jobList;
    }

}
