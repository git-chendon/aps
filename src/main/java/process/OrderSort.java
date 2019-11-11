package process;

import model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther chen.don
 * @date 2019/10/23 14:21
 */
public class OrderSort {

    //排序订单  -- 计数排序算法
    public List<Order> orderSort(List<Order> orderList) {
        List<Order> result = new ArrayList<>();
        int orderNum = orderList.size();
        int[][] temp = new int[3][orderNum];    //List转为数组进行排序
        for (int i = 0; i < orderNum; i++) {
            Order order = orderList.get(i);
            temp[0][i] = order.getOrderNum();
            temp[1][i] = order.getOrderPriority();
            temp[2][i] = order.getOrderEndDate();
        }
        //排序
        for (int i = 0; i < orderNum; i++) {
            int index =i;
            for (int j = i +1; j< orderNum;j++) {
                if (temp[1][j] < temp[1][i]) {
                    index =j;
                }else if (temp[1][j] == temp[1][i] && temp[2][j] < temp[2][i]) {
                    index =j;
                }
            }
            if (i != index) {
                int a = temp[0][i];int b = temp[1][i];int c = temp[2][i];
                temp[0][i] = temp[0][index];
                temp[1][i] = temp[1][index];
                temp[2][i] = temp[2][index];
                temp[0][index] = a;
                temp[1][index] = b;
                temp[2][index] = c;
            }
        }
        for (int i = 0;i < orderNum; i++) {
            result.add(orderList.get(temp[0][i] - 1));
        }
        return result;
    }

}
