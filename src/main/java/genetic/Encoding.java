package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Encoding {
    //初始化一条染色体
    //输入每条染色体的基因总数，对应机器的集合
    public int[][] initSingle(int GeneNum, List<int[]> Machine) {
        int[][] chromosome = new int[GeneNum][2];
        Random rand = new Random();
        boolean[] bool = new boolean[GeneNum+1];
        for (int i = 0; i < GeneNum; i++) {
            //对优先级赋值
            do {
                chromosome[i][0] = rand.nextInt(GeneNum)+1;//把0-9变成1-10
            } while (bool[chromosome[i][0]]);
            bool[chromosome[i][0]] = true;

            //选择机器
            int index = rand.nextInt(Machine.get(i).length);
            int[] temp = Machine.get(i);
            chromosome[i][1] = temp[index];
        }
        return chromosome;
    }

    //初始化一组染色体
    public List<int[][]> initGroup(int GeneNum, List<int[]> Machine,int groupSize){

    }


}
