package genetic;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/14 18:13
 */
public class Selection {

    //对变异和交叉后的结果和原始种群一起进行选择
    public int selection(List<int[][]> P, List<int[][]> C1, List<int[][]> C2, int[][] durationTime) {
        Fitness fitness = new Fitness();
        //获取适应度--各解决方案的时间集合
        int[] fitP = fitness.fitness(P, durationTime);
        int[] fitC1 = fitness.fitness(C1, durationTime);
        int[] fitC2 = fitness.fitness(C2, durationTime);
        //分别排序各适应度结果
        int[][] sortFitP = selectSort(fitP);
        int[][] sortFitC1 = selectSort(fitC1);
        int[][] sortFitC2 = selectSort(fitC2);
        //筛选出各种群中最优的
        for(int i= 0;i<P.size();i++){
            if (sortFitP[1][i] < sortFitC1[1][i]){

            }
        }


        return 0;
    }

    //选择排序
    public int[][] selectSort(int[] array) {
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
                if (result[1][j] < result[1][j]) {
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
