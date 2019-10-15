package genetic;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther chen.don
 * @date 2019/10/14 15:06
 */
class MutationTest {
    Mutation mutation = new Mutation();
    int aa = 7;
    int[][] xx = new int[][]{{2, 4, 3, 1, 5, 7, 6}, {1, 2, 3, 4, 5, 6, 7,}};


    @org.junit.jupiter.api.Test
    void mutation() {

    }

    @org.junit.jupiter.api.Test
    void priorityMut1() {
        int[][] xx1 = mutation.priorityMut1(xx, aa);
        for(int i = 0;i<aa;i++) {
            System.out.println(xx1[0][i]);
        }
    }

    @org.junit.jupiter.api.Test
    void priorityMut2() {

    }
}