import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int[] xx = new int[]{1, 2, 3};
        List<int[]> aa = new ArrayList<int[]>();
        aa.add(xx);
//        for(int i=0; i<aa.size(); i++) {
//            System.out.println("列表项为: "+i);
//        }
//        int[] zz= aa.get(0);
//        for (int i =0 ;i<zz.length;i++)


        int[][] string = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 2, 3}};
        StringBuilder xxx = new StringBuilder("");
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                xxx.append(string[i][j]);
            }
            xxx.append("\r\n");
        }
        System.out.println(xxx);
        System.out.println(string.length);
        System.out.println((int) 5 / 2);

        int groupSize = 10;
        int[] rand = random(groupSize);
        for (int i = 0; i < rand.length; i++) {
            System.out.println(rand[i]);
            System.out.println(Math.random());
        }
        Random random = new Random();
        System.out.println("随机数：" + random.nextInt(2));

        int a = 6;
        double b = 0.16;
        int c;
        c = (int) (a * b);
        System.out.println(a * b + "and" + c);

    }

    public static int[] random(int groupSize) {
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
