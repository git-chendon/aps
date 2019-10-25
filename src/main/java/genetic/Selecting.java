package genetic;

import model.Job;
import model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/25 10:15
 */
public class Selecting {

    //对变异和交叉后的结果和原始种群一起进行选择，和selectionAll相比对选出的排序后输出
    public List<int[][]> selectNextGeneration(List<int[][]> P, List<int[][]> C1, List<int[][]> C2,
                                              List<Order> orderSorted, List<Job> originJobList, int machineNum) {
        List<int[][]> result = new ArrayList<>();

        //计算各种群的适应度，并排序输出
        int[][] sortFitP = sortByFit(P, orderSorted, originJobList, machineNum);
        int[][] sortFitC1 = sortByFit(C1, orderSorted, originJobList, machineNum);
        int[][] sortFitC2 = sortByFit(C2, orderSorted, originJobList, machineNum);

        //筛选出各种群中最优的，形成新的种群
        int tag = 0;
        int tagP = 0;
        int tagC1 = 0;
        int tagC2 = 0;
        for (int i = 0; i < P.size(); i++) {
            while (tag < P.size()) {
                result.add(P.get(sortFitP[0][tagP]));
                tagP = tagP + 1;
                tag = tag + 1;
                for (int c1 = 0; c1 < C1.size(); c1++) {
                    if (sortFitP[1][i] >= sortFitC1[1][i] && tag < P.size()) {
                        result.add(C1.get(sortFitC1[0][tagC1]));
                        tag = tag + 1;
                        tagC1 = tagC1 + 1;
                    }
                }
                for (int c2 = 0; c2 < C2.size(); c2++) {
                    if (sortFitP[1][i] >= sortFitC2[1][i] && tag < P.size()) {
                        result.add(C2.get(sortFitC2[0][tagC2]));
                        tag = tag + 1;
                        tagC2 = tagC2 + 1;
                    }
                }
            }
        }
        result = sortChromGroup(result, orderSorted, originJobList, machineNum);  //排序输出，保证最优结果
        return result;
    }

    //求出适应度，并排序。--输出个体编号和对应的适应度排序后结果
    private int[][] sortByFit(List<int[][]> chromGroup, List<Order> orderSorted,
                              List<Job> originJobList, int machineNum) {
        Fitness fitness = new Fitness();
        int[] fit = fitness.fitness(chromGroup, orderSorted, originJobList, machineNum);
        return selectSort(fit);
    }

    //选出排序后的种群--保证选出最优解
    public List<int[][]> sortChromGroup(List<int[][]> chromGroup, List<Order> orderSorted,
                                        List<Job> originJobList, int machineNum) {
        Fitness fitness = new Fitness();
        List<int[][]> result = new ArrayList<>();
        int[] fit = fitness.fitness(chromGroup, orderSorted, originJobList, machineNum); //找到每个个体适应度
        int[][] sortFit = selectSort(fit); //排序每个个体适应度得到 （适应度--个体）映射数组
        for (int i = 0; i < fit.length; i++) {
            result.add(chromGroup.get(sortFit[0][i]));
        }
        return result;
    }

    //选择排序
    private int[][] selectSort(int[] array) {
        int length = array.length;
        int[][] result = new int[2][length];
        for (int i = 0; i < length; i++) {
            result[0][i] = i;
            result[1][i] = array[i];
        }
        //排序算子
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (result[1][j] < result[1][minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp0 = result[0][i];
                int temp1 = result[1][i];
                result[0][i] = result[0][minIndex];
                result[1][i] = result[1][minIndex];
                result[0][minIndex] = temp0;
                result[1][minIndex] = temp1;
            }
        }
        return result;
    }


}
