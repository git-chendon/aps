package genetic;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/14 18:13
 */
public class Selection {

    //选出排序后的种群
    public List<int[][]> selectSingleSortGroup(List<int[][]> chromGroup, int[][] durationTime) {
        Fitness fitness = new Fitness();
        List<int[][]> result = new ArrayList<>();
        int[] fit = fitness.fitness(chromGroup, durationTime); //找到每个个体适应度
        int[][] sortFit = selectSort(fit); //排序每个个体适应度得到 （适应度--个体）映射数组
        for (int i = 0; i < fit.length; i++) {
            result.add(chromGroup.get(sortFit[0][i]));
        }
        return result;
    }

    //求出适应度，并排序。
    public int[][] selectionSingle(List<int[][]> chromGroup, int[][] durationTime) {
        Fitness fitness = new Fitness();
        int[] fit = fitness.fitness(chromGroup, durationTime);
        return selectSort(fit);
    }

    //对变异和交叉后的结果和原始种群一起进行选择，和selectionAll相比对选出的排序后输出
    public List<int[][]> selectNextGeneration(List<int[][]> P, List<int[][]> C1, List<int[][]> C2, int[][] durationTime) {
        List<int[][]> result = new ArrayList<>();
        int[][] sortFitP = selectionSingle(P, durationTime);
        int[][] sortFitC1 = selectionSingle(C1, durationTime);
        int[][] sortFitC2 = selectionSingle(C2, durationTime);
        //筛选出各种群中最优的
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
        result = selectSingleSortGroup(result, durationTime);  //排序输出，保证最优结果
        return result;
    }

    //从父代，子代中选出最优子代
    public List<int[][]> selectOptChild(List<int[][]> P, int[][] sortFitP, int fitNum) {
        List<int[][]> result = new ArrayList<>();
        for (int i = 0; i < fitNum; i++) {
            int[][] temp = P.get(sortFitP[0][i]);
            result.add(temp);
        }
        return result;
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


//       if (tagP + tagC1 + tagC2 == P.size()) {
//               System.out.println("选择成功");
//               } else {
//               System.out.println("选择失败");
//               }
//               if (result.size() == P.size()) {
//               System.out.println("生成子代成功");
//               } else {
//               System.out.println("生成子代失败");
//               }

//    //对变异和交叉后的结果和原始种群一起进行选择，返回选择后的各种群，选出种群不加排序
//    public List<int[][]> selectionAll(List<int[][]> P, List<int[][]> C1, List<int[][]> C2, int[][] durationTime) {
//        List<int[][]> result = new ArrayList<>();
//        int[][] sortFitP = selectionSingle(P, durationTime);
//        int[][] sortFitC1 = selectionSingle(C1, durationTime);
//        int[][] sortFitC2 = selectionSingle(C2, durationTime);
//        int[] temp = selectionOptimal(sortFitP, sortFitC1, sortFitC2);
//        //选出各自适应的群体
//        P = selectOptChild(P, sortFitP, temp[0]);
//        C1 = selectOptChild(C1, sortFitC1, temp[1]);
//        C2 = selectOptChild(C2, sortFitC2, temp[2]);
//        result.addAll(P);
//        result.addAll(C1);
//        result.addAll(C2);
//        return result;
//    }


// 返回最优种群
//    public int[] selectionOptimal(int[][] sortFitP, int[][] sortFitC1, int[][] sortFitC2) {
//        int temp = sortFitP[0].length;
//        int[] result = new int[3];
//        //筛选出各种群中最优的
//        int tag = 0;
//        int tagP = 0;
//        int tagC1 = 0;
//        int tagC2 = 0;
//        for (int i = 0; i < temp; i++) {
//            while (tag < temp) {
//                tagP = tagP + 1;
//                tag = tag + 1;
//                result[0] = tagP;
//                for (int c1 = 0; c1 < sortFitC1[0].length; c1++) {
//                    if (sortFitP[1][i] >= sortFitC1[1][i] && tag < temp) {
//                        tag = tag + 1;
//                        tagC1 = tagC1 + 1;
//                        result[1] = tagC1;
//                    }
//                }
//                for (int c2 = 0; c2 < sortFitC2[0].length; c2++) {
//                    if (sortFitP[1][i] >= sortFitC2[1][i] && tag < temp) {
//                        tag = tag + 1;
//                        tagC2 = tagC2 + 1;
//                        result[2] = tagC2;
//                    }
//                }
//            }
//        }
//        if (tagP + tagC1 + tagC2 == temp) {
//            System.out.println("选择成功");
//        } else {
//            System.out.println("选择失败");
//        }
//        return result;
//    }