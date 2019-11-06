package genetic;

import model.Job;
import model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/12 13:53
 */
public class Decoding {

    //按订单优先级，对每个订单内的工序解码得到各自内部的工序
    public List<List<Job>> decodingAll(List<int[][]> chromGroup, List<Order> orderSorted ,
                                       List<Job> originJobList) {

        List<List<Job>> result = new ArrayList<>();
        for (int[][] chromosome : chromGroup) {

            List<Job> tempJob = sortAndInitJobMsg(chromosome, originJobList);    //先按优先级对染色体工序进行排序
//            订单优先级加的位置不对。
//            List<Job> resultJob = new ArrayList<>();
//
//            for (Order tempOrder : orderSorted) {                //遍历排序后的订单表
//                for (Job job : tempJob) {
//                    if (tempOrder.getOrderJob().contains(job.getJobNum())) {
//                        resultJob.add(job);
//                    }
//                }
//            }
//            result.add(resultJob);
            result.add(tempJob);
        }
        return result;
    }

    //根据优先级排序染色体，并插入工序相关信息
    private List<Job> sortAndInitJobMsg(int[][] chromosome, List<Job> originJobList) {
        List<Job> tempJob = new ArrayList<>();
        int length = chromosome[0].length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (chromosome[0][j] == i + 1) {       //根据随机分配的优先级确定生产顺序
                    Job job = new Job();
                    job.setJobNum(j + 1);              //j+1是工序号
                    job.setMachineNum(chromosome[1][j]);   //chromosome[1][j]是对应的机器号

                    //赋值job信息
                    job.setJobModel(originJobList.get(j).getJobModel());
                    job.setJobMaterial(originJobList.get(j).getJobMaterial());
                    job.setJobColor(originJobList.get(j).getJobColor());
                    job.setJobProductTime(originJobList.get(j).getJobProductTime());
                    job.setJobReadyTime(originJobList.get(j).getJobReadyTime());
                    job.setJobTakeDownTime(originJobList.get(j).getJobTakeDownTime());

                    //处理工序在机器上的加工时间
                    int productNum = originJobList.get(j).getJobQuantity();    //获取工序要加工的数量
                    int machProductivity = originJobList.get(j).getMachJobCapMapper()[chromosome[1][j] - 1]; //获取对应的生产力
//                    System.out.println("机器号：" + (chromosome[1][j]) + "工序号：" + (j + 1));
//                    System.out.println("生产力：" + machProductivity);
                    double productTime = (double)productNum/machProductivity;
                    BigDecimal bg = new BigDecimal(productTime);
                    productTime = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    job.setJobProductTime(productTime);
                    
                    tempJob.add(job);
                }
            }
        }
        return tempJob;
    }
}
