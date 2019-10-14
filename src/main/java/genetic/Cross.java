package genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther chen.don
 * @date 2019/10/12 19:46
 */
public class Cross {
    //交叉，对机器采用两点交叉，对优先级采用顺序交叉


    //交叉算子
    public List<int[][]> cross(List<int[][]> chromGroup, int GeneNum, double crossRate) {
        int groupSize = chromGroup.size();
        int halfGroupSize = groupSize / 2;
        List<int[][]> newChromGroup = new ArrayList<int[][]>();
        Random rand = new Random();
        for (int i = 0; i < halfGroupSize; i++) {
            if (Math.random() < crossRate) {                  //按交叉率取变异数
                int[][] newChrom = new int[2][GeneNum];
                int[] random = random(groupSize);             //随机获取两个父代
                int[][] P1 = chromGroup.get(random[0]);
                int[][] P2 = chromGroup.get(random[1]);
                //产生机器随机切点
                int[] randCrossMach = new int[]{rand.nextInt(halfGroupSize), rand.nextInt(halfGroupSize) + halfGroupSize};
                //两点交叉机器
                for (int j = 0; j < GeneNum; j++) {
                    if (j < randCrossMach[0] || j > randCrossMach[1]) {
                        newChrom[0][j] = P1[0][j];
                        newChrom[1][j] = P1[1][j];
                    } else {
                        newChrom[1][j] = P2[1][j];
                    }
                }
                //顺序交叉优先级
                for (int j = 0; j < GeneNum; j++) {
                    if (j >= randCrossMach[0] || j <= randCrossMach[1]) {
                        for (int k = 0; k < GeneNum; k++) {
                            int tag = 0;
                            for (int g = 0; g < GeneNum; g++) {
                                if (newChrom[0][g] == P2[0][k]) {
                                    tag = 0;
                                    break;
                                } else {
                                    tag = 1;
                                }
                            }
                            if (tag == 1)
                                newChrom[0][j] = P2[0][k];
                        }
                    }
                }
                newChromGroup.add(newChrom);
            }
        }
        return newChromGroup;
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
