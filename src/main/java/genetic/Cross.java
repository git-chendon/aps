package genetic;

import java.util.List;
import java.util.Random;

/**
 * @auther chen.don
 * @date 2019/10/12 19:46
 */
public class Cross {

    //对编码的染色体交叉。
    //交叉，对机器采用两点交叉，对优先级采用顺序交叉
    //输入初始染色体种群，输出交叉后的种群
    public List<int[][]> cross(List<int[][]> chromGroup, int GeneNum, double crossRate) {
        int groupSize = chromGroup.size();
        int halfGroupSize = groupSize / 2;


        for (int i = 0; i < halfGroupSize; i++) {  //按交叉率取变异数
            if (Math.random() < crossRate) {
                int[] random = random(groupSize);
                int[][] P1 = chromGroup.get(random[0]);
                int[][] P2 = chromGroup.get(random[1]);



            }
        }

        return null;
    }

    private int[] random(int groupSize) {
        int[] rand = new int[2];
        Random random = new Random();
        boolean[] bool = new boolean[groupSize];
        for (int i = 0; i < 2; i++) {
            do {
                rand[i] = random.nextInt(groupSize);
            } while (bool[i]);
            bool[i] = true;
        }
        return rand;
    }

}
