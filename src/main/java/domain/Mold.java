package domain;

import java.util.Map;

/**
 * @Author chen.don
 * @date 2019/11/12
 */
public class Mold {

    private int moldId;                            //模具id
    private Integer cycle;                         // 周期秒级
    private Map<Long, Integer> holes;              // {processId:hole}

    public int getMoldId() {
        return moldId;
    }

    public void setMoldId(int moldId) {
        this.moldId = moldId;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Map<Long, Integer> getHoles() {
        return holes;
    }

    public void setHoles(Map<Long, Integer> holes) {
        this.holes = holes;
    }

    public String dataValid() {
        if (cycle == null)
            return "周期为null";
        if (holes == null)
            return "产品穴数表为null";
        return "";
    }



}
