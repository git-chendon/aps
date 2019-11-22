package domain.solver;

import java.math.BigDecimal;

/**
 * @Author chen.don
 * @date 2019/11/22
 */
public class DecimalReserve {

//    public <T> T Reserve(T Num){
//
//        BigDecimal bgstart = new BigDecimal(Num);
//        jobStartTime = bgstart.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//    }

    public double Reserve2(double Num,int k){
        BigDecimal bigDecimal = new BigDecimal(Num);
        return bigDecimal.setScale(k, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
