package genetic;

import domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther chen.don
 * @date 2019/10/28 10:06
 */
public class Encoding {
    //初始化一条染色体
    //输入每条染色体的基因总数，对应机器的集合
    public int[][] initSingle(int GeneNum, List<Job> machineList) {
        int[][] chromosome = new int[2][GeneNum];
        Random rand = new Random();
        boolean[] bool = new boolean[GeneNum + 1];
        for (int i = 0; i < GeneNum; i++) {
            //对优先级赋值
            do {
                chromosome[0][i] = rand.nextInt(GeneNum) + 1;//产生1-GeneNum以内的非重复随机数
            } while (bool[chromosome[0][i]]);
            bool[chromosome[0][i]] = true;
            //选择机器
            int index = rand.nextInt(machineList.get(i).getMachJobMapper().length);
            int[] temp = machineList.get(i).getMachJobMapper();
            chromosome[1][i] = temp[index];
        }
        return chromosome;
    }

    //初始化一组染色体,得到二维编码
    public List<int[][]> initGroup(int GeneNum, List<Job> machineList, int groupSize) {
        List<int[][]> chromGroup = new ArrayList<>();
        for (int i = 0; i < groupSize; i++) {
            chromGroup.add(initSingle(GeneNum, machineList));
        }
        System.out.println("初始化染色体完成");
        return chromGroup;
    }
}
