package genetic;

import domain.Job;
import domain.Order;
import schedule.Scheduling;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/25 11:26
 */
public class Fitness {

    //计算适应度
    public int[] fitness(List<int[][]> chromList, List<Order> orderSorted,
                         List<Job> originJobList, int machineNum ,int fitFlag) {
        List<List<Job>> jobListScheduled;
        int size = chromList.size();
        //获取排程结果
        jobListScheduled = scheduling(chromList, orderSorted, originJobList, machineNum);

        //提取适应度参数--订单完成时间--订单惩罚度--计算订单超时惩罚
        int n = 0;
        double[][] fitParam = new double[3][size];
        for (List<Job> jobList : jobListScheduled) {
            double orderOvertimePunish = 0;
            for (Order order : orderSorted) {       //遍历订单找到超时订单并计算超时惩罚
                double endTime = 0;
                for (int i : order.getOrderJob()) {
                    if (endTime < jobList.get(i - 1).getEndTime()) {
                        endTime = jobList.get(i - 1).getEndTime();
                    }
                }
                if (endTime > order.getOrderEndDate()) {          //如果超时
                    orderOvertimePunish += 5;                     //按超时数量计算
//                    orderOvertimePunish += (endTime - order.getOrderEndDate()); //按超时时间算
                }
            }


            fitParam[0][n] = minTime(jobList);     //订单完成时间
            fitParam[2][n] = machineWorkTime(jobList, machineNum);     //机器平均使用时间
            fitParam[1][n] = punishSum(jobList) + orderOvertimePunish;   //惩罚因子

            n++;
        }

        //计算适应度
        int[] result = new int[size];
        switch (fitFlag)
        {
            case 0:   //综合
                for (int i = 0; i < size; i++) {
                    result[i] = (int) (0.5 * fitParam[0][i] + 0.4 * fitParam[2][i] + 0.1 * fitParam[1][i]);
                }
                break;
            case 1 :  //考虑总完成时间
                for (int i = 0; i < size; i++) {
                    result[i] = (int) (0.9 * fitParam[0][i] + 0.1 * fitParam[1][i]);
                }
                break;
            case 2:  //考虑平均完成时间
                for (int i = 0; i < size; i++) {
                    result[i] = (int) (0.9 * fitParam[2][i] + 0.1 * fitParam[1][i]);
                }
                break;
            case 3:
                for (int i = 0; i < size; i++) {
                    result[i] = (int) (fitParam[0][i]);
                }
        }

        return result;
    }

    //获取解码排程结果
    private List<List<Job>> scheduling(List<int[][]> chromList, List<Order> orderSorted,
                                       List<Job> originJobList, int machineNum) {
        List<List<Job>> jobList;
        List<List<Job>> jobListScheduled;
        Decoding decoding = new Decoding();
        Scheduling schedule = new Scheduling();
        //解码染色体
        jobList = decoding.decodingAll(chromList, orderSorted, originJobList);
        //排程
        jobListScheduled = schedule.schedulingGroup(jobList, machineNum);
        return jobListScheduled;
    }

    //获取总订单最小完成时间
    private double minTime(List<Job> jobList) {
        double Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

    //获取每组染色体的惩罚值
    private double punishSum(List<Job> jobList) {
        double sum = 0;
        for (Job job : jobList) {
            sum += job.getPunishment();
        }
        return sum;
    }

    //获取机器每台机器的工作时间
    private double machineWorkTime(List<Job> jobList, int machineNum) {
        double[] temp = new double[machineNum];
        double result = 0;
        for (int i = 0; i < machineNum; i++) {
            for (Job job : jobList) {
                if (job.getMachineNum() == i + 1 && job.getEndTime() > temp[i]) {
                    temp[i] = job.getEndTime();
                }
            }
            result = result + temp[i];
        }
        return result / machineNum;
    }

}
