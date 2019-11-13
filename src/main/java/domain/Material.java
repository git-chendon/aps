package domain;

/**
 * @Author chen.don
 * @date 2019/11/13
 */
public class Material {

private int materialId;
private int materialInventory;
private double materialPrepareTime;

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getMaterialInventory() {
        return materialInventory;
    }

    public void setMaterialInventory(int materialInventory) {
        this.materialInventory = materialInventory;
    }

    public double getMaterialPrepareTime() {
        return materialPrepareTime;
    }

    public void setMaterialPrepareTime(double materialPrepareTime) {
        this.materialPrepareTime = materialPrepareTime;
    }
}
