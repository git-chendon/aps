package domain;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author chen.don
 * @date 2019/11/12
 */
public class Process {

    // 前置工序和次数 {processId:count}
    private Map<Long, Integer> processMaterials;
    // 产量转序条件 {processId:count}
    private Map<Long, Integer> processTransferCount;
    // 采购原料,目前无用 {materialId:count}
    private Map<Long, BigDecimal> materials;
    // 备料时间 {materialId:timeGrainCount}
    private Map<Long, Integer> materialPreparationTime;
    // 需求的锁模力单位t
    private Integer clampingForce;
    // 颜色
    private Color color;
    //所属的工艺路线ID
    private Long routeId;
    //产出产品ID
    private Long productId;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
