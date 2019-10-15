package model;

import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/15 16:01
 */
public class Machine {

    /**
     * 工序--机器映射
     */
    private List<int[]> Machine;

    public List<int[]> getMachine() {
        return Machine;
    }

    public void setMachine(List<int[]> machine) {
        Machine = machine;
    }


}
