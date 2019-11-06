package demo;

import model.Job;
import model.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class demo {
    public static final double CP = 0.6;  //交叉概率
    public static final double MP = 0.15;  //变异概率
    public static final long ITERA = 10;  //迭代次数


    public static void main(String[] args) {

        List<int[][]> chromList;

        int GeneNum = 6;
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
        Machine.add(v6);                       //机器-工序映射
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        Set<Integer> tempOrder = new HashSet<>();
        tempOrder.add(1);
        tempOrder.add(2);
        tempOrder.add(3);
        tempOrder.add(4);
        tempOrder.add(5);
        tempOrder.add(6);
        order.setOrderNum(1);
        order.setOrderJob(tempOrder);
        orderList.add(order);                //订单信息
        int machineNum = 5;                  //机器数量

//        //编码染色体，得到染色体组
//        Encoding encoding = new Encoding();
//        chromList = encoding.initGroup(GeneNum, Machine, 30);
//        chromOut(chromList);
//        //交叉
//        Crossing crossing = new Crossing();
//        List<int[][]> C1 = crossing.cross(chromList,GeneNum,CP);
//        //变异
//        Mutation mutation = new Mutation();
//        List<int[][]> C2 = mutation.mutation(chromList,GeneNum,Machine,MP);
//        //选择
//        Selecting selecting = new Selecting();
//        List<int[][]> P = selecting.selectNextGeneration(chromList,C1,C2,orderList,machineNum);
//        //解码染色体，得到工序排列组合列表
//        Decoding decoding = new Decoding();
//        List<List<Job>> jobList = decoding.decodingAll(P, orderList); //含有订单优先级的解码
//        System.out.println("含有订单优先级的解码:");
//        schedulingOut(jobList);
//        //简单排程
//        Scheduling schedule = new Scheduling();
//        List<List<Job>> jobListEnd = schedule.schedulingGroup(jobList, machineNum);
//        System.out.println("简单排程:");
//        schedulingOut(jobListEnd);
//        //适应度计算
//        Fitness fitness = new Fitness();
//        System.out.println("适应度");
//        int[] fit = fitness.fitness(P,orderList,machineNum);
//        for (int i = 0;i <fit.length;i++) {
//            System.out.println(i+"组的适应度"+fit[i]);
//        }
    }

    private static void schedulingOut(List<List<Job>> jobListEnd) {
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

    private static void chromOut(List<int[][]> list) {
        for (int i = 0; i < list.size(); i++) {
            int[][] temp = list.get(i);
            System.out.println("染色体" + i + ":\n" + toString(temp));
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

    public static String toString(int[][] string) {
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

//    int[][] durationTime = new int[][]{
//                {4, 5, 0, 0, 5},
//                {6, 0, 3, 0, 0},
//                {0, 2, 4, 3, 0},
//                {0, 0, 0, 4, 4},
//                {3, 0, 3, 5, 0},
//                {0, 4, 0, 4, 0}
//        };

//        //适应度
//        Fitness fitness = new Fitness();
//        int[] fit = fitness.fitness(chromList, durationTime);
////        for (int i = 0; i < fit.length; i++) {
////            System.out.println(i + "适应度：" + fit[i]);
////        }
//        //交叉
//        Cross cross = new Cross();
//        List<int[][]> crossChromList;
//        crossChromList = cross.cross(chromList, GeneNum, CP);
//        System.out.println("交叉后:");
//        chromOut(crossChromList);
//        //变异
//        Mutation mutation = new Mutation();
//        List<int[][]> mutChromList;
//        mutChromList = mutation.mutation(chromList, GeneNum, Machine, MP);
//        System.out.println("变异后:");
//        chromOut(mutChromList);
////        选择
//
//
//        int[][] seleSingle = selection.selectionSingle(chromList, durationTime);
//        for (int i = 0; i < seleSingle[0].length; i++) {
//
//            System.out.println("适应度排序：" + seleSingle[0][i] + " " + seleSingle[1][i]);
//        }
//
//        List<int[][]> selGroup = selection.selectNextGeneration(chromList, crossChromList, mutChromList, durationTime);
//        System.out.println("选择后染色体数：" + selGroup.size());
//        int[] fit1 = fitness.fitness(selGroup, durationTime);
//        for (int i = 0; i < fit1.length; i++) {
//            System.out.println(i + "选择后适应度：" + fit1[i]);
//        }


////输出排程结果
//        System.out.println("wode1染色体组数：" + jobListAll.size());
//                for (List<Job> tempJob : jobListAll) {
//        //System.out.println(tempJob.size());
//        for (Job job : tempJob) {
//        System.out.print("<工序" + job.getJobNum() + " 生产机器" + job.getMachineNum() + ">");
//        }
//        System.out.println();
//        }
//        //输出工序排列
//        System.out.println("染色体组数：" + jobListEnd.size());
//        for (List<Job> tempJob : jobListEnd) {
//        //System.out.println(tempJob.size());
//        System.out.println("工作完成时间：" + minTime(tempJob));
//        for (Job job : tempJob) {
//        System.out.print("<工序" + job.getJobNum() + "机器" + job.getMachineNum() +
//        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
//        }
//        System.out.println();
//        }










