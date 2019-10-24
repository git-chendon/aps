package model;

import java.util.Set;

/**
 * @auther chen.don
 * @date 2019/10/23 15:55
 */
public class Order {
    /**
     * 订单中工序的集合
     */
    private Set<Integer> orderJob;
    /**
     * 订单号
     */
    private int orderNum;
    /**
     * 订单优先级
     */
   private int orderPriority;
    /**
     * 订单开始日期
     */
    private int orderStartDate;
    /**
     * 订单截至日期
     */
    private int orderEndDate;

    public Set<Integer> getOrderJob() {
        return orderJob;
    }

    public void setOrderJob(Set<Integer> orderOperation) {
        this.orderJob = orderOperation;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(int orderPriority) {
        this.orderPriority = orderPriority;
    }

    public int getOrderStartDate() {
        return orderStartDate;
    }

    public void setOrderStartDate(int orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public int getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(int orderEndDate) {
        this.orderEndDate = orderEndDate;
    }
}
