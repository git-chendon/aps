package model;

import java.awt.*;

/**
 * @auther chen.don
 * @date 2019/10/15 16:01
 */
public class Machine {

    /**
     * 工序--机器映射
     */
    private int machineNum;
    /**
     * 机器对应的生产能力
     */
    private int machineCapacity;
    /**
     * 机器使用的物料
     */
    private int machineMaterial;
    /**
     * 机器使用的颜色
     */
    private Color machineColor;
    /**
     * 机器可使用的时间
     */
    private int machineUseableTime;
    /**
     * 机器使用的模具
     */
    private int machineModel;

    public int getMachineNum() {
        return machineNum;
    }

    public void setMachineNum(int machineNum) {
        this.machineNum = machineNum;
    }

    public int getMachineCapacity() {
        return machineCapacity;
    }

    public void setMachineCapacity(int machineCapacity) {
        this.machineCapacity = machineCapacity;
    }

    public int getMachineMaterial() {
        return machineMaterial;
    }

    public void setMachineMaterial(int machineMaterial) {
        this.machineMaterial = machineMaterial;
    }

    public int getMachineUseableTime() {
        return machineUseableTime;
    }

    public void setMachineUseableTime(int machineUseableTime) {
        this.machineUseableTime = machineUseableTime;
    }

    public int getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(int machineModel) {
        this.machineModel = machineModel;
    }

    public Color getMachineColor() {
        return machineColor;
    }

    public void setMachineColor(Color machineColor) {
        this.machineColor = machineColor;
    }
}
