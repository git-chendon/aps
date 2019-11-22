package demo;

import domain.solver.DecimalReserve;
import genetic.*;
import domain.Job;
import domain.Machine;
import domain.Order;
import domain.solver.OrderSort;
import schedule.Scheduling;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @auther chen.don
 * @date 2019/10/15 13:38
 */
public class OpsMain {

    private static final double CP = 0.8;  //交叉概率
    private static final double MP = 0.15;  //变异概率
    private static final long ITERA = 100;  //迭代次数
    private static final int ChromNum = 32;  //染色体数
    private static final int GroupSize = 40; //种群数
    private static final int MachineNum = 8; //机器数
    private static final int fitFlag = 0;   //0：综合考虑;1:完成时间&惩罚；2：机器平均时间&惩罚；3：完全由完成时间

    private static List<Order> originOrderList = new ArrayList<>();      //订单信息
    private static List<Job> originJobList = new ArrayList<>();          //工序信息,和工序机器映射信息
    private static List<Machine> machineMapperList = new ArrayList<>();  //机器信息


    private static void initTxt() throws IOException {
        Order order;
        Set<Integer> tempSet;
        Job job;
        String[] arrayTemp = null;

        File otherFile = new File("otherInput.txt");
        List<String[]> otherFileIn = fileRead(otherFile);

        for (int i = 0; i < otherFileIn.size(); i++) {
            arrayTemp = otherFileIn.get(i);
            for (String l : arrayTemp)
                System.out.print(l + " ");
            System.out.println();
        }

        int geneNum = Integer.parseInt(otherFileIn.get(0)[1]);


        File orderFile = new File("workOrderInput.txt");
        List<String[]> orderFileIn = fileRead(orderFile);              //订单
        File modelFile = new File("productAndModelInput.txt");
        List<String[]> modelFileIn = fileRead(modelFile);              //产品


        int[][] orderInfo = new int[orderFileIn.get(2).length][geneNum];
        int[][] model = new int[4][geneNum];


        for (int i = 2; i < orderFileIn.size(); i++) {
            arrayTemp = orderFileIn.get(i);
            orderInfo[0][i - 2] = Integer.parseInt(arrayTemp[3]);//优先级
            orderInfo[1][i - 2] = Integer.parseInt(arrayTemp[2]);//截止日期
            model[0][i - 2] = Integer.parseInt(arrayTemp[1]);
            model[2][i - 2] = Integer.parseInt(arrayTemp[4]);//生产数量
        }

        for (int i = 2; i < modelFileIn.size(); i++) {

        }


        int[][] model1 = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},   //模具
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},   //物料
                {1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 1200, 1400,
                        1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200}   //生产数量
        };

        System.out.println("初始化数据成功");
    }

    private static void init() {

        /******初始化订单****/
        Order order;
        Set<Integer> tempSet;
        Job job;


        /*********初始化机器映射信息******/
        /************初始化工序信息***************/

        List<Color> colors = new ArrayList<>();   //物料颜色
        colors.add(new Color(0.5f, 0.6f, 0.8f));
        colors.add(new Color(0.1f, 0.1f, 0.8f));
        colors.add(new Color(0.5f, 0.9f, 0.3f));
        colors.add(new Color(0.0f, 0.0f, 0.0f));
        colors.add(new Color(0.9f, 0.3f, 0.2f));
        colors.add(new Color(0.5f, 0.9f, 0.7f));
        colors.add(new Color(0.12f, 0.66f, 0.7f));
        colors.add(new Color(0.58f, 0.41f, 0.66f));
        colors.add(new Color(0.66f, 0.1f, 0.3f));
        colors.add(new Color(1.0f, 1.0f, 1.0f));
        colors.add(new Color(0.6f, 0.6f, 0.6f));
        colors.add(new Color(0.2f, 0.3f, 0.5f));
        colors.add(new Color(0.1f, 0.9f, 0.3f));
        colors.add(new Color(0.5f, 0.6f, 0.0f));
        colors.add(new Color(0.0f, 0.9f, 0.0f));
        colors.add(new Color(0.56f, 0.8f, 0.6f));
        colors.add(new Color(0.5f, 0.6f, 0.8f));
        colors.add(new Color(0.1f, 0.1f, 0.8f));
        colors.add(new Color(0.5f, 0.9f, 0.3f));
        colors.add(new Color(0.0f, 0.0f, 0.0f));
        colors.add(new Color(0.9f, 0.3f, 0.2f));
        colors.add(new Color(0.5f, 0.9f, 0.7f));
        colors.add(new Color(0.12f, 0.66f, 0.7f));
        colors.add(new Color(0.58f, 0.41f, 0.66f));
        colors.add(new Color(0.66f, 0.1f, 0.3f));
        colors.add(new Color(1.0f, 1.0f, 1.0f));
        colors.add(new Color(0.6f, 0.6f, 0.6f));
        colors.add(new Color(0.2f, 0.3f, 0.5f));
        colors.add(new Color(0.1f, 0.9f, 0.3f));
        colors.add(new Color(0.5f, 0.6f, 0.0f));
        colors.add(new Color(0.0f, 0.9f, 0.0f));
        colors.add(new Color(0.56f, 0.8f, 0.6f));
        // 任务-机器映射表

        //机器-工序生产力映射
        int[][] machineModelMapper = new int[][]{
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {200, 200, 200, 200, 0, 0, 0, 0},
                {0, 0, 0, 0, 250, 250, 250, 250}, {0, 0, 300, 300, 300, 300, 0, 0},
                {350, 350, 350, 350, 0, 0, 0, 0}, {0, 0, 0, 0, 400, 400, 400, 400},
                {200, 200, 200, 200, 0, 0, 0, 0}, {0, 0, 0, 0, 250, 250, 250, 250},
                {0, 0, 300, 300, 300, 300, 0, 0}, {350, 350, 350, 350, 0, 0, 0, 0},
                {0, 0, 0, 0, 400, 400, 400, 400}, {0, 0, 200, 200, 200, 200, 0, 0}

        };

        //初始化工序信息
        int[][] model = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16},   //模具
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},   //物料
                {1200, 1400, 1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200, 1200, 1400,
                        1600, 1800, 2000, 2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000, 4200}   //生产数量
        };
        double[][] model1 = new double[][]{
                {.2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2, .2},    //准备时间
                {0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2,
                        0.2, 0.2, 0.2, 0.2, 0.2, 0.2},    //拆卸时间
        };
        int[][] orderInfo = {
                {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8},  //优先级
                {40, 42, 44, 46, 48, 50, 52, 54, 52, 50, 48, 46, 44, 42, 40, 38, 40, 42, 44, 46, 48, 50, 52, 54, 52, 50, 48, 46, 44, 42, 40, 38}   //截止日期
        };

        for (int i = 0; i < 32; i++) {
            /**
             * 初始化订单
             */
            tempSet = new HashSet<>();
            tempSet.add(i + 1);
            order = new Order();
            order.setOrderId(i + 1);
            order.setOrderJob(tempSet);
            order.setOrderPriority(orderInfo[0][i]);    //优先级
            order.setOrderEndDate(orderInfo[1][i]);     //截止日期
            originOrderList.add(order);

            /**
             * 初始化任务
             */
            job = new Job();
            job.setJobModel(model[0][i]);                 //模具
            job.setJobMaterial(model[1][i]);              //物料
            job.setJobReadyTime(model1[0][i]);            //准备时间
            job.setJobTakeDownTime(model1[1][i]);         //拆卸时间
            job.setJobQuantity(model[2][i]);              //数量
            job.setJobColor(colors.get(i));               //颜色

            /**
             * 处理映射
             */
            List<Integer> list = new ArrayList<>();
            int modelNum = job.getJobModel();
            for (int j = 0; j < MachineNum; j++) {
                if (machineModelMapper[modelNum - 1][j] != 0) {
                    list.add(j + 1);
                }
            }
            int[] machJob = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                machJob[k] = list.get(k);                         //初始化机器任务映射
            }
            job.setMachJobMapper(machJob);                         //机器--任务映射
            job.setMachJobCapMapper(machineModelMapper[modelNum - 1]); //机器--任务产能映射
            originJobList.add(job);

        }

        System.out.println("初始化数据成功");
    }

    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        init();                        //初始化机器，订单，工序参数


