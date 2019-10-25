package main;

import genetic.*;
import geneticOld.Schedule;
import geneticOld.Selection;
import model.Job;
import model.Order;

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
    private static final int GeneNum = 6;  //染色体数
    private static final int GroupSize = 30; //种群数
    private static List<int[]> Machine = new ArrayList<>();
    private static List<Order> originOrderList = new ArrayList<>();
    private static List<Job> originJobList = new ArrayList<>();

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
        order.setOrderPriority(1);
        order.setOrderEndDate(50);
        originOrderList.add(order);

        /*********初始化工序--对应机的器映射，模具，物料，物料颜色******/
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
        Machine.add(v1);
        Machine.add(v2);
        Machine.add(v3);
        Machine.add(v4);
        Machine.add(v5);
        Machine.add(v6);
        Machine.add(v7);
        Machine.add(v8);
        Machine.add(v9);
        Machine.add(v10);
        Machine.add(v11);
        Machine.add(v12);
        Machine.add(v13);
        Machine.add(v14);
        Machine.add(v15);

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
            originJobList.add(job);
        }


    }

    public static void main(String[] args) {


        int[][] durationTime = new int[][]{
                {4, 5, 0, 0, 5},
                {6, 0, 3, 0, 0},
                {0, 2, 4, 3, 0},
                {0, 0, 0, 4, 4},
                {3, 0, 3, 5, 0},
                {0, 4, 0, 4, 0}
        };
        Encoding encoding = new Encoding();
        Crossing crossing = new Crossing();
        Mutation mutation = new Mutation();
        Selection selection = new Selection();
        List<int[][]> parentGroup = new ArrayList<>(); //原始种群
        List<int[][]> C1 = new ArrayList<>(); //交叉后子代
        List<int[][]> C2 = new ArrayList<>(); //变异后子代

        //编码得到原始种群
        parentGroup = encoding.initGroup(GeneNum, Machine, GroupSize);
        System.out.println("原始染色体:");
        chromOut(parentGroup);
        System.out.println("初始排程结果：");
        schedulingOut(parentGroup, durationTime);

        //排序原始染色体组,保证选择正常进行
        parentGroup = selection.selectSingleSortGroup(parentGroup, durationTime);


        //迭代求最优解
        for (int i = 0; i < ITERA; i++) {
            //交叉
            C1 = crossing.cross(parentGroup, GeneNum, CP);
            //变异
            C2 = mutation.mutation(parentGroup, GeneNum, Machine, MP);
            //选择
            parentGroup = selection.selectNextGeneration(parentGroup, C1, C2, durationTime);
        }
        System.out.println("迭代后排程结果：");
        schedulingOut(parentGroup, durationTime);


    }

    //输出染色体
    private static void chromOut(List<int[][]> list) {
        for (int i = 0; i < list.size(); i++) {
            int[][] temp = list.get(i);
            System.out.println("染色体" + i + ":\n" + toString(temp));
        }
    }

    //输出初始种群排程结果
    private static void schedulingOut(List<int[][]> parentGroup, int[][] durationTime) {
        //解码染色体，得到工序排列组合列表
        List<List<Job>> jobList = new ArrayList<List<Job>>();
        Decoding decoding = new Decoding();
//        jobList = decoding.decoding(parentGroup);
        //简单排程
        List<List<Job>> jobListEnd = new ArrayList<List<Job>>();
        Schedule schedule = new Schedule();
        jobListEnd = schedule.scheduleGroup(jobList, durationTime);
        //输出排程结果
        System.out.println("染色体组数：" + jobList.size());
        for (List<Job> tempJob : jobList) {
            //System.out.println(tempJob.size());
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobNum() + " 生产机器" + job.getMachineNum() + ">");
            }
            System.out.println();
        }
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        int i = 0;
        for (List<Job> tempJob : jobListEnd) {
            //System.out.println(tempJob.size());
            i++;
            System.out.println(i + "工作完成时间：" + minTime(tempJob));
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
                xxx.append(string[i][j]);
            }
            xxx.append("\r\n");
        }
        return xxx.toString();
    }

}
