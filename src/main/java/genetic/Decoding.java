package genetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/12 13:53
 */
public class Decoding {

    //解码染色体，对染色体组内的每条染色体解码
    //输入染色体组，工序总数
    //输出对应工序序列的集合
    public List<List<Job>> decoding(List<int[][]> chromGroup) {

        List<List<Job>> jobArrayGroup = new ArrayList<List<Job>>();
        System.out.println("染色体组数"+chromGroup.size());
        for (int[][] tempChrom : chromGroup) {
            //System.out.println("decoding tempChrom[0].length:"+tempChrom[0].length);
            List<Job> tempJob = new ArrayList<Job>();
            for (int i = 0; i < tempChrom[0].length; i++) {
                for (int j = 0; j < tempChrom[0].length; j++) {
                    if (tempChrom[0][j] == i + 1) {//根据随机分配的优先级确定生产顺序
                        Job job = new Job();
                        job.setJobNum(j+1);
                        job.setMachineNum(tempChrom[1][j]);
                        tempJob.add(job);
                    }
                }
            }
            jobArrayGroup.add(tempJob);
        }
        return jobArrayGroup;
    }

}
