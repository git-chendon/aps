package process;

import model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther chen.don
 * @date 2019/10/25 16:23
 */
class OrderSortTest {

    private OrderSort orderSort = new OrderSort();
    private Order order;
    private List<Order> orderList = new ArrayList<>();

    @Before
    void init() {
        order = new Order();
        order.setOrderPriority(1);
        order.setOrderNum(1);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(3);
        order.setOrderNum(2);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(2);
        order.setOrderNum(3);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(5);
        order.setOrderNum(4);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(4);
        order.setOrderNum(5);
        orderList.add(order);
    }

    @Test
    void orderSort() {
        init();
        List<Order> orders =orderSort.orderSort(orderList);
        System.out.println(orderList.size());
        for (Order order : orders){
            System.out.println(order.getOrderNum());
        }
    }
}