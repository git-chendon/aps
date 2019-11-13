package demo;

import genetic.*;
import domain.Job;
import domain.Machine;
import domain.Order;
import solver.OrderSort;

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
    private static final long ITERA = 100;  //迭代次数
    private static final int ChromNum = 32;  //染色体数
    private static final int GroupSize = 40; //种群数
    private static final int MachineNum = 8; //机器数
    private static final int fitFlag = 0;   //0：综合考虑;1:完成时间&惩罚；2：机器平均时间&惩罚；3：完全由完成时间

    private static List<Order> originOrderList = new ArrayList<>();      //订单信息
    private static List<Job> originJobList = new ArrayList<>();          //工序信息,和工序机器映射信息
    private static List<Machine> machineMapperList = new ArrayList<>();  //机器信息

    public static void init() {

        /******初始化订单****/
        Order order;
        Set<Integer> tempSet;
        Job job;


        /*********初始化机器映射信息******/
        /************初始化工序信息***************/

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
        // 任务-机器映射表

//        int[] v1 = {1, 2, 3, 4};
//        int[] v2 = {5, 6, 7, 8};
//        int[] v3 = {3, 4, 5, 6};
//        int[] v4 = {1, 2, 3, 4};
//        int[] v5 = {5, 6, 7, 8};
//        int[] v6 = {1, 2, 3, 4};
//        int[] v7 = {5, 6, 7, 8};
//        int[] v8 = {3, 4, 5, 6};
//        int[] v9 = {1, 2, 3, 4};
//        int[] v10 = {5, 6, 7, 8};
//        int[] v11 = {1, 2, 3, 4};
//        int[] v12 = {5, 6, 7, 8};
//        int[] v13 = {3, 4, 5, 6};
//        int[] v14 = {1, 2, 3, 4};
//        int[] v15 = {5, 6, 7, 8};
//        int[] v16 = {3, 4, 5, 6};
        //机器-工序生产力映射
        int[][] machineModelMapper = new int[][]{
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {200, 200, 200, 200, 0, 0, 0, 0},
                {0, 0, 0, 0, 250, 250, 250, 250}, {0, 0, 300, 300, 300, 300, 0, 0},
                {350, 350, 350, 350, 0, 0, 0, 0}, {0, 0, 0, 0, 400, 400, 400, 400},
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {0, 0, 200, 200, 200, 200, 0, 0}
//                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
//                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
//                {0, 0, 0, 0, 400, 400, 400, 400}, {200, 200, 200, 200, 0, 0, 0, 0},
//                {0, 0, 0, 0, 250, 250, 250, 250}, {0, 0, 300, 300, 300, 300, 0, 0},
//                {350, 350, 350, 350, 0, 0, 0, 0}, {0, 0, 0, 0, 400, 400, 400, 400},
//                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
//                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
//                {0, 0, 0, 0, 400, 400, 400, 400}, {0, 0, 200, 200, 200, 200, 0, 0}
        };
//        List<int[]> tempMachine = new ArrayList<>();
//
//        tempMachine.add(v1);
//        tempMachine.add(v2);
//        tempMachine.add(v3);
//        tempMachine.add(v4);
//        tempMachine.add(v5);
//        tempMachine.add(v6);
//        tempMachine.add(v7);
//        tempMachine.add(v8);
//        tempMachine.add(v9);
//        tempMachine.add(v10);
//        tempMachine.add(v11);
//        tempMachine.add(v12);
//        tempMachine.add(v13);
//        tempMachine.add(v14);
//        tempMachine.add(v15);
//        tempMachine.add(v16);
//        tempMachine.add(v17);
//        tempMachine.add(v18);
//        tempMachine.add(v19);
//        tempMachine.add(v20);
//        tempMachine.add(v21);
//        tempMachine.add(v22);
//        tempMachine.add(v23);
//        tempMachine.add(v24);
//        tempMachine.add(v25);
//        tempMachine.add(v26);
//        tempMachine.add(v27);
//        tempMachine.add(v28);
//        tempMachine.add(v29);
//        tempMachine.add(v30);
//        tempMachine.add(v31);
//        tempMachine.add(v32);
        //初始化工序信息
        int[][] model = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},   //模具
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},   //物料
                {1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 1200, 1400,
                        1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200}   //生产数量
        };
        double[][] model1 = new double[][]{
                {.2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2},    //准备时间
                {0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2,
                        0.2, 0.2, 0.2, 0.2, 0.2, 0.2},    //拆卸时间
        };
        int[][] orderInfo = {
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},  //优先级
                {40, 42, 44, 46, 48, 50, 52, 54, 52, 50, 48, 46, 44, 42, 40, 38, 40, 42, 44, 46, 48, 50, 52, 54, 52, 50, 48, 46, 44, 42, 40, 38}   //截止日期
        };

        for (int i = 0; i < 32; i++) {
            /**
             * 初始化订单
             */
            tempSet = new HashSet<>();
            tempSet.add(i + 1);
            order = new Order();
            order.setOrderId(i + 1);
            order.setOrderJob(tempSet);
            order.setOrderPriority(orderInfo[0][i]);    //优先级
            order.setOrderEndDate(orderInfo[1][i]);     //截止日期
            originOrderList.add(order);

            /**
             * 初始化任务
             */
            job = new Job();
            job.setJobModel(model[0][i]);                 //模具
            job.setJobMaterial(model[1][i]);              //物料
            job.setJobReadyTime(model1[0][i]);            //准备时间
            job.setJobTakeDownTime(model1[1][i]);         //拆卸时间
            job.setJobQuantity(model[2][i]);              //数量
            job.setJobColor(colors.get(i));               //颜色

            /**
             * 处理映射
             */
            List<Integer> list = new ArrayList<>();
            int modelNum = job.getJobModel();
            for (int j = 0; j < MachineNum; j++) {
                if (machineModelMapper[modelNum - 1][j] != 0) {
                    list.add(j + 1);
                }
            }
            int[] machJob = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                machJob[k] = list.get(k);                         //初始化机器任务映射
            }
            job.setMachJobMapper(machJob);                         //机器--任务映射
            job.setMachJobCapMapper(machineModelMapper[modelNum - 1]); //机器--任务产能映射
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
        parentGroup = new Selecting().sortChromGroup(parentGroup, sortedOrderList, originJobList, MachineNum, fitFlag);

        //迭代求最优解
        for (int i = 0; i < ITERA; i++) {
            //交叉
            C1 = crossing.cross(parentGroup, ChromNum, CP);
            //变异
            C2 = mutation.mutation(parentGroup, ChromNum, originJobList, MP);
            //选择
            parentGroup = selecting.selectNextGeneration(parentGroup, C1, C2, sortedOrderList, originJobList, MachineNum ,fitFlag);
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
                System.out.print("<工序" + job.getJobId() + "机器" + job.getMachineNum() +
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
        int[] fitness = new Fitness().fitness(parentGroup, orderSorted, originJobList, machineNum ,fitFlag);
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        System.out.println("最小工作完成时间：" + minTime(jobListEnd.get(0)) +" 平均完成时间："
                + machineWorkTime(jobListEnd.get(0),machineNum) +  " 惩罚值：" + punishSum(jobListEnd.get(0)) + " 总惩罚值：" + fitness[0]);
        //找到超时订单数量
        System.out.println("超时订单数量：" + outTimeNum(orderSorted,jobListEnd.get(0)));
        for (int m = 0; m < MachineNum; m++) {
            List<Job> jobs = new ArrayList<>();
            for (Job job : jobListEnd.get(0)) {
                if (job.getMachineNum() == m + 1) {
                    jobs.add(job);
                }
            }
            System.out.println("机器" + (m + 1));
            for (Job job : jobs) {
                System.out.print("<工序" + job.getJobId() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }

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

    //获取每组染色体的惩罚值
    private static double punishSum(List<Job> jobList) {
        double sum = 0;
        for (Job job : jobList) {
            sum += job.getPunishment();
        }
        return sum;
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

    //获取机器每台机器的工作时间
    private static double machineWorkTime(List<Job> jobList, int machineNum) {
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

    private static int outTimeNum(List<Order> orderSorted,List<Job> jobList){
        int orderOvertimePunish = 0;
        for (Order order : orderSorted) {       //遍历订单找到超时订单并计算超时惩罚
            double endTime = 0;
            for (int i : order.getOrderJob()) {
                if (endTime < jobList.get(i - 1).getEndTime()) {
                    endTime = jobList.get(i - 1).getEndTime();
                }
            }
            if (endTime > order.getOrderEndDate()) {          //如果超时
                orderOvertimePunish += 1;                     //按超时数量计算
//                    orderOvertimePunish += (endTime - order.getOrderEndDate()); //按超时时间算
            }
        }
        return orderOvertimePunish;
    }


}

