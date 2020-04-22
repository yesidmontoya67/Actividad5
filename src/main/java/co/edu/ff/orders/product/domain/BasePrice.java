package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.serialization.NumberSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice  implements NumberSerializable {
    BigDecimal value;

    private BasePrice(BigDecimal value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.compareTo(BigDecimal.ZERO) >= 0);
        this.value = value;
    }


    @Override
    public BigDecimal valueOf() {
        return value;
    }
}
