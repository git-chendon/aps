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


        int[][] string = new int[][]{{1,2,3},{4,5,6},{7,8,9},{1,2,3}};
        StringBuilder xxx = new StringBuilder("");
        for (int i=0;i<string.length;i++) {
            for (int j=0;j<string[i].length;j++){
                xxx.append(string[i][j]);
            }
            xxx.append("\r\n");
        }
        System.out.println(xxx);
        System.out.println(string.length);

    }
}
