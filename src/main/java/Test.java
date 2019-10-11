import java.util.ArrayList;
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
        int[] zz= aa.get(0);
        for (int i =0 ;i<zz.length;i++)
        System.out.println(zz[i]);
    }
}
