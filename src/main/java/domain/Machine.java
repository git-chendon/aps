package domain;

import java.awt.*;
import java.util.Set;

/**
 * @auther chen.don
 * @date 2019/10/15 16:01
 */
public class Machine {

    private Long workshopId;                       //所在车间
    private Integer clampingForce;                 //锁模力
    private Set<Long> moldIds;                     // 可用模具id集合
    private double machineUseableTime;             //机器可使用的时间
    /**
     * 工序--机器映射
     */
    private int machineId;
    /**
     * 机器使用的物料
     */
    private int machineMaterial;
    /**
     * 机器使用的颜色
     */
    private Color machineColor;
    /**
     * 机器使用的模具
     */
    private int machineModel;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineMaterial() {
        return machineMaterial;
    }

    public void setMachineMaterial(int machineMaterial) {
        this.machineMaterial = machineMaterial;
    }

    public double getMachineUseableTime() {
        return machineUseableTime;
    }

    public void setMachineUseableTime(double machineUseableTime) {
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
