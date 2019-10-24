package genetic;

import model.Job;
import model.Order;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/13 21:07
 */
public class Fitness {

    /*********************初始版本**************************/
    //计算个体适应度,完成时间最小
    public int[] fitness(List<int[][]> chromList, int[][] durationTime) {
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
        Scheduling scheduling = new Scheduling();
        jobListScheduled = scheduling.schedulingGroup(jobList, durationTime);
        return jobListScheduled;
    }

    /******************改进后版本**************************************/
    //计算适应度
    public int[] fitness(List<int[][]> chromList, List<Order> orderSorted, int machineNum) {
        List<List<Job>> jobListScheduled;
        int size = chromList.size();
        //获取排程结果
        jobListScheduled = fitGroup(chromList, orderSorted, machineNum);

        //提取适应度参数--订单完成时间--订单惩罚度--计算订单超时惩罚
        int n = 0;
        int[][] fitParam = new int[2][size];
        for (List<Job> jobList : jobListScheduled) {
            int orderOvertimePunish = 0;
            for (Order order : orderSorted) {       //遍历订单找到超时订单并计算超时惩罚
                int endTime = 0;
                for (int i : order.getOrderJob()) {
                    if (endTime < jobList.get(i).getEndTime()) {
                        endTime = jobList.get(i).getEndTime();
                    }
                }
                if (endTime > order.getOrderEndDate()) {
                    orderOvertimePunish += order.getOrderEndDate() - endTime;
                }
            }
            fitParam[0][n] = minTime(jobList);     //订单完成时间
            fitParam[1][n] = punishSum(jobList) + orderOvertimePunish;   //惩罚因子
            n++;
        }

        //计算适应度
        int[] result = new int[size];
        for (int i =0;i < size; i++) {
            result[i] = (int)(0.9*fitParam[0][n] + 0.1*fitParam[1][n]);
        }
        return result;
    }

    //获取解码排程结果
    public List<List<Job>> fitGroup(List<int[][]> chromList, List<Order> orderSorted, int machineNum) {
        List<List<Job>> jobList;
        List<List<Job>> jobListScheduled;
        Decoding decoding = new Decoding();
        Scheduling scheduling = new Scheduling();
        //解码染色体
        jobList = decoding.decodingAll(chromList, orderSorted);
        //排程
        jobListScheduled = scheduling.schedulingGroup(jobList, machineNum);
        return jobListScheduled;
    }

    //获得适应度--全部工序完成时间
    private int[] timeList(List<List<Job>> jobListScheduled) {
        int cont = jobListScheduled.size();
        int[] fitTime = new int[cont];
        for (int i = 0; i < cont; i++) {
            List<Job> jobs = jobListScheduled.get(i);
            fitTime[i] = minTime(jobs);
        }
        return fitTime;
    }

    private int minTime(List<Job> jobList) {
        int Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

    private int punishSum(List<Job> jobList) {
        int sum = 0;
        for (Job job : jobList) {
            sum += job.getPunishment();
        }
        return sum;
    }

}
