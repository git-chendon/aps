package genetic;

import model.Job;
import model.Order;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/25 11:26
 */
public class Fitness {

    //计算适应度
    public int[] fitness(List<int[][]> chromList, List<Order> orderSorted,
                         List<Job> originJobList, int machineNum) {
        List<List<Job>> jobListScheduled;
        int size = chromList.size();
        //获取排程结果
        jobListScheduled = scheduling(chromList, orderSorted, originJobList, machineNum);

        //提取适应度参数--订单完成时间--订单惩罚度--计算订单超时惩罚
        int n = 0;
        double[][] fitParam = new double[2][size];
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
                    orderOvertimePunish += (endTime - order.getOrderEndDate());
                }
            }
            fitParam[0][n] = minTime(jobList);     //订单完成时间
            fitParam[1][n] = punishSum(jobList) + orderOvertimePunish;   //惩罚因子
            n++;
        }

        //计算适应度
        int[] result = new int[size];
        for (int i =0;i < size; i++) {

//            System.out.println("工作完成时间"+fitParam[0][i]+"惩罚值"+fitParam[1][i]);

            result[i] = (int)(0.7*fitParam[0][i] + 0.3*fitParam[1][i]);
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
    private int punishSum(List<Job> jobList) {
        int sum = 0;
        for (Job job : jobList) {
            sum += job.getPunishment();
        }
        return sum;
    }

}
