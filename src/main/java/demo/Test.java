package demo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {

        Scanner s1 = new Scanner(new File("workOrderInput.txt"));

        while (s1.hasNext()) {
            System.out.println(s1.next());
        }
        FileReader fr = null;
        BufferedReader br = null;
        String s = null;
        String[] arrayTemp = null;
        List<String[]> contentArrays = new ArrayList<String[]>();

        try {
            fr = new FileReader(new File("workOrderInput.txt"));
            br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                if (s.equals("#")) continue;
                arrayTemp = s.split("，");
                contentArrays.add(arrayTemp);
            }

            for (int i = 2; i < contentArrays.size(); i++) {
                arrayTemp = contentArrays.get(i);
                for (String l : arrayTemp)
                    System.out.print(l + " ");
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            br.close();
            fr.close();
        }


        try {
            File writeName = new File("out.txt");
            writeName.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write("排程输出：");
            out.flush();
        }catch(Exception e){
            e.printStackTrace();
        }

//        double aa = (double)1000/30;
//        BigDecimal bg = new BigDecimal(aa);
//        double d3 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//        System.out.println(d3);


//        long rMean = ((long)color1.getRed() + (long)color2.getRed());
//        System.out.println(rMean);
//        long r = (long)color1.getRed() - (long)color2.getRed();
//        System.out.println(r);
//        long g = (long)color1.getGreen() - (long)color2.getGreen();
//        long b = (long)color1.getBlue() - (long)color2.getBlue();
//        double dist = Math.sqrt((((512 + rMean)*r*r)>>8) + 4*g*g + (((767 - rMean)*b*b)>>8));
//        System.out.println((int)((dist/764)*5));
//        System.out.println(((Math.sqrt((((512 + rMean)*r*r)>>8) + 4*g*g + (((767 - rMean)*b*b)>>8))/764)*5));


//        int[] xx = new int[]{1, 2, 3};
//        List<int[]> aa = new ArrayList<int[]>();
//        aa.add(xx);
//
//        List<Machine> machineList = new ArrayList<>();
//        Machine machine = new Machine();
//        machine.setMachineNum(11);
//        machineList.add(machine);
//        System.out.println("初始 "+machineList.get(0).getMachineNum());
//        Machine machine1 = machineList.get(0);
//        machine1.setMachineNum(10);
//        System.out.println("赋值后 "+machineList.get(0).getMachineNum());
//
////        for(int i=0; i<aa.size(); i++) {
////            System.out.println("列表项为: "+i);
////        }
////        int[] zz= aa.get(0);
////        for (int i =0 ;i<zz.length;i++)
//
//
//        int[][] string = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 2, 3}};
//        StringBuilder xxx = new StringBuilder("");
//        for (int i = 0; i < string.length; i++) {
//            for (int j = 0; j < string[i].length; j++) {
//                xxx.append(string[i][j]);
//            }
//            xxx.append("\r\n");
//        }
//        System.out.println(xxx);
//        System.out.println(string.length);
//        System.out.println((int) 5 / 2);
//
//        int groupSize = 10;
//        int[] rand = random(groupSize);
//        for (int i = 0; i < rand.length; i++) {
//            System.out.println(rand[i]);
//            System.out.println(Math.random());
//        }
//        Random random = new Random();
//        System.out.println("随机数：" + random.nextInt(2));
//
//        int a = 6;
//        double b = 0.16;
//        int c;
//        c = (int) (a * b);
//        System.out.println(a * b + "and" + c);

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
