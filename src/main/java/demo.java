import genetic.Decoding;
import genetic.Encoding;
import genetic.Job;
import genetic.Scheduling;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class demo {

    public static void main(String[] args) {
        Encoding encoding = new Encoding();
        List<int[][]> chromList;
        int GeneNum = 6;
        List<int[]> Machine = new ArrayList<int[]>();
        int[] v1 = {1, 2, 5};
        int[] v2 = {1, 3};
        int[] v3 = {2, 3, 4};
        int[] v4 = {4, 5};
        int[] v5 = {1, 3, 4};
        int[] v6 = {2, 4};
        Machine.add(v1);
        Machine.add(v2);
        Machine.add(v3);
        Machine.add(v4);
        Machine.add(v5);
        Machine.add(v6);
        int[][] durationTime = new int[][]{
                {4, 5, 0, 0, 5},
                {6, 0, 3, 0, 0},
                {0, 2, 4, 3, 0},
                {0, 0, 0, 4, 4},
                {3, 0, 3, 5, 0},
                {0, 4, 0, 4, 0}
        };
        //编码染色体，得到染色体组
        chromList = encoding.initGroup(GeneNum, Machine, 4);
        //解码染色体，得到工序排列组合列表
        List<List<Job>> jobList = new ArrayList<List<Job>>();
        Decoding decoding = new Decoding();
        jobList = decoding.decoding(chromList);
        //简单排程
        List<List<Job>> jobListEnd = new ArrayList<List<Job>>();
        Scheduling scheduling = new Scheduling();
        jobListEnd = scheduling.schedulingGroup(jobList, durationTime);


        //输出编码后的染色体组
        System.out.println(Machine.size());
        for (int i = 0; i < chromList.size(); i++) {
            int[][] temp = chromList.get(i);
            System.out.println("染色体" + i + ":\n" + toString(temp));
        }
        //输出排程结果
        System.out.println("染色体组数：" + jobList.size());
        for (List<Job> tempJob : jobList) {
            //System.out.println(tempJob.size());
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobNum() + " 生产机器" + job.getMachineNum() + ">");
            }
            System.out.println();
        }
        //输出工序排列
        System.out.println("染色体组数：" + jobListEnd.size());
        for (List<Job> tempJob : jobListEnd) {
            //System.out.println(tempJob.size());
            System.out.println("工作完成时间："+ minTime(tempJob));
            for (Job job : tempJob) {
                System.out.print("<工序" + job.getJobNum() + "机器" + job.getMachineNum() +
                        "开始" + job.getStartTime() + "结束" + job.getEndTime() + ">");
            }
            System.out.println();
        }

    }

    public static int minTime(List<Job> jobList) {
        int Tmin = 0;
        for (Job job : jobList) {
            if(job.getEndTime() > Tmin) {
                Tmin = job.getEndTime();
            }
        }
        return Tmin;
    }

    public static String toString(int[][] string) {
        StringBuilder xxx = new StringBuilder("");
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                xxx.append(string[i][j]);
            }
            xxx.append("\r\n");
        }
        return xxx.toString();
    }


}
