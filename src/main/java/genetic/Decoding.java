package genetic;

import model.Job;
import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @auther chen.don
 * @date 2019/10/12 13:53
 */
public class Decoding {

    //解码染色体，对染色体组内的每条染色体解码   ---初始
    //输入：染色体组
    //输出：对应工序序列的集合
    public List<List<Job>> decoding(List<int[][]> chromGroup) {
        List<List<Job>> jobArrayGroup = new ArrayList<List<Job>>();
//        System.out.println("染色体组数"+chromGroup.size());
        for (int[][] tempChrom : chromGroup) {
            List<Job> tempJob = new ArrayList<Job>();
            for (int i = 0; i < tempChrom[0].length; i++) {
                for (int j = 0; j < tempChrom[0].length; j++) {
                    if (tempChrom[0][j] == i + 1) {              //根据随机分配的优先级确定生产顺序
                        Job job = new Job();
                        job.setJobNum(j + 1);
                        job.setMachineNum(tempChrom[1][j]);
                        tempJob.add(job);
                    }
                }
            }
            jobArrayGroup.add(tempJob);
        }
        return jobArrayGroup;
    }

    //按订单优先级，对每个订单内的工序解码得到各自内部的工序  ----新加
    //输入：总的编码染色体组；排序后的订单列表--优先级映射集合；
    //输出：根据订单优先级处理后的工序列表集合
    public List<List<Job>> decodingAll(List<int[][]> chromGroup, List<Order> orderSorted) {
        List<List<Job>> result = new ArrayList<>();
        for (int[][] chromosome : chromGroup) {
            List<Job> tempJob = decodingSingle(chromosome);    //先按优先级对染色体工序进行排序
            List<Job> resultJob = new ArrayList<>();
            for (Order tempOrder : orderSorted) {                //遍历排序后的订单表
                for (Job job : tempJob) {
                    if (tempOrder.getOrderJob().contains(job.getJobNum())) {
                        resultJob.add(job);
                    }
                }
            }
            result.add(resultJob);
        }
        return result;
    }

    //对某条染色体内的一条进行工序生成，返回一条排序后的
    private List<Job> decodingSingle(int[][] chromosome) {
        List<Job> tempJob = new ArrayList<>();
        int length = chromosome[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (chromosome[0][j] == i + 1) {       //根据随机分配的优先级确定生产顺序
                    Job job = new Job();
                    job.setJobNum(j + 1);
                    job.setMachineNum(chromosome[1][j]);
                    tempJob.add(job);
                }
            }
        }
        return tempJob;
    }
}
