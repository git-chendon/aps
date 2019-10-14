package genetic;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther chen.don
 * @date 2019/10/14 17:42
 */
class FitnessTest {


    private  List<int[][]> chromList = new ArrayList<>();
    private int[][] xx = new int[][]{{2, 4, 3, 1, 5, 6}, {1, 3, 2, 5, 3, 4}};
    private Fitness fitness = new Fitness();

    private int[][] durationTime = new int[][]{
            {4, 5, 0, 0, 5},
            {6, 0, 3, 0, 0},
            {0, 2, 4, 3, 0},
            {0, 0, 0, 4, 4},
            {3, 0, 3, 5, 0},
            {0, 4, 0, 4, 0}
    };

    @Test
    void fitness() {
        Duration duration = new Duration();
        duration.setDurationTime(durationTime);
        chromList.add(xx);
        int[] aa = new int[chromList.size()];
        aa = fitness.fitness(chromList,duration.getDurationTime());
        System.out.println(aa[0]);

    }

    @Test
    void fitGroup() {
    }

    @Test
    void minTime() {
    }
}