package domain;

import java.util.Map;
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
    private int orderId;
    /**
     * 订单优先级
     */
   private int orderPriority;
    /**
     * 订单截至日期
     */
    private int orderEndDate;
    /**
     *产品和对应数量
     */
    private Map<Integer,Integer> productAndNum;

    public Map<Integer, Integer> getProductAndNum() {
        return productAndNum;
    }

    public void setProductAndNum(Map<Integer, Integer> productAndNum) {
        this.productAndNum = productAndNum;
    }

    public Set<Integer> getOrderJob() {
        return orderJob;
    }

    public void setOrderJob(Set<Integer> orderOperation) {
        this.orderJob = orderOperation;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(int orderPriority) {
        this.orderPriority = orderPriority;
    }

    public int getOrderEndDate() {
        return orderEndDate;
    }

    public void setOrderEndDate(int orderEndDate) {
        this.orderEndDate = orderEndDate;
    }
}