//        File otherFile = new File("otherInput.txt");
//        List<String[]> otherFileIn = fileRead(otherFile);
//
//        double CP = Double.parseDouble(otherFileIn.get(4)[1]);  //交叉概率
//        double MP = Double.parseDouble(otherFileIn.get(5)[1]);  //变异概率
//        long ITERA = Integer.parseInt(otherFileIn.get(2)[1]);  //迭代次数
//        int ChromNum = Integer.parseInt(otherFileIn.get(0)[1]);  //染色体数
//        int GroupSize = Integer.parseInt(otherFileIn.get(3)[1]); //种群数
//        int MachineNum = Integer.parseInt(otherFileIn.get(1)[1]); //机器数
//        int fitFlag = 0;   //0：综合考虑;1:完成时间&惩罚；2：机器平均时间&惩罚；3：完全由完成时间


        OrderSort orderSort = new OrderSort();
        List<Order> sortedOrderList = orderSort.orderSort(originOrderList);   //根据订单优先级排序订单

        Encoding encoding = new Encoding();
        Crossing crossing = new Crossing();
        Mutation mutation = new Mutation();
        Selecting selecting = new Selecting();
        List<int[][]> parentGroup; //原始种群
        List<int[][]> C1; //交叉后子代
        List<int[][]> C2; //变异后子代

        parentGroup = encoding.initGroup(ChromNum, originJobList, GroupSize);
        //对初始种群排序
        parentGroup = new Selecting().sortChromGroup(parentGroup, sortedOrderList, originJobList, MachineNum, fitFlag);

        //迭代求最优解
        for (int i = 0; i < ITERA; i++) {
            //交叉
            C1 = crossing.cross(parentGroup, ChromNum, CP);
            //变异
            C2 = mutation.mutation(parentGroup, ChromNum, originJobList, MP);
            //选择
            parentGroup = selecting.selectNextGeneration(parentGroup, C1, C2, sortedOrderList, originJobList, MachineNum, fitFlag);
        }
        System.out.println("迭代后排程结果：");

