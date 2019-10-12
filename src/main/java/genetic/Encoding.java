package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther chen.don
 * @date 2019/10/11 13:53
 */
public class Encoding {
    //初始化一条染色体
    //输入每条染色体的基因总数，对应机器的集合
    public int[][] initSingle(int GeneNum, List<int[]> Machine) {
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
            int index = rand.nextInt(Machine.get(i).length);
            int[] temp = Machine.get(i);
            chromosome[1][i]  = temp[index];
        }
        System.out.println("染色体长度："+chromosome[0].length);
        return chromosome;

    }

    //初始化一组染色体
    public List<int[][]> initGroup(int GeneNum, List<int[]> Machine, int groupSize) {
        List<int[][]> chromGroup = new ArrayList<int[][]>();
        for (int i = 0; i < groupSize; i++) {
            chromGroup.add(initSingle(GeneNum, Machine));
        }
        return chromGroup;
    }

}
