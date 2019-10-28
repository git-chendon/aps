package main;

import genetic.*;
import model.Job;
import model.Machine;
import model.Order;
import process.OrderSort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auther chen.don
 * @date 2019/10/15 13:38
 */
public class OpsMain {

    private static final double CP = 0.8;  //交叉概率
    private static final double MP = 0.15;  //变异概率
    private static final long ITERA = 1000;  //迭代次数
    private static final int ChromNum = 15;  //染色体数
    private static final int GroupSize = 30; //种群数
    private static final int MachineNum = 6; //种群数

    private static List<Order> originOrderList = new ArrayList<>();      //订单信息
    private static List<Job> originJobList = new ArrayList<>();          //工序信息,和工序机器映射信息
    private static List<Machine> machineMapperList = new ArrayList<>();  //机器信息

    public static void init() {

        /******初始化订单****/
        Order order;
        Set<Integer> tempSet;

        tempSet = new HashSet<>();
        tempSet.add(1);
        tempSet.add(2);
        tempSet.add(3);
        order = new Order();
        order.setOrderNum(1);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(30);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(4);
        tempSet.add(5);
        tempSet.add(6);
        order = new Order();
        order.setOrderNum(2);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(35);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(7);
        tempSet.add(8);
        tempSet.add(9);
        tempSet.add(10);
        order = new Order();
        order.setOrderNum(3);
        order.setOrderJob(tempSet);
        order.setOrderPriority(3);
        order.setOrderEndDate(23);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(11);
        tempSet.add(12);
        tempSet.add(13);
        order = new Order();
        order.setOrderNum(4);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(40);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(14);
        tempSet.add(15);
        order = new Order();
        order.setOrderNum(5);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(50);
        originOrderList.add(order);
//        int or = 1;
//        for (Order tempOrder : originOrderList) {
//            Set<Integer> jobSet = tempOrder.getOrderJob();
//            System.out.println("订单" + or);
//            for (int xx : jobSet) {
//                System.out.println("工序：" + xx);
//            }
//            or = or + 1;
//        }
        /*********初始化机器映射信息,,,,******/ /************初始化工序信息***************/
        // 工序-机器映射表
        int[] v1 = {1, 3, 5};
        int[] v2 = {2, 5, 6};
        int[] v3 = {1, 4, 6};
        int[] v4 = {3, 4, 6};
        int[] v5 = {1, 2, 5, 6};
        int[] v6 = {2, 4};
        int[] v7 = {1, 3, 6};
        int[] v8 = {1, 2, 4, 5};
        int[] v9 = {2, 3, 5};
        int[] v10 = {2, 3, 4, 6};
        int[] v11 = {1, 5, 6};
        int[] v12 = {3, 4, 5};
        int[] v13 = {3, 5, 6};
        int[] v14 = {1, 4, 5, 6};
        int[] v15 = {2, 3, 6};
        //机器-工序生产力映射
        int[][] machineJobMapper = new int[][]{
                {2, 0, 3, 0, 4, 0}, {0, 2, 0, 0, 3, 4}, {2, 0, 0, 3, 0, 4},
                {0, 0, 2, 3, 0, 4}, {2, 3, 0, 0, 4, 2}, {0, 3, 0, 4, 0, 0},
                {2, 0, 3, 0, 0, 4}, {2, 3, 0, 4, 2, 0}, {0, 3, 4, 0, 2, 0},
                {0, 3, 4, 2, 0, 3}, {4, 0, 0, 0, 2, 3}, {0, 0, 4, 2, 3, 0},
                {0, 0, 4, 0, 2, 3}, {4, 0, 0, 2, 3, 4}, {0, 2, 3, 0, 0, 4}
        };
        List<int[]> tempMachine = new ArrayList<>();
        tempMachine.add(v1);
        tempMachine.add(v2);
        tempMachine.add(v3);
        tempMachine.add(v4);
        tempMachine.add(v5);
        tempMachine.add(v6);
        tempMachine.add(v7);
        tempMachine.add(v8);
        tempMachine.add(v9);
        tempMachine.add(v10);
        tempMachine.add(v11);
        tempMachine.add(v12);
        tempMachine.add(v13);
        tempMachine.add(v14);
        tempMachine.add(v15);
        //初始化工序信息
        int[][] model = new int[][]{
                {1, 2, 3, 4, 5, 2, 4, 6, 7, 8, 3, 6, 9, 10, 9},   //模具
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5},   //物料
                {1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},    //准备时间
                {2, 2, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2},    //拆卸时间
                {12, 20, 24, 18, 20, 14, 16, 17, 22, 20, 15, 18, 14, 22, 24}   //生产数量
        };
        Job job;
        for (int i = 0; i < 15; i++) {
            job = new Job();
            job.setJobModel(model[0][i]);
            job.setJobMaterial(model[1][i]);
            job.setJobReadyTime(model[2][i]);
            job.setJobTakeDownTime(model[3][i]);
            job.setJobQuantity(model[4][i]);
            //插入机器工序映射
            job.setMachJobMapper(tempMachine.get(i));
            job.setMachJobCapMapper(machineJobMapper[i]);

            originJobList.add(job);
        }
    }

    public static void main(String[] args) {

        init();                        //初始化机器，订单，工序参数

        OrderSort orderSort = new OrderSort();
        List<Order> sortedOrderList = orderSort.orderSort(originOrderList);   //根据订单优先级排序订单

        Encoding encoding = new Encoding();
        Crossing crossing = new Crossing();
        Mutation mutation = new Mutation();
        Selecting selecting = new Selecting();
        List<int[][]> parentGroup; //原始种群
        List<int[][]> C1; //交叉后子代
        List<int[][]> C2; //变异后子代

        parentGroup = encoding.initGroup(ChromNum, originJobList, GroupSize);

        //迭代求最优解
        for (int i = 0; i < ITERA; i++) {
            //交叉
            C1 = crossing.cross(parentGroup, ChromNum, CP);
            //变异
            C2 = mutation.mutation(parentGroup, ChromNum, originJobList, MP);
            //选择
            parentGroup = selecting.selectNextGeneration(parentGroup, C1, C2, sortedOrderList, originJobList, MachineNum);
        }
        System.out.println("迭代后排程结果：");
        schedulingOut(parentGroup, sortedOrderList, originJobList, MachineNum);

    }

    //输出染色体
    private static void chromOut(List<int[][]> list) {
        for (int i = 0; i < list.size(); i++) {
            int[][] temp = list.get(i);
            System.out.println("染色体" + i + ":\n" + toString(temp));
        }
    }

    private static void decodingOut(List<List<Job>> jobListEnd) {
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        for (List<Job> tempJob : jobListEnd) {
            //System.out.println(tempJob.size());
            System.out.println("工作完成时间：" + minTime(tempJob));
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobNum() + "机器" + job.getMachineNum() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }
    }

    //输出初始种群排程结果
    private static void schedulingOut(List<int[][]> parentGroup, List<Order> orderSorted,
                                      List<Job> originJobList, int machineNum) {
        //解码染色体，得到工序排列组合列表
        List<List<Job>> jobList = new Decoding().decodingAll(parentGroup, orderSorted, originJobList);
        //简单排程
        List<List<Job>> jobListEnd = new Scheduling().schedulingGroup(jobList, machineNum);
        //计算适应度
        int[] fitness = new Fitness().fitness(parentGroup, orderSorted, originJobList, machineNum);
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        int i = 0;
        for (List<Job> tempJob : jobListEnd) {
            i++;
            System.out.println(i + "工作完成时间：" + minTime(tempJob) + " 惩罚值" + fitness[i-1]);
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobNum() + "机器" + job.getMachineNum() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }
    }

    private static int minTime(List<Job> jobList) {
        int Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

    private static String toString(int[][] string) {
        StringBuilder xxx = new StringBuilder("");
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                xxx.append(" " + string[i][j]);
            }
            xxx.append("\r\n");
        }
        return xxx.toString();
    }

}


//    chromOut(parentGroup);
//    List<List<Job>> decodedChromList = decoding.decodingAll(parentGroup,sortedOrderList,originJobList);
//    decodingOut(decodedChromList);
//        parentGroup = initChromList;
//                C1 = crossing.cross(parentGroup,ChromNum,CP);
////        chromOut(C1);
//                C2 = mutation.mutation(parentGroup,ChromNum,originJobList,MP);
//                chromOut(C2);
////        选择
//                List<int[][]> P = selecting.selectNextGeneration(parentGroup,C1,C2,sortedOrderList,originJobList,MachineNum);