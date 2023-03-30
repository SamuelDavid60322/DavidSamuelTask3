package cme;
import java.math.BigDecimal;
public class ManagementRate implements ICarParkKind {
    @Override
    public BigDecimal reductionFee(BigDecimal cost) {
        BigDecimal minimumPayable = new BigDecimal(5);
        //if cost less than 5 then return 0
        if(cost.compareTo(minimumPayable) == -1){
            return minimumPayable;
        }
        else {
            return minimumPayable;
        }
    }
}
