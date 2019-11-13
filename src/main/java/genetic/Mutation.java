package genetic;

import domain.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther chen.don
 * @date 2019/10/14 9:46
 */
public class Mutation {
    //变异：对机器，随机选择几个工序，从机器集合中程序选择可替代机器。
    //对优先级，随机选择一个工序，拿出来插到前面去，其他顺序不变后移一位。

    public List<int[][]> mutation(List<int[][]> chromGroup, int GeneNum, List<Job> machineList, double mutationRate) {
        Random random = new Random();
        List<int[][]> mutChromGrop = new ArrayList<>();
        for (int[][] tempChrom : chromGroup) {
            //机器变异
            for (int i = 0; i < GeneNum * mutationRate; i++) {
                int mutLocation = random.nextInt(GeneNum);
                //选择机器
                int index = random.nextInt(machineList.get(mutLocation).getMachJobMapper().length);
                int[] temp = machineList.get(mutLocation).getMachJobMapper();
                tempChrom[1][mutLocation] = temp[index];
            }
            //优先级变异
            tempChrom = priorityMut1(tempChrom, GeneNum);
            mutChromGrop.add(tempChrom);
        }
        return mutChromGrop;
    }

    //扰动变异
    public int[][] priorityMut1(int[][] tempChrom, int GeneNum) {
        int halfGene = GeneNum / 2;
        Random random = new Random();
        int[] Location = new int[]{random.nextInt(halfGene), random.nextInt(halfGene) + halfGene};
        int temp = tempChrom[0][Location[0]]; //记录挑出的值
        for (int i = 0; i < GeneNum; i++) {
            if (i >= Location[0] && i < Location[1]) {
                tempChrom[0][i] = tempChrom[0][i + 1];
            }
        }
        tempChrom[0][Location[1]] = temp;
        return tempChrom;
    }

    //领域搜索变异
    public int[][] priorityMut2(int[][] tempChrom, int GeneNum) {
        return null;
    }

}
