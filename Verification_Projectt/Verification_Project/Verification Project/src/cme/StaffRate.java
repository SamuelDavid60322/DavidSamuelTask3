package cme;

import java.math.BigDecimal;

public class StaffRate implements ICarParkKind{
    @Override
    public BigDecimal reductionFee(BigDecimal cost) {
        BigDecimal maximumPayable = new BigDecimal(10);

        if(cost.compareTo(maximumPayable) == -1){
            return maximumPayable;
        }
        else {
            return cost;
        }
    }
}
