package main;

import genetic.*;
import model.Duration;
import model.Job;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
//        List<int[]> Machine = new ArrayList<>(); //工序对应机器列表
//        Duration durationTime = new Duration();
//        int [][] duration = durationTime.getDurationTime();
        List<int[]> Machine = new ArrayList<int[]>();
        int[] v1 = {1, 2, 5};
        int[] v2 = {1, 3};
        int[] v3 = {2, 3, 4};
        int[] v4 = {4, 5};
        int[] v5 = {1, 3, 4};
        int[] v6 = {2, 4};
        Machine.add(v1);
        Machine.add(v2);
        Machine.add(v3);
        Machine.add(v4);
        Machine.add(v5);
        Machine.add(v6);
        int[][] durationTime = new int[][]{
                {4, 5, 0, 0, 5},
                {6, 0, 3, 0, 0},
                {0, 2, 4, 3, 0},
                {0, 0, 0, 4, 4},
                {3, 0, 3, 5, 0},
                {0, 4, 0, 4, 0}
        };
        Encoding encoding = new Encoding();
        Cross cross = new Cross();
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
        schedulingOut(parentGroup,durationTime);

        //排序原始染色体组,保证选择正常进行
        parentGroup = selection.selectSingleSortGroup(parentGroup,durationTime);


        //迭代求最优解
        for (int i = 0; i < ITERA; i++) {
            //交叉
            C1 = cross.cross(parentGroup, GeneNum, CP);
            //变异
            C2 = mutation.mutation(parentGroup, GeneNum, Machine, MP);
            //选择
            parentGroup = selection.selectNextGeneration(parentGroup, C1, C2, durationTime);
        }
        System.out.println("迭代后排程结果：");
        schedulingOut(parentGroup,durationTime);


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
        jobList = decoding.decoding(parentGroup);
        //简单排程
        List<List<Job>> jobListEnd = new ArrayList<List<Job>>();
        Scheduling scheduling = new Scheduling();
        jobListEnd = scheduling.schedulingGroup(jobList, durationTime);
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
        int i =0;
        for (List<Job> tempJob : jobListEnd) {
            //System.out.println(tempJob.size());
            i++;
            System.out.println(i+"工作完成时间：" + minTime(tempJob));
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
