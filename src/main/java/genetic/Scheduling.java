package genetic;

import domain.Job;
import domain.Machine;
import solver.ChangeColor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/25 11:18
 */

public class Scheduling {

    //加需求后--排程全部
    public List<List<Job>> schedulingGroup(List<List<Job>> jobArrayGroup, int machineNum) {
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
        List<Machine> machineList = new ArrayList<>();
        for (int i = 0; i < machineNum; i++) {
            machineList.add(new Machine());
            machineList.get(i).setMachineId(i + 1);
        }
        //遍历工序，排程
        for (Job job : jobList) {
            double jobStartTime;
            double jobEndTime;
            int punishValue = 0;

            // 处理约束
            Machine tempMachine = machineList.get(job.getMachineNum() - 1);
            if (tempMachine.getMachineUseableTime() == 0) {
                jobStartTime = job.getJobReadyTime();
            } else if (job.getJobModel() != tempMachine.getMachineModel()) {     //判断是否换模,换模惩罚
                punishValue += 5;     //换模惩罚
                if (job.getJobMaterial() != tempMachine.getMachineMaterial()) {    //判断物料是否一致
                    punishValue += 0.1;    //物料惩罚
                    ChangeColor changeColor = new ChangeColor();
                    punishValue += changeColor.changeColorPunish(job.getJobColor(), tempMachine.getMachineColor());  //颜色惩罚
                }
                jobStartTime = tempMachine.getMachineUseableTime() + job.getJobReadyTime() + job.getJobTakeDownTime() ;  //换模计算时间
            } else {
                if (job.getJobMaterial() != tempMachine.getMachineMaterial()) {    //判断物料是否一致
                    punishValue += 0.1;    //物料惩罚
                    ChangeColor changeColor = new ChangeColor();
                    punishValue += changeColor.changeColorPunish(job.getJobColor(), tempMachine.getMachineColor());  //颜色惩罚
                }
                jobStartTime = tempMachine.getMachineUseableTime();    //不换模具不计算换模时间
            }

            jobEndTime = jobStartTime + job.getJobProductTime();

            //更新工序参数
            BigDecimal bgstart = new BigDecimal(jobStartTime);
            jobStartTime = bgstart.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal bgend = new BigDecimal(jobEndTime);
            jobEndTime = bgend.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
