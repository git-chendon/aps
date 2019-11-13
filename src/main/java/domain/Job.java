package domain;

import java.awt.*;

/**
 * @auther chen.don
 * @date 2019/10/12 10:53
 */
public class Job {

    /**
     * 对应的订单信息
     */
    private Order order;
    /**
     * 对应工艺路线
     */
    private Process process;
    /**
     * 工序编号
     */
    private int jobId;
    /**
     * 使用的机器
     */
    private int MachineNum;
    /**
     * 使用的物料
     */
    private int jobMaterial;
    /**
     * 物料的颜色
     */
    private Color jobColor;
    /**
     * 使用的摸具
     */
    private int jobModel;
    /**
     * 工序订单数量
     */
    private int jobQuantity;
    /**
     * 订单加工时间
     */
    private double jobProductTime;
    /**
     * 生产准备时间
     */
    private double jobReadyTime;
    /**
     * 摸具拆卸时间
     */
    private double jobTakeDownTime;
    /**
     * 工序开始时间
     */
    private double StartTime;
    /**
     * 工序结束时间
     */
    private double endTime;
    /**
     * 惩罚值
     */
    private double punishment;
    /**
     * 机器-工序映射
     */
    private int[] machJobMapper;
    /**
     * 机器对应的生产能力
     */
    private int[] machJobCapMapper;

    public double getPunishment() {
        return punishment;
    }

    public void setPunishment(double punishment) {
        this.punishment = punishment;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getMachineNum() {
        return MachineNum;
    }

    public void setMachineNum(int machineNum) {
        MachineNum = machineNum;
    }

    public double getStartTime() {
        return StartTime;
    }

    public void setStartTime(double startTime) {
        StartTime = startTime;
    }

    public int getJobMaterial() {
        return jobMaterial;
    }

    public void setJobMaterial(int jobMaterial) {
        this.jobMaterial = jobMaterial;
    }

    public int getJobModel() {
        return jobModel;
    }

    public void setJobModel(int jobModel) {
        this.jobModel = jobModel;
    }

    public int getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(int jobQuantity) {
        this.jobQuantity = jobQuantity;
    }

    public double getJobProductTime() {
        return jobProductTime;
    }

    public void setJobProductTime(double jobProductTime) {
        this.jobProductTime = jobProductTime;
    }

    public double getJobReadyTime() {
        return jobReadyTime;
    }

    public void setJobReadyTime(double jobReadyTime) {
        this.jobReadyTime = jobReadyTime;
    }

    public double getJobTakeDownTime() {
        return jobTakeDownTime;
    }

    public void setJobTakeDownTime(double jobTakeDownTime) {
        this.jobTakeDownTime = jobTakeDownTime;
    }

    public Color getJobColor() {
        return jobColor;
    }

    public void setJobColor(Color jobColor) {
        this.jobColor = jobColor;
    }

    public int[] getMachJobMapper() {
        return machJobMapper;
    }

    public void setMachJobMapper(int[] machJobMapper) {
        this.machJobMapper = machJobMapper;
    }

    public int[] getMachJobCapMapper() {
        return machJobCapMapper;
    }

    public void setMachJobCapMapper(int[] machJobCapMapper) {
        this.machJobCapMapper = machJobCapMapper;
    }
}
