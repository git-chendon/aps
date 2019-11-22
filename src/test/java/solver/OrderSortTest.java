package solver;

import domain.Order;
import domain.solver.OrderSort;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        order.setOrderId(1);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(3);
        order.setOrderId(2);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(2);
        order.setOrderId(3);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(5);
        order.setOrderId(4);
        orderList.add(order);
        order = new Order();
        order.setOrderPriority(4);
        order.setOrderId(5);
        orderList.add(order);
    }

    @Test
    void orderSort() {
        init();
        List<Order> orders =orderSort.orderSort(orderList);
        System.out.println(orderList.size());
        for (Order order : orders){
            System.out.println(order.getOrderId());
        }
    }
}