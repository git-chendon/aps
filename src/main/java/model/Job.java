package model;

import java.awt.*;

/**
 * @auther chen.don
 * @date 2019/10/12 10:53
 */
public class Job {
    /**
     * 工序编号
     */
    private int jobNum;
    /**
     * 机器
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
    private int jobProductTime;
    /**
     * 生产准备时间
     */
    private int jobReadyTime;
    /**
     * 摸具拆卸时间
     */
    private int jobTakeDownTime;
    /**
     * 工序开始时间
     */
    private int StartTime;
    /**
     * 工序结束时间
     */
    private int endTime;
    /**
     * 惩罚值
     */
    private int punishment;

    public int getPunishment() {
        return punishment;
    }

    public void setPunishment(int punishment) {
        this.punishment = punishment;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getJobNum() {
        return jobNum;
    }

    public void setJobNum(int jobNum) {
        this.jobNum = jobNum;
    }

    public int getMachineNum() {
        return MachineNum;
    }

    public void setMachineNum(int machineNum) {
        MachineNum = machineNum;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int startTime) {
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

    public int getJobProductTime() {
        return jobProductTime;
    }

    public void setJobProductTime(int jobProductTime) {
        this.jobProductTime = jobProductTime;
    }

    public int getJobReadyTime() {
        return jobReadyTime;
    }

    public void setJobReadyTime(int jobReadyTime) {
        this.jobReadyTime = jobReadyTime;
    }

    public int getJobTakeDownTime() {
        return jobTakeDownTime;
    }

    public void setJobTakeDownTime(int jobTakeDownTime) {
        this.jobTakeDownTime = jobTakeDownTime;
    }

    public Color getJobColor() {
        return jobColor;
    }

    public void setJobColor(Color jobColor) {
        this.jobColor = jobColor;
    }
}