//        schedulingOut(parentGroup, sortedOrderList, originJobList, MachineNum);
        long endTime = System.currentTimeMillis();

        try {
            int machineNum = MachineNum;
            File writeName = new File("out.txt");
            writeName.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write("############################排程结果#############################\r\n" + "\r\n");

            //解码染色体，得到工序排列组合列表
            List<List<Job>> jobList = new Decoding().decodingAll(parentGroup, sortedOrderList, originJobList);
            //简单排程
            List<List<Job>> jobListEnd = new Scheduling().schedulingGroup(jobList, machineNum);
            //计算适应度
            int[] fitness = new Fitness().fitness(parentGroup, sortedOrderList, originJobList, machineNum, fitFlag);
            //输出工序排列
            System.out.println("染色体组数：" + jobListEnd.size());
            System.out.println("最小工作完成时间：" + minTime(jobListEnd.get(0)) + " 平均完成时间："
                    + machineWorkTime(jobListEnd.get(0), machineNum) + " 惩罚值：" + punishSum(jobListEnd.get(0)) + " 总惩罚值：" + fitness[0]);
            //找到超时订单数量
            System.out.println("超时订单数量：" + outTimeNum(sortedOrderList, jobListEnd.get(0)));
            for (int m = 0; m < MachineNum; m++) {
                List<Job> jobs = new ArrayList<>();
                for (Job job : jobListEnd.get(0)) {
                    if (job.getMachineNum() == m + 1) {
                        jobs.add(job);
                    }
                }
                System.out.println("机器" + (m + 1));
                out.write("第" + (m + 1) + "台机器上加工的工单依次为：" + "\r\n");
                for (Job job : jobs) {
                    System.out.print("<工序" + job.getJobId() +
                            "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
                    out.write("工单：" + job.getJobId() +
                            "    开始时间为：" + job.getStartTime() + "   结束时间为：" + job.getEndTime() +
                            "   截至日期为:" + sortedOrderList.get(job.getJobId() - 1).getOrderEndDate());
                    if (sortedOrderList.get(job.getJobId() - 1).getOrderEndDate() < job.getEndTime()) {
                        out.write("   订单超期 超期时长：" +
                                new DecimalReserve().Reserve2(job.getEndTime() - sortedOrderList.get(job.getJobId() - 1).getOrderEndDate(),2)+"\r\n");
                    } else {
                        out.write("\r\n");
                    }
                }
                System.out.println();
                out.write("================================华丽丽=================================\r\n");
            }

            out.write("染色体组数：" + jobListEnd.size() + "\r\n");
            out.write("最小工作完成时间：" + minTime(jobListEnd.get(0)) +
                    "\n平均完成时间：" + new DecimalReserve().Reserve2(machineWorkTime(jobListEnd.get(0), machineNum),2) +
                    "\n惩罚值：" + punishSum(jobListEnd.get(0)) +
                    "\n总惩罚值：" + fitness[0] + "\r\n");
            out.write("超时订单数量：" + outTimeNum(sortedOrderList, jobListEnd.get(0)) + "\r\n");
            out.write("迭代次数：" + ITERA +
                    "\n程序运行时间：" + (endTime - startTime) + "ms");
            out.flush();//缓存压入
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



        System.out.println("迭代次数：" + ITERA + " 程序运行时间：" + (endTime - startTime) + "ms");

    }

    //输出染色体
    private static void chromOut(List<int[][]> list) {
        for (int i = 0; i < list.size(); i++) {
            int[][] temp = list.get(i);
            System.out.println("染色体" + i + ":\n" + toString(temp));
        }
    }

    private static void decodingOut(List<List<Job>> jobListEnd) {
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        for (List<Job> tempJob : jobListEnd) {
            //System.out.println(tempJob.size());
            System.out.println("工作完成时间：" + minTime(tempJob));
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobId() + "机器" + job.getMachineNum() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }
    }

    //输出初始种群排程结果
    private static void schedulingOut(List<int[][]> parentGroup, List<Order> sortedOrderList,
                                      List<Job> originJobList, int machineNum) {
        try {
            File writeName = new File("out.txt");
            writeName.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            out.write("############################排程结果#############################\r\n" + "\r\n");

            //解码染色体，得到工序排列组合列表
            List<List<Job>> jobList = new Decoding().decodingAll(parentGroup, sortedOrderList, originJobList);
            //简单排程
            List<List<Job>> jobListEnd = new Scheduling().schedulingGroup(jobList, machineNum);
            //计算适应度
            int[] fitness = new Fitness().fitness(parentGroup, sortedOrderList, originJobList, machineNum, fitFlag);
            //输出工序排列
            System.out.println("染色体组数：" + jobListEnd.size());
            System.out.println("最小工作完成时间：" + minTime(jobListEnd.get(0)) + " 平均完成时间："
                    + machineWorkTime(jobListEnd.get(0), machineNum) + " 惩罚值：" + punishSum(jobListEnd.get(0)) + " 总惩罚值：" + fitness[0]);
            //找到超时订单数量
            System.out.println("超时订单数量：" + outTimeNum(sortedOrderList, jobListEnd.get(0)));
            for (int m = 0; m < MachineNum; m++) {
                List<Job> jobs = new ArrayList<>();
                for (Job job : jobListEnd.get(0)) {
                    if (job.getMachineNum() == m + 1) {
                        jobs.add(job);
                    }
                }
                System.out.println("机器" + (m + 1));
                out.write("第" + (m + 1) + "台机器上加工的工单依次为：" + "\r\n");
                for (Job job : jobs) {
                    System.out.print("<工序" + job.getJobId() +
                            "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
                    out.write("工单：" + job.getJobId() +
                            "    开始时间为：" + job.getStartTime() + "   结束时间为：" + job.getEndTime() +
                            "   截至日期为:" + sortedOrderList.get(job.getJobId() - 1).getOrderEndDate());
                    if (sortedOrderList.get(job.getJobId() - 1).getOrderEndDate() < job.getEndTime()) {
                        out.write("   订单超期 超期时长：" +
                                new DecimalReserve().Reserve2(job.getEndTime() - sortedOrderList.get(job.getJobId() - 1).getOrderEndDate(),2)+"\r\n");
                    } else {
                        out.write("\r\n");
                    }
                }
                System.out.println();
                out.write("================================华丽丽=================================\r\n");
            }

            out.write("染色体组数：" + jobListEnd.size() + "\r\n");
            out.write("最小工作完成时间：" + minTime(jobListEnd.get(0)) +
                    "\n平均完成时间：" + new DecimalReserve().Reserve2(machineWorkTime(jobListEnd.get(0), machineNum),2) +
                    "\n惩罚值：" + punishSum(jobListEnd.get(0)) +
                    "\n总惩罚值：" + fitness[0] + "\r\n");
            out.write("超时订单数量：" + outTimeNum(sortedOrderList, jobListEnd.get(0)) + "\r\n");
            out.flush();//缓存压入
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static double minTime(List<Job> jobList) {
        double Tmin = 0;
        for (Job job : jobList) {
            if (job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

    private static double punishSum(List<Job> jobList) {
        //获取每组染色体的惩罚值
        double sum = 0;
        for (Job job : jobList) {
            sum += job.getPunishment();
        }
        return sum;
    }

    private static String toString(int[][] string) {
        StringBuilder xxx = new StringBuilder("");
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                xxx.append(" " + string[i][j]);
            }
            xxx.append("\r\n");
        }
        return xxx.toString();
    }

    private static double machineWorkTime(List<Job> jobList, int machineNum) {
        //获取机器每台机器的工作时间
        double[] temp = new double[machineNum];
        double result = 0;
        for (int i = 0; i < machineNum; i++) {
            for (Job job : jobList) {
                if (job.getMachineNum() == i + 1 && job.getEndTime() > temp[i]) {
                    temp[i] = job.getEndTime();
                }
            }
            result = result + temp[i];
        }
        return result / machineNum;
    }

    private static int outTimeNum(List<Order> orderSorted, List<Job> jobList) {
        int orderOvertimePunish = 0;
        for (Order order : orderSorted) {       //遍历订单找到超时订单并计算超时惩罚
            double endTime = 0;
            for (int i : order.getOrderJob()) {
                if (endTime < jobList.get(i - 1).getEndTime()) {
                    endTime = jobList.get(i - 1).getEndTime();
                }
            }
            if (endTime > order.getOrderEndDate()) {          //如果超时
                orderOvertimePunish += 1;                     //按超时数量计算
//                    orderOvertimePunish += (endTime - order.getOrderEndDate()); //按超时时间算
            }
        }
        return orderOvertimePunish;
    }

    private static List<String[]> fileRead(File file) throws IOException {
        //一行一行的读取数据

        FileReader fr = null;
        BufferedReader br = null;
        String s = null;
        String[] arrayTemp = null;
        List<String[]> contentArrays = new ArrayList<String[]>();

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                if (s.equals("#")) continue;
                arrayTemp = s.split("，");
                contentArrays.add(arrayTemp);
            }

//            for (int i = 0; i < contentArrays.size(); i++) {
//                arrayTemp = contentArrays.get(i);
//                for (String l : arrayTemp)
//                    System.out.print(l + " ");
//                System.out.println();
//            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            fr.close();
        }
        return contentArrays;
    }

    private static void fileWrite(List<Job> jobs) {

    }
}

