package main;

import genetic.*;
import model.Job;
import model.Machine;
import model.Order;
import process.OrderSort;

import java.awt.*;
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
    private static final int ChromNum = 32;  //染色体数
    private static final int GroupSize = 40; //种群数
    private static final int MachineNum = 8; //种群数

    private static List<Order> originOrderList = new ArrayList<>();      //订单信息
    private static List<Job> originJobList = new ArrayList<>();          //工序信息,和工序机器映射信息
    private static List<Machine> machineMapperList = new ArrayList<>();  //机器信息

    public static void init() {

        /******初始化订单****/
        Order order;
        Set<Integer> tempSet;

        tempSet = new HashSet<>();
        tempSet.add(1);
        order = new Order();
        order.setOrderNum(1);
        order.setOrderJob(tempSet);
        order.setOrderPriority(8);
        order.setOrderEndDate(40);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(2);
        order = new Order();
        order.setOrderNum(2);
        order.setOrderJob(tempSet);
        order.setOrderPriority(7);
        order.setOrderEndDate(42);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(3);
        order = new Order();
        order.setOrderNum(3);
        order.setOrderJob(tempSet);
        order.setOrderPriority(6);
        order.setOrderEndDate(44);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(4);
        order = new Order();
        order.setOrderNum(4);
        order.setOrderJob(tempSet);
        order.setOrderPriority(5);
        order.setOrderEndDate(46);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(5);
        order = new Order();
        order.setOrderNum(5);
        order.setOrderJob(tempSet);
        order.setOrderPriority(4);
        order.setOrderEndDate(48);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(6);
        order = new Order();
        order.setOrderNum(6);
        order.setOrderJob(tempSet);
        order.setOrderPriority(3);
        order.setOrderEndDate(50);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(7);
        order = new Order();
        order.setOrderNum(7);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(52);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(8);
        order = new Order();
        order.setOrderNum(8);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(54);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(9);
        order = new Order();
        order.setOrderNum(9);
        order.setOrderJob(tempSet);
        order.setOrderPriority(8);
        order.setOrderEndDate(52);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(10);
        order = new Order();
        order.setOrderNum(10);
        order.setOrderJob(tempSet);
        order.setOrderPriority(7);
        order.setOrderEndDate(50);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(11);
        order = new Order();
        order.setOrderNum(11);
        order.setOrderJob(tempSet);
        order.setOrderPriority(6);
        order.setOrderEndDate(48);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(12);
        order = new Order();
        order.setOrderNum(12);
        order.setOrderJob(tempSet);
        order.setOrderPriority(5);
        order.setOrderEndDate(46);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(13);
        order = new Order();
        order.setOrderNum(13);
        order.setOrderJob(tempSet);
        order.setOrderPriority(4);
        order.setOrderEndDate(44);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(14);
        order = new Order();
        order.setOrderNum(14);
        order.setOrderJob(tempSet);
        order.setOrderPriority(3);
        order.setOrderEndDate(42);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(15);
        order = new Order();
        order.setOrderNum(15);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(40);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(16);
        order = new Order();
        order.setOrderNum(16);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(38);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(17);
        order = new Order();
        order.setOrderNum(17);
        order.setOrderJob(tempSet);
        order.setOrderPriority(8);
        order.setOrderEndDate(40);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(18);
        order = new Order();
        order.setOrderNum(18);
        order.setOrderJob(tempSet);
        order.setOrderPriority(7);
        order.setOrderEndDate(42);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(19);
        order = new Order();
        order.setOrderNum(19);
        order.setOrderJob(tempSet);
        order.setOrderPriority(6);
        order.setOrderEndDate(44);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(20);
        order = new Order();
        order.setOrderNum(20);
        order.setOrderJob(tempSet);
        order.setOrderPriority(5);
        order.setOrderEndDate(46);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(21);
        order = new Order();
        order.setOrderNum(21);
        order.setOrderJob(tempSet);
        order.setOrderPriority(4);
        order.setOrderEndDate(48);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(22);
        order = new Order();
        order.setOrderNum(22);
        order.setOrderJob(tempSet);
        order.setOrderPriority(3);
        order.setOrderEndDate(50);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(23);
        order = new Order();
        order.setOrderNum(23);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(52);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(24);
        order = new Order();
        order.setOrderNum(24);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(54);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(25);
        order = new Order();
        order.setOrderNum(25);
        order.setOrderJob(tempSet);
        order.setOrderPriority(8);
        order.setOrderEndDate(52);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(26);
        order = new Order();
        order.setOrderNum(26);
        order.setOrderJob(tempSet);
        order.setOrderPriority(7);
        order.setOrderEndDate(50);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(27);
        order = new Order();
        order.setOrderNum(27);
        order.setOrderJob(tempSet);
        order.setOrderPriority(6);
        order.setOrderEndDate(48);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(28);
        order = new Order();
        order.setOrderNum(28);
        order.setOrderJob(tempSet);
        order.setOrderPriority(5);
        order.setOrderEndDate(46);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(29);
        order = new Order();
        order.setOrderNum(29);
        order.setOrderJob(tempSet);
        order.setOrderPriority(4);
        order.setOrderEndDate(44);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(30);
        order = new Order();
        order.setOrderNum(30);
        order.setOrderJob(tempSet);
        order.setOrderPriority(3);
        order.setOrderEndDate(42);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(31);
        order = new Order();
        order.setOrderNum(31);
        order.setOrderJob(tempSet);
        order.setOrderPriority(2);
        order.setOrderEndDate(40);
        originOrderList.add(order);

        tempSet = new HashSet<>();
        tempSet.add(32);
        order = new Order();
        order.setOrderNum(32);
        order.setOrderJob(tempSet);
        order.setOrderPriority(1);
        order.setOrderEndDate(38);
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
        int[] v1 = {1, 2, 3, 4};
        int[] v2 = {5, 6, 7, 8};
        int[] v3 = {3, 4, 5, 6};
        int[] v4 = {1, 2, 3, 4};
        int[] v5 = {5, 6, 7, 8};
        int[] v6 = {1, 2, 3, 4};
        int[] v7 = {5, 6, 7, 8};
        int[] v8 = {3, 4, 5, 6};
        int[] v9 = {1, 2, 3, 4};
        int[] v10 = {5, 6, 7, 8};
        int[] v11 = {1, 2, 3, 4};
        int[] v12 = {5, 6, 7, 8};
        int[] v13 = {3, 4, 5, 6};
        int[] v14 = {1, 2, 3, 4};
        int[] v15 = {5, 6, 7, 8};
        int[] v16 = {3, 4, 5, 6};
        int[] v17 = {1, 2, 3, 4};
        int[] v18 = {5, 6, 7, 8};
        int[] v19 = {3, 4, 5, 6};
        int[] v20 = {1, 2, 3, 4};
        int[] v21 = {5, 6, 7, 8};
        int[] v22 = {1, 2, 3, 4};
        int[] v23 = {5, 6, 7, 8};
        int[] v24 = {3, 4, 5, 6};
        int[] v25 = {1, 2, 3, 4};
        int[] v26 = {5, 6, 7, 8};
        int[] v27 = {1, 2, 3, 4};
        int[] v28 = {5, 6, 7, 8};
        int[] v29 = {3, 4, 5, 6};
        int[] v30 = {1, 2, 3, 4};
        int[] v31 = {5, 6, 7, 8};
        int[] v32 = {3, 4, 5, 6};
        //机器-工序生产力映射
        int[][] machineJobMapper = new int[][]{
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {200, 200, 200, 200, 0, 0, 0, 0},
                {0, 0, 0, 0, 250, 250, 250, 250}, {0, 0, 300, 300, 300, 300, 0, 0},
                {350, 350, 350, 350, 0, 0, 0, 0}, {0, 0, 0, 0, 400, 400, 400, 400},
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {0, 0, 200, 200, 200, 200, 0, 0},
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {200, 200, 200, 200, 0, 0, 0, 0},
                {0, 0, 0, 0, 250, 250, 250, 250}, {0, 0, 300, 300, 300, 300, 0, 0},
                {350, 350, 350, 350, 0, 0, 0, 0}, {0, 0, 0, 0, 400, 400, 400, 400},
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {0, 0, 200, 200, 200, 200, 0, 0}

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
        tempMachine.add(v16);
        tempMachine.add(v17);
        tempMachine.add(v18);
        tempMachine.add(v19);
        tempMachine.add(v20);
        tempMachine.add(v21);
        tempMachine.add(v22);
        tempMachine.add(v23);
        tempMachine.add(v24);
        tempMachine.add(v25);
        tempMachine.add(v26);
        tempMachine.add(v27);
        tempMachine.add(v28);
        tempMachine.add(v29);
        tempMachine.add(v30);
        tempMachine.add(v31);
        tempMachine.add(v32);
        //初始化工序信息
        int[][] model = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},   //模具
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},   //物料
                {1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200}   //生产数量
        };
        double[][] model1 = new double[][]{
                {.2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2},    //准备时间
                {0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2},    //拆卸时间
        };

        List<Color> colors = new ArrayList<>();   //物料颜色
        colors.add(new Color(0.5f, 0.6f, 0.8f));
        colors.add(new Color(0.1f, 0.1f, 0.8f));
        colors.add(new Color(0.5f, 0.9f, 0.3f));
        colors.add(new Color(0.0f, 0.0f, 0.0f));
        colors.add(new Color(0.9f, 0.3f, 0.2f));
        colors.add(new Color(0.5f, 0.9f, 0.7f));
        colors.add(new Color(0.12f, 0.66f, 0.7f));
        colors.add(new Color(0.58f, 0.41f, 0.66f));
        colors.add(new Color(0.66f, 0.1f, 0.3f));
        colors.add(new Color(1.0f, 1.0f, 1.0f));
        colors.add(new Color(0.6f, 0.6f, 0.6f));
        colors.add(new Color(0.2f, 0.3f, 0.5f));
        colors.add(new Color(0.1f, 0.9f, 0.3f));
        colors.add(new Color(0.5f, 0.6f, 0.0f));
        colors.add(new Color(0.0f, 0.9f, 0.0f));
        colors.add(new Color(0.56f, 0.8f, 0.6f));
        colors.add(new Color(0.5f, 0.6f, 0.8f));
        colors.add(new Color(0.1f, 0.1f, 0.8f));
        colors.add(new Color(0.5f, 0.9f, 0.3f));
        colors.add(new Color(0.0f, 0.0f, 0.0f));
        colors.add(new Color(0.9f, 0.3f, 0.2f));
        colors.add(new Color(0.5f, 0.9f, 0.7f));
        colors.add(new Color(0.12f, 0.66f, 0.7f));
        colors.add(new Color(0.58f, 0.41f, 0.66f));
        colors.add(new Color(0.66f, 0.1f, 0.3f));
        colors.add(new Color(1.0f, 1.0f, 1.0f));
        colors.add(new Color(0.6f, 0.6f, 0.6f));
        colors.add(new Color(0.2f, 0.3f, 0.5f));
        colors.add(new Color(0.1f, 0.9f, 0.3f));
        colors.add(new Color(0.5f, 0.6f, 0.0f));
        colors.add(new Color(0.0f, 0.9f, 0.0f));
        colors.add(new Color(0.56f, 0.8f, 0.6f));
        Job job;
        for (int i = 0; i < 32; i++) {
            job = new Job();
            job.setJobModel(model[0][i]);   //模具
            job.setJobMaterial(model[1][i]);
            job.setJobReadyTime(model1[0][i]);
            job.setJobTakeDownTime(model1[1][i]);
            job.setJobQuantity(model[2][i]);
            job.setJobColor(colors.get(i));
            //插入机器工序映射
            job.setMachJobMapper(tempMachine.get(i));
            job.setMachJobCapMapper(machineJobMapper[i]);
            originJobList.add(job);
        }

        System.out.println("初始化数据成功");
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
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
        //对初始种群排序
        parentGroup = new Selecting().sortChromGroup(parentGroup, sortedOrderList, originJobList, MachineNum);

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
        long endTime = System.currentTimeMillis();
        System.out.println("迭代次数：" + ITERA + " 程序运行时间：" + (endTime - startTime) + "ms");

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
        System.out.println("最小工作完成时间：" + minTime(jobListEnd.get(0)) + " 惩罚值" + fitness[0]);
        for (int m = 0; m < MachineNum; m++) {
            List<Job> jobs = new ArrayList<>();
            for (Job job : jobListEnd.get(0)) {
                if (job.getMachineNum() == m + 1) {
                    jobs.add(job);
                }
            }
            System.out.println("机器" + (m + 1));
            for (Job job : jobs) {
                System.out.print("<工序" + job.getJobNum() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }


//        for (List<Job> tempJob : jobListEnd) {
//            i++;
//            System.out.println(i + "工作完成时间：" + minTime(tempJob) + " 惩罚值" + fitness[i - 1]);
//            for (Job job : tempJob) {
//                System.out.print("<工序" + job.getJobNum() + "机器" + job.getMachineNum() +
//                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
//            }
//            System.out.println();
//        }
    }

    private static double minTime(List<Job> jobList) {
        double Tmin = 0;
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