package cme;

import java.math.BigDecimal;

public class StudentRate implements ICarParkKind{
    @Override
    public BigDecimal reductionFee(BigDecimal cost) {
        BigDecimal freeAmount = new BigDecimal(8);
        BigDecimal reductionAmount = new BigDecimal(0.5);
        //if cost less than 10 then return 0
        if(cost.compareTo(freeAmount) == -1){
            return BigDecimal.ZERO;
        }
        else {
            BigDecimal result = cost.subtract(freeAmount).multiply(reductionAmount);
            return result;
        }
    }
}
